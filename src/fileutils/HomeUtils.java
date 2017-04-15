package fileutils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeUtils {

	//��ȡָ����item��Ϣ
    public static String ReadItemPosition(int position) {
    	
		String string = FileUtils.Readfile("Home\\Home_Item.txt");
		
		 Pattern pattern = Pattern.compile("(\\{\\s*" +
				 							"id:\\S*?;\\s*" +
				 							"tag:" + position + ";\\s*" +
				 							"status:1;\\s*" +
				 							"person:\\S*?;\\s*" +
		 		 							"name:\\S*?;\\s*" +
                 							"sell:\\S*?;\\s*" +
                 							"rent:\\S*?;\\s*" +
                 							"introduce:\\S*?;\\s*"
                 							+ "\\},)\\s*");
		 
		 Matcher matcher = pattern.matcher(string);
		 System.out.println("��ȡ��" + position + "��item��Ϣ");
		 
		 int i = 0;
		 String item = null;
		 while (matcher.find()) {
			if (position == i) {
				item = matcher.group();
				break;
			}
			i++;
		}
		 System.out.println("������Ϊ:" + item);
		 return item;
	}
    
    //��ȡitemͼƬ����
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
    
    //��ȡ���е�statusΪ1��item��Ϣ
    public static String GetAllItem() {
		String string = FileUtils.Readfile("Home\\Home_Item.txt");
		
		Pattern pattern = Pattern.compile("(\\{\\s*" +
				 							"id:\\S*?;\\s*" +
				 							"tag:\\S*?;\\s*" +
				 							"status:1;\\s*" +
				 							"person:\\S*?;\\s*" +
		 		 							"name:\\S*?;\\s*" +
                 							"sell:\\S*?;\\s*" +
                 							"rent:\\S*?;\\s*" +
                 							"introduce:\\S*?;\\s*"
                 							+ "\\},)\\s*");
		Matcher matcher = pattern.matcher(string);
		System.out.println("��ȡ����item��Ϣ");
		
		String all = "";
		 while (matcher.find()) {
			 all += matcher.group();
		 }
		
		return all;
	}
}
