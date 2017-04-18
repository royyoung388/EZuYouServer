package fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReleaseUtils {
	
	//获取学校
	public static String getSchool(String id) {
		
		String string = FileUtils.Readfile("Account\\" + id + ".txt");
		
		Pattern pattern = Pattern.compile("\\{[^\\{\\}]*?userschool:(\\S*?);[^\\{\\}]*?\\},\\s*");

		Matcher matcher = pattern.matcher(string);
		System.out.println("获取id 为:" + id + "的school信息");

		String item = "";
		if (matcher.find()) {
			item += matcher.group();
		}
		System.out.println("该内容为:" + item);
		return item;
	}
}
