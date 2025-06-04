package com.bankmain.UserEntityRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bankmain.userservice.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query(value = "select rolename from role", nativeQuery = true)
	List<String> getAllRole();
	
	Role findByroleName(String rolename);
}
