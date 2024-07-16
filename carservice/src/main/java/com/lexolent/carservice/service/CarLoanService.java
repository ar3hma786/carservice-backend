package com.lexolent.carservice.service;

import java.util.List;

import com.lexolent.carservice.exception.CarLoanException;
import com.lexolent.carservice.model.CarLoan;
import com.lexolent.carservice.request.CarLoanRequest;

public interface CarLoanService {
	
	public CarLoan createCarLoan(CarLoanRequest req) throws CarLoanException;
	
    public void deleteCarLoan(Long id) throws CarLoanException;
	
    public CarLoan updateCarLoan(Long id, CarLoan carLoanDetails) throws CarLoanException;
    
	public List<CarLoan> getAllCarLoans(); 
	
	public CarLoan getCarLoanById(Long id) throws CarLoanException;
	
	public List<CarLoan> getCarLoansByUserId(Long userId);
	

}
