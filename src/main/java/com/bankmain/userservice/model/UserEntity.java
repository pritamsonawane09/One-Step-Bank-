package com.bankmain.userservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bankmain.userservice.enumdata.Status;

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
@Table(name = "User_Entity")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uid;

	private String fname;

	private String mname;

	private String lname;

	private String mnumber;

	private long addharNo;

	private String panNo;

	private String email;

	private String uname;

	private String pass;

	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne(cascade = CascadeType.ALL)
	private PermanantAddress permanentAddress;

	@OneToOne(cascade = CascadeType.ALL)
	private LocalAddress localaddress;

	@OneToOne(cascade = CascadeType.ALL)
	private Role role;


}
