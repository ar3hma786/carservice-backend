package com.lexolent.carservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lexolent.carservice.config.JwtProvider;
import com.lexolent.carservice.exception.UserException;
import com.lexolent.carservice.model.User;
import com.lexolent.carservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	

	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	private PasswordEncoder passwordEncoder;
	
	
	public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findUserById(Long userId) throws UserException {
		
        Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent()){
			return user.get();
		}
		throw new UserException("user not found with id "+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		System.out.println("user service");
		String email=jwtProvider.getTokenFromJwt(jwt);
		
		System.out.println("email"+email);
		
		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not exist with email "+email);
		}
		System.out.println("email user"+user.getEmail());
		return user;
	}

	@Override
	public List<User> findAllUsers() {

		return userRepository.findAll();
	}
	
	

	@Override
	public User findUserByEmail(String username) throws UserException {
		
		User user=userRepository.findByEmail(username);
		
		if(user!=null) {
			
			return user;
		}
		
		throw new UserException("user not exist with username "+username);
	}

	

}
