package com.bankmain.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankmain.accountservice.sarvice.AccountService;
import com.bankmain.userservice.model.AccountRequest;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {

	@Autowired
	private AccountService AccountService;

	@PostMapping(value = "/creat")
	public ResponseEntity<?> creatAccount(@RequestBody AccountRequest accountRequest) {

		System.out.println("Account Data: " + accountRequest);

		AccountService.creatAccount(accountRequest);

		return ResponseEntity.ok("AccountCreated");
	}
}
