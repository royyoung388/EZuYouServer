package loginport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.LoginUtils;
import fileutils.UserUtils;
import keyword.KeyWord;

//登录
public class Login {

	private ServerSocket serverSocket;

	// 初始化，监听
	public Login() {
		// TODO Auto-generated constructor stub
		System.out.println("Login启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_LOGIN);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备，Loign：" + client.getInetAddress().toString());

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
				
				System.out.println("username:" + username);
				System.out.println("userpwd:" + userpwd);

				switch (LoginUtils.userIsExist(username, userpwd, "0", "0")) {
				case 0:
					out.writeUTF("right");
					System.out.println("登录/注册成功");
					UserUtils userUtils = new UserUtils(username, userpwd);
					out.writeUTF(userUtils.getID());
					break;
				case 2:
					out.writeUTF("用户已存在");
					break;
				default:
					out.writeUTF("error");
					System.out.println("登录/注册失败");
					break;
				}
				
				in.close();
				out.close();
				client.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("登录出错");
				e.printStackTrace();
			}
		}

	}
}
