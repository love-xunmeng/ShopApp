package com.michael.shopapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import android.util.Log;

public class CatalogRunnable implements Runnable{

	private String message_ = null;
	private List<String> catalogList = null;
	
	public CatalogRunnable(String message)
	{
		message_ = message;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Socket socket = null;
		
		try{
			//Log.d("NetworkThread", "thread start");
			//socket = new Socket("10.0.2.2", 7777);
			socket = new Socket();
			SocketAddress socketAddress = new InetSocketAddress("10.0.2.2", 7777);
			socket.connect(socketAddress, 10);
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8")), true);
			out.println(message_);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			String jsonData = in.readLine();
			if(null == jsonData){
				Log.v("CatalogRunnable", "null == jsonData");
			}else{
				//Log.v("NetworkThread", jsonData);
				try{
					JSONArray array2 = new JSONArray(jsonData);
					catalogList = new ArrayList<String>();
					for(int i = 0; i < array2.length(); ++i) {
						//System.out.println(array2.getString(i));
						catalogList.add(array2.getString(i));
					}
				}catch(Exception e){
					Log.e("CatalogRunnable", e.toString());
				}
			}
			//Log.d("NetworkThread", "thread finish");
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(null != socket) {
					socket.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	public List<String> getCatalogList(){
		return catalogList;
	}
}
