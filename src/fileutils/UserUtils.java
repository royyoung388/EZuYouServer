package fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtils {

	private String username, userpwd;

	public UserUtils(String username, String userpwd) {
		this.username = username;
		this.userpwd = userpwd;
	}

	public String getID() {
		String all = FileUtils.Readfile("Login\\Login.txt");
		String string = "\\{\\s*"
				+ "username:" + username + ";\\s*"
				+ "userpwd:" + userpwd + ";\\s*"
				+ "userschool:(\\S*?);\\s*"
				+ "userschool_class:(\\S*?);\\s*"
				+ "usernumber:(\\S*?);\\s*"
				+ "usersex:(\\S*?);\\s*"
				+ "\\},\\s*";
		
		Pattern pstr = Pattern.compile(string);
		Matcher mstr = pstr.matcher(all);
		
		String id = username;
		
		while (mstr.find()) {
			id += mstr.group(1) + mstr.group(2) + mstr.group(3) + mstr.group(4);
			System.out.println("ID:" + id);
			break;
		}
		
		return id;
	}
}
