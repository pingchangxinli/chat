package com.lee.chat.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class WxBatchContactResponse {

	@JsonProperty(value="BaseResponse")
	private BaseResponse response;
	@JsonProperty(value="Count")
	private Integer count;
	@JsonProperty(value="ContactList")
	private List<Contact> list;
	public BaseResponse getResponse() {
		return response;
	}
	public void setResponse(BaseResponse response) {
		this.response = response;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<Contact> getList() {
		return list;
	}
	public void setList(List<Contact> list) {
		this.list = list;
	}
	
}
