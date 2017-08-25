package com.lee.chat.controller.support;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lee.chat.service.exception.ServiceException;
import com.lee.modules.utils.mapper.JsonMapper;

@ControllerAdvice(annotations = { RestController.class })
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	private JsonMapper jsonMapper = new JsonMapper();


	@ExceptionHandler(value = {ServiceException.class})
	public final ResponseEntity<Result> handleServiceException(ServiceException ex, HttpServletRequest request) {
		// 注入servletRequest，用于出错时打印请求URL与来源地址
		logError(ex, request);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		Result result = new Result(ex.errorCode.code, ex.getMessage());
		return new ResponseEntity<Result>(result, headers, HttpStatus.valueOf(ex.errorCode.code));
	}
	@ExceptionHandler(value = { Exception.class })
	public final String handleGeneralException(Exception ex, HttpServletRequest request) {
		logError(ex, request);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		Result result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		return jsonMapper.toJson(result);
	}
	@ExceptionHandler(value = {SQLException.class})
	public final ResponseEntity<Result> handleServiceException(SQLException ex, HttpServletRequest request) {
		// 注入servletRequest，用于出错时打印请求URL与来源地址
		logError(ex, request);
		logger.debug("status:"+HttpStatus.CONFLICT.value());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		Result result = new Result(HttpStatus.CONFLICT.value(), ex.getMessage());
		return new ResponseEntity<Result>(result, headers, HttpStatus.valueOf(HttpStatus.CONFLICT.value()));
	}
	/**
	 * 重载ResponseEntityExceptionHandler的方法，加入日志
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		logError(ex);

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
		}

		return new ResponseEntity<Object>(body, headers, status);
	}

	public void logError(Exception ex) {
		Map<String, String> map = new HashMap();
		map.put("message", ex.getMessage());
		logger.error(jsonMapper.toJson(map), ex);
	}

	public void logError(Exception ex, HttpServletRequest request) {
		Map<String, String> map = new HashMap();
		map.put("message", ex.getMessage());
		map.put("from", request.getRemoteAddr());
		String queryString = request.getQueryString();
		map.put("path", queryString != null ? (request.getRequestURI() + "?" + queryString) : request.getRequestURI());

		logger.error(jsonMapper.toJson(map), ex);
	}
}
