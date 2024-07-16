package com.lexolent.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lexolent.carservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);

}
