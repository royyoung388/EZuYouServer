package fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReleaseUtils {
	
	//��ȡѧУ
	public static String getSchool(String id) {
		
		String string = FileUtils.Readfile("Account\\" + id + ".txt");
		
		Pattern pattern = Pattern.compile("\\{[^\\{\\}]*?userschool:(\\S*?);[^\\{\\}]*?\\},\\s*");

		Matcher matcher = pattern.matcher(string);
		System.out.println("��ȡid Ϊ:" + id + "��school��Ϣ");

		String item = "";
		if (matcher.find()) {
			item += matcher.group();
		}
		System.out.println("������Ϊ:" + item);
		return item;
	}
}
