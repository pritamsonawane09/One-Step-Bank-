package com.bankmain.accountTransaction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.bankmain.userservice.model.AccountDetails;

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
public class AccountTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tId;

	private String senderName;

	private String senderAccountNo;

	private String receivername;

	private String receiveraccountno;

	private String tDate;

	private double amount;

	@OneToOne(cascade = CascadeType.DETACH)
	private AccountDetails accountDetails;

	public long gettId() {
		return tId;
	}

	public void settId(long tId) {
		this.tId = tId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAccountNo() {
		return senderAccountNo;
	}

	public void setSenderAccountNo(String senderAccountNo) {
		this.senderAccountNo = senderAccountNo;
	}

	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

	public String getReceiveraccountno() {
		return receiveraccountno;
	}

	public void setReceiveraccountno(String receiveraccountno) {
		this.receiveraccountno = receiveraccountno;
	}

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	@Override
	public String toString() {
		return "AccountTransaction [tId=" + tId + ", senderName=" + senderName + ", senderAccountNo=" + senderAccountNo
				+ ", receivername=" + receivername + ", receiveraccountno=" + receiveraccountno + ", tDate=" + tDate
				+ ", amount=" + amount + ", accountDetails=" + accountDetails + "]";
	}

}
