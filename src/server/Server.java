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
    	System.out.println("����������...");
    	
    	//������ͼƬ
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
		    	Home_Advertise advertise = new Home_Advertise();
			}
		}).start();
    	
    	//����item��Ϣ
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
		    	Home_Item item = new Home_Item();
			}
		}).start();
    	
    	//����image
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
		    	Home_Image item = new Home_Image();
			}
		}).start();
    }
}