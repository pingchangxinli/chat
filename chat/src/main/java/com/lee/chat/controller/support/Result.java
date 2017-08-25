package com.lee.chat.controller.support;

public class Result {

	public int code;
	public String message;
	public Object data;
	
	public Result() {
	}

	public Result(int code,String message) {
		super();
		this.code = code ;
		this.message = message;
	}
	public Result(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
}
