package homeport;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import keyword.KeyWord;

//传输列表信息
public class Home_Item {

	private ServerSocket serverSocket;
	
	//初始化，监听
	public Home_Item() {
		// TODO Auto-generated constructor stub
		System.out.println("Home_Item启动");
		
		try {
					
			serverSocket = new ServerSocket(KeyWord.PORT_HOME_ITEM);
				            
			while (true) {
				// 一旦有堵塞, 则表示服务器与客户端获得了连接  
				Socket client = serverSocket.accept();
				          
				System.out.println("新的设备，获取item信息：" + client.getInetAddress().toString());
						
				new HandlerThread(client);	        
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		} 
	}
	
	// 处理这次连接  
	//处理信息
	//广告处理
	private class HandlerThread implements Runnable{
		
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
				
				String string = FileUtils.Readfile("Home\\Home_Item.txt");
				
                out.writeUTF(string);
                out.close();
                
                System.out.println("传输Home_Item成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("传输Home_Item失败");
				e.printStackTrace();
			} 
		}
		
		
	}
}
