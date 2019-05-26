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

	// 初始化，监听
	public Search_Image() {
		// TODO Auto-generated constructor stub
		System.out.println("Search_Image启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_SEARCH_IMAGE);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备,获取搜索Search_Image信息：" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}

	// 处理信息
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
					System.out.println("获取home的image搜索信息");

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

					System.out.println("传输search home image成功");
				} else if (want.equals("strategy")) {
					System.out.println("获取strategy的搜索信息");

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

					System.out.println("传输search strategy image成功");
				}

				out.close();
				in.close();
				client.close();
				System.out.println("传输search image成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("传输search image失败");
				e.printStackTrace();
			}
		}

		private void transferSearchImage(File file) throws IOException {
			FileInputStream fis = new FileInputStream(file);
			int size = fis.available();

			System.out.println("transferSearchImage传输图片");
			System.out.println("size = " + size);

			byte[] data = new byte[size];
			fis.read(data);
			out.writeInt(size);
			out.write(data);
			out.flush();

			fis.close();
			System.out.println("transferSearchImage成功");
		}
	}

}
