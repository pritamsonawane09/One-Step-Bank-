package com.bankmain.account.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankmain.UserEntityService.UserEntityServiceIMPL;
import com.bankmain.userservice.model.LoginResponses;
import com.bankmain.utility.JwtUtility;

@RestController
@RequestMapping(value = "/api/v1")
public class LoginController {

	@Autowired
	private UserEntityServiceIMPL userEntityServiceIMPL;

	@Autowired
	private JwtUtility jwtUtility;

	@PostMapping(value = "/login")
	public ResponseEntity<?> getLogin(@RequestParam String uname, @RequestParam String pass) {
		System.out.println("Login Credentials : " + uname + "  " + pass);
		UserDetails userDetails = userEntityServiceIMPL.loadUserByUsername(uname);
		System.out.println(userDetails);
		LoginResponses loginResponse = new LoginResponses();

		if (userDetails != null) {
			String token = jwtUtility.generateToken(userDetails);
			loginResponse.setToken(token);
			loginResponse.setUname(userDetails.getUsername());
			loginResponse.setPass(userDetails.getPassword());
			Collection<? extends GrantedAuthority> list = userDetails.getAuthorities();
			for (GrantedAuthority l : list) {
				System.out.println(l);
			}
			loginResponse.setAuthorities(userDetails.getAuthorities());
		}

		return ResponseEntity.ok(loginResponse);
	}

}