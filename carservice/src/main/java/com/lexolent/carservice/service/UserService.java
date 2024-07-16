package com.lexolent.carservice.service;

import java.util.List;

import com.lexolent.carservice.exception.UserException;
import com.lexolent.carservice.model.User;

public interface UserService {
	
    public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;
	
	public List<User> findAllUsers();
	

}
