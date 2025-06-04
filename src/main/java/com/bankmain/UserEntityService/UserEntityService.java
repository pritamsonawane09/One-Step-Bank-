package com.bankmain.UserEntityService;

import java.util.List;

import com.bankmain.userservice.model.LoginResponses;
import com.bankmain.userservice.model.UserEntity;

public interface UserEntityService {

	void SaveUserEntityData(UserEntity UserEntity);
	
	UserEntity getUserEntity(long id);
	
	UserEntity updateUserService(UserEntity UserEntity);
	
	void deleteUserEntityData(long id);
	
	List<String> getAllUserName();
	
	LoginResponses getLoginData(String uname, String pass);
	
	List<UserEntity> getAllData();

}

