package searchport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import fileutils.SearchUtils;
import keyword.KeyWord;

public class Search_Item {

	private ServerSocket serverSocket;

	// 初始化，监听
	public Search_Item() {
		// TODO Auto-generated constructor stub
		System.out.println("Search_Item启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_SEARCH_ITEM);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备,获取搜索Search_Item信息：" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}

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
				DataInputStream in = new DataInputStream(client.getInputStream());
				DataOutputStream out = new DataOutputStream(client.getOutputStream());

				String want = in.readUTF();

				if (want.equals("home")) {
					System.out.println("获取home的搜索信息");
										
					int count = SearchUtils.getHomeImageCount();
					out.writeInt(count);				
					System.out.println("search_home 图片个数" + count);

					out.writeUTF(FileUtils.Readfile("Home\\Home_Item.txt"));
					
					System.out.println("获取home的搜索信息成功");
				} else if (want.equals("strategy")) {
					System.out.println("获取strategy的搜索信息");
					
					int count = SearchUtils.getStrategyImageCount();
					out.writeInt(count);
					System.out.println("home sreategy图片");

					out.writeUTF(FileUtils.Readfile("Strategy\\Strategy_Item.txt"));
					
					System.out.println("获取strategy的搜索信息成功");
				}

				out.close();
				in.close();
				client.close();
				System.out.println("search_item成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("search_item失败");
				e.printStackTrace();
			}
		}
	}
}
