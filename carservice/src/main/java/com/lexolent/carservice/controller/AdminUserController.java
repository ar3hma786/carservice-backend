package com.lexolent.carservice.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexolent.carservice.exception.UserException;
import com.lexolent.carservice.model.User;
import com.lexolent.carservice.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminUserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String jwt) throws UserException{
		List<User> user=userService.findAllUsers();
		return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
	}
	
	


}
