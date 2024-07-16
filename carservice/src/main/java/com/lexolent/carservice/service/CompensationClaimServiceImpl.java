package com.lexolent.carservice.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.lexolent.carservice.exception.CompensationClaimException;
import com.lexolent.carservice.model.CompensationClaim;
import com.lexolent.carservice.model.User;
import com.lexolent.carservice.repository.CompensationClaimRepository;
import com.lexolent.carservice.repository.UserRepository;
import com.lexolent.carservice.request.CompensationClaimRequest;

@Service
public class CompensationClaimServiceImpl implements CompensationClaimService {

    private CompensationClaimRepository compensationClaimRepository;
    private UserRepository userRepository;


    public CompensationClaimServiceImpl(CompensationClaimRepository compensationClaimRepository,
			UserRepository userRepository) {
		super();
		this.compensationClaimRepository = compensationClaimRepository;
		this.userRepository = userRepository;
	}

	@Override
    public CompensationClaim createCompensationClaim(CompensationClaimRequest request)
            throws CompensationClaimException {
        try {
            CompensationClaim claim = new CompensationClaim();
            claim.setClaimedAmount(request.getClaimedAmount());
            claim.setClaimReason(request.getClaimReason());
            claim.setSubmissionDate(request.getSubmissionDate());

            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new CompensationClaimException("User not found with id: " + request.getUserId()));
            claim.setUser(user);

         

            return compensationClaimRepository.save(claim);
        } catch (Exception e) {
            throw new CompensationClaimException("Failed to create compensation claim");
        }
    }

    @Override
    public void deleteCompensationClaim(Long claimId) throws CompensationClaimException {
        try {
            compensationClaimRepository.deleteById(claimId);
        } catch (Exception e) {
            throw new CompensationClaimException("Failed to delete compensation claim with id: " + claimId);
        }
    }

    @Override
    public List<CompensationClaim> getAllCompensationClaims() {
        return compensationClaimRepository.findAll();
    }

    @Override
    public CompensationClaim getCompensationClaimById(Long id) throws CompensationClaimException {
        Optional<CompensationClaim> optionalClaim = compensationClaimRepository.findById(id);
        return optionalClaim.orElseThrow(() -> new CompensationClaimException("Compensation claim not found with id: " + id));
    }

    @Override
    public List<CompensationClaim> getCompensationClaimsByUserId(Long userId) {
        return compensationClaimRepository.findByUserId(userId);
    }

    @Override
    public CompensationClaim updateCompensationClaim(Long id, CompensationClaim compensationClaimDetails)
            throws CompensationClaimException {
        try {
            CompensationClaim claim = getCompensationClaimById(id);

            claim.setClaimedAmount(compensationClaimDetails.getClaimedAmount());
            claim.setClaimReason(compensationClaimDetails.getClaimReason());
            claim.setSubmissionDate(compensationClaimDetails.getSubmissionDate());

            if (compensationClaimDetails.getUser() != null) {
                claim.setUser(compensationClaimDetails.getUser());
            }

            if (compensationClaimDetails.getCarLoan() != null) {
                claim.setCarLoan(compensationClaimDetails.getCarLoan());
            }

            return compensationClaimRepository.save(claim);
        } catch (Exception e) {
            throw new CompensationClaimException("Failed to update compensation claim with id: " + id);
        }
    }
}
