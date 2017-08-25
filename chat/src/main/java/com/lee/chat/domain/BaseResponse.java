package com.lee.chat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {

	@JsonProperty(value="Ret")
	private Integer  ret;
	@JsonProperty(value="ErrMsg")
	private String errMsg;
	public Integer getRet() {
		return ret;
	}
	public void setRet(Integer ret) {
		this.ret = ret;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
