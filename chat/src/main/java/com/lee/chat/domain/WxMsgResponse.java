package com.lee.chat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WxMsgResponse {

	@JsonProperty(value="BaseResponse")
	private BaseResponse baseResponse;
	@JsonProperty(value="MsgID")
	private Long msgID;
	@JsonProperty(value="LocalID")
	private Long localID;
	public BaseResponse getBaseResponse() {
		return baseResponse;
	}
	public void setBaseResponse(BaseResponse baseResponse) {
		this.baseResponse = baseResponse;
	}
	public Long getMsgID() {
		return msgID;
	}
	public void setMsgID(Long msgID) {
		this.msgID = msgID;
	}
	public Long getLocalID() {
		return localID;
	}
	public void setLocalID(Long localID) {
		this.localID = localID;
	}
	
}
