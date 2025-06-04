package com.bankmain.ATMCard;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bankmain.UserEntityRepository.UserEntityRepository;
import com.bankmain.bankdetailsserviceRepo.ATMDetailsRepository;
import com.bankmain.userservice.model.UserEntity;

@Service
public class ATMDetailServiceIMPL implements ATMDetailService {

	@Autowired
	private UserEntityRepository UserEntityRepository;

	@Autowired
	private ATMDetailsRepository ATMDetailsRepository;
	
	@Autowired
	JavaMailSender JavaMailSender;

	@Override
	public ResponseEntity<String> atmRequest(String email) {

		UserEntity userentity = UserEntityRepository.findByEmail(email);

		AtmDetails atmdetails = ATMDetailsRepository.findByUserUname(userentity.getUname());

		if (userentity != null) {
			if (atmdetails == null) {
				AtmDetails atm = new AtmDetails();
				int prefix = 52;
				int prifix1 = 0;
				atm.setAnumber(generateRandom(prefix));
				atm.setPin(generatePin(prifix1));
				atm.setCvv(generateCvv(prifix1));
				LocalDate td = LocalDate.now();
				atm.setSdate(td.plusDays(10));
				atm.setEdate(td.plusYears(5));
				atm.setAtmholdername(userentity.getFname());
				atm.setUser(userentity);
				ATMDetailsRepository.save(atm);
				
				SimpleMailMessage simplemailmsg = new SimpleMailMessage();
				simplemailmsg.setTo(userentity.getEmail());
				simplemailmsg.setText("Hello, " + userentity.getUname());
				simplemailmsg.setSubject("Hello, " + userentity.getUname() + "Your ATM Request Successfully Submitted You Will Get Your ATM Card On Your Permanant Address Within 10 Days");
				
				JavaMailSender.send(simplemailmsg);
				
				return ResponseEntity.ok(
						"Your ATM Request Successfully Submitted You Will Get Your ATM Card On Your Permanant Address Within 10 Days");

			} else {
				return ResponseEntity.ok("You Have Already applied for Atm Card");
			}
		}

		return new ResponseEntity<String>("Invalid Email !!", HttpStatus.NOT_FOUND);
	}

	public static long generateRandom(int prefix) {
		Random rand = new Random();

		long x = (long) (rand.nextDouble() * 100000000000000L);

		String s = String.valueOf(prefix) + String.format("%014d", x);
		return Long.valueOf(s);
	}

	public static String generatePin(int prifix) {
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		return id;

	}

	public static String generateCvv(int prefix) {
		Random random = new Random();
		String id1 = String.format("%03d", random.nextInt(900) + 100);
		return id1;
	}

}