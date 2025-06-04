package com.bankmain.bankdetailsserviceRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankmain.userservice.model.BankDetails;

@Repository
public interface BankDetailsRepo extends JpaRepository<BankDetails, Integer> {

	BankDetails findByBankNameAndBarnachCode(String bname,String bcode);
}
