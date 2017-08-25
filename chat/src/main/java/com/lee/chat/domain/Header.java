package com.lee.chat.domain;

import java.util.List;
import java.util.Map;

public class Header {

	private List<Map<String, Object>> cookieList;

	public List<Map<String, Object>> getCookieList() {
		return cookieList;
	}

	public void setCookieList(List<Map<String, Object>> cookieList) {
		this.cookieList = cookieList;
	} 
	
}
