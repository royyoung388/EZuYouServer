package fileutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
	
	//��ȡmy������Ϣ
	public static String  getMyDetil(String id) {
		
		//��ȡmy������Ϣ	
		System.out.println("��ȡmy������Ϣ");

		String string = FileUtils.Readfile("Account/" + id + ".txt");
		
		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		
		String all = "";
		if (matcher.find()) all += matcher.group();
		
		System.out.println("������ϢΪ:" + all);
		
		return all;
	}
	
	//��ȡ������Ϣtrade
	public static String  getTrade(String id) {
		
		// ��ȡ������Ϣ��trade
		System.out.println("��ȡ������Ϣ��trade");
		String string = FileUtils.Readfile("Account/" + id + ".txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?item:home;[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		String all = "";
		while (matcher.find())
			all += matcher.group();

		System.out.println("������Ϣ��tradeΪ:" + all);

		return all;
	}
	
	// �ж������Ƿ���ȷ����������
	public static String is_And_Change_Pwd(String id, String pwd_old, String pwd_new) {
		
		System.out.println("�ж������Ƿ���ȷ����������");
		
		String string = FileUtils.Readfile("Login//Login.txt");
		
		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?userpwd:" + pwd_old + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		
		String status = "wrong";
		
		if (matcher.find()) {
			status = "right";
			changePwd(id, pwd_new);
			System.out.println("�û���������ȷ,�޸ĳɹ�");
		}
		System.out.println("�ж������Ƿ���ȷ�������������");
		return status;
	}
	
	// ��������
	public static void changePwd(String id, String pwd_new) {
		try {

			System.out.println("�޸�" + id + "������Ϊ" + pwd_new);

			File file = new File("Login/Login.txt");
			
			InputStream is = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String filename = file.getName();
			// tmpfile Ϊ�����ļ�������������Ϻ���ļ���������ΪԴ�ļ����֡�
			File tmpfile = new File(file.getParentFile().getAbsolutePath() + "/" + filename + ".tmp");

			BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));

			boolean flag = false;
			String str = null;

			while (true) {
				str = reader.readLine();

				if (str == null)
					break;

				if (str.contains("id:" + id + ";")) {

					writer.write(str + "\r\n");
					
					str = reader.readLine();	
					writer.write(str + "\r\n");
					
					str = reader.readLine();
					
					str = "userpwd:" + pwd_new + ";\r\n";
					writer.write(str + "\r\n");

					flag = true;
				} else
					writer.write(str + "\r\n");
			}

			is.close();
			reader.close();
			writer.flush();
			writer.close();

			if (flag) {
				file.delete();
				tmpfile.renameTo(new File(file.getAbsolutePath()));
			} else
				tmpfile.delete();

			System.out.println("�޸�" + id + "������Ϊ" + pwd_new + "�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
