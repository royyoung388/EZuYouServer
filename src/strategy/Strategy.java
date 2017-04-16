package strategy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import fileutils.StrategyUtils;
import keyword.KeyWord;

public class Strategy {
	
	private ServerSocket serverSocket;

	// 初始化，监听
	public Strategy() {
		// TODO Auto-generated constructor stub
		System.out.println("Strategy启动");

		try {

			serverSocket = new ServerSocket(KeyWord.PORT_STRATEGY);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备，获取Strategy信息：" + client.getInetAddress().toString());

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
				DataInputStream inputStream = new DataInputStream(client.getInputStream());
				
				int position = inputStream.readInt();
				
				int count = StrategyUtils.GetImageCount_Strategy();
				out.writeInt(count);
				
				System.out.println("strategy图片个数" + count);
				
				if (position == -1) {
					System.out.println("获取所有Strategy信息");
					String string = FileUtils.Readfile("Strategy\\Strategy_Item.txt");
					out.writeUTF(string);
				} else {
					System.out.println("获取第" + position + "个item信息");
					String string = StrategyUtils.ReadItemPosition_strategy(position);
					out.writeUTF(string);
				}
				
				out.close();
				inputStream.close();
				client.close();

				System.out.println("传输Strategy_Item成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("传输Strategy_Item失败");
				e.printStackTrace();
			}
		}

	}
}
