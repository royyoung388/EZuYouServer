package fileutils;

import java.io.File;

public class SearchUtils {

	public static int getHomeImageCount() {
		int count = 0;
		int i = 0;

		while (true) {
			File file = new File("Home/Home_image/image" + i + "1.jpg");
			if (file.exists()) {
				i++;
				count++;
			} else
				return count;
		}
	}
	
	public static int getStrategyImageCount() {
		int count = 0;
		int i = 0;

		while (true) {
			File file = new File("Strategy/Strategy_Image/image" + i + "1.jpg");
			if (file.exists()) {
				i++;
				count++;
			} else
				return count;
		}
	}
	
	//search获取homeItem的name信息
	/*public static String getHomeItemName(String homeItem) {

		Pattern pattern = Pattern.compile("\\{[^\\{\\}]*?name:(\\S*?);[^\\{/}]*?\\},\\s*");

		Matcher matcher = pattern.matcher(homeItem);
		System.out.println("search获取homeitem的name信息");

		String name = "";
		while (matcher.find()) {
			name += matcher.group();

		}
		System.out.println("该name内容为:" + name);
		return name;
	}
	
	//search获取strategy的program信息
	public static String getStrategy_Program(String strategyItem) {

		Pattern pattern = Pattern.compile("\\{[^\\{\\}]*?program:(\\S*?);[^\\{\\}]*?\\},\\s*");

		Matcher matcher = pattern.matcher(strategyItem);
		System.out.println("search获取strategyItem的program信息");

		String name = "";
		while (matcher.find()) {
			name += matcher.group();

		}
		System.out.println("该program内容为:" + name);
		return name;
	}*/
}
