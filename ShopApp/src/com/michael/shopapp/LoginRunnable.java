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

public class LoginRunnable implements Runnable {
	
	private Socket socket = null;
	private String mobile_phone = null;
	private Boolean is_login_valid = false;
	
	public LoginRunnable(String mobile_phone){
		this.mobile_phone = mobile_phone;
	}

	@Override
	public void run() {
		try{
			socket = new Socket();
			SocketAddress socketAddress = new InetSocketAddress("10.0.2.2", 7777);
			socket.connect(socketAddress, 10);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8")), true);
			pw.println("Login");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("mobile_phone", mobile_phone);
			pw.println(jsonObject.toString());
			//
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			String data = br.readLine();
			jsonObject = new JSONObject(data);
			is_login_valid = jsonObject.getBoolean("is_login_valid");
			if(is_login_valid){
				//接收姓名，地址，email等信息
				data = br.readLine();
				jsonObject = new JSONObject(data);
				UserInfo.getInstance().setMobilePhone(mobile_phone);
				UserInfo.getInstance().setId(jsonObject.getInt("id"));
				UserInfo.getInstance().setUserName(jsonObject.getString("user_name"));
				UserInfo.getInstance().setAddress(jsonObject.getString("address"));
				UserInfo.getInstance().setEmail(jsonObject.getString("email"));
			}
			
			pw.close();
			br.close();
		}catch(Exception e){
			
		}finally{
			try{
				if(null != socket){
					socket.close();
				}
			}catch(Exception e){
				Log.e("LoginRunnable", e.toString());
			}
		}		
	}
	
	public Boolean getIsValid(){
		return this.is_login_valid;
	}

}
