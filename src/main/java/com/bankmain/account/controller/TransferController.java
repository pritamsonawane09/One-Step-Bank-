package com.bankmain.account.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankmain.PDFservice.TransactionPdfInter;
import com.bankmain.fundTransferService.FundTransferService;
import com.lowagie.text.DocumentException;

@RestController
@RequestMapping("/v1/api/Transaction")
public class TransferController {

	@Autowired
	private FundTransferService FundTransferService;
	
	@Autowired
	private TransactionPdfInter transactionPdf;

	@PostMapping(value = "/bankTobank")
	public ResponseEntity<String> transactionBankToBank(@RequestParam String accountNumber, @RequestParam double amount,
			@RequestParam String pin) {

		ResponseEntity<String> reponse = FundTransferService.bankToBankTransfer(accountNumber, amount, pin);
		return reponse;
	}

	@GetMapping(value = "/credit")
	public ResponseEntity<String> addAmount(@RequestParam String accountNumber, @RequestParam double amount) {
		ResponseEntity<String> response = FundTransferService.credit(accountNumber, amount);
		return response;
	}

	@GetMapping(value = "/debit")
	public ResponseEntity<String> debitAmount(@RequestParam String accountNumber, @RequestParam double amount) {
		ResponseEntity<String> response = FundTransferService.withDrow(accountNumber, amount);
		return response;
	}
	
	@GetMapping(value = "writeInPdf")
	public ResponseEntity<String> WriteInPdf(HttpServletResponse response) throws DocumentException, IOException {
		ResponseEntity<String> response1 = transactionPdf.writeInPdf(response);
		return response1;
	}
	
	@GetMapping(value = "DateToDate")
	public void Statement(HttpServletResponse response,@RequestParam String accountno,@RequestParam String tDate1, @RequestParam String tDate2) throws DocumentException, IOException {
		FundTransferService.generateStatement(response, accountno, tDate1, tDate2);
		
	}
}
