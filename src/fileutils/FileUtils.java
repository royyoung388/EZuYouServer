package fileutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    
    //创建文件
    public static void CreatFile(String path) {
    	File file = new File(path);
    	 if(file.exists()) {  
             System.out.println("创建单个文件" + path + "失败，目标文件已存在！");  
         }  
    	 //创建目标文件  
         try {  
             if (file.createNewFile()) {  
                 System.out.println("创建单个文件" + path + "成功！");  
             } else {  
                 System.out.println("创建单个文件" + path + "失败！");  
             }  
         } catch (IOException e) {  
             e.printStackTrace();  
             System.out.println("创建单个文件" + file + "失败！" + e.getMessage());  
         }  
    }
    
    
    
   
}
