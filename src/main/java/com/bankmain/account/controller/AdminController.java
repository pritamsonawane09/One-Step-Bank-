package com.bankmain.account.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankmain.AdminService.AdminService;

@RestController
@RequestMapping(value = "/api/v1/admin" )
public class AdminController {

	@Autowired
	private AdminService adminservice;

	
	@RequestMapping(value = "/roleassign")
	public String assignRole(@RequestParam String uname,@RequestParam String rolename) {
		System.out.println("Check Role Data :"+uname+""+rolename);
		adminservice.assignedRoleToUser(uname, rolename);
		return "Role Assigned Successfully......!";
	}
			
}
