package strategyport;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import keyword.KeyWord;

public class Strategy_Release {
	
	private ServerSocket serverSocket;

	// ��ʼ��������
	public Strategy_Release() {
		// TODO Auto-generated constructor stub
		System.out.println("Strategy_Release����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_STRATEGY_RELEASE);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸,����Strategy_Release��Ϣ��" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

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

				String id = in.readUTF();
				String person = in.readUTF();
				String program = in.readUTF();
				String money = in.readUTF();
				String detil = in.readUTF();

				//Ѱ��image�ı��
				int image_count = 0;

				while (new File("Strategy/Strategy_Image/image" + image_count + "1" + ".jpg").exists()) {
					image_count++;
				}

				int count = in.readInt();

				for (int i = 0; i < count; i++) {
					FileOutputStream fos = new FileOutputStream(
							"Strategy/Strategy_Image/image" + image_count + (i + 1) + ".jpg");

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
				
				//д����Ϣ
				FileUtils.Writefile("Strategy/Strategy_Item.txt", 
						"{\r\n" 
						//��ʶ��
						+ "id:" + id + ";\r\n" 
						//���
						+ "tag:" + image_count +";\r\n"
						+ "person:" + person + ";\r\n" 
						+ "program:" + program + ";\r\n" 
						+ "money:" + money + ";\r\n" 
						+ "detil:" + detil+ ";\r\n" 
						+ "},\n");

				System.out.println("�ϴ�Strategy_Release��Ϣ�ɹ�");

				
				//д����Ӧ�ĸ����˺���
				FileUtils.Writefile("Account/" + id + ".txt", 
						"{\r\n" +
						"item:strategy" + ";\r\n" + 
						"tag:" + image_count +";\r\n" +
						//״̬
						"status:" + (person == null ? "0" : "1") + "1;\r\n" +
						"},\n");

				in.close();
				client.close();
				System.out.println("Strategy_Release�����ɹ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Strategy_Release����ʧ��");
				e.printStackTrace();
			}
		}
	}
}
