package searchport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import keyword.KeyWord;

public class Search_Image {

	private ServerSocket serverSocket;

	// ��ʼ��������
	public Search_Image() {
		// TODO Auto-generated constructor stub
		System.out.println("Search_Image����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_SEARCH_IMAGE);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸,��ȡ����Search_Image��Ϣ��" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

	// ������Ϣ
	private class HandlerThread implements Runnable {

		private Socket client;
		private DataInputStream in;
		private DataOutputStream out;

		public HandlerThread(Socket client) {
			this.client = client;
			new Thread(this).start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {
				in = new DataInputStream(client.getInputStream());
				out = new DataOutputStream(client.getOutputStream());

				String want = in.readUTF();

				int i = 0;

				if (want.equals("home")) {
					System.out.println("��ȡhome��image������Ϣ");

					while (true) {
						File file = new File("Home/Home_image/image" + i + "1.jpg");

						if (file.exists()) {
							transferSearchImage(file);
							i++;
							file = new File("Home/Home_image" + i + "1.jpg");
						} else {
							out.writeInt(0);
							out.write(0);
							break;
						}
					}

					System.out.println("����search home image�ɹ�");
				} else if (want.equals("strategy")) {
					System.out.println("��ȡstrategy��������Ϣ");

					while (true) {
						File file1 = new File("Strategy/Strategy_Image/image" + i + "1.jpg");

						if (file1.exists()) {
							transferSearchImage(file1);
							i++;
							file1 = new File("Strategy/Strategy_Image/image" + i + "1.jpg");
						} else {
							out.writeInt(0);
							out.write(0);
							break;
						}
					}

					System.out.println("����search strategy image�ɹ�");
				}

				out.close();
				in.close();
				client.close();
				System.out.println("����search image�ɹ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("����search imageʧ��");
				e.printStackTrace();
			}
		}

		private void transferSearchImage(File file) throws IOException {
			FileInputStream fis = new FileInputStream(file);
			int size = fis.available();

			System.out.println("transferSearchImage����ͼƬ");
			System.out.println("size = " + size);

			byte[] data = new byte[size];
			fis.read(data);
			out.writeInt(size);
			out.write(data);
			out.flush();

			fis.close();
			System.out.println("transferSearchImage�ɹ�");
		}
	}

}
