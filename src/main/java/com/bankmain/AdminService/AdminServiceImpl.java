package com.bankmain.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankmain.UserEntityRepository.RoleRepository;
import com.bankmain.UserEntityRepository.UserEntityRepository;
import com.bankmain.userservice.model.Role;
import com.bankmain.userservice.model.UserEntity;

@Service
public class AdminServiceImpl implements AdminService {

	
	@Autowired
	private UserEntityRepository UserEntityRepository;
	
	@Autowired
	private RoleRepository RoleRepository;
	
	@Override
	public void assignedRoleToUser(String uname, String rolename) {
		UserEntity userentity=UserEntityRepository.findByUname(uname);
		Role role = RoleRepository.findByroleName(rolename);
		userentity.setRole(role);
		
		UserEntityRepository.save(userentity);
	}

}
