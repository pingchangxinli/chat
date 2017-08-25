package com.lee.chat.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lee.chat.domain.Account;
import com.lee.chat.repository.AccountRepository;
import com.lee.chat.service.exception.ErrorCode;
import com.lee.chat.service.exception.ServiceException;

@Service
public class AccountService {

	private static Logger logger = LoggerFactory.getLogger(ChatService.class);
	@Autowired
	private AccountRepository accountRepository;
	
	public  Account register(String mobile, String password, String userName) throws SQLException {
		// TODO Auto-generated method stub
		Long id = Long.valueOf(mobile);
		Account account = new Account();
		account.setPassword(password);
		account.setUserName(userName);
		account.setMobile(id);
		if(accountRepository.exists(id)){
			throw new SQLException("用户手机号："+id+"已注册！");
		}else {
			return accountRepository.save(account);
		}
		
	}

	public Account login(Long mobile, String password) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findOne(mobile);
		if(account == null) {
			throw new ServiceException("用户不存在", ErrorCode.UNAUTHORIZED);
		}
		return account;
	}

	public Account getAcountByMobile(Long mobile) {
		// TODO Auto-generated method stub
		if(mobile == null) {
			throw new ServiceException("请重新登录", ErrorCode.UNAUTHORIZED);
		}
		return accountRepository.findOne(mobile);
	}

}
