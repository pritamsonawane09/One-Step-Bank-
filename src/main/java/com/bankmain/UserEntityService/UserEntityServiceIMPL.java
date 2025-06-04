package com.bankmain.UserEntityService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.bankmain.UserEntityRepository.UserEntityRepository;
import com.bankmain.userservice.enumdata.Status;
import com.bankmain.userservice.model.LoginResponses;
import com.bankmain.userservice.model.UserEntity;

@Service
public class UserEntityServiceIMPL implements UserEntityService, UserDetailsService {

	@Autowired
	private UserEntityRepository UserEntityRepository;

	@Autowired
	private JavaMailSender JavaMailSender;

	@Override
	public void SaveUserEntityData(UserEntity UserEntity) {

		UserEntity.setStatus(Status.CREATED);
		UserEntity userEntity2 = UserEntityRepository.save(UserEntity);
		if (userEntity2 != null) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(userEntity2.getEmail(), "pritamsonawane2000@gmail.com","mahesh.shinde69690@gmail.com");
			message.setSubject("Thanks For Registeration in HDFC Bank");

			message.setText("Hello, " + userEntity2.getFname() + userEntity2.getMname() + userEntity2.getLname()
					+ ", Resgistration Successfully ");

			JavaMailSender.send(message);
			System.out.println("mail Send End ---- >");
		}

	}

	@Override
	public UserEntity getUserEntity(long id) {

		UserEntity userentity = UserEntityRepository.findById(id).get();
		return userentity;
	}

	@Override
	public UserEntity updateUserService(UserEntity UserEntity) {

		UserEntity.setStatus(Status.CREATED);
		UserEntityRepository.save(UserEntity);
		UserEntity UserEntity1 = UserEntityRepository.findById(UserEntity.getUid()).get();
		return UserEntity1;
	}

	@Override
	public void deleteUserEntityData(long id) {

		UserEntity userEntity = UserEntityRepository.findById(id).get();
		userEntity.setStatus(Status.DISEABLED);
		UserEntityRepository.save(userEntity);

	}

	@Override
	public List<String> getAllUserName() {
		List<String> list = new ArrayList<String>();
		List<UserEntity> ulist = UserEntityRepository.findAll();

		for (UserEntity userEntity : ulist) {
			list.add(userEntity.getUname());
		}
		return null;
	}

	@Override
	public LoginResponses getLoginData(String uname, String pass) {

		LoginResponses loginresponse = new LoginResponses();
		UserEntity userEntity = UserEntityRepository.findByUnameAndPass(uname, pass);

		if (userEntity != null && userEntity.getRole() != null) {
//			loginresponse.setFname(userEntity.getFname());
//			loginresponse.setMname(userEntity.getMname());
//			loginresponse.setLname(userEntity.getLname());
			loginresponse.setEmail(userEntity.getEmail());
			loginresponse.setRolename(userEntity.getRole().getRoleName());
//			loginresponse.getStatus(userEntity.getStatus());
			return loginresponse;
		}
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity = UserEntityRepository.findByUname(username);
		
		return new User(userEntity.getUname(), userEntity.getPass(),new ArrayList<GrantedAuthority>());
	}

	@Override
	public List<UserEntity> getAllData() {
		List<UserEntity> userData = UserEntityRepository.findAll(Sort .by("name").ascending());
		return userData;
	}


}
