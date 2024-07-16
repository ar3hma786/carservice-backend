package com.lexolent.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexolent.carservice.exception.UserException;
import com.lexolent.carservice.model.ForgotPassword;
import com.lexolent.carservice.model.User;
import com.lexolent.carservice.request.OTPVerificationRequest;
import com.lexolent.carservice.request.UpdatePasswordRequest;
import com.lexolent.carservice.response.ApiResponse;
import com.lexolent.carservice.service.EmailService;
import com.lexolent.carservice.service.ForgotPasswordService;
import com.lexolent.carservice.service.UserService;
import com.lexolent.carservice.utils.OtpUtils;

@RestController
@RequestMapping("/reset-password")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse> sendUpdatePasswordOTP(@RequestBody UpdatePasswordRequest req) throws Exception {
        User user = userService.findUserByEmail(req.getEmail());
        String otp = OtpUtils.generateOTP();
        ForgotPassword token = forgotPasswordService.createToken(user, otp, req.getEmail());
        System.out.println(token);
        
        emailService.sendVerificationOtpEmail(user.getEmail(), otp);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Password Reset OTP sent successfully.");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody OTPVerificationRequest request) {
        try {
            boolean isOTPValid = forgotPasswordService.verifyOTP(request);

            if (isOTPValid) {
                return ResponseEntity.ok("OTP is valid. You can now reset your password.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP. Please try again.");
            }
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    
    @PutMapping("/update-password")
    public ResponseEntity<User> updatePassword(@RequestBody UpdatePasswordRequest request) throws UserException {
        User updatedUser = forgotPasswordService.updatePassword(request);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }



}
