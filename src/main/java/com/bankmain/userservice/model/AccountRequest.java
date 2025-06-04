package com.bankmain.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor
@ToString

public class AccountRequest {

	private String accountnumber;

	private int accounttype;

	private int accstatus;

	private String uname;

	private String pin;

	private String bankname;

	private String bankcode;

	private double amount;


}
