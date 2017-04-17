package strategyport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.StrategyUtils;
import keyword.KeyWord;

public class Strategy_Image {
	
	private ServerSocket serverSocket;

	// 初始化，监听
	public Strategy_Image() {
		// TODO Auto-generated constructor stub
		System.out.println("Strategy_Image启动");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_STRATEGY_IMAGE);

			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接
				Socket client = serverSocket.accept();

				System.out.println("新的设备，获取strategy_image信息：" + client.getInetAddress().toString());

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
				
				String id = inputStream.readUTF();
				int tag = inputStream.readInt();

				//所有图片
				if (tag == -1) {
					int i = 0;
					while (true) {
						File file = new File("Strategy\\Strategy_Image\\image" + i + "1.jpg");

						if (file.exists()) {
							FileInputStream fis = new FileInputStream(file);
							int size = fis.available();

							System.out.println("传输strategy图片" + i + 1);
							System.out.println("size = " + size);

							byte[] data = new byte[size];
							fis.read(data);
							
							out.writeInt(size);
							out.write(data);
							
							out.flush();
							i++;
							fis.close();
							System.out.println("strategy图片" + (i - 1) + "1发送成功");
						} else {
							out.writeInt(0);
							out.write(0);
							break;
						}
					}
					
				} if (tag == -2) {
					// 获取指定id的Strategy——Image消息
					int i = 0;
					while (true) {
						File file = new File("Strategy\\Strategy_Image\\image" + i + "1.jpg");

						if (file.exists()) {
							if (StrategyUtils.isStrategyID(id, tag)) {
							FileInputStream fis = new FileInputStream(file);
							int size = fis.available();

							System.out.println("传输strategy图片" + i + 1);
							System.out.println("size = " + size);

							byte[] data = new byte[size];
							fis.read(data);
							
							out.writeInt(size);
							out.write(data);
							
							out.flush();
							i++;
							fis.close();
							System.out.println("strategy图片" + (i - 1) + "1发送成功");
							}
						} else {
							out.writeInt(0);
							out.write(0);
							break;
						}
					}
				}
				
				else {
					//指定图片
					int i = 1;
					while (true) {
						File file = new File("Strategy\\Strategy_Image\\image" + tag + i + ".jpg");
						
						if (file.exists()) {
							FileInputStream fis = new FileInputStream(file);
							int size = fis.available();

							System.out.println("传输strategy图片" + tag + i);
							System.out.println("size = " + size);

							byte[] data = new byte[size];
							fis.read(data);
							out.writeInt(size);
							out.write(data);
							out.flush();

							i++;
							fis.close();
							System.out.println("strategy图片" + (i - 1) + "发送成功");
						} else {
							out.writeInt(0);
							out.write(0);
							out.flush();
							break;
						}
					}
				}

				out.close();
				inputStream.close();
				client.close();

				System.out.println("传输strategy_image成功");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("传输strategy_image失败");
				e.printStackTrace();
			}
		}

	}

}
