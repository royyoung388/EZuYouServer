package myport;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import keyword.KeyWord;

public class Upload_Image {

	private ServerSocket serverSocket;

	// ��ʼ��������
	public Upload_Image() {
		// TODO Auto-generated constructor stub
		System.out.println("Upload_Image����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_MY_UPLOOAD_IMAGE);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸��Upload_Image��" + client.getInetAddress().toString());

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

				FileOutputStream fos = new FileOutputStream("Account/" + id + ".jpg");

				int size = inputStream.readInt();
				byte[] data = new byte[size];
				int len = 0;
				while (len < size) {
					len += inputStream.read(data, len, size - len);
				}
				fos.write(data);
				fos.close();
				System.out.println("Upload_ImageͼƬ:" + id);

				inputStream.close();
				client.close();

				System.out.println("Upload_Image�ɹ�");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Upload_Imageʧ��");
				e.printStackTrace();
			}
		}

	}
}
