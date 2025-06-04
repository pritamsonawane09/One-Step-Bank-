package com.bankmain.account.Rolecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankmain.account.RoleService.RoleService;

@RestController
@RequestMapping(value = "/api/v1/role")
public class RoleController {

	
	@Autowired
	private RoleService roleservice;
	
	
	@GetMapping(value = "/getAllRoles")
	public ResponseEntity<List<String>> getAllRole(){
		
		List<String> rlist=	roleservice.getRolelist();
		return ResponseEntity.ok(rlist);
	}
}
