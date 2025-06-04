package com.bankmain.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity

public class PermanantAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pid;

	private String textAddar;

	private String landmark;

	private String city;

	private String dist;

	private int zipcode;

	
}
