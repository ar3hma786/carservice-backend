package com.lexolent.carservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OTPVerificationRequest {
	
	private String email;
	private String otp;

}
