package fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {
	
	//获取my基本信息
	public static String  getMyDetil(String id) {
		
		//获取my基本信息	
		System.out.println("获取my基本信息");

		String string = FileUtils.Readfile("Account//" + id + ".txt");
		
		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		
		String all = "";
		if (matcher.find()) all += matcher.group();
		
		System.out.println("基本信息为:" + all);
		
		return all;
	}
	
	//获取租售信息trade
	public static String  getTrade(String id) {
		
		// 获取租售信息，trade
		System.out.println("获取租售信息，trade");
		String string = FileUtils.Readfile("Account//" + id + ".txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?item:home;[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		String all = "";
		while (matcher.find())
			all += matcher.group();

		System.out.println("租售信息，trade为:" + all);

		return all;
	}

}
