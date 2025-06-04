package com.bankmain.ATMCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/atm")
public class ATMController {

	@Autowired
	private ATMDetailService ATMDetailService;

	@PostMapping(value = "/request")
	public ResponseEntity<String> applyForAtm(@RequestParam String email) {
		ResponseEntity<String> response = ATMDetailService.atmRequest(email);
		return response;

	}

}
