package myport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.MyUtils;
import keyword.KeyWord;

public class MyDetil {

	private ServerSocket serverSocket;

	// 初始化，监听
	public MyDetil() {
		// TODO Auto-generated constructor stub
		System.out.println("Mydetil启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_MY);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备，获取My基本信息：" + client.getInetAddress().toString());

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
				
				//获取id
				String id = in.readUTF();
				System.out.println("获取到ID:" + id);

				//UserUtils userUtils = new UserUtils(id);
				
				out.writeUTF(MyUtils.getMyDetil(id));
				
				if (new File("Account\\" + id + ".jpg").exists()) {
					out.writeUTF("yes");
					System.out.println("yes");
					FileInputStream fis = new FileInputStream("Account\\" + id + ".jpg");
					int size = fis.available();
					System.out.println("size = " + size);

					byte[] data = new byte[size];
					fis.read(data);
					out.writeInt(size);
					out.write(data);
					fis.close();
				} else {
					out.writeUTF("no");
					System.out.println("no");
				}
				
				out.flush();
				out.close();
				client.close();
				System.out.println("my_Imgae发送成功");
				
				in.close();
				out.close();
				client.close();
				
				System.out.println("获取my基本信息成功");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("获取my基本信息出错");
				e.printStackTrace();
			}
		}

	}
}
