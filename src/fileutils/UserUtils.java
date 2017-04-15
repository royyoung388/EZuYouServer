package fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtils {

	private String id, username, userpwd, userschool, 
		userschool_class, usernumber, usersex;

	public UserUtils(String username, String userpwd, String userschool,
			String userschool_class, String usernumber, String usersex) {
		this.username = username;
		this.userpwd = userpwd;
		this.userschool = userschool;
		this.userschool_class = userschool_class;
		this.usernumber = usernumber;
		this.usersex = usersex;
	}
	
	public UserUtils(String username, String userpwd) {
		this.username = username;
		this.userpwd = userpwd;
	}
	
	public UserUtils(String id) {
		this.id = id;
	}

	//获取id
	public String getID() {
		String all = FileUtils.Readfile("Login\\Login.txt");
		String string = "\\{\\s*"
				+ "id:(\\S*?);\\s*"
				+ "username:" + username + ";\\s*"
				+ "userpwd:" + userpwd + ";\\s*"
				+ "userschool:(\\S*?);\\s*"
				+ "userschool_class:(\\S*?);\\s*"
				+ "usernumber:(\\S*?);\\s*"
				+ "usersex:(\\S*?);\\s*"
				+ "\\},\\s*";
		
		Pattern pstr = Pattern.compile(string);
		Matcher mstr = pstr.matcher(all);
		
		String id = "";
		
		while (mstr.find()) {
			id += mstr.group(1);
			System.out.println("ID:" + id);
			break;
		}
		
		return id;
	}
	
	//创建id
	public String creatID() {
		return username + userschool + userschool_class + usernumber + usersex;
	}
	
	//获取基本信息
	public String getMy() {
		String all = FileUtils.Readfile("Account\\"+ id + ".txt");
		
		String string = "\\{\\s*"
				+ "id:(\\S*?);\\s*"
				+ "username:(\\S*?);\\s*"
				+ "userschool:(\\S*?);\\s*"
				+ "userschool_class:(\\S*?);\\s*"
				+ "usernumber:(\\S*?);\\s*"
				+ "usersex:(\\S*?);\\s*"
				+ "\\},\\s*";
		
		Pattern pstr = Pattern.compile(string);
		Matcher mstr = pstr.matcher(all);
		
		String my = "";
		
		while (mstr.find()) {
			my += mstr.group(1) + mstr.group(2) + mstr.group(3) + mstr.group(4)
					+ mstr.group(5) +  mstr.group(6);
			System.out.println("My:" + my);
			break;
		}
		
		return my;
	}
}
