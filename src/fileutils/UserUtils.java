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
			
		//�û������󣬷���1
		if (!mname.find()) {
			System.out.println("�û������󣬷���1");
			return 1;
		}
		
		//������󣬷���2
		if (!mpwd.find()) {
			System.out.println("������󣬷���2");
			return 2;
		}
		
		//�û�����������ȷ������0
		System.out.println("�û�����������ȷ������0");
		return 0;
	}
}
