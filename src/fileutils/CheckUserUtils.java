package fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUserUtils {
	public static int userIsExist(String username, String userpwd,String userschool, String usernumber) {
		String detil = FileUtils.Readfile("Login\\Login.txt");
		String name = "username:" + username + ";";
		String pwd = "userpwd:" +userpwd + ";";
		String school = "userschool:" + userschool + ";";
		String number = "usernumber" + usernumber + ";";
		
		Pattern pname = Pattern.compile(name);
		Pattern ppwd = Pattern.compile(pwd);
		Pattern pschool = Pattern.compile(school);
		Pattern pnumber = Pattern.compile(number);
		
		Matcher mname = pname.matcher(detil);
		Matcher mpwd = ppwd.matcher(detil);
		Matcher mshcool = pschool.matcher(school);
		Matcher mnumber = pnumber.matcher(number);
			
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
		
		if (!(mshcool.find() && mnumber.find())) {
			System.out.println("用户已存在，返回3");
			return 3;
		}
				
		//用户名，密码正确，返回0
		System.out.println("用户名，密码正确，返回0");
		return 0;
	}
}
