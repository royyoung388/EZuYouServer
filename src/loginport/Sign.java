package loginport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import fileutils.UserUtils;
import keyword.KeyWord;

//注册
public class Sign {
	private ServerSocket serverSocket;

	// 初始化，监听
	public Sign() {
		// TODO Auto-generated constructor stub
		System.out.println("Sign启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_LOGIN_SIGN);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备，Sign：" + client.getInetAddress().toString());

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
				DataInputStream in = new DataInputStream(client.getInputStream());
				DataOutputStream out = new DataOutputStream(client.getOutputStream());

				String username = in.readUTF();
				String userpwd = in.readUTF();

				switch (UserUtils.userIsExist(username, userpwd)) {
				case 1:
					out.writeUTF("right");
					System.out.println("注册成功");
					FileUtils.Writefile("Login\\Login.txt", "{\r\n"
													+ "username:" + username + ";\r\n"
													+ "userpwd:" + userpwd + ";\r\n"
													+ "},\n");
					break;
				default:
					out.writeUTF("error");
					System.out.println("注册失败");
					break;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("注册出错");
				e.printStackTrace();
			}
		}

	}
}
