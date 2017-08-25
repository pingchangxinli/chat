package com.lee.chat.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author liht
 * @since	0.1	
 */
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY)
public class WxInitResponse {
	
	@JsonProperty(value="BaseResponse")
	private BaseResponse baseResponse;
	
	@JsonProperty(value="ChatSet")
	private String chatSet;
	@JsonProperty(value="ClickReportInterval")
	private Integer clickReportInterval;
	@JsonProperty(value="ClientVersion")
	private Integer clientVersion;
	
	
	
	@JsonProperty(value="ContactList")
	private List<Contact> contactList;
	
	@JsonProperty(value="Count")
	private Integer count;
	@JsonProperty(value="GrayScale")
	private Integer grayScale;
	@JsonProperty(value="InviteStartCount")
	private Integer inviteStartCount;
	@JsonProperty(value="MPSubscribeMsgCount")
	private Integer mPSubscribeMsgCount;
	@JsonProperty(value="MPSubscribeMsgList")
	private List mPSubscribeMsgList;
	@JsonProperty(value="SKey")
	private String sKey;
	@JsonProperty(value="SyncKey")
	private SyncKey syncKey;
	@JsonProperty(value="SystemTime")
	private Integer systemTime;
	@JsonProperty(value="User")
	private Contact user;
	public BaseResponse getBaseResponse() {
		return baseResponse;
	}

	public void setBaseResponse(BaseResponse baseResponse) {
		this.baseResponse = baseResponse;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	public SyncKey getSyncKey() {
		return syncKey;
	}

	public void setSyncKey(SyncKey syncKey) {
		this.syncKey = syncKey;
	}

	public Integer getClickReportInterval() {
		return clickReportInterval;
	}

	public void setClickReportInterval(Integer clickReportInterval) {
		this.clickReportInterval = clickReportInterval;
	}

	public String getChatSet() {
		return chatSet;
	}

	public void setChatSet(String chatSet) {
		this.chatSet = chatSet;
	}

	public Integer getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(Integer clientVersion) {
		this.clientVersion = clientVersion;
	}

	public Integer getGrayScale() {
		return grayScale;
	}

	public void setGrayScale(Integer grayScale) {
		this.grayScale = grayScale;
	}

	public Integer getInviteStartCount() {
		return inviteStartCount;
	}

	public void setInviteStartCount(Integer inviteStartCount) {
		this.inviteStartCount = inviteStartCount;
	}

	public Integer getmPSubscribeMsgCount() {
		return mPSubscribeMsgCount;
	}

	public void setmPSubscribeMsgCount(Integer mPSubscribeMsgCount) {
		this.mPSubscribeMsgCount = mPSubscribeMsgCount;
	}

	public List getmPSubscribeMsgList() {
		return mPSubscribeMsgList;
	}

	public void setmPSubscribeMsgList(List mPSubscribeMsgList) {
		this.mPSubscribeMsgList = mPSubscribeMsgList;
	}

	public String getsKey() {
		return sKey;
	}

	public void setsKey(String sKey) {
		this.sKey = sKey;
	}

	public Integer getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Integer systemTime) {
		this.systemTime = systemTime;
	}

	public Contact getUser() {
		return user;
	}

	public void setUser(Contact user) {
		this.user = user;
	}
	
	
}
