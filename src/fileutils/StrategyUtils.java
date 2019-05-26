package fileutils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrategyUtils {

	// ��ȡָ��tag��strategy_item��Ϣ
	public static String ReadItemPosition_strategy(int tag) {

		String string = FileUtils.Readfile("Strategy/Strategy_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?tag:" + tag + ";[^\\{\\}]*?\\},\\s*)");

		Matcher matcher = pattern.matcher(string);
		System.out.println("��ȡ��" + tag + "��strategy_item��Ϣ");

		String item = null;
		if (matcher.find()) {
				item = matcher.group();
		}
		System.out.println("������Ϊ:" + item);
		return item;
	}

	// ��ȡָ��id��Strategy����item��Ϣ
	public static String ReadId_strategy(String id) {

		String string = FileUtils.Readfile("Strategy/Strategy_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		System.out.println("��ȡidΪ" + id + "strategy_item��Ϣ");

		String all = "";

		while (matcher.find()) {
			all += matcher.group();
			break;
		}

		System.out.println("������Ϊ:" + all);
		return all;
	}
	
	//��ȡstrategy_imgae��id
	public static boolean isStrategyID(String id, int tag) {
		
		String string = FileUtils.Readfile("Strategy/Strategy_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?tag:"+ tag +";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		System.out.println("��ȡidΪ" + id + "tagΪ:" + tag + "strategy_image��Ϣ");

		if (matcher.find()) {
			System.out.println("�ҵ�tagΪ:" + tag + "idΪ" + id + "��Strategy����image��Ϣ:" + matcher.group());
			return true;
		}

		System.out.println("δ�ҵ�tagΪ:" + tag + "idΪ" + id + "��Strategy����image��Ϣ");
		return false;
	}

	// ��ȡStrategyͼƬ����
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
