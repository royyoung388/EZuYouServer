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
	
	//search��ȡhomeItem��name��Ϣ
	/*public static String getHomeItemName(String homeItem) {

		Pattern pattern = Pattern.compile("\\{[^\\{\\}]*?name:(\\S*?);[^\\{/}]*?\\},\\s*");

		Matcher matcher = pattern.matcher(homeItem);
		System.out.println("search��ȡhomeitem��name��Ϣ");

		String name = "";
		while (matcher.find()) {
			name += matcher.group();

		}
		System.out.println("��name����Ϊ:" + name);
		return name;
	}
	
	//search��ȡstrategy��program��Ϣ
	public static String getStrategy_Program(String strategyItem) {

		Pattern pattern = Pattern.compile("\\{[^\\{\\}]*?program:(\\S*?);[^\\{\\}]*?\\},\\s*");

		Matcher matcher = pattern.matcher(strategyItem);
		System.out.println("search��ȡstrategyItem��program��Ϣ");

		String name = "";
		while (matcher.find()) {
			name += matcher.group();

		}
		System.out.println("��program����Ϊ:" + name);
		return name;
	}*/
}
