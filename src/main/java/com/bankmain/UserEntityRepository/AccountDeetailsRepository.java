package com.bankmain.UserEntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankmain.userservice.enumdata.AccountType;
import com.bankmain.userservice.model.AccountDetails;

@Repository
public interface AccountDeetailsRepository extends JpaRepository <AccountDetails, Long> {

	
	AccountDetails findByAccountTypeAndUserUname(AccountType accountType, String uname);
	
	AccountDetails findByAccountNumber(String AccountNumber);
	
	AccountDetails findByPin(String pin);
}
