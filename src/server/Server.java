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
		System.out.println("服务器启动...");

		// 处理广告图片
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Home_Advertise();
			}
		}).start();

		// 处理item信息
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Home_Item();
			}
		}).start();

		// 处理image
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Home_Image();
			}
		}).start();

		// 处理home_pay修改status
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Home_Pay();
			}
		}).start();

		// 处理strategy_item信息
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Strategy();
			}
		}).start();

		// 处理strategy_image
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Strategy_Image();
			}
		}).start();

		// 处理Strategy_Release
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Strategy_Release();
			}
		}).start();

		// 处理登录
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Login();
			}
		}).start();

		// 处理注册
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Sign();
			}
		}).start();

		// 处理发布
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Release();
			}
		}).start();

		// 处理聊天
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new ChatPort();
			}
		}).start();

		// 处理My基本信息
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new MyDetil();
			}
		}).start();

		// 处理Myupload_Image信息
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Upload_Image();
			}
		}).start();

		// 处理修改密码
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Change_Pwd();
			}
		}).start();

		// 处理search_item
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Search_Item();
			}
		}).start();

		// 处理search_image
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Search_Image();
			}
		}).start();
	}
}