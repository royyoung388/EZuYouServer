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

	//获取指定的item信息
	public static String ReadItemPosition(int tag) {

		String string = FileUtils.Readfile("Home\\Home_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?tag:" + tag + ";[^\\{\\}]*?\\},\\s*)");

		Matcher matcher = pattern.matcher(string);
		System.out.println("获取tag:" + tag + "的item信息");

		String item = "";
		while (matcher.find()) {
			item += matcher.group();

		}
		System.out.println("该内容为:" + item);
		return item;
	}
    
    //获取item图片个数
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
    
    //获取所有的status为1的item信息
    public static String getStatus1Item() {
		String string = FileUtils.Readfile("Home\\Home_Item.txt");
		
		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?status:1;[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		System.out.println("获取所有item信息");
		
		String all = "";
		int i = 0;
		while (matcher.find()) {
			 all += matcher.group();
			 System.out.println("获取所有中的第" + i + "个");
			 i++;
		}
		
		return all;
	}
    
    //获取指定id和status的item
    public static String getIdStatusItem(String id, int status) {
    	String string = FileUtils.Readfile("Home\\Home_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?status:" + status + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		System.out.println("获取指定id和status的item");

		String all = "";
		int i = 0;
		while (matcher.find()) {
			all += matcher.group();
			System.out.println("获取到第" + i + "条:" + matcher.group());
			i++;
		}

		return all;
	}
    
    //获取指定id的信息
	public static String getIdItem(String id) {
		String string = FileUtils.Readfile("Home\\Home_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		System.out.println("获取指定id的item信息");

		String all = "";
		int i = 0;
		while (matcher.find()) {
			all += matcher.group();
			System.out.println("获取到第" + i + "条:" + matcher.group());
			i++;
		}

		return all;
	}
    
    //获取指定tag的status值
    public static boolean isStatus(int tag) {
    	String string = FileUtils.Readfile("Home\\Home_Item.txt");
		
		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?tag:" + tag + ";[^\\{\\}]*?status:1;[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		System.out.println("获取指定item的tag:" + tag + "的status信息");
		
		if (matcher.find()) {
			System.out.println("item的tag:" + tag + "的status信息:1");
			return true;
		}
		
		System.out.println("item的tag:" + tag + "的status信息:0");
		
		return false;
	}
    
	// 获取item的id信息
	public static boolean isID(String id, int tag) {

		String string = FileUtils.Readfile("Home\\Home_Item.txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?tag:" + tag + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		System.out.println("获取指定id信息:" + id + "的tag:" + tag + "的image信息");

		if (matcher.find()) {
			System.out.println("找到id，信息:" + matcher.group());
			return true;
		}
		System.out.println("未找到id信息");

		System.out.println("获取指定id信息" + id + "的image信息完成");
		return false;
	}

	// 修改指定tag的status信息
	public static void chageStatus(String id, int tag) {
		try {
			System.out.println("item:修改" + id + "的" + tag + "信息");
			File file = new File("Home\\Home_Item.txt");
			InputStream is = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String filename = file.getName();
			// tmpfile 为缓存文件，代码运行完毕后此文件将重命名为源文件名字。
			File tmpfile = new File(file.getParentFile().getAbsolutePath() + "\\" + filename + ".tmp");

			BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));

			boolean flag = false;
			String str = null;

			while (true) {
				str = reader.readLine();

				if (str == null)
					break;

				if (str.contains("tag:" + tag + ";")) {
					// 找到了指定的tag，然后修改下一行status
					// 1改为0,0改为1

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
			
			System.out.println("item:修改" + id + "的" + tag + "信息成功");
			
			//启动account的修改
			AccountUtils.chageStatus(id, tag);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
