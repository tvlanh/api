package com.mwg.java.repository;

import org.springframework.data.repository.CrudRepository;

import com.mwg.java.models.User;

public interface UserRepository extends CrudRepository<User, String> {

	User getOne(String username);

	

	
	

}
