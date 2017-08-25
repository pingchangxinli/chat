package com.lee.chat.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {

	/**
	 * window.QRLogin.code = 200; window.QRLogin.uuid = "IcbmtLHpUQ==";
	 * 上述样式格式转换成map类型
	 * @param message	等号格式的字符串
	 * @param firstSign	
	 * @param secondSign
	 * @return
	 */
	public static Map<String, Object> equalSignStringToMap(String message,String firstSign,String secondSign){
		Map<String, Object> map = new HashMap<String,Object>();
		String[] array = message.split(firstSign);
		for(String mess : array) {
			String key = mess.substring(0, mess.indexOf("=")).trim();
			String value = mess.substring(mess.indexOf("=")+1, mess.length()).trim();
			map.put(key.replaceAll("\"", ""), value.replaceAll("\"", ""));
		}
		return map;
	}
}
