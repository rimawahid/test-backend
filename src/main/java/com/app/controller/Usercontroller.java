package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.repo.UserRepo;

@RestController
//@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:4200/")
public class Usercontroller {
	@Autowired
	private UserRepo repo;
	
	@PostMapping("/user")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		System.out.println("database connect");
		return ResponseEntity.ok(repo.save(user));
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<?> loginUSer(@RequestBody User userData){
		System.out.println(userData.getPassword());
		User user = repo.findByUserId(userData.getUserId());
		if(user.getPassword().equals(userData.getPassword()))
			return ResponseEntity.ok(user);
		
		
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
	}
}
