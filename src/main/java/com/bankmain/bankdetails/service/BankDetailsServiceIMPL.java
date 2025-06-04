package com.bankmain.bankdetails.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankmain.bankdetailsserviceRepo.BankDetailsRepo;
import com.bankmain.userservice.model.BankDetails;

@Service
public class BankDetailsServiceIMPL implements BankDetailsService {

	private static final Logger LOGGER = LogManager.getLogger(BankDetailsServiceIMPL.class);
	
	@Autowired
	private BankDetailsRepo BankDetailsRepo;
	
	@Override
	public void SaveBankDetailsInfo(BankDetails BankDetails) {
		LOGGER.info("In Bank Details Service Start : " + BankDetails);
		BankDetails BankDetails1 = BankDetailsRepo.save(BankDetails);
		if (BankDetails1 != null) {
			LOGGER.debug("Bank Details Successfully Stored");
		}
		LOGGER.info("In Bank Details Service End");
		
	}

}
