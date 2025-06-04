package com.bankmain.ExceptionHandaling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bankmain.userservice.model.ExceptionResponse;

@ControllerAdvice
public class ExceptionHandelerController {

	@ExceptionHandler
	public @ResponseBody ExceptionResponse exceptionHandle(AccountAlradyCreated accountAlradyCreated) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();

		exceptionResponse.setErrormsg(accountAlradyCreated.getMessage());
		return exceptionResponse;
	}
}
