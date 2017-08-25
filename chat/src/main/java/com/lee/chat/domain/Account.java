package com.lee.chat.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	private String userName;
	@Id
	private Long mobile;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
