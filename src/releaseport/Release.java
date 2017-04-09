package releaseport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import keyword.KeyWord;

public class Release {
	private ServerSocket serverSocket;

	// ��ʼ��������
	public Release() {
		// TODO Auto-generated constructor stub
		System.out.println("Release����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_RELEASE);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸,������Ϣ��" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

	//������Ϣ
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
				
				String name = in.readUTF();
				String sell = in.readUTF();
				String rent = in.readUTF();
				String detil = in.readUTF();
				
				FileUtils.Writefile("Home\\Home_Item.txt", "{\r\n"
						+ "name:" + name + ";\r\n"
						+ "sell:" + sell + ";\r\n"
						+ "rent:" + rent + ";\r\n"
						+ "introduce:" + detil + ";\r\n"
						+ "},\n");
				
				System.out.println("�ϴ���Ϣ�ɹ�");
				
				int count = in.readInt();
				
				int image_count = 0;
				
				while (new File("Home\\Home_image\\image" + image_count + "1" + ".jpg").exists()) {
					image_count++;
				}
				
				for (int i = 0; i < count; i++) {		
					FileOutputStream fos = new FileOutputStream("Home\\Home_image\\image" + (image_count + i) + (i + 1) + ".jpg");
					
					int size = in.readInt();
					byte[] data = new byte[size];
					int len = 0;
                    while (len < size) {
                        len += in.read(data, len, size - len);
                    }
					fos.write(data);
					fos.close();
					System.out.println("����ͼƬ" + (image_count + i) + (i + 1));
				}
				
				in.close();
				client.close();
				System.out.println("�����ɹ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("ͼƬ����ʧ��");
				e.printStackTrace();
			}
		}
	}
}