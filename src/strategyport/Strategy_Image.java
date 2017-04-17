package strategyport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.StrategyUtils;
import keyword.KeyWord;

public class Strategy_Image {
	
	private ServerSocket serverSocket;

	// ��ʼ��������
	public Strategy_Image() {
		// TODO Auto-generated constructor stub
		System.out.println("Strategy_Image����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_STRATEGY_IMAGE);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸����ȡstrategy_image��Ϣ��" + client.getInetAddress().toString());

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
				DataInputStream inputStream = new DataInputStream(client.getInputStream());
				
				String id = inputStream.readUTF();
				int tag = inputStream.readInt();

				//����ͼƬ
				if (tag == -1) {
					int i = 0;
					while (true) {
						File file = new File("Strategy\\Strategy_Image\\image" + i + "1.jpg");

						if (file.exists()) {
							FileInputStream fis = new FileInputStream(file);
							int size = fis.available();

							System.out.println("����strategyͼƬ" + i + 1);
							System.out.println("size = " + size);

							byte[] data = new byte[size];
							fis.read(data);
							
							out.writeInt(size);
							out.write(data);
							
							out.flush();
							i++;
							fis.close();
							System.out.println("strategyͼƬ" + (i - 1) + "1���ͳɹ�");
						} else {
							out.writeInt(0);
							out.write(0);
							break;
						}
					}
					
				} if (tag == -2) {
					// ��ȡָ��id��Strategy����Image��Ϣ
					int i = 0;
					while (true) {
						File file = new File("Strategy\\Strategy_Image\\image" + i + "1.jpg");

						if (file.exists()) {
							if (StrategyUtils.isStrategyID(id, tag)) {
							FileInputStream fis = new FileInputStream(file);
							int size = fis.available();

							System.out.println("����strategyͼƬ" + i + 1);
							System.out.println("size = " + size);

							byte[] data = new byte[size];
							fis.read(data);
							
							out.writeInt(size);
							out.write(data);
							
							out.flush();
							i++;
							fis.close();
							System.out.println("strategyͼƬ" + (i - 1) + "1���ͳɹ�");
							}
						} else {
							out.writeInt(0);
							out.write(0);
							break;
						}
					}
				}
				
				else {
					//ָ��ͼƬ
					int i = 1;
					while (true) {
						File file = new File("Strategy\\Strategy_Image\\image" + tag + i + ".jpg");
						
						if (file.exists()) {
							FileInputStream fis = new FileInputStream(file);
							int size = fis.available();

							System.out.println("����strategyͼƬ" + tag + i);
							System.out.println("size = " + size);

							byte[] data = new byte[size];
							fis.read(data);
							out.writeInt(size);
							out.write(data);
							out.flush();

							i++;
							fis.close();
							System.out.println("strategyͼƬ" + (i - 1) + "���ͳɹ�");
						} else {
							out.writeInt(0);
							out.write(0);
							out.flush();
							break;
						}
					}
				}

				out.close();
				inputStream.close();
				client.close();

				System.out.println("����strategy_image�ɹ�");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("����strategy_imageʧ��");
				e.printStackTrace();
			}
		}

	}

}
