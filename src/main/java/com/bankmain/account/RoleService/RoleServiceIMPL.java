package com.bankmain.account.RoleService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankmain.UserEntityRepository.RoleRepository;
import com.bankmain.userservice.model.Role;

@Service
public class RoleServiceIMPL implements RoleService {

	@Autowired
	private RoleRepository reolerepository;

	@Override
	public List<String> getRolelist() {

		List<String> roleList=new ArrayList<String>();
//		List<String> roleList = reolerepository.getAllRole();
		 
		List<Role> rlist = reolerepository.findAll();
		System.out.println(rlist);
		for (Role role : rlist) {
			roleList.add(role.getRoleName());
		}
		return roleList;
	}

}
