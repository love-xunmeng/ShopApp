package com.michael.shopapp;

public class UserInfo {
	
	private static UserInfo instance = new UserInfo();	
	private int id = -1;
	private String userName = null;
	private String password = null;
	
	private UserInfo(){
		
	}
	
	public static UserInfo getInstance(){
		return instance;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}
