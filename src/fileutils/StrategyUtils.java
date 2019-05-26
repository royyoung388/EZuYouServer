package fileutils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrategyUtils {

	// 获取指定tag的strategy_item信息
	public static String ReadItemPosition_strategy(int tag) {

		String string = FileUtils.Readfile("Strategy/Strategy_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?tag:" + tag + ";[^\\{\\}]*?\\},\\s*)");

		Matcher matcher = pattern.matcher(string);
		System.out.println("获取第" + tag + "条strategy_item信息");

		String item = null;
		if (matcher.find()) {
				item = matcher.group();
		}
		System.out.println("该内容为:" + item);
		return item;
	}

	// 获取指定id的Strategy——item消息
	public static String ReadId_strategy(String id) {

		String string = FileUtils.Readfile("Strategy/Strategy_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		System.out.println("获取id为" + id + "strategy_item信息");

		String all = "";

		while (matcher.find()) {
			all += matcher.group();
			break;
		}

		System.out.println("该内容为:" + all);
		return all;
	}
	
	//获取strategy_imgae的id
	public static boolean isStrategyID(String id, int tag) {
		
		String string = FileUtils.Readfile("Strategy/Strategy_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?tag:"+ tag +";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		System.out.println("获取id为" + id + "tag为:" + tag + "strategy_image信息");

		if (matcher.find()) {
			System.out.println("找到tag为:" + tag + "id为" + id + "的Strategy——image信息:" + matcher.group());
			return true;
		}

		System.out.println("未找到tag为:" + tag + "id为" + id + "的Strategy——image信息");
		return false;
	}

	// 获取Strategy图片个数
	public static int GetImageCount_Strategy() {

		int count = 0;
		int i = 0, j = 1;

		while (true) {
			File file = new File("Strategy/Strategy_Image/image" + i + j + ".jpg");
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
}
