package fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginUtils {
	public static int userIsExist(String username, String userpwd, String userschool, String usernumber) {
		String detil = FileUtils.Readfile("Login\\Login.txt");
		String name_pwd = "(\\{[^\\{\\}]*?username:" + username
				+ ";[^\\{\\}]*?userpwd:" + userpwd
				+ ";[^\\{\\}]*?\\},\\s*)";
		String name = username;
		String user = "(\\{[^\\{\\}]*?username:" + username
				+ ";[^\\{\\}]*?userschool:" + userschool
				+ ";[^\\{\\}]*?usernumber:" + usernumber 
				+ ";[^\\{\\}]*?\\},\\s*)";

		Pattern pname_pwd = Pattern.compile(name_pwd);
		Pattern pname = Pattern.compile(name);
		Pattern puser = Pattern.compile(user);

		Matcher mname_pwd = pname_pwd.matcher(detil);
		Matcher mname = pname.matcher(name);
		Matcher muser = puser.matcher(user);

		//�û����ҵ�
		if (mname.find()) {
			// �û�����������󣬷���1
			if (!mname_pwd.find()) {
				System.out.println("�û������󣬷���1");
				return 1;
			}
		}

		if (muser.find()) {
			System.out.println("�û��Ѵ��ڣ�����2");
			return 2;
		}

		// �û�����������ȷ������0
		System.out.println("�û�����������ȷ������0");
		return 0;
	}
}
