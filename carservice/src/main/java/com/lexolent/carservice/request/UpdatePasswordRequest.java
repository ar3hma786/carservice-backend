package com.lexolent.carservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePasswordRequest {
	
	private String email;
	
	private String newPassword;
	
	private String confirmPassword;

}
