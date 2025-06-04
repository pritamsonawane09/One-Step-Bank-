package com.bankmain.userservice.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class ExceptionResponse {

	private String errormsg;

	

}
