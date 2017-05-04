package com.cheng.web.entity;

import java.io.Serializable;

public class User implements Serializable{
    /**
	 * 字段或域定义：<code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userName;
	private String userPassWord;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassWord() {
		return userPassWord;
	}
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	
	
}
