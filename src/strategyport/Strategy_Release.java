package strategyport;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import keyword.KeyWord;

public class Strategy_Release {
	
	private ServerSocket serverSocket;

	// 初始化，监听
	public Strategy_Release() {
		// TODO Auto-generated constructor stub
		System.out.println("Strategy_Release启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_STRATEGY_RELEASE);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备,发布Strategy_Release消息：" + client.getInetAddress().toString());

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

				String id = in.readUTF();
				String person = in.readUTF();
				String program = in.readUTF();
				String money = in.readUTF();
				String detil = in.readUTF();

				//寻找image的编号
				int image_count = 0;

				while (new File("Strategy/Strategy_Image/image" + image_count + "1" + ".jpg").exists()) {
					image_count++;
				}

				int count = in.readInt();

				for (int i = 0; i < count; i++) {
					FileOutputStream fos = new FileOutputStream(
							"Strategy/Strategy_Image/image" + image_count + (i + 1) + ".jpg");

					int size = in.readInt();
					byte[] data = new byte[size];
					int len = 0;
					while (len < size) {
						len += in.read(data, len, size - len);
					}
					fos.write(data);
					fos.close();
					System.out.println("发布图片" + (image_count + i) + (i + 1));
				}
				
				//写入信息
				FileUtils.Writefile("Strategy/Strategy_Item.txt", 
						"{\r\n" 
						//标识符
						+ "id:" + id + ";\r\n" 
						//编号
						+ "tag:" + image_count +";\r\n"
						+ "person:" + person + ";\r\n" 
						+ "program:" + program + ";\r\n" 
						+ "money:" + money + ";\r\n" 
						+ "detil:" + detil+ ";\r\n" 
						+ "},\n");

				System.out.println("上传Strategy_Release信息成功");

				
				//写到对应的个人账号中
				FileUtils.Writefile("Account/" + id + ".txt", 
						"{\r\n" +
						"item:strategy" + ";\r\n" + 
						"tag:" + image_count +";\r\n" +
						//状态
						"status:" + (person == null ? "0" : "1") + "1;\r\n" +
						"},\n");

				in.close();
				client.close();
				System.out.println("Strategy_Release发布成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Strategy_Release发布失败");
				e.printStackTrace();
			}
		}
	}
}
