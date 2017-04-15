package loginport;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fileutils.FileUtils;
import fileutils.UserUtils;
import fileutils.CheckUserUtils;
import keyword.KeyWord;

//ע��
public class Sign {
	private ServerSocket serverSocket;

	// ��ʼ��������
	public Sign() {
		// TODO Auto-generated constructor stub
		System.out.println("Sign����");
		try {

			serverSocket = new ServerSocket(KeyWord.PORT_LOGIN_SIGN);

			while (true) {
				// һ���ж���, ���ʾ��������ͻ��˻��������
				Socket client = serverSocket.accept();

				System.out.println("�µ��豸��Sign��" + client.getInetAddress().toString());

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
				DataInputStream in = new DataInputStream(client.getInputStream());
				DataOutputStream out = new DataOutputStream(client.getOutputStream());

				String username = in.readUTF();
				String userpwd = in.readUTF();
				String userschool = in.readUTF();
				String userschool_class = in.readUTF();
				String usernumber = in.readUTF();
				String usersex = in.readUTF();

				switch (CheckUserUtils.userIsExist(username, userpwd,userschool, usernumber)) {
				case 1:
					out.writeUTF("right");
					System.out.println("ע��ɹ�");
					
					//UserUtils
					UserUtils userUtils = new UserUtils(username, userpwd, 
							userschool, userschool_class, usernumber, usersex);
							
					//�����˺ŵ��ܺͣ��൱������
					FileUtils.Writefile("Login\\Login.txt", "{\r\n"
											+ "id:" + userUtils.creatID() + ";\r\n"
											+ "username:" + username + ";\r\n"
											+ "userpwd:" + userpwd + ";\r\n"
											+ "userschool:" + userschool + ";\r\n"
											+ "userschool_class:" + userschool_class + ";\r\n"
											+ "usernumber:" + usernumber + ";\r\n"
											+ "usersex:" + usersex + ";\r\n"
											+ "},\n");
					
					//����ÿ���˺ŵĸ����ļ�
					FileUtils.CreatFile("Account\\" + userUtils.creatID() + ".txt");
					//д���˺���Ϣ
					FileUtils.Writefile("Account\\" + userUtils.creatID() + ".txt", "{\r\n"
									+ "id:" + userUtils.creatID() + ";\r\n"
									+ "username:" + username + ";\r\n"
									+ "userschool:" + userschool + ";\r\n"
									+ "userschool_class:" + userschool_class + ";\r\n"
									+ "usernumber:" + usernumber + ";\r\n"
									+ "usersex:" + usersex + ";\r\n"
									+ "},\n");
					break;
				default:
					out.writeUTF("error");
					System.out.println("ע��ʧ��");
					break;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("ע�����");
				e.printStackTrace();
			}
		}

	}
}
