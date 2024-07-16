package com.lexolent.carservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lexolent.carservice.exception.CompensationClaimException;
import com.lexolent.carservice.model.CompensationClaim;
import com.lexolent.carservice.request.CompensationClaimRequest;
import com.lexolent.carservice.service.CompensationClaimService;

@RestController
@RequestMapping("/api/compensationclaim")
public class CompensationClaimController {

    @Autowired
    private CompensationClaimService compensationClaimService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<CompensationClaim> createCompensationClaim(@RequestBody CompensationClaimRequest request) throws CompensationClaimException {
        CompensationClaim compensationClaim = compensationClaimService.createCompensationClaim(request);
        return new ResponseEntity<CompensationClaim>(compensationClaim, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{claimId}")
    public ResponseEntity<Void> deleteCompensationClaim(@PathVariable Long claimId) throws CompensationClaimException {
        compensationClaimService.deleteCompensationClaim(claimId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CompensationClaim> updateCompensationClaim(@PathVariable Long id, @RequestBody CompensationClaim compensationClaimDetails) throws CompensationClaimException {
        CompensationClaim updatedCompensationClaim = compensationClaimService.updateCompensationClaim(id, compensationClaimDetails);
        return new ResponseEntity<CompensationClaim>(updatedCompensationClaim, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<CompensationClaim>> getAllCompensationClaims() {
        List<CompensationClaim> compensationClaims = compensationClaimService.getAllCompensationClaims();
        return new ResponseEntity<List<CompensationClaim>>(compensationClaims, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<CompensationClaim> getCompensationClaimById(@PathVariable Long id) throws CompensationClaimException {
        CompensationClaim compensationClaim = compensationClaimService.getCompensationClaimById(id);
        return new ResponseEntity<CompensationClaim>(compensationClaim, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CompensationClaim>> getCompensationClaimsByUserId(@PathVariable Long userId) {
        List<CompensationClaim> compensationClaims = compensationClaimService.getCompensationClaimsByUserId(userId);
        return new ResponseEntity<List<CompensationClaim>>(compensationClaims, HttpStatus.OK);
    }
}
