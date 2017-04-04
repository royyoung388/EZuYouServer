package fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtils {
	public static int userIsExist(String username, String userpwd) {
		String detil = FileUtils.Readfile("Home\\Login.txt");
		String name = "username:" + username + ";";
		String pwd = "userpwd:" +userpwd + ";";
		
		Pattern pname = Pattern.compile(name);
		Pattern ppwd = Pattern.compile(pwd);
		Matcher mname = pname.matcher(detil);
		Matcher mpwd = ppwd.matcher(detil);
			
		//用户名错误，返回1
		if (!mname.find()) {
			System.out.println("用户名错误，返回1");
			return 1;
		}
		
		//密码错误，返回2
		if (!mpwd.find()) {
			System.out.println("密码错误，返回2");
			return 2;
		}
		
		//用户名，密码正确，返回0
		System.out.println("用户名，密码正确，返回0");
		return 0;
	}
}
