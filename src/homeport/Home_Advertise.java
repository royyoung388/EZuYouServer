package homeport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import keyword.KeyWord;

//处理主页的广告信息
//向服务器传送图片
public class Home_Advertise {

	private ServerSocket serverSocket;

	// 初始化，监听
	public Home_Advertise() {
		// TODO Auto-generated constructor stub
		System.out.println("Home_Advertise启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_HOME_ADVERTISE);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备,获取广告图片：" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}

	// 处理这次连接
	// 处理信息
	// 广告处理
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
				FileInputStream fis = new FileInputStream("Home\\advertisement.png");

				int size = fis.available();
				System.out.println("size = " + size);

				byte[] data = new byte[size];
				fis.read(data);
				out.writeInt(size);
				out.write(data);

				out.flush();
				out.close();
				fis.close();
				client.close();
				System.out.println("图片发送成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("图片发送失败");
				e.printStackTrace();
			}
		}
	}
}
