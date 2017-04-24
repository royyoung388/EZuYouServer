package chatport;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import keyword.KeyWord;

public class Chat2Client extends Thread {

	String ip, idString, username, message;

	public Chat2Client(String ip, String idString, String username, String message) {
		this.ip = ip;
		this.idString = idString;
		this.username = username;
		this.message = message;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Socket socket = null;

		try {
			// ���ص�һ�γ��ֵ�ָ�����ַ����ڴ��ַ����е�������
			int index = ip.indexOf("/");
			
			// ��ȡ/֮���ip�ַ�
			ip = ip.substring(index + 1);
			System.out.println("�Է�ip��" + ip);

			socket = new Socket(ip, KeyWord.PORT_CHAT2CLIENT);

			DataOutputStream out = new DataOutputStream(socket.getOutputStream());

			out.writeUTF(idString);
			out.writeUTF(username);
			out.writeUTF(message);

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
