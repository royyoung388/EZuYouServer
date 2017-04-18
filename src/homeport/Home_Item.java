package homeport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.HomeUtils;
import keyword.KeyWord;

//�����б���Ϣ
public class Home_Item {

	private ServerSocket serverSocket;

	// ��ʼ��������
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
				
				String id = inputStream.readUTF();
				int tag = inputStream.readInt();
				
				int count = HomeUtils.GetImageCount();
				out.writeInt(count);
				System.out.println("ͼƬ����" + count);
				
				if (tag == -1) {
					System.out.println("��ȡ����statusΪ1��item��Ϣ");
					out.writeUTF(HomeUtils.getStatus1Item());
				} else if (tag == -2) {
					//��ȡָ��id����Ϣ
					System.out.println("��ȡָ��id:��item��Ϣ");
					out.writeUTF(HomeUtils.getIdItem(id));
				} else if (tag == -3) {
					// ��ȡָ��id��statusΪ1��item
					System.out.println("��ȡָ��id��statusΪ1��item");
					out.writeUTF(HomeUtils.getIdStatusItem(id, 1));
				} else if (tag == -4) {
					// ��ȡָ��id��statusΪ0��item
					System.out.println("��ȡָ��id��statusΪ0��item");
					out.writeUTF(HomeUtils.getIdStatusItem(id, 0));
				} else {
					//��ȡָ��tag����Ϣ
					System.out.println("��ȡtag:" + tag + "��item��Ϣ");
					String string = HomeUtils.ReadItemPosition(tag);
					out.writeUTF(string);
				}
				
				out.close();
				inputStream.close();
				client.close();

				System.out.println("����Home_Item�ɹ�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("����Home_Itemʧ��");
				e.printStackTrace();
			}
		}

	}
}
