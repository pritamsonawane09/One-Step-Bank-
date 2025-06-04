package com.bankmain.bankdetails.Controller;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankmain.bankdetails.service.BankDetailsService;
import com.bankmain.userservice.model.BankDetails;

@RestController
@RequestMapping(value = "/api/v1/BankDetails")
public class BankDetailsServiceController {

	private static final Logger LOGGER = LogManager.getLogger(BankDetailsServiceController.class);

	@Autowired
	private BankDetailsService bankDetailsService;

	@RequestMapping(value = "/save")
	public ResponseEntity<String> SaveBankDetailsInfo(@RequestBody BankDetails BankDetails) {

		LOGGER.info("Save Bank Details Info Start : " + BankDetails);
		bankDetailsService.SaveBankDetailsInfo(BankDetails);
		
		return ResponseEntity.ok("Successfully Save Bank Details.....!");

	}

}
