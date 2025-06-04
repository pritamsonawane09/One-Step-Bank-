package com.bankmain.ATMCard;

import org.springframework.http.ResponseEntity;

public interface ATMDetailService {

	
	ResponseEntity<String> atmRequest(String email);
}
