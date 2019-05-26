package fileutils;

import java.io.*;
import java.net.URL;

public class FileUtils {

    private static String getProjectPath() {
        URL url = FileUtils.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;

        try {
            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (filePath.endsWith(".jar"))
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);

        File file = new File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;
    }

    //ʵ���ļ�д
    public static void Writefile(String file, String str) {
        try {
            //ʹ��BufferedReader��BufferedWriter�����ļ����ƣ����������ַ�,����Ϊ��λ�����ַ���
            BufferedWriter bw = new BufferedWriter(new FileWriter(getProjectPath() + "/" + file, true));
            bw.write(str);
            //����BufferedReader��rendLIne()�ǲ����뻻�з��ģ�����д�뻻��ʱ����newLine()����
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //ʵ���ļ���
    public static String Readfile(String file) {

        String s = "";

        try {
            //ʹ��BufferedReader��BufferedWriter�����ļ����ƣ����������ַ�,����Ϊ��λ�����ַ���
            BufferedReader br = new BufferedReader(new FileReader(getProjectPath() + "/" + file));

            String str = null;

            while ((str = br.readLine()) != null) {
                s += str;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    //�����ļ�
    public static void CreatFile(String path) {
        File file = new File(getProjectPath() + "/" + path);
        if (file.exists()) {
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
