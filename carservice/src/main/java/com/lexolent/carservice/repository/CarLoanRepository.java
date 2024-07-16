package com.lexolent.carservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lexolent.carservice.model.CarLoan;

public interface CarLoanRepository extends JpaRepository<CarLoan, Long> {

	public List<CarLoan> findByUserId(Long userId);

}
