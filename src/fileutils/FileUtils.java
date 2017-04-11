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

	//ʵ���ļ�д
    public static void Writefile(String file, String str) {  
    	try  
	    {  
	    //ʹ��BufferedReader��BufferedWriter�����ļ����ƣ����������ַ�,����Ϊ��λ�����ַ���  
    		
	    BufferedWriter bw=new BufferedWriter(new FileWriter(file, true));   
	    bw.write(str);  
	    //����BufferedReader��rendLIne()�ǲ����뻻�з��ģ�����д�뻻��ʱ����newLine()����  
	    bw.newLine();  
    	bw.close();  
	    }  
    	catch (IOException e)  
    	{  
    		e.printStackTrace();  
    	}  	
    }
    
    
    //ʵ���ļ���
    public static String Readfile(String file) {
    	
    	String s = "";
    	
    	try  
        {  
        //ʹ��BufferedReader��BufferedWriter�����ļ����ƣ����������ַ�,����Ϊ��λ�����ַ���  
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
    
    //��ȡָ����item��Ϣ
    public static String ReadItemPosition(int position) {
    	
		String string = FileUtils.Readfile("Home\\Home_Item.txt");
		
		 Pattern pattern = Pattern.compile("(\\{\\s*name:\\S*?;\\s*" +
                 "sell:\\S*?;\\s*" +
                 "rent:\\S*?;\\s*" +
                 "introduce:\\S*?;\\s*\\},)");
		 Matcher matcher = pattern.matcher(string);
		 System.out.println("��ȡ��" + position + "��item��Ϣ");
		 
		 int i = 0;
		 String item = null;
		 while (matcher.find()) {
			if (position == i) {
				item = matcher.group();
				break;
			}
		}
		 System.out.println("������Ϊ:" + item);
		 return item;
	}
    
    //��ȡͼƬ����
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
