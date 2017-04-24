package server;

import chatport.ChatPort;
import homeport.Home_Advertise;
import homeport.Home_Image;
import homeport.Home_Item;
import homeport.Home_Pay;
import loginport.Login;
import loginport.Sign;
import myport.Change_Pwd;
import myport.MyDetil;
import myport.Upload_Image;
import releaseport.Release;
import searchport.Search_Image;
import searchport.Search_Item;
import strategyport.Strategy;
import strategyport.Strategy_Image;
import strategyport.Strategy_Release;

public class Server {
	public static void main(String[] args) {
		System.out.println("����������...");

		// ������ͼƬ
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Home_Advertise();
			}
		}).start();

		// ����item��Ϣ
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Home_Item();
			}
		}).start();

		// ����image
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Home_Image();
			}
		}).start();

		// ����home_pay�޸�status
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Home_Pay();
			}
		}).start();

		// ����strategy_item��Ϣ
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Strategy();
			}
		}).start();

		// ����strategy_image
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Strategy_Image();
			}
		}).start();

		// ����Strategy_Release
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Strategy_Release();
			}
		}).start();

		// �����¼
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Login();
			}
		}).start();

		// ����ע��
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Sign();
			}
		}).start();

		// ������
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Release();
			}
		}).start();

		// ��������
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new ChatPort();
			}
		}).start();

		// ����My������Ϣ
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new MyDetil();
			}
		}).start();

		// ����Myupload_Image��Ϣ
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Upload_Image();
			}
		}).start();

		// �����޸�����
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Change_Pwd();
			}
		}).start();

		// ����search_item
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Search_Item();
			}
		}).start();

		// ����search_image
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Search_Image();
			}
		}).start();
	}
}