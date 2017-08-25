package com.lee.chat.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
@Component
public class WxMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty(value="Type")
	private String type;
	@JsonProperty(value="ToUserName")
	private String toUserName;
	@JsonProperty(value="LocalID")
	private String localID;
	@JsonProperty(value="FromUserName")
	private String fromUserName;
	@JsonProperty(value="Content")
	private String content;
	@JsonProperty(value="ClientMsgId")
	private String clientMsgId;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getLocalID() {
		return localID;
	}
	public void setLocalID(String localID) {
		this.localID = localID;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getClientMsgId() {
		return clientMsgId;
	}
	public void setClientMsgId(String clientMsgId) {
		this.clientMsgId = clientMsgId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
