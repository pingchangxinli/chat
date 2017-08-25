package com.lee.chat.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 网页微信发送信息请求
 * @author liht
 * @since	0.1
 */
@Component
public class WxMsgRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty(value="BaseRequest")
	private BaseRequest baseRequest;
	@JsonProperty(value="Msg")
	private WxMsg msg;
	@JsonProperty(value="Scene")
	private Integer scene;
	public BaseRequest getBaseRequest() {
		return baseRequest;
	}
	public void setBaseRequest(BaseRequest baseRequest) {
		this.baseRequest = baseRequest;
	}
	public WxMsg getMsg() {
		return msg;
	}
	public void setMsg(WxMsg msg) {
		this.msg = msg;
	}
	public Integer getScene() {
		return scene;
	}
	public void setScene(Integer scene) {
		this.scene = scene;
	}
	
}
