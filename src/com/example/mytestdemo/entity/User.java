package com.example.mytestdemo.entity;

/**
 * @author Zengcq
 * @date 2016Äê12ÔÂ16ÈÕ
 * @version 1.0
 * @description
 */
public class User {
	private String userName;
	private String nick;
	private String avatar;
	
	public User(String userName,String nick, String avatar){
		this.userName = userName;
		this.nick = nick;
		this.avatar = avatar;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
