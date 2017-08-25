package com.lee.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lee.chat.domain.Content;
import com.lee.chat.repository.ContentRepository;
import com.lee.chat.service.exception.ErrorCode;
import com.lee.chat.service.exception.ServiceException;

@Service
public class ContentService {

	@Autowired
	private ContentRepository contentRepository;
	
	public Content saveContent(Long mobile, String content,String[] receivers) {
		// TODO Auto-generated method stub
		Content con = new Content();
		con.setAccountId(mobile);
		con.setContent(content);
		StringBuffer r = new StringBuffer();
		for(int i=0;i<receivers.length;i++) {
			r.append(receivers[i]);
			if(i != receivers.length -1) {
				r.append(",");
			}
		}
		con.setReceiver(r.toString());
		return contentRepository.save(con);
	}

	public Content getContentByMobile(Long mobile) {
		// TODO Auto-generated method stub
		if(mobile == null) {
			throw new ServiceException("请重新登录", ErrorCode.UNAUTHORIZED);
		}
		return contentRepository.findOne(mobile);
	}

}
