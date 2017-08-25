package com.lee.chat.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
	@JsonProperty(value="Uin")
	private Integer uin;
	@JsonProperty(value="UserName")
	private String userName;
	@JsonProperty(value="NickName")
	private String nickName;
	@JsonProperty(value="HeadImgUrl")
	private String headImgUrl;
	@JsonProperty(value="ContactFlag")
	private Integer contactFlag;
	@JsonProperty(value="MemberCount")
	private Integer memberCount;
	@JsonProperty(value="MemberList")
	private List memberList;
	@JsonProperty(value="RemarkName")
	private String remarkName;
	@JsonProperty(value="HideInputBarFlag")
	private Integer hideInputBarFlag;
	@JsonProperty(value="Sex")
	private Integer sex;
	@JsonProperty(value="Signature")
	private String signature;
	@JsonProperty(value="VerifyFlag")
	private Integer verifyFlag;
	@JsonProperty(value="OwnerUin")
	private Integer ownerUin;
	@JsonProperty(value="PYInitial")
	private String pYInitial;
	@JsonProperty(value="PYQuanPin")
	private String pYQuanPin;
	@JsonProperty(value="RemarkPYInitial")
	private String remarkPYInitial;
	@JsonProperty(value="RemarkPYQuanPin")
	private String remarkPYQuanPin;
	@JsonProperty(value="StarFriend")
	private Integer starFriend;
	@JsonProperty(value="AppAccountFlag")
	private Integer appAccountFlag;
	@JsonProperty(value="Statues")
	private Integer statues;
	@JsonProperty(value="AttrStatus")
	private Long attrStatus;
	@JsonProperty(value="Province")
	private String province;
	@JsonProperty(value="City")
	private String city;
	@JsonProperty(value="Alias")
	private String alias;
	@JsonProperty(value="SnsFlag")
	private Integer snsFlag;
	@JsonProperty(value="UniFriend")
	private Integer uniFriend;
	@JsonProperty(value="DisplayName")
	private String displayName;
	@JsonProperty(value="ChatRoomId")
	private Integer chatRoomId;
	@JsonProperty(value="KeyWord")
	private String keyWord;
	@JsonProperty(value="EncryChatRoomId")
	private String encryChatRoomId;
	@JsonProperty(value="IsOwner")
	private Integer isOwner;
	public Integer getUin() {
		return uin;
	}
	public void setUin(Integer uin) {
		this.uin = uin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public Integer getContactFlag() {
		return contactFlag;
	}
	public void setContactFlag(Integer contactFlag) {
		this.contactFlag = contactFlag;
	}
	public Integer getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}
	public List getMemberList() {
		return memberList;
	}
	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}
	public String getRemarkName() {
		return remarkName;
	}
	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}
	public Integer getHideInputBarFlag() {
		return hideInputBarFlag;
	}
	public void setHideInputBarFlag(Integer hideInputBarFlag) {
		this.hideInputBarFlag = hideInputBarFlag;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Integer getVerifyFlag() {
		return verifyFlag;
	}
	public void setVerifyFlag(Integer verifyFlag) {
		this.verifyFlag = verifyFlag;
	}
	public Integer getOwnerUin() {
		return ownerUin;
	}
	public void setOwnerUin(Integer ownerUin) {
		this.ownerUin = ownerUin;
	}
	public String getpYInitial() {
		return pYInitial;
	}
	public void setpYInitial(String pYInitial) {
		this.pYInitial = pYInitial;
	}
	public String getpYQuanPin() {
		return pYQuanPin;
	}
	public void setpYQuanPin(String pYQuanPin) {
		this.pYQuanPin = pYQuanPin;
	}
	public String getRemarkPYInitial() {
		return remarkPYInitial;
	}
	public void setRemarkPYInitial(String remarkPYInitial) {
		this.remarkPYInitial = remarkPYInitial;
	}
	public String getRemarkPYQuanPin() {
		return remarkPYQuanPin;
	}
	public void setRemarkPYQuanPin(String remarkPYQuanPin) {
		this.remarkPYQuanPin = remarkPYQuanPin;
	}
	public Integer getStarFriend() {
		return starFriend;
	}
	public void setStarFriend(Integer starFriend) {
		this.starFriend = starFriend;
	}
	public Integer getAppAccountFlag() {
		return appAccountFlag;
	}
	public void setAppAccountFlag(Integer appAccountFlag) {
		this.appAccountFlag = appAccountFlag;
	}
	public Integer getStatues() {
		return statues;
	}
	public void setStatues(Integer statues) {
		this.statues = statues;
	}
	public Long getAttrStatus() {
		return attrStatus;
	}
	public void setAttrStatus(Long attrStatus) {
		this.attrStatus = attrStatus;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getSnsFlag() {
		return snsFlag;
	}
	public void setSnsFlag(Integer snsFlag) {
		this.snsFlag = snsFlag;
	}
	public Integer getUniFriend() {
		return uniFriend;
	}
	public void setUniFriend(Integer uniFriend) {
		this.uniFriend = uniFriend;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Integer getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getEncryChatRoomId() {
		return encryChatRoomId;
	}
	public void setEncryChatRoomId(String encryChatRoomId) {
		this.encryChatRoomId = encryChatRoomId;
	}
	public Integer getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(Integer isOwner) {
		this.isOwner = isOwner;
	}
}
