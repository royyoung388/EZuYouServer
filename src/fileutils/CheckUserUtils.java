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
		
		if (!(mshcool.find() && mnumber.find())) {
			System.out.println("�û��Ѵ��ڣ�����3");
			return 3;
		}
				
		//�û�����������ȷ������0
		System.out.println("�û�����������ȷ������0");
		return 0;
	}
}
