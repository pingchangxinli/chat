package com.lee.chat.domain;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class WxStatusNotifyRequest {

	@JsonProperty(value="BaseRequest")
	private BaseRequest request;
	@JsonProperty(value="ClientMsgId")
	private Long clientMsgId;
	@JsonProperty(value="Code")
	private Integer code;
	@JsonProperty(value="FromUserName")
	private String fromUserName;
	@JsonProperty(value="ToUserName")
	private String toUserName;
	public BaseRequest getRequest() {
		return request;
	}
	public void setRequest(BaseRequest request) {
		this.request = request;
	}
	public Long getClientMsgId() {
		return clientMsgId;
	}
	public void setClientMsgId(Long clientMsgId) {
		this.clientMsgId = clientMsgId;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
}
