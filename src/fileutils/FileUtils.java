package fileutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
    
    //�����ļ�
    public static void CreatFile(String path) {
    	File file = new File(path);
    	 if(file.exists()) {  
             System.out.println("���������ļ�" + path + "ʧ�ܣ�Ŀ���ļ��Ѵ��ڣ�");  
         }  
    	 //����Ŀ���ļ�  
         try {  
             if (file.createNewFile()) {  
                 System.out.println("���������ļ�" + path + "�ɹ���");  
             } else {  
                 System.out.println("���������ļ�" + path + "ʧ�ܣ�");  
             }  
         } catch (IOException e) {  
             e.printStackTrace();  
             System.out.println("���������ļ�" + file + "ʧ�ܣ�" + e.getMessage());  
         }  
    }
    
    
    
   
}
