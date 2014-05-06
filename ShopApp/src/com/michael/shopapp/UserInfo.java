package com.michael.shopapp;

public class UserInfo {
	
	private static UserInfo instance = new UserInfo();	
	private int id = -1;
	private String userName = null;
	private String password = null;
	private String moblie_phone = null;
	private String address = null;
	private String email = null;
	
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
	
	public String getMobilePhone(){
		return this.moblie_phone;
	}
	
	public void setMobilePhone(String mobile_phone){
		this.moblie_phone = mobile_phone;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
}
