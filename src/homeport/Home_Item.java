package homeport;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import keyword.KeyWord;

//�����б���Ϣ
public class Home_Item {

	private ServerSocket serverSocket;
	
	//��ʼ��������
	public Home_Item() {
		// TODO Auto-generated constructor stub
		System.out.println("Home_Item����");
		
		try {
					
			serverSocket = new ServerSocket(KeyWord.PORT_HOME_ITEM);
				            
			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������  
				Socket client = serverSocket.accept();
				          
				System.out.println("�µ��豸����ȡitem��Ϣ��" + client.getInetAddress().toString());
						
				new HandlerThread(client);	        
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		} 
	}
	
	// �����������  
	//������Ϣ
	//��洦��
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
                
                System.out.println("����Home_Item�ɹ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("����Home_Itemʧ��");
				e.printStackTrace();
			} 
		}
		
		
	}
}
