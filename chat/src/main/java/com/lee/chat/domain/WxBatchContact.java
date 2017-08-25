package com.lee.chat.domain;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class WxBatchContact {
	@JsonProperty(value="UserName")
	private String userName;
	@JsonProperty(value="ChatRoomId")
	private String chatRoomId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
}
