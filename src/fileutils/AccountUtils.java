package fileutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AccountUtils {

	//����item��status
	public static void chageStatus(String id, int tag) {
		try {
			
			System.out.println("account:�޸�" + id + "��" + tag + "��Ϣ");
			
			File file = new File("Account/" + id + ".txt");
			InputStream is = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String filename = file.getName();
			// tmpfile Ϊ�����ļ�������������Ϻ���ļ���������ΪԴ�ļ����֡�
			File tmpfile = new File(file.getParentFile().getAbsolutePath() + "/" + filename + ".tmp");

			BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfile));

			boolean flag = false;
			String str = null;

			while (true) {
				str = reader.readLine();

				if (str == null)
					break;

				if (str.contains("tag:" + tag + ";")) {
					// �ҵ���ָ����tag��Ȼ���޸���һ��status
					// 1��Ϊ0,0��Ϊ1

					writer.write(str + "\r\n");

					str = reader.readLine();
					if (str.contains("0"))
						writer.write("status:1;\r\n");
					else if (str.contains("1"))
						writer.write("status:0;\r\n");

					flag = true;
				} else
					writer.write(str + "\r\n");
			}

			is.close();
			reader.close();
			writer.flush();
			writer.close();

			if (flag) {
				file.delete();
				tmpfile.renameTo(new File(file.getAbsolutePath()));
			} else
				tmpfile.delete();
			
			System.out.println("account:�޸�" + id + "��" + tag + "��Ϣ�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
