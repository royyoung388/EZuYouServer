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

//获取image图片
public class Home_Image {
	private ServerSocket serverSocket;

	// 初始化，监听
	public Home_Image() {
		// TODO Auto-generated constructor stub
		System.out.println("Home_Image启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_HOME_IMAGE);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备，获取image信息：" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}

	// 处理这次连接
	// 处理信息
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

				// 所有图片
				if (tag == -1) {
					System.out.println("获取所有的item的image信息");
					int i = 0;
					while (true) {
						File file = new File("Home/Home_image/image" + i + "1.jpg");

						if (file.exists()) {
							if (HomeUtils.isStatus(i)) {
								transferImage(file);
								
								/*FileInputStream fis = new FileInputStream(file);
								int size = fis.available();

								System.out.println("传输图片" + i + 1);
								System.out.println("size = " + size);

								byte[] data = new byte[size];
								fis.read(data);

								outputStream.writeInt(size);
								outputStream.write(data);

								outputStream.flush();
								fis.close();
								System.out.println("图片" + (i - 1) + "1发送成功");*/
							}
							i++;
						} else {
							outputStream.writeInt(0);
							outputStream.write(0);
							break;
						}
					}
				} else if (tag == -2) {
					// 获取指定id的图片
					System.out.println("获取指定id的image信息");
					int i = 0;
					while (true) {
						File file = new File("Home/Home_image/image" + i + "1.jpg");

						if (file.exists()) {
							if (HomeUtils.isID(id, i)) {
								transferImage(file);
								
								/*FileInputStream fis = new FileInputStream(file);
								int size = fis.available();

								System.out.println("传输图片" + i + 1);
								System.out.println("size = " + size);

								byte[] data = new byte[size];
								fis.read(data);

								outputStream.writeInt(size);
								outputStream.write(data);

								outputStream.flush();
								fis.close();
								System.out.println("图片" + (i - 1) + "1发送成功");*/
							}
							i++;
						} else {
							outputStream.writeInt(0);
							outputStream.write(0);
							break;
						}
					}
				} else if (tag == -3) {
					// 获取指定id的status为1的图片
					System.out.println("获取指定id的status为1的图片");
					int i = 0;
					while (true) {
						File file = new File("Home/Home_image/image" + i + "1.jpg");

						if (file.exists()) {
							if (HomeUtils.isID(id, i) && HomeUtils.isStatus(i)) {
								transferImage(file);
								
								/*FileInputStream fis = new FileInputStream(file);
								int size = fis.available();

								System.out.println("传输图片" + i + 1);
								System.out.println("size = " + size);

								byte[] data = new byte[size];
								fis.read(data);

								outputStream.writeInt(size);
								outputStream.write(data);

								outputStream.flush();
								fis.close();
								System.out.println("图片" + (i - 1) + "1发送成功");*/
							}
							i++;
						} else {
							outputStream.writeInt(0);
							outputStream.write(0);
							break;
						}
					}
				} else if (tag == -4) {
					// 获取指定id的status为0的图片
					System.out.println("获取指定id的status为0的图片");
					int i = 0;
					while (true) {
						File file = new File("Home/Home_image/image" + i + "1.jpg");

						if (file.exists()) {
							if (HomeUtils.isID(id, i) && !HomeUtils.isStatus(i)) {
								
								transferImage(file);
								
								/*FileInputStream fis = new FileInputStream(file);
								int size = fis.available();

								System.out.println("传输图片" + i + 1);
								System.out.println("size = " + size);

								byte[] data = new byte[size];
								fis.read(data);

								outputStream.writeInt(size);
								outputStream.write(data);

								outputStream.flush();
								fis.close();
								System.out.println("图片" + (i - 1) + "1发送成功");*/
							}
							i++;
						} else {
							outputStream.writeInt(0);
							outputStream.write(0);
							break;
						}
					}
				} else {
					// 获取指定tag图片
					System.out.println("获取指定tag的image信息");
					int i = 1;
					while (true) {
						File file = new File("Home/Home_image/image" + tag + i + ".jpg");

						if (file.exists()) {

							transferImage(file);
							
							/*FileInputStream fis = new FileInputStream(file);
							int size = fis.available();

							System.out.println("传输图片" + tag + i);
							System.out.println("size = " + size);

							byte[] data = new byte[size];
							fis.read(data);
							out.writeInt(size);
							out.write(data);
							out.flush();

							fis.close();
							System.out.println("图片" + (i - 1) + "发送成功");*/

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

				System.out.println("传输image成功");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("传输image失败");
				e.printStackTrace();
			}
		}
		
		private void  transferImage(File file) throws IOException {
			FileInputStream fis = new FileInputStream(file);
			int size = fis.available();

			System.out.println("transferImage传输图片");
			System.out.println("size = " + size);

			byte[] data = new byte[size];
			fis.read(data);
			outputStream.writeInt(size);
			outputStream.write(data);
			outputStream.flush();

			fis.close();
			System.out.println("transferImage成功");
		}
	}	
}
