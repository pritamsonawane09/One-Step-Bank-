package com.bankmain.ATMCard;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.bankmain.userservice.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AtmDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long aid;

	private String atmholdername;

	private long anumber;

	private String pin;

	private String cvv;

	private LocalDate sdate;

	private LocalDate edate;

	@OneToOne(cascade = CascadeType.ALL)
	private UserEntity user;

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public String getAtmholdername() {
		return atmholdername;
	}

	public void setAtmholdername(String atmholdername) {
		this.atmholdername = atmholdername;
	}

	public long getAnumber() {
		return anumber;
	}

	public void setAnumber(long anumber) {
		this.anumber = anumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public LocalDate getSdate() {
		return sdate;
	}

	public void setSdate(LocalDate sdate) {
		this.sdate = sdate;
	}

	public LocalDate getEdate() {
		return edate;
	}

	public void setEdate(LocalDate edate) {
		this.edate = edate;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AtmDetails [aid=" + aid + ", atmholdername=" + atmholdername + ", anumber=" + anumber + ", pin=" + pin
				+ ", cvv=" + cvv + ", sdate=" + sdate + ", edate=" + edate + ", user=" + user + "]";
	}

}
