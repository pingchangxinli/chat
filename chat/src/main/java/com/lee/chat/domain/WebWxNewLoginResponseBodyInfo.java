package com.lee.chat.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <error>
 * 	<ret>0</ret>
 * 	<message></message>
 * 	<skey>@crypt_487e40a7_b7d86eeb2bbfa567e1521f9eecd5f90d</skey>
 * 	<wxsid>57pCi5hkJfVpff/V</wxsid><wxuin>345190155</wxuin>
 * 	<pass_ticket>v2WNnb%2BpPSxhPUqqLKa7y%2F%2BeL7JquxCZhY1q0lvear1PHMzuDm%2B69x4Hstl9DWac</pass_ticket>
 * 	<isgrayscale>1</isgrayscale>
 * </error>
 * 
 * @author liht
 * @since 0.1
 */
@XmlRootElement(name="error")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebWxNewLoginResponseBodyInfo {

	private String ret;
	private String message;
	private String skey;
	private String wxuin;
	private String wxsid;
	@XmlElement(name="pass_ticket")
	private String passTicket;
	private String isgrayscale;
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public String getWxsid() {
		return wxsid;
	}
	public void setWxsid(String wxsid) {
		this.wxsid = wxsid;
	}
	public String getPassTicket() {
		return passTicket;
	}
	public void setPassTicket(String passTicket) {
		this.passTicket = passTicket;
	}
	public String getIsgrayscale() {
		return isgrayscale;
	}
	public void setIsgrayscale(String isgrayscale) {
		this.isgrayscale = isgrayscale;
	}
	public String getWxuin() {
		return wxuin;
	}
	public void setWxuin(String wxuin) {
		this.wxuin = wxuin;
	}
	
}
