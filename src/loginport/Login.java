package loginport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.UserUtils;
import keyword.KeyWord;

//��¼
public class Login {

	private ServerSocket serverSocket;

	// ��ʼ��������
	public Login() {
		// TODO Auto-generated constructor stub
		System.out.println("Login����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_LOGIN);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸��Loign��" + client.getInetAddress().toString());

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
				DataInputStream in = new DataInputStream(client.getInputStream());
				DataOutputStream out = new DataOutputStream(client.getOutputStream());

				String username = in.readUTF();
				String userpwd = in.readUTF();

				switch (UserUtils.userIsExist(username, userpwd)) {
				case 0:
					out.writeUTF("right");
					System.out.println("��¼�ɹ�");
					break;
				default:
					out.writeUTF("error");
					System.out.println("��¼ʧ��");
					break;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("��¼����");
				e.printStackTrace();
			}
		}

	}
}
