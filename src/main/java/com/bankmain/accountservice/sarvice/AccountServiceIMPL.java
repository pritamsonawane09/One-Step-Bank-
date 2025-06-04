package com.bankmain.accountservice.sarvice;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bankmain.ExceptionHandaling.AccountAlradyCreated;
import com.bankmain.UserEntityRepository.AccountDeetailsRepository;
import com.bankmain.UserEntityRepository.RandomAccountRepository;
import com.bankmain.UserEntityRepository.UserEntityRepository;
import com.bankmain.bankdetailsserviceRepo.BankDetailsRepo;
import com.bankmain.userservice.enumdata.AccountStatus;
import com.bankmain.userservice.enumdata.AccountType;
import com.bankmain.userservice.model.AccountDetails;
import com.bankmain.userservice.model.AccountRequest;
import com.bankmain.userservice.model.BankDetails;
import com.bankmain.userservice.model.RandomAccountNumber;
import com.bankmain.userservice.model.UserEntity;

@Service
public class AccountServiceIMPL implements AccountService {

	@Autowired
	private RandomAccountRepository randomaccountrepository;

	@Autowired
	private BankDetailsRepo bankdetailsrepo;

	@Autowired
	private UserEntityRepository userentityrepository;

	@Autowired
	private AccountDeetailsRepository accountdeetailsreqpository;

	@Override
	public void creatAccount(AccountRequest accountrequest) {

		AccountDetails accountdetails1 = accountdeetailsreqpository.findByAccountTypeAndUserUname(
				AccountType.values()[accountrequest.getAccounttype()], accountrequest.getUname());
		System.out.println(accountdetails1);

		if (accountdetails1 == null) {

			AccountDetails accountdetails = new AccountDetails();

			RandomAccountNumber randomeaccountnumber = randomaccountrepository.findById(1).get();
			System.out.println("Random Account Number" + randomeaccountnumber);
			String acc = accountrequest.getAccountnumber();
			acc = acc + randomeaccountnumber.getAccountNumber();
			accountrequest.setAccountnumber(acc);

			System.out.println(accountrequest);

			randomeaccountnumber.setAccountNumber(randomeaccountnumber.getAccountNumber() + 1);

			BankDetails bankdetails = bankdetailsrepo.findByBankNameAndBarnachCode(accountrequest.getBankname(),
					accountrequest.getBankcode());

			UserEntity userentity = userentityrepository.findByUname(accountrequest.getUname());

			accountdetails.setAccountNumber(accountrequest.getAccountnumber());
			accountdetails.setAccountStatus(AccountStatus.values()[accountrequest.getAccstatus()]);
			accountdetails.setAccountType(AccountType.values()[accountrequest.getAccounttype()]);
			accountdetails.setActulbalance(accountrequest.getAmount());
			accountdetails.setPin(accountrequest.getPin());

			SimpleDateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			String strdate = dateformate.format(new Date(0));

			accountdetails.setCreateDate(strdate);
			accountdetails.setUser(userentity);
			accountdetails.setBankdetails(bankdetails);

			randomaccountrepository.save(randomeaccountnumber);
			accountdeetailsreqpository.save(accountdetails);
		} else {
			System.out.println("Already Account Created");
			throw new AccountAlradyCreated("Already Account Created");
		}
	}
}
