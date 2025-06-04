package com.bankmain.bankdetailsserviceRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankmain.accountTransaction.AccountTransaction;

@Repository
public interface FundTransferRepository extends JpaRepository<AccountTransaction, Long> {

	@Query("FROM AccountTransaction WHERE tDate BETWEEN :tDate1 AND :tDate2")
	List<AccountTransaction> findByTDateBetween(String tDate1, String tDate2);

}
