package homeport;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.HomeUtils;
import keyword.KeyWord;

public class Home_Pay {
	private ServerSocket serverSocket;

	// ��ʼ��������
	public Home_Pay() {
		
		// TODO Auto-generated constructor stub
		System.out.println("Home_Pay����");

		try {

			serverSocket = new ServerSocket(KeyWord.PORT_HOME_PAY);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸��Home_Pay֧����" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

	// �����������
	// ������Ϣ
	private class HandlerThread implements Runnable {

		private Socket client;

		public HandlerThread(Socket client) {
			this.client = client;
			new Thread(this).start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				DataInputStream inputStream = new DataInputStream(client.getInputStream());
				
				String id = inputStream.readUTF();
				System.out.println("��ȡid:" + id);

				int tag = inputStream.readInt();
				System.out.println("��ȡtag" + tag);

				HomeUtils.chageStatus(id, tag);
				
				System.out.println("status�޸ĳɹ�");
				
				inputStream.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("status�޸�ʧ��");
			}

		}
	}
}
