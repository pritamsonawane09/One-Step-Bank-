package com.bankmain.userservice.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class LoginResponses {

	private String email;

	private String rolename;

	private String uname;

	private String pass;

	private String token;

	private Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

	private String status;

	

}
