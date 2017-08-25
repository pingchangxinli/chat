package com.lee.chat.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;


@Component
public class WxContactResponse {

	@JsonProperty(value="BaseResponse")
	private BaseResponse response;
	@JsonProperty(value="MemberCount")
	private Integer memberCount;
	@JsonProperty(value="MemberList")
	private List<Contact> contactList;
	public BaseResponse getResponse() {
		return response;
	}
	public void setResponse(BaseResponse response) {
		this.response = response;
	}
	public Integer getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}
	public List<Contact> getContactList() {
		return contactList;
	}
	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}
	
}
