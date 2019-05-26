package myport;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import keyword.KeyWord;

public class Upload_Image {

	private ServerSocket serverSocket;

	// 初始化，监听
	public Upload_Image() {
		// TODO Auto-generated constructor stub
		System.out.println("Upload_Image启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_MY_UPLOOAD_IMAGE);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备，Upload_Image：" + client.getInetAddress().toString());

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
				System.out.println("Upload_Image图片:" + id);

				inputStream.close();
				client.close();

				System.out.println("Upload_Image成功");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Upload_Image失败");
				e.printStackTrace();
			}
		}

	}
}
