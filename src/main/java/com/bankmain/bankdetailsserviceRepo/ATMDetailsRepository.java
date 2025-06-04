package com.bankmain.bankdetailsserviceRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankmain.ATMCard.AtmDetails;

@Repository
public interface ATMDetailsRepository extends JpaRepository<AtmDetails, Long>{

	AtmDetails findByUserUname(String uname);
}