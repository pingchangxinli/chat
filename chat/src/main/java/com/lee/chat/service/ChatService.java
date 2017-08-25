package com.lee.chat.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.hibernate.annotations.NaturalId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.lee.chat.util.StringUtil;
import com.lee.modules.utils.mapper.JsonMapper;
import com.lee.modules.utils.mapper.XmlMapper;
import com.lee.util.http.HttpClientUtil;
import com.lee.util.http.domain.Response;
import com.lee.chat.domain.Account;
import com.lee.chat.domain.BaseRequest;
import com.lee.chat.domain.Contact;
import com.lee.chat.domain.Content;
import com.lee.chat.domain.WebWxNewLoginResponseBodyInfo;
import com.lee.chat.domain.WxBatchContact;
import com.lee.chat.domain.WxBatchContactRequest;
import com.lee.chat.domain.WxBatchContactResponse;
import com.lee.chat.domain.WxContactResponse;
import com.lee.chat.domain.WxInitResponse;
import com.lee.chat.domain.WxMsg;
import com.lee.chat.domain.WxMsgRequest;
import com.lee.chat.domain.WxMsgResponse;
import com.lee.chat.domain.WxStatusNotifyRequest;
import com.lee.chat.domain.WxInitRequest;

@Service
public class ChatService {

	@Autowired
	private WxBatchContactRequest batchRequest;
	@Autowired
	private WxInitRequest wxRequest;
	@Autowired
	private BaseRequest baseRequest;
	@Autowired
	private WxMsg msg;
	@Autowired
	private WxMsgRequest msgRequest;
	@Autowired
	private WxStatusNotifyRequest notifyRequest;
	@Autowired
	private ContentService contentService;
	private static Logger logger = LoggerFactory.getLogger(ChatService.class);

	/**
	 * 获取网页微信登录的二维码参数
	 * 
	 * @param uri
	 *            从微信后台获取二维码参数链接
	 * @param keyUri
	 *            证书路径
	 * @return
	 * @throws KeyManagementException
	 * @throws ClientProtocolException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 * @throws IOException
	 */
	public String getQRcode(String uri, String keyUri) throws KeyManagementException, ClientProtocolException,
			NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		// TODO Auto-generated method stub
		return HttpClientUtil.doGet(uri, keyUri);
	}

	/**
	 * 得到uuid，发起login链接登录，并设置该业务的总时间
	 * 
	 * @param waitLoginUri
	 *            请求uri
	 * @param uuid
	 *            微信需要的uuid
	 * @param keyUri
	 *            秘钥路径
	 * @param waitLoginCode
	 *            链接返回的状态代码
	 * @param waitTimes
	 *            该业务的总时间
	 * @param webWxInitUri
	 *            获取微信通讯录链接
	 * @param sendMsgUri
	 *            发送微信信息链接
	 * @param getContactUri
	 * 			 获取当前登录微信账户的所有微信朋友
	 * @return
	 * @throws KeyManagementException
	 * @throws ClientProtocolException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 * @throws IOException
	 */
	@Async
	public String waitForLoginInformation(String waitLoginUri, String uuid, String keyUri, String waitLoginCode,
			Long waitTimes, String webWxInitUri,String statusNotifyUri, String sendMsgUri,String getContactUri,String batchGetContactUri,Long mobile) throws KeyManagementException,
			ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> msgParamsMap = new HashMap<String, String>();
		Map<String, String> requestMap = new HashMap<String,String>();
		requestMap.put("uuid", uuid);
		requestMap.put("r", "1824801562");
		requestMap.put("_",String.valueOf(new Date().getTime()));
		int i = 0;
		long startTime = new Date().getTime();// 发起请求时间
		long seconds = 0l;
		while (!"200".equals(map.get(waitLoginCode)) && waitTimes > seconds) {
			String result = HttpClientUtil.doGet(waitLoginUri,requestMap, keyUri);
			// map = StringUtil.equalSignStringToMap(result, ";", "=");
			map.put("window.code", result.substring(result.indexOf("=") + 1, result.indexOf("=") + 4));
			if (null != map && "200".equals(map.get("window.code"))) {
				map = StringUtil.equalSignStringToMap(result, ";", "=");
			}
			long endTime = new Date().getTime();
			seconds = (endTime - startTime) / 1000;
			logger.debug("result:" + result + "*****count:" + i++);

		}
		Response response = webWxNewLoginPage(map, keyUri);
		List<HeaderElement> elementList = response.getHeaderElements();
		String xml = response.getBody();
		WebWxNewLoginResponseBodyInfo info = XmlMapper.fromXml(xml, WebWxNewLoginResponseBodyInfo.class);
		logger.debug("*****************" + info.getPassTicket());
		JsonMapper mapper = new JsonMapper();
		String jsonString = mapper.toJson(info);
		logger.debug("*****************" + jsonString);
		WxInitResponse wxInitResponse = webWxInit(info, keyUri, webWxInitUri);
		String notifyResponse = webWxStatusNotify(info,wxInitResponse,keyUri,statusNotifyUri);
		logger.debug("notifyResponse:"+notifyResponse);
		msgParamsMap.put("pass_ticket", info.getPassTicket());
		List<Contact> list = webWxGetContact(info, getContactUri, keyUri,elementList);
		Content content = contentService.getContentByMobile(mobile);
		webWxSendMsgToAllContacts(info,wxInitResponse, list, sendMsgUri, keyUri, msgParamsMap,content);
		if(content.getReceiver().indexOf("0") >= 0) {
			List<Contact> batchList = webWxBatchGetContact(info, batchGetContactUri, keyUri, elementList,wxInitResponse);
			Iterator<Contact> iterator = batchList.iterator();
			while(iterator.hasNext()) {
				Contact contact = iterator.next();
				if(contact.getUserName().indexOf("@@") < 0 ) {
					iterator.remove();
				}
			}
			webWxSendMsgToAllGroup(info,wxInitResponse, batchList, sendMsgUri, keyUri, msgParamsMap, content);
		}
		
		return webWxNewLoginPage(map, keyUri).getBody();
	}

	private String webWxStatusNotify(WebWxNewLoginResponseBodyInfo info,WxInitResponse init, String keyUri, String statusNotifyUri) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String,String>();
		
		baseRequest.setUin(info.getWxuin());
		baseRequest.setSid(info.getWxsid());
		baseRequest.setSkey(info.getSkey());
		baseRequest.setDeviceID("e011336683640830");
		notifyRequest.setClientMsgId(new Date().getTime());
		notifyRequest.setCode(3);
		notifyRequest.setFromUserName(init.getUser().getUserName());
		notifyRequest.setToUserName(init.getUser().getUserName());
		notifyRequest.setRequest(baseRequest);
		params.put("lang", "zh_CN");
		params.put("pass_ticket", info.getPassTicket());
		
		
		return HttpClientUtil.doPost(statusNotifyUri, keyUri, params, JsonMapper.INSTANCE.toJson(notifyRequest), null);
	}

	private Response webWxNewLoginPage(Map<String, Object> map, String keyUri) throws KeyManagementException,
			ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		Response response = null;
		String code = (String) map.get("window.code");
		String redirectUri = (String) map.get("window.redirect_uri");
		redirectUri = redirectUri + "&fun=new&version=v2&lang=zh_CN";
		if ("200".equals(code)) {
			response = HttpClientUtil.doGetReturnResponse(redirectUri,null, keyUri,null);
		}
		logger.debug("webWxNewPageLoginPage:" + response);
		for(HeaderElement element: response.getHeaderElements()) {
			logger.debug("element>>> key"+element.getName()+"----value:"+element.getValue());
		}
		return response;
	}

	/**
	 * 读取用户信息
	 * 
	 * @param info
	 * @param keyUri
	 * @param webWxInitUri
	 * @return
	 * @throws KeyManagementException
	 * @throws ClientProtocolException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 * @throws IOException
	 */
	private WxInitResponse webWxInit(WebWxNewLoginResponseBodyInfo info, String keyUri, String webWxInitUri)
			throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException,
			CertificateException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		baseRequest.setUin(info.getWxuin());
		baseRequest.setSid(info.getWxsid());
		baseRequest.setSkey(info.getSkey());
		baseRequest.setDeviceID("e011336683640830");
		wxRequest.setRequest(baseRequest);
		String json = JsonMapper.INSTANCE.toJson(wxRequest);
		logger.debug(json);
		params.put("r", String.valueOf(new Date().getTime()));
		params.put("lang", "zh_CN");
		params.put("pass_ticket", info.getPassTicket());
		String result = HttpClientUtil.doPost(webWxInitUri, keyUri, params, json, null);
		//logger.debug("webwxinit:" + result);
		WxInitResponse response = JsonMapper.INSTANCE.fromJson(result, WxInitResponse.class);
		logger.debug(response.toString());
		return response;
	}

	private List<Contact> webWxGetContact(WebWxNewLoginResponseBodyInfo info,String getContactUri,String keyUri,List<HeaderElement> elements) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException{
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> headers = new HashMap<String,String>();
		StringBuffer cookieBuffer = new StringBuffer();
		params.put("lang", "zh_CN");
		params.put("pass_ticket", info.getPassTicket());
		params.put("r", String.valueOf(new Date().getTime()));
		params.put("seq", "0");
		params.put("skey", info.getSkey());
		for(HeaderElement element : elements) {
			cookieBuffer.append(element.getName()).append("=").append(element.getValue()).append(";");
		}
			headers.put("Cookie", cookieBuffer.toString());
		String response = HttpClientUtil.doGet(getContactUri, params, keyUri,headers);
		logger.debug("getContact:"+response);
		WxContactResponse result = JsonMapper.INSTANCE.fromJson(response, WxContactResponse.class);
		return result.getContactList();
	}
	private List<Contact> webWxBatchGetContact(WebWxNewLoginResponseBodyInfo info,String batchGetContactUri,String keyUri,List<HeaderElement> elements,WxInitResponse initResponse) throws KeyManagementException, ClientProtocolException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException{
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> headers = new HashMap<String,String>();
		StringBuffer cookieBuffer = new StringBuffer();
		params.put("lang", "zh_CN");
		params.put("pass_ticket", info.getPassTicket());
		params.put("r", String.valueOf(new Date().getTime()));
		params.put("type","ex");
		for(HeaderElement element : elements) {
			cookieBuffer.append(element.getName()).append("=").append(element.getValue()).append(";");
		}
		headers.put("Cookie", cookieBuffer.toString());

		baseRequest.setUin(info.getWxuin());
		baseRequest.setSid(info.getWxsid());
		baseRequest.setSkey(info.getSkey());
		baseRequest.setDeviceID("e011336683640830");
		
		batchRequest.setBaseRequest(baseRequest);
		List<WxBatchContact> list = new ArrayList();
		for (String userName : initResponse.getChatSet().split(",")) {
			if(userName.indexOf("@@") >= 0) {
				WxBatchContact contact = new WxBatchContact();
				contact.setChatRoomId("");
				contact.setUserName(userName);
				list.add(contact);
			}
			
		}
		batchRequest.setList(list);
		batchRequest.setCount(list.size());
		String response = HttpClientUtil.doPost(batchGetContactUri, keyUri, params, JsonMapper.INSTANCE.toJson(batchRequest), headers);
		WxBatchContactResponse result = JsonMapper.INSTANCE.fromJson(response, WxBatchContactResponse.class);
		logger.debug("webWxBatchGetContact response:"+response);
		return result.getList();
	}
	private String webWxSendMsg(WebWxNewLoginResponseBodyInfo info, WxInitResponse init,Contact contact, String sendMsgUri, String keyUri,
			Map<String, String> params,String content) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			CertificateException, IOException {
		String id = String.valueOf(new Date().getTime());

		baseRequest.setUin(info.getWxuin());
		baseRequest.setSid(info.getWxsid());
		baseRequest.setSkey(info.getSkey());
		baseRequest.setDeviceID("e011336683640830");

		msg.setClientMsgId(id);
		msg.setContent(content);
		msg.setFromUserName(init.getUser().getUserName());
		msg.setLocalID(id);
		msg.setToUserName(contact.getUserName());
		msg.setType("1");

		msgRequest.setBaseRequest(baseRequest);
		msgRequest.setMsg(msg);
		msgRequest.setScene(0);

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=UTF-8");
		logger.debug(JsonMapper.INSTANCE.toJson(msgRequest));
		String result = HttpClientUtil.doPost(sendMsgUri, keyUri, params, JsonMapper.INSTANCE.toJson(msgRequest),
				headers);
		logger.debug("webWxSendMsg:" + result);
		return result;
	}

	private String webWxSendMsgToAllContacts(WebWxNewLoginResponseBodyInfo info, WxInitResponse init,List<Contact> list,
			String sendMsgUri, String keyUri, Map<String, String> params,Content content) throws KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		String receiver = content.getReceiver();
		for (Contact contact : list) {
			if(receiver.indexOf(String.valueOf(contact.getSex())) > -1 && contact.getSex() != 0) {
				webWxSendMsg(info,init, contact, sendMsgUri, keyUri, params,content.getContent());
			}
			
		}
		return null;
	}
	private String webWxSendMsgToAllGroup(WebWxNewLoginResponseBodyInfo info, WxInitResponse init,List<Contact> list,
			String sendMsgUri, String keyUri, Map<String, String> params,Content content) throws KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
		for (Contact contact : list) {
			logger.debug("group contact:"+ contact.getUserName());
			if(contact.getUserName().indexOf("@@") >= 0) {
				webWxSendMsg(info,init, contact, sendMsgUri, keyUri, params,content.getContent());
			}
			
		}
		return null;
	}
}
