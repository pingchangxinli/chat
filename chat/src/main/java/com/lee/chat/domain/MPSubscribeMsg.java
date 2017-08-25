package com.lee.chat.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author liht
 * @since	0.1	
 */
public class MPSubscribeMsg {
	@JsonProperty(value="UserName")
	private String userName;
	@JsonProperty(value="MPArticleCount")
	private Integer mPArticleCount;
	@JsonProperty(value="MPArticleList")
	private List<MapArticle> mPArticleList;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getmPArticleCount() {
		return mPArticleCount;
	}
	public void setmPArticleCount(Integer mPArticleCount) {
		this.mPArticleCount = mPArticleCount;
	}
	public List<MapArticle> getmPArticleList() {
		return mPArticleList;
	}
	public void setmPArticleList(List<MapArticle> mPArticleList) {
		this.mPArticleList = mPArticleList;
	}
	@JsonProperty(value="Time")
	private Integer time;
	@JsonProperty(value="NickName")
	private String nickName;
}
