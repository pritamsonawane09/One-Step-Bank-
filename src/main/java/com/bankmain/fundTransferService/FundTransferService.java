package com.bankmain.fundTransferService;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.lowagie.text.DocumentException;

public interface FundTransferService {

	ResponseEntity<String> bankToBankTransfer(String accountNumber, double amount, String pin);

	ResponseEntity<String> credit(String accountNumber, double amount);

	ResponseEntity<String> withDrow(String accountNumber, double amount);

	void generateStatement(HttpServletResponse response, String accoountNo, String date1, String date2)
			throws DocumentException, IOException;

}