package com.bankmain.UserEntityRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankmain.userservice.model.RandomAccountNumber;

@Repository
public interface RandomAccountRepository extends JpaRepository <RandomAccountNumber, Integer>{

}
