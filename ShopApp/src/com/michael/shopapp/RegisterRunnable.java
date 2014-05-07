package com.michael.shopapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.json.JSONObject;

import android.util.Log;

public class RegisterRunnable implements Runnable {
	
	private Socket socket = null;
	private Boolean isRegisterSuccess = false;

	public Boolean getIsRegisterSuccess() {
		return isRegisterSuccess;
	}

	public void setIsRegisterSuccess(Boolean isRegisterSuccess) {
		this.isRegisterSuccess = isRegisterSuccess;
	}

	@Override
	public void run() {
		try{
			socket = new Socket();
			SocketAddress socketAddress = new InetSocketAddress("10.0.2.2", 7777);
			socket.connect(socketAddress, 10);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8")), true);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("user_name", UserInfo.getInstance().getUserName());
			jsonObject.put("mobile_phone", UserInfo.getInstance().getMobilePhone());
			jsonObject.put("address", UserInfo.getInstance().getAddress());
			jsonObject.put("email", UserInfo.getInstance().getEmail());
			//
			pw.println("Register");
			//
			pw.println(jsonObject.toString());
			//Receive result from server
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			String data = br.readLine();
			jsonObject = new JSONObject(data);
			isRegisterSuccess = jsonObject.getBoolean("register_result");
		
			pw.close();
			br.close();
		}catch(Exception e){
			Log.e("RegisterRunnable",e.toString());
		}finally{
			try{
				if(null != socket){
					socket.close();
				}
			}catch(Exception e){
				
			}
		}
		
	}

}
