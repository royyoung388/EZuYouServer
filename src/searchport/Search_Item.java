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

	// ��ʼ��������
	public Search_Item() {
		// TODO Auto-generated constructor stub
		System.out.println("Search_Item����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_SEARCH_ITEM);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸,��ȡ����Search_Item��Ϣ��" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

	// ������Ϣ
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
					System.out.println("��ȡhome��������Ϣ");
										
					int count = SearchUtils.getHomeImageCount();
					out.writeInt(count);				
					System.out.println("search_home ͼƬ����" + count);

					out.writeUTF(FileUtils.Readfile("Home\\Home_Item.txt"));
					
					System.out.println("��ȡhome��������Ϣ�ɹ�");
				} else if (want.equals("strategy")) {
					System.out.println("��ȡstrategy��������Ϣ");
					
					int count = SearchUtils.getStrategyImageCount();
					out.writeInt(count);
					System.out.println("home sreategyͼƬ");

					out.writeUTF(FileUtils.Readfile("Strategy\\Strategy_Item.txt"));
					
					System.out.println("��ȡstrategy��������Ϣ�ɹ�");
				}

				out.close();
				in.close();
				client.close();
				System.out.println("search_item�ɹ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("search_itemʧ��");
				e.printStackTrace();
			}
		}
	}
}
