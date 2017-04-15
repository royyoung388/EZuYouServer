package fileutils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrategyUtils {
	
	 //��ȡָ����strategy_item��Ϣ
    public static String ReadItemPosition_strategy(int position) {
    	
		String string = FileUtils.Readfile("Strategy\\Strategy_Item.txt");
		
		 Pattern pattern = Pattern.compile("(\\{\\s*" +
				 							"id:\\S*?;\\s*" +
				 							"tag:" + position + ";\\s*" +
				 							"person:\\S*?;\\s*" +
		 		 							"program:\\S*?;\\s*" +
                 							"money:\\S*?;\\s*" +
                 							"detil:\\S*?;\\s*" +
				 							"\\},)\\s*");
		 
		 Matcher matcher = pattern.matcher(string);
		 System.out.println("��ȡ��" + position + "��strategy_item��Ϣ");
		 
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
    
   
  //��ȡStrategyͼƬ����
    public static int GetImageCount_Strategy() {
    	
		int count = 0;
		int i = 0, j = 1;
		
		while (true) {
			File file = new File("Strategy\\Strategy_Image\\image" + i + j + ".jpg");
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
}
