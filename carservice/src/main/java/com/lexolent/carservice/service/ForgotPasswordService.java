package com.lexolent.carservice.service;



import com.lexolent.carservice.exception.UserException;
import com.lexolent.carservice.model.ForgotPassword;
import com.lexolent.carservice.model.User;
import com.lexolent.carservice.request.OTPVerificationRequest;
import com.lexolent.carservice.request.UpdatePasswordRequest;


public interface ForgotPasswordService {
	
	    public ForgotPassword createToken(User user, String otp, String email);
	    
	    public ForgotPassword findById(Long id);
	    
	    public ForgotPassword findByUser(Long userId);
	    
	    public void deleteToken(ForgotPassword token);

		public boolean verifyOTP(OTPVerificationRequest request) throws UserException;
		
		public User updatePassword(UpdatePasswordRequest requesta) throws UserException;
}