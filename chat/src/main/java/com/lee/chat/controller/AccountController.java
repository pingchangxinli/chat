package com.lee.chat.controller;

import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lee.chat.controller.support.Result;
import com.lee.chat.domain.Account;
import com.lee.chat.service.AccountService;
import com.lee.chat.service.exception.ErrorCode;
import com.lee.chat.service.exception.ServiceException;
import com.lee.chat.util.StringUtil;

@RestController
@SessionAttributes("mobile")
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private HttpSession session;
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	/**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
    /**
     * 用户登录
     * @param mobile	手机号
     * @param password	密码
     * @return
     */
	@RequestMapping(value="/account/{mobile}",method=RequestMethod.GET)
	public Result login(@PathVariable("mobile") String mobile,@RequestParam("password") String password) {
		logger.info("用户登录准备：mobile："+mobile);
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
			throw new ServiceException("手机号或密码为空", ErrorCode.BAD_REQUEST);
		}
		Account account = accountService.login(Long.valueOf(mobile),password);
		logger.info("用户登录成功，mobile："+mobile);
		if(!password.equals(account.getPassword())) {
			throw new ServiceException("密码不正确，请重新输入", ErrorCode.UNAUTHORIZED);
		}
		session.setAttribute("mobile", account.getMobile());
		Result result = new Result(HttpStatus.OK.value(),"欢迎回来，"+account.getUserName(),null);
		logger.info("用户登录返回，mobile："+mobile);
		return result;
	}
	@RequestMapping(value="/account/acc")
	public String acc(@SessionAttribute("mobile") String mobile) {
		return mobile;
	}
	/**
	 * 用户注册
	 * @param mobile	手机号
	 * @param password	密码
	 * @param userName	昵称
	 * @return
	 * @throws SQLException 
	 */
	@RequestMapping(value="/account")
	public Result register(@RequestParam("mobile") String mobile,String password,String userName) throws SQLException {
		if(!isMobile(mobile)) {
			throw new ServiceException("手机号为空或者格式不正确", ErrorCode.BAD_REQUEST);
		}else if(StringUtils.isEmpty(password)) {
			throw new ServiceException("请输入密码", ErrorCode.BAD_REQUEST);
		}else if(StringUtils.isEmpty(userName)) {
			throw new ServiceException("请输入昵称", ErrorCode.BAD_REQUEST);
		}
		Account account = accountService.register(mobile,password,userName);
		Result result = new Result(HttpStatus.OK.value(),"注册成功",account);
		return result;
	}
	/**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    private static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
  
}
