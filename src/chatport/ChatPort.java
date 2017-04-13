package chatport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fileutils.FileUtils;
import keyword.KeyWord;

public class ChatPort {

	private ServerSocket serverSocket;
	private List<Socket> clients = new ArrayList<>();
	private List<String> ids = new ArrayList<>();
	private int index = 0;

	// 初始化，监听
	public ChatPort() {
		// TODO Auto-generated constructor stub
		System.out.println("ChatPort启动");

		try {

			serverSocket = new ServerSocket(KeyWord.PORT_CHAT);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				// Socket client = serverSocket.accept();

				clients.add(serverSocket.accept());

				new HandlerThread(clients.get(index), index);

				System.out.println("新的设备，连接chat：" + clients.get(index).getInetAddress().toString());
				System.out.println("当前设备个数:" + clients.size());

				index++;
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}

	// 处理这次连接
	private class HandlerThread implements Runnable {

		private Socket client;
		private int index;

		public HandlerThread(Socket client, int index) {
			this.client = client;
			this.index = index;
			new Thread(this).start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {

				DataOutputStream out = new DataOutputStream(client.getOutputStream());
				DataInputStream inputStream = new DataInputStream(client.getInputStream());

				String act = inputStream.readUTF();
				String idString = inputStream.readUTF();

				if (act.equals("连接")) {
					ids.add(idString);
					System.out.println("新的聊天客户端:" + idString);
					out.writeUTF("连接成功");
				} else if (act.equals("聊天")) {
					System.out.println("请求聊天");
					System.out.println("对象:" + idString);

					// 获取发送方id,发送方name和消息
					String userid = inputStream.readUTF();
					String username = inputStream.readUTF();
					String message = inputStream.readUTF();
					System.out.println("要发送的消息:" + message);

					// 寻找对象id，判断是否存在
					int find_index = ids.size() - 1;
					while (!idString.equals(ids.get(find_index))) {
						find_index--;
					}
					System.out.println("查找对象id:" + ids.get(find_index));

					
					System.out.println("发送方id:" + userid);
					
					// 这里给客户端发送的id是发送方的id
					// 之前的id是对象的id，用于查找是否存在的
					System.out.println("开始发送");
					Thread chat2client = new Chat2Client(clients.get(find_index)
							.getInetAddress().toString(), userid,
							username, message);
					chat2client.start();
					chat2client.join();
					out.writeUTF("聊天成功");
					System.out.println("发送成功");
				}

				System.out.println("聊天运行成功");

				out.close();
				inputStream.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("聊天运行失败");
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("聊天运行失败");
				e.printStackTrace();
			}
		}
	}
}