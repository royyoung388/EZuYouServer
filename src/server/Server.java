package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import homeport.Home_Advertise;
import homeport.Home_Image;
import homeport.Home_Item;

public class Server {
    public static void main(String[] args) {
    	System.out.println("服务器启动...");
    	
    	//处理广告图片
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
		    	Home_Advertise advertise = new Home_Advertise();
			}
		}).start();
    	
    	//处理item信息
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
		    	Home_Item item = new Home_Item();
			}
		}).start();
    	
    	//处理image
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
		    	Home_Image item = new Home_Image();
			}
		}).start();
    }
}