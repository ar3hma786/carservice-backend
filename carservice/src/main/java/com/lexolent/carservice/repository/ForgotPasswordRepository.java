package com.lexolent.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.lexolent.carservice.model.ForgotPassword;


public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

	 ForgotPassword findByUserId(Long userId);
	
	 
	 
}
