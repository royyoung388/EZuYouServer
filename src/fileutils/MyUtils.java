package fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
	
	//��ȡmy������Ϣ
	public static String  getMyDetil(String id) {
		
		//��ȡmy������Ϣ	
		System.out.println("��ȡmy������Ϣ");

		String string = FileUtils.Readfile("Account//" + id + ".txt");
		
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
		String string = FileUtils.Readfile("Account//" + id + ".txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?item:home;[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		String all = "";
		while (matcher.find())
			all += matcher.group();

		System.out.println("������Ϣ��tradeΪ:" + all);

		return all;
	}

}
