package myport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.MyUtils;
import keyword.KeyWord;

public class MyDetil {

	private ServerSocket serverSocket;

	// ��ʼ��������
	public MyDetil() {
		// TODO Auto-generated constructor stub
		System.out.println("Mydetil����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_MY);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸����ȡMy������Ϣ��" + client.getInetAddress().toString());

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
				
				//��ȡid
				String id = in.readUTF();
				System.out.println("��ȡ��ID:" + id);

				//UserUtils userUtils = new UserUtils(id);
				
				out.writeUTF(MyUtils.getMyDetil(id));
				
				in.close();
				out.close();
				client.close();
				
				System.out.println("��ȡmy������Ϣ�ɹ�");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("��ȡmy������Ϣ����");
				e.printStackTrace();
			}
		}

	}
}
