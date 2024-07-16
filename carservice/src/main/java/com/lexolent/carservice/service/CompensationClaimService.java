package com.lexolent.carservice.service;

import com.lexolent.carservice.exception.CompensationClaimException;
import com.lexolent.carservice.model.CompensationClaim;
import com.lexolent.carservice.request.CompensationClaimRequest;

import java.util.List;

public interface CompensationClaimService {
	
	public CompensationClaim createCompensationClaim(CompensationClaimRequest request) throws CompensationClaimException;
	
	public void deleteCompensationClaim(Long claimId) throws CompensationClaimException;
	
	public List<CompensationClaim> getAllCompensationClaims();

	public CompensationClaim getCompensationClaimById(Long id) throws CompensationClaimException;

    public List<CompensationClaim> getCompensationClaimsByUserId(Long userId);

    public CompensationClaim updateCompensationClaim(Long id, CompensationClaim compensationClaimDetails) throws CompensationClaimException;

    
}
