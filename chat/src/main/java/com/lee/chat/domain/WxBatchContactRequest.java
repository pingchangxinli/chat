package com.lee.chat.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class WxBatchContactRequest {

	@JsonProperty(value="BaseRequest")
	private BaseRequest baseRequest;
	@JsonProperty(value="Count")
	private Integer count;
	@JsonProperty(value="List")
	private List<WxBatchContact> list;
	public BaseRequest getBaseRequest() {
		return baseRequest;
	}
	public void setBaseRequest(BaseRequest baseRequest) {
		this.baseRequest = baseRequest;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<WxBatchContact> getList() {
		return list;
	}
	public void setList(List<WxBatchContact> list) {
		this.list = list;
	}
}
