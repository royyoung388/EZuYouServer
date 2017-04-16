package strategy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import fileutils.StrategyUtils;
import keyword.KeyWord;

public class Strategy {
	
	private ServerSocket serverSocket;

	// ��ʼ��������
	public Strategy() {
		// TODO Auto-generated constructor stub
		System.out.println("Strategy����");

		try {

			serverSocket = new ServerSocket(KeyWord.PORT_STRATEGY);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸����ȡStrategy��Ϣ��" + client.getInetAddress().toString());

				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("�������쳣: " + e.getMessage());
		}
	}

	// �����������
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

				DataOutputStream out = new DataOutputStream(client.getOutputStream());
				DataInputStream inputStream = new DataInputStream(client.getInputStream());
				
				int position = inputStream.readInt();
				
				int count = StrategyUtils.GetImageCount_Strategy();
				out.writeInt(count);
				
				System.out.println("strategyͼƬ����" + count);
				
				if (position == -1) {
					System.out.println("��ȡ����Strategy��Ϣ");
					String string = FileUtils.Readfile("Strategy\\Strategy_Item.txt");
					out.writeUTF(string);
				} else {
					System.out.println("��ȡ��" + position + "��item��Ϣ");
					String string = StrategyUtils.ReadItemPosition_strategy(position);
					out.writeUTF(string);
				}
				
				out.close();
				inputStream.close();
				client.close();

				System.out.println("����Strategy_Item�ɹ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("����Strategy_Itemʧ��");
				e.printStackTrace();
			}
		}

	}
}
