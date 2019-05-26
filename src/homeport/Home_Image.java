package homeport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.HomeUtils;
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
		private String id;
		private int tag;
		private DataInputStream inputStream;
		private DataOutputStream outputStream;

		public HandlerThread(Socket client) {
			this.client = client;
			new Thread(this).start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {

				outputStream = new DataOutputStream(client.getOutputStream());
				inputStream = new DataInputStream(client.getInputStream());
				
				id = inputStream.readUTF();
				tag = inputStream.readInt();

				// ����ͼƬ
				if (tag == -1) {
					System.out.println("��ȡ���е�item��image��Ϣ");
					int i = 0;
					while (true) {
						File file = new File("Home/Home_image/image" + i + "1.jpg");

						if (file.exists()) {
							if (HomeUtils.isStatus(i)) {
								transferImage(file);
								
								/*FileInputStream fis = new FileInputStream(file);
								int size = fis.available();

								System.out.println("����ͼƬ" + i + 1);
								System.out.println("size = " + size);

								byte[] data = new byte[size];
								fis.read(data);

								outputStream.writeInt(size);
								outputStream.write(data);

								outputStream.flush();
								fis.close();
								System.out.println("ͼƬ" + (i - 1) + "1���ͳɹ�");*/
							}
							i++;
						} else {
							outputStream.writeInt(0);
							outputStream.write(0);
							break;
						}
					}
				} else if (tag == -2) {
					// ��ȡָ��id��ͼƬ
					System.out.println("��ȡָ��id��image��Ϣ");
					int i = 0;
					while (true) {
						File file = new File("Home/Home_image/image" + i + "1.jpg");

						if (file.exists()) {
							if (HomeUtils.isID(id, i)) {
								transferImage(file);
								
								/*FileInputStream fis = new FileInputStream(file);
								int size = fis.available();

								System.out.println("����ͼƬ" + i + 1);
								System.out.println("size = " + size);

								byte[] data = new byte[size];
								fis.read(data);

								outputStream.writeInt(size);
								outputStream.write(data);

								outputStream.flush();
								fis.close();
								System.out.println("ͼƬ" + (i - 1) + "1���ͳɹ�");*/
							}
							i++;
						} else {
							outputStream.writeInt(0);
							outputStream.write(0);
							break;
						}
					}
				} else if (tag == -3) {
					// ��ȡָ��id��statusΪ1��ͼƬ
					System.out.println("��ȡָ��id��statusΪ1��ͼƬ");
					int i = 0;
					while (true) {
						File file = new File("Home/Home_image/image" + i + "1.jpg");

						if (file.exists()) {
							if (HomeUtils.isID(id, i) && HomeUtils.isStatus(i)) {
								transferImage(file);
								
								/*FileInputStream fis = new FileInputStream(file);
								int size = fis.available();

								System.out.println("����ͼƬ" + i + 1);
								System.out.println("size = " + size);

								byte[] data = new byte[size];
								fis.read(data);

								outputStream.writeInt(size);
								outputStream.write(data);

								outputStream.flush();
								fis.close();
								System.out.println("ͼƬ" + (i - 1) + "1���ͳɹ�");*/
							}
							i++;
						} else {
							outputStream.writeInt(0);
							outputStream.write(0);
							break;
						}
					}
				} else if (tag == -4) {
					// ��ȡָ��id��statusΪ0��ͼƬ
					System.out.println("��ȡָ��id��statusΪ0��ͼƬ");
					int i = 0;
					while (true) {
						File file = new File("Home/Home_image/image" + i + "1.jpg");

						if (file.exists()) {
							if (HomeUtils.isID(id, i) && !HomeUtils.isStatus(i)) {
								
								transferImage(file);
								
								/*FileInputStream fis = new FileInputStream(file);
								int size = fis.available();

								System.out.println("����ͼƬ" + i + 1);
								System.out.println("size = " + size);

								byte[] data = new byte[size];
								fis.read(data);

								outputStream.writeInt(size);
								outputStream.write(data);

								outputStream.flush();
								fis.close();
								System.out.println("ͼƬ" + (i - 1) + "1���ͳɹ�");*/
							}
							i++;
						} else {
							outputStream.writeInt(0);
							outputStream.write(0);
							break;
						}
					}
				} else {
					// ��ȡָ��tagͼƬ
					System.out.println("��ȡָ��tag��image��Ϣ");
					int i = 1;
					while (true) {
						File file = new File("Home/Home_image/image" + tag + i + ".jpg");

						if (file.exists()) {

							transferImage(file);
							
							/*FileInputStream fis = new FileInputStream(file);
							int size = fis.available();

							System.out.println("����ͼƬ" + tag + i);
							System.out.println("size = " + size);

							byte[] data = new byte[size];
							fis.read(data);
							out.writeInt(size);
							out.write(data);
							out.flush();

							fis.close();
							System.out.println("ͼƬ" + (i - 1) + "���ͳɹ�");*/

							i++;

						} else {
							outputStream.writeInt(0);
							outputStream.write(0);
							outputStream.flush();
							break;
						}
					}
				}

				inputStream.close();
				outputStream.close();
				client.close();

				System.out.println("����image�ɹ�");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("����imageʧ��");
				e.printStackTrace();
			}
		}
		
		private void  transferImage(File file) throws IOException {
			FileInputStream fis = new FileInputStream(file);
			int size = fis.available();

			System.out.println("transferImage����ͼƬ");
			System.out.println("size = " + size);

			byte[] data = new byte[size];
			fis.read(data);
			outputStream.writeInt(size);
			outputStream.write(data);
			outputStream.flush();

			fis.close();
			System.out.println("transferImage�ɹ�");
		}
	}	
}
