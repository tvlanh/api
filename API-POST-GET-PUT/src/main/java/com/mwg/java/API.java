package com.mwg.java;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mwg.java.models.User;
import com.mwg.java.repository.UserRepository;

//RestControler=@Controller +@ResponseBody
//@PostMapping=@RequestMapping+RequestMethod
@RestController
@RequestMapping("/api")
public class API {
	public static Logger logger = LoggerFactory.getLogger(API.class);
	// @PostMapping("/new")

//	public User createNew(@RequestBody User model) {
//		return model;
//	}

	@Autowired
	UserRepository contactService;

//GET ALL USER
	@RequestMapping(value = "/contact/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllContact() {
		List<User> listContact = (List<User>) contactService.findAll();
		if (listContact.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(listContact, HttpStatus.OK);
	}

//	GET USER BY ID 
	@RequestMapping(value = "/contact/{username}", method = RequestMethod.GET)
	public User findContact(@PathVariable("username") String username) {
		User contact = contactService.getOne(username);
		if (contact == null) {
			ResponseEntity.notFound().build();
		}
		return contact;
	}

//delete
	@RequestMapping(value = "/contact/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteContact(@PathVariable(value = "username") String username) {
		User contact = contactService.getOne(username);
		if (contact == null) {
			return ResponseEntity.notFound().build();
		}
		contactService.delete(contact);
		return ResponseEntity.ok().build();
	}

// CREATE NEW USER
	@RequestMapping(value = "/contact/", method = RequestMethod.POST)
	public User saveContact(@Valid @RequestBody User contact) {
		return contactService.save(contact);
	}

	/* ---------------- UPDATE USER ------------------------ */
	@RequestMapping(value = "/contact/{username}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateContact(@PathVariable(value = "username") String username,
			@Valid @RequestBody User contactForm) {
		User contact = contactService.getOne(username);
		if (contact == null) {
			return ResponseEntity.notFound().build();
		}
		contact.setUsername(contactForm.getUsername());
		contact.setPassword(contactForm.getPassword());
		contact.setFullname(contactForm.getFullname());
		contact.setAge(contactForm.getAge());
		contact.setGender(contactForm.isGender());
		User updatedContact = contactService.save(contact);
		return ResponseEntity.ok(updatedContact);
	}

	/*
	 * ---------------- UPDATE USER ------------------------
	 * 
	 * @RequestMapping(value = "/users", method = RequestMethod.PUT) public
	 * ResponseEntity<String> updateUser(@RequestBody User user) { User oldUser =
	 * mapUser.get(user.getId()); if (oldUser == null) { return new
	 * ResponseEntity<String>("Not Found User", HttpStatus.NO_CONTENT); }
	 * 
	 * // replace old user by new user. mapUser.put(user.getId(), user); return new
	 * ResponseEntity<String>("Updated!", HttpStatus.OK); }
	 */
}