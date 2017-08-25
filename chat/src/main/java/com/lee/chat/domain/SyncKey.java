package com.lee.chat.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SyncKey {
	@JsonProperty(value="Count")
	private Integer count;
	@JsonProperty(value="SyncKey")
	private List<SyncKeyKV> list;
	@JsonProperty(value="User")
	private Contact user;
	@JsonProperty(value="ChatSet")
	private String chatSet;
	@JsonProperty(value="SKey")
	private String skey;
	@JsonProperty(value="ClientVersion")
	private String clientVersion;
	@JsonProperty(value="SystemTime")
	private String systemTime;
	@JsonProperty(value="GrayScale")
	private Integer grayScale;
	@JsonProperty(value="InviteStartCount")
	private Integer inviteStartCount;
	@JsonProperty(value="MPSubscribeMsgCount")
	private Integer mPSubscribeMsgCount;
	@JsonProperty(value="MPSubscribeMsgList")
	private List<MPSubscribeMsg> mPSubscribeMsgList;
	@JsonProperty(value="Time")
	private Integer time;
	@JsonProperty(value="NickName")
	private String nickName;
	
}
