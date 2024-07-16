package com.lexolent.carservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lexolent.carservice.model.CompensationClaim;

public interface CompensationClaimRepository extends JpaRepository<CompensationClaim, Long> {

	List<CompensationClaim> findByCarLoan_Id(Long carLoanId);

	List<CompensationClaim> findByUserId(Long userId);

}
