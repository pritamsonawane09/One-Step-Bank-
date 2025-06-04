package com.bankmain.ExceptionHandaling;

public class AccountAlradyCreated extends RuntimeException {

	public AccountAlradyCreated(String msg) {

		super(msg);
	}

}
