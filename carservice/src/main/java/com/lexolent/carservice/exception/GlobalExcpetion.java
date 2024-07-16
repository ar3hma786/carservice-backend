package com.lexolent.carservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExcpetion {
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> handleUserException(UserException userException, WebRequest request)
	{
		ErrorDetails errorDetails = new ErrorDetails(userException.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CarCompanyException.class)
	public ResponseEntity<ErrorDetails> handleCarCompanyException(CarCompanyException carCompanyException, WebRequest request)
	{
		ErrorDetails errorDetails = new ErrorDetails(carCompanyException.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
    
	@ExceptionHandler(CarLoanException.class)
	public ResponseEntity<ErrorDetails> handleCarLoanException(CarLoanException carLoanException, WebRequest request)
	{
		ErrorDetails errorDetails = new ErrorDetails(carLoanException.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CompensationClaimException.class)
    public ResponseEntity<ErrorDetails> handleCompensationClaimException(CompensationClaimException compensationClaimException, WebRequest request)
    {
		ErrorDetails errorDetails = new ErrorDetails(compensationClaimException.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllException(Exception exception, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false), LocalDateTime.now());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
