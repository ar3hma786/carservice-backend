package com.lexolent.carservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lexolent.carservice.model.ForgotPassword;


public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

	 ForgotPassword findByUserId(Long userId);
	 
	 @Query("SELECT fp FROM ForgotPassword fp WHERE fp.user.id = :userId ORDER BY fp.createdAt DESC")
	 List<ForgotPassword> findRecentTokenByUserId(@Param("userId") Long userId);
	
	 
	 
}
