package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Sides;

import chatport.ChatPort;
import homeport.Home_Advertise;
import homeport.Home_Image;
import homeport.Home_Item;
import loginport.Login;
import loginport.Sign;
import releaseport.Release;
import strategy.Strategy;
import strategy.Strategy_Image;

public class Server {
	public static void main(String[] args) {
		System.out.println("����������...");

		// ������ͼƬ
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Home_Advertise advertise = new Home_Advertise();
			}
		}).start();

		// ����item��Ϣ
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Home_Item item = new Home_Item();
			}
		}).start();

		// ����image
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Home_Image item = new Home_Image();
			}
		}).start();

		// ����strategy_item��Ϣ
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Strategy item = new Strategy();
			}
		}).start();

		// ����strategy_image
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Strategy_Image item = new Strategy_Image();
			}
		}).start();

		// �����¼
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Login login = new Login();
			}
		}).start();

		// ����ע��
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Sign sign = new Sign();
			}
		}).start();

		// ������
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Release release = new Release();
			}
		}).start();

		// ��������
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ChatPort chatPort = new ChatPort();
			}
		}).start();
	}
}