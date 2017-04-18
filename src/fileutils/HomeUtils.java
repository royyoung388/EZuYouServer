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

public class HomeUtils {

	//��ȡָ����item��Ϣ
	public static String ReadItemPosition(int tag) {

		String string = FileUtils.Readfile("Home\\Home_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?tag:" + tag + ";[^\\{\\}]*?\\},\\s*)");

		Matcher matcher = pattern.matcher(string);
		System.out.println("��ȡtag:" + tag + "��item��Ϣ");

		String item = "";
		while (matcher.find()) {
			item += matcher.group();

		}
		System.out.println("������Ϊ:" + item);
		return item;
	}
    
    //��ȡitemͼƬ����
    public static int GetImageCount() {
    	
		int count = 0;
		int i = 0, j = 1;
		
		
		while (true) {
			File file = new File("Home\\Home_image\\image" + i + j + ".jpg");
			if (file.exists()) {
				count++;
				j++;
			} else if (j == 1) {
				break;
			} else {
				i++;
				j = 1;
			}
		}
		return count;
	}
    
    //��ȡ���е�statusΪ1��item��Ϣ
    public static String getStatus1Item() {
		String string = FileUtils.Readfile("Home\\Home_Item.txt");
		
		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?status:1;[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		System.out.println("��ȡ����item��Ϣ");
		
		String all = "";
		int i = 0;
		while (matcher.find()) {
			 all += matcher.group();
			 System.out.println("��ȡ�����еĵ�" + i + "��");
			 i++;
		}
		
		return all;
	}
    
    //��ȡָ��id��status��item
    public static String getIdStatusItem(String id, int status) {
    	String string = FileUtils.Readfile("Home\\Home_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?status:" + status + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		System.out.println("��ȡָ��id��status��item");

		String all = "";
		int i = 0;
		while (matcher.find()) {
			all += matcher.group();
			System.out.println("��ȡ����" + i + "��:" + matcher.group());
			i++;
		}

		return all;
	}
    
    //��ȡָ��id����Ϣ
	public static String getIdItem(String id) {
		String string = FileUtils.Readfile("Home\\Home_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		System.out.println("��ȡָ��id��item��Ϣ");

		String all = "";
		int i = 0;
		while (matcher.find()) {
			all += matcher.group();
			System.out.println("��ȡ����" + i + "��:" + matcher.group());
			i++;
		}

		return all;
	}
    
    //��ȡָ��tag��statusֵ
    public static boolean isStatus(int tag) {
    	String string = FileUtils.Readfile("Home\\Home_Item.txt");
		
		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?tag:" + tag + ";[^\\{\\}]*?status:1;[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		System.out.println("��ȡָ��item��tag:" + tag + "��status��Ϣ");
		
		if (matcher.find()) {
			System.out.println("item��tag:" + tag + "��status��Ϣ:1");
			return true;
		}
		
		System.out.println("item��tag:" + tag + "��status��Ϣ:0");
		
		return false;
	}
    
	// ��ȡitem��id��Ϣ
	public static boolean isID(String id, int tag) {

		String string = FileUtils.Readfile("Home\\Home_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?tag:" + tag + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		System.out.println("��ȡָ��id��Ϣ:" + id + "��tag:" + tag + "��image��Ϣ");

		if (matcher.find()) {
			System.out.println("�ҵ�id����Ϣ:" + matcher.group());
			return true;
		}
		System.out.println("δ�ҵ�id��Ϣ");

		System.out.println("��ȡָ��id��Ϣ" + id + "��image��Ϣ���");
		return false;
	}

	// �޸�ָ��tag��status��Ϣ
	public static void chageStatus(String id, int tag) {
		try {
			System.out.println("item:�޸�" + id + "��" + tag + "��Ϣ");
			File file = new File("Home\\Home_Item.txt");
			InputStream is = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String filename = file.getName();
			// tmpfile Ϊ�����ļ�������������Ϻ���ļ���������ΪԴ�ļ����֡�
			File tmpfile = new File(file.getParentFile().getAbsolutePath() + "\\" + filename + ".tmp");

			BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));

			boolean flag = false;
			String str = null;

			while (true) {
				str = reader.readLine();

				if (str == null)
					break;

				if (str.contains("tag:" + tag + ";")) {
					// �ҵ���ָ����tag��Ȼ���޸���һ��status
					// 1��Ϊ0,0��Ϊ1

					writer.write(str + "\r\n");

					str = reader.readLine();
					if (str.contains("0"))
						writer.write("status:1;\r\n");
					else if (str.contains("1"))
						writer.write("status:0;\r\n");

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
			
			System.out.println("item:�޸�" + id + "��" + tag + "��Ϣ�ɹ�");
			
			//����account���޸�
			AccountUtils.chageStatus(id, tag);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
