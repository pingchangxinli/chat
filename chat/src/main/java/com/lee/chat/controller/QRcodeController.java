package com.lee.chat.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lee.chat.service.ChatService;
import com.lee.chat.util.StringUtil;
import com.lee.util.http.HttpClientUtil;

/**
 * 
 * @author liht
 * @since	0.1
 */
@RestController
@SessionAttributes("receiver")
public class QRcodeController {

	public static Logger logger = LoggerFactory.getLogger(QRcodeController.class);
	@Value("${chat.appid}")
	private String appid;
	@Value("${chat.redirect_uri}") 
	private String redirectUri;
	@Value("${chat.uri}")
	private String uri;
	@Value("${chat.key_uri}")
	private String keyUri;
	@Value("${chat.redirect_uri.code}")
	private String redirectUriCode;
	@Value("${chat.redirect_uri.uuid}")
	private String redirectUriUuid;
	@Value("${chat.wait_login_uri}")
	private String waitLoginUri;
	@Value("${chat.wait_login_code}")
	private String waiteLoginCode;
	@Value("${chat.wait_times}")
	private Long waitTimes;
	@Value("${chat.web_wx_init_uri}")
	private String webWxInitUri;
	@Value("${chat.web_wx_send_msg_uri}")
	private String sendMsgUri;
	@Value("${chat.web_wx_get_contact_uri}")
	private String getContactUri;
	@Value("${chat.web_wx_batch_get_contact_uri}")
	private String batchGetContactUri;
	@Value("${chat.web_wx_status_notify_uri}")
	private String statusNotifyUri;
	@Autowired
	private ChatService service;
	@Autowired
	private HttpSession session;
	@RequestMapping(value="/qrcode")
	public String getQRcode() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(uri).append("?appid=").append(appid).append("&redirect_uri=")
		.append(redirectUri).append("&fun=new&lang=zh_CN").append("&_=").append(new Date().getTime());
		try {
			//window.QRLogin.code = 200; window.QRLogin.uuid = "IcbmtLHpUQ==";
			String result =  service.getQRcode(buffer.toString(), keyUri);
//			service.waitForLogin(waitLoginUri,(String)map.get(redirectUriUuid),keyUri,waiteLoginCode,waitTimes,webWxInitUri,sendMsgUri);
			Map<String, Object> map = StringUtil.equalSignStringToMap(result, ";", "=");
			if("200".equals(map.get(redirectUriCode))) {
				Long mobile  = (Long) session.getAttribute("mobile");
				service.waitForLoginInformation(waitLoginUri,(String)map.get(redirectUriUuid),keyUri,waiteLoginCode,waitTimes,webWxInitUri,statusNotifyUri,sendMsgUri,getContactUri,batchGetContactUri,mobile);
			}
			return (String)map.get(redirectUriUuid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
