package com.michael.shopapp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONArray;

import android.util.Log;

public class ShoppingCarRunnable implements Runnable {
	
	private JSONArray jsonArray = null;
	
	public ShoppingCarRunnable(JSONArray jsonArray){
		this.jsonArray = jsonArray;
	}
	
	@Override
	public void run(){
		Socket socket = null;
		try {
			//Log.d("ShoppingCarRunnable-Debug", "Start");
			socket = new Socket("10.0.2.2", 7777);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8")), true);
			pw.println("Order");
			pw.println(jsonArray.toString());
			//Log.d("ShoppingCarRunnable-Debug", "Finish");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(null != socket){
					socket.close();
				}
			}catch(Exception e){
				Log.e("ShoppingCarRunnable", e.toString());
			}
		}
	}
}
