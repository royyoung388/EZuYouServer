package homeport;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import keyword.KeyWord;

//��ȡimageͼƬ
public class Home_Image {
	private ServerSocket serverSocket;

	// ��ʼ��������
	public Home_Image() {
		// TODO Auto-generated constructor stub
		System.out.println("Home_Image����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_HOME_IMAGE);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸����ȡimage��Ϣ��" + client.getInetAddress().toString());

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

				DataOutputStream out = new DataOutputStream(client.getOutputStream());

				int i = 0;

				while (true) {
					File file = new File("Home\\Home_image\\image" + i + ".png");

					if (file.exists()) {
						FileInputStream fis = new FileInputStream(file);
						int size = fis.available();

						System.out.println("����ͼƬ" + i);
						System.out.println("size = " + size);

						byte[] data = new byte[size];
						fis.read(data);
						out.writeInt(size);
						out.write(data);
						out.flush();

						i++;
						fis.close();
						System.out.println("ͼƬ" + i + "���ͳɹ�");
					} else {
						out.writeInt(0);
						out.write(0);
						out.flush();
						break;
					}
				}

				out.close();
				client.close();

				System.out.println("����image�ɹ�");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("����imageʧ��");
				e.printStackTrace();
			}
		}

	}
}
