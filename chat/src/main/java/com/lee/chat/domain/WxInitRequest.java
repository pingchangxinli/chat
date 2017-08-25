package com.lee.chat.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class WxInitRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value="BaseRequest")
	private BaseRequest request;
	public BaseRequest getRequest() {
		return request;
	}

	public void setRequest(BaseRequest request) {
		this.request = request;
	}
}
