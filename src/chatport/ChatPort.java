package chatport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fileutils.FileUtils;
import keyword.KeyWord;

public class ChatPort {

	private ServerSocket serverSocket;
	private List<Socket> clients = new ArrayList<>();
	private List<String> ids = new ArrayList<>();
	private int index = 0;

	// ��ʼ��������
	public ChatPort() {
		// TODO Auto-generated constructor stub
		System.out.println("ChatPort����");

		try {

			serverSocket = new ServerSocket(KeyWord.PORT_CHAT);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				// Socket client = serverSocket.accept();

				clients.add(serverSocket.accept());

				new HandlerThread(clients.get(index), index);

				System.out.println("�µ��豸������chat��" + clients.get(index).getInetAddress().toString());
				System.out.println("��ǰ�豸����:" + clients.size());

				index++;
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

	// �����������
	private class HandlerThread implements Runnable {

		private Socket client;
		private int index;

		public HandlerThread(Socket client, int index) {
			this.client = client;
			this.index = index;
			new Thread(this).start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {

				DataOutputStream out = new DataOutputStream(client.getOutputStream());
				DataInputStream inputStream = new DataInputStream(client.getInputStream());

				String act = inputStream.readUTF();
				String idString = inputStream.readUTF();

				if (act.equals("����")) {
					ids.add(idString);
					System.out.println("�µ�����ͻ���:" + idString);
					out.writeUTF("���ӳɹ�");
				} else if (act.equals("����")) {
					System.out.println("��������");
					System.out.println("����:" + idString);

					// ��ȡ���ͷ�id,���ͷ�name����Ϣ
					String userid = inputStream.readUTF();
					String username = inputStream.readUTF();
					String message = inputStream.readUTF();
					System.out.println("Ҫ���͵���Ϣ:" + message);

					// Ѱ�Ҷ���id���ж��Ƿ����
					int find_index = ids.size() - 1;
					while (!idString.equals(ids.get(find_index))) {
						find_index--;
					}
					System.out.println("���Ҷ���id:" + ids.get(find_index));

					
					System.out.println("���ͷ�id:" + userid);
					
					// ������ͻ��˷��͵�id�Ƿ��ͷ���id
					// ֮ǰ��id�Ƕ����id�����ڲ����Ƿ���ڵ�
					System.out.println("��ʼ����");
					Thread chat2client = new Chat2Client(clients.get(find_index)
							.getInetAddress().toString(), userid,
							username, message);
					chat2client.start();
					chat2client.join();
					out.writeUTF("����ɹ�");
					System.out.println("���ͳɹ�");
				}

				System.out.println("�������гɹ�");

				out.close();
				inputStream.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("��������ʧ��");
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("��������ʧ��");
				e.printStackTrace();
			}
		}
	}
}