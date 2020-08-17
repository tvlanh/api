package com.mwg.java.sevices;

import java.util.List;
import java.util.Optional;

import com.mwg.java.models.User;

public interface UserService {

	void deleteAll();

	void deleteAll(Iterable<? extends User> entities);

	void delete(User entity);

	void deleteById(String id);

	long count();

	List<User> findAllById(List<String> ids);

	List<User> findAll();

	boolean existsById(String id);

	Optional<User> findById(String id);

	List<User> saveAll(List<User> entities);

	User save(User entity);

	//hàm check login
	boolean checkLogin(String username, String password);

//	DTO save(DTO dto);

	

}
