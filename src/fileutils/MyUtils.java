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

public class MyUtils {
	
	//获取my基本信息
	public static String  getMyDetil(String id) {
		
		//获取my基本信息	
		System.out.println("获取my基本信息");

		String string = FileUtils.Readfile("Account/" + id + ".txt");
		
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
		String string = FileUtils.Readfile("Account/" + id + ".txt");

		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?item:home;[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);

		String all = "";
		while (matcher.find())
			all += matcher.group();

		System.out.println("租售信息，trade为:" + all);

		return all;
	}
	
	// 判断密码是否正确，更改密码
	public static String is_And_Change_Pwd(String id, String pwd_old, String pwd_new) {
		
		System.out.println("判断密码是否正确，更改密码");
		
		String string = FileUtils.Readfile("Login//Login.txt");
		
		Pattern pattern = Pattern.compile("(\\{[^\\{\\}]*?id:" + id + ";[^\\{\\}]*?userpwd:" + pwd_old + ";[^\\{\\}]*?\\},\\s*)");
		Matcher matcher = pattern.matcher(string);
		
		String status = "wrong";
		
		if (matcher.find()) {
			status = "right";
			changePwd(id, pwd_new);
			System.out.println("用户的密码正确,修改成功");
		}
		System.out.println("判断密码是否正确，更改密码完成");
		return status;
	}
	
	// 更改密码
	public static void changePwd(String id, String pwd_new) {
		try {

			System.out.println("修改" + id + "的密码为" + pwd_new);

			File file = new File("Login/Login.txt");
			
			InputStream is = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String filename = file.getName();
			// tmpfile 为缓存文件，代码运行完毕后此文件将重命名为源文件名字。
			File tmpfile = new File(file.getParentFile().getAbsolutePath() + "/" + filename + ".tmp");

			BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));

			boolean flag = false;
			String str = null;

			while (true) {
				str = reader.readLine();

				if (str == null)
					break;

				if (str.contains("id:" + id + ";")) {

					writer.write(str + "\r\n");
					
					str = reader.readLine();	
					writer.write(str + "\r\n");
					
					str = reader.readLine();
					
					str = "userpwd:" + pwd_new + ";\r\n";
					writer.write(str + "\r\n");

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

			System.out.println("修改" + id + "的密码为" + pwd_new + "成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
