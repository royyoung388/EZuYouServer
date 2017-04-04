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

						System.out.println("传输图片" + i);
						System.out.println("size = " + size);

						byte[] data = new byte[size];
						fis.read(data);
						out.writeInt(size);
						out.write(data);
						out.flush();

						i++;
						fis.close();
						System.out.println("图片" + i + "发送成功");
					} else {
						out.writeInt(0);
						out.write(0);
						out.flush();
						break;
					}
				}

				out.close();
				client.close();

				System.out.println("传输image成功");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("传输image失败");
				e.printStackTrace();
			}
		}

	}
}
