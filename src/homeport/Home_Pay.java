package homeport;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.HomeUtils;
import keyword.KeyWord;

public class Home_Pay {
	private ServerSocket serverSocket;

	// 初始化，监听
	public Home_Pay() {
		
		// TODO Auto-generated constructor stub
		System.out.println("Home_Pay启动");

		try {

			serverSocket = new ServerSocket(KeyWord.PORT_HOME_PAY);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备，Home_Pay支付：" + client.getInetAddress().toString());

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
				System.out.println("获取id:" + id);

				int tag = inputStream.readInt();
				System.out.println("获取tag" + tag);

				HomeUtils.chageStatus(id, tag);
				
				System.out.println("status修改成功");
				
				inputStream.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("status修改失败");
			}

		}
	}
}
