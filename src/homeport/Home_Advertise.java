package homeport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import keyword.KeyWord;

//������ҳ�Ĺ����Ϣ
//�����������ͼƬ
public class Home_Advertise {

	private ServerSocket serverSocket;

	// ��ʼ��������
	public Home_Advertise() {
		// TODO Auto-generated constructor stub
		System.out.println("Home_Advertise����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_HOME_ADVERTISE);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸,��ȡ���ͼƬ��" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

	// �����������
	// ������Ϣ
	// ��洦��
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

				DataOutputStream out = new DataOutputStream(client.getOutputStream());
				FileInputStream fis = new FileInputStream("Home\\advertisement.png");

				int size = fis.available();
				System.out.println("size = " + size);

				byte[] data = new byte[size];
				fis.read(data);
				out.writeInt(size);
				out.write(data);

				out.flush();
				out.close();
				fis.close();
				client.close();
				System.out.println("ͼƬ���ͳɹ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("ͼƬ����ʧ��");
				e.printStackTrace();
			}
		}
	}
}
