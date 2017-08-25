package com.lee.chat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MapArticle {
	@JsonProperty(value="Title")
	private String title;
	@JsonProperty(value="Digest")
	private String digest;
	@JsonProperty(value="Cover")
	private String cover;
	@JsonProperty(value="Url")
	private String url;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
