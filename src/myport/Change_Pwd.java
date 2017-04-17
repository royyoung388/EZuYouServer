package myport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.MyUtils;
import keyword.KeyWord;

public class Change_Pwd {
	
	private ServerSocket serverSocket;

	// ��ʼ��������
	public Change_Pwd() {
		// TODO Auto-generated constructor stub
		System.out.println("Change_Pwd����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_MY_CHANGE_PWD);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸��Change_Pwd��" + client.getInetAddress().toString());

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
				
				String id = in.readUTF();
				System.out.println("��ȡ��id:" + id);

				String pwd_old = in.readUTF();							
				System.out.println("��ȡ��pwd_old:" + pwd_old);
				
				String pwd_new = in.readUTF();
				System.out.println("��ȡ��pwd_new:" + pwd_new);
				
				out.writeUTF(MyUtils.is_And_Change_Pwd(id, pwd_old, pwd_new));
				
				in.close();
				out.close();
				client.close();
				
				System.out.println("Change_Pwd�ɹ�");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Change_Pwd����");
				e.printStackTrace();
			}
		}

	}
}
