package com.lee.chat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.lee.chat.controller.support.Result;
import com.lee.chat.domain.Content;
import com.lee.chat.service.ContentService;
import com.lee.chat.service.exception.ErrorCode;
import com.lee.chat.service.exception.ServiceException;

@RestController
public class ContentController {

	@Autowired
	private ContentService contentService;
	@Autowired
	private HttpSession session;
	@Autowired
	private QRcodeController qrcodeController;
	@RequestMapping(value="/content")
	public Result saveContent(@RequestParam(value="content")String content,@RequestParam(value="receiver") String[] receivers) {
		Long mobile = (Long) session.getAttribute("mobile");
		if(null == mobile) {
			throw new ServiceException("请重新登录", ErrorCode.UNAUTHORIZED);
		}
		if(StringUtils.isEmpty(content)) {
			throw new ServiceException("发送内容为空", ErrorCode.BAD_REQUEST);
		}
		Content contentObject = contentService.saveContent(mobile,content,receivers);
		if(contentObject != null) {
			String code =  qrcodeController.getQRcode();
			Result result = new Result(HttpStatus.OK.value(),"二维码",code);
			return result;
		}else {
			throw new ServiceException("更新失败", ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/content/getContent")
	public Content getContent() {
		Long mobile = (Long) session.getAttribute("mobile");
		if(null == mobile) {
			throw new ServiceException("请重新登录", ErrorCode.UNAUTHORIZED);
		}
		return contentService.getContentByMobile(mobile);
	}
}
