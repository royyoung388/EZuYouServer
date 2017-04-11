package fileutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {

	//实现文件写
    public static void Writefile(String file, String str) {  
    	try  
	    {  
	    //使用BufferedReader和BufferedWriter进行文件复制（操作的是字符,以行为单位读入字符）  
    		
	    BufferedWriter bw=new BufferedWriter(new FileWriter(file, true));   
	    bw.write(str);  
	    //由于BufferedReader的rendLIne()是不读入换行符的，所以写入换行时须用newLine()方法  
	    bw.newLine();  
    	bw.close();  
	    }  
    	catch (IOException e)  
    	{  
    		e.printStackTrace();  
    	}  	
    }
    
    
    //实现文件读
    public static String Readfile(String file) {
    	
    	String s = "";
    	
    	try  
        {  
        //使用BufferedReader和BufferedWriter进行文件复制（操作的是字符,以行为单位读入字符）  
    	BufferedReader br=new BufferedReader(new FileReader(file));  
         
    	String str = null;

         while ((str = br.readLine()) != null) {
             s += str;
         }
         br.close();  
         }  
    	 catch (IOException e)  
    	 {  
    		 e.printStackTrace();  
         }
		return s;  
    }
    
    //获取指定的item信息
    public static String ReadItemPosition(int position) {
    	
		String string = FileUtils.Readfile("Home\\Home_Item.txt");
		
		 Pattern pattern = Pattern.compile("(\\{\\s*name:\\S*?;\\s*" +
                 "sell:\\S*?;\\s*" +
                 "rent:\\S*?;\\s*" +
                 "introduce:\\S*?;\\s*\\},)");
		 Matcher matcher = pattern.matcher(string);
		 System.out.println("获取第" + position + "条item信息");
		 
		 int i = 0;
		 String item = null;
		 while (matcher.find()) {
			if (position == i) {
				item = matcher.group();
				break;
			}
		}
		 System.out.println("该内容为:" + item);
		 return item;
	}
    
    //获取图片个数
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
}
