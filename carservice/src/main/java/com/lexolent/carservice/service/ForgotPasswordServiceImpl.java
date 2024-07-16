package com.lexolent.carservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexolent.carservice.exception.UserException;
import com.lexolent.carservice.model.ForgotPassword;
import com.lexolent.carservice.model.User;
import com.lexolent.carservice.repository.ForgotPasswordRepository;
import com.lexolent.carservice.request.OTPVerificationRequest;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;
    
    @Autowired
    private UserService userService;
    

    @Override
    public ForgotPassword createToken(User user, String otp, String email) {
        ForgotPassword forgotPasswordToken = new ForgotPassword();
        forgotPasswordToken.setUser(user);
        forgotPasswordToken.setOtp(otp);
        forgotPasswordToken.setEmail(email);
        return forgotPasswordRepository.save(forgotPasswordToken);
    }

    @Override
    public ForgotPassword findById(Long id) {
        Optional<ForgotPassword> opt = forgotPasswordRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public ForgotPassword findByUser(Long userId) {
        return forgotPasswordRepository.findByUserId(userId);
    }

    @Override
    public void deleteToken(ForgotPassword token) {
        forgotPasswordRepository.delete(token);
    }

    
    @Override
    public boolean verifyOTP(OTPVerificationRequest request) throws UserException {
     
        User user = userService.findUserByEmail(request.getEmail());
        if (user == null) {
            throw new UserException("User not found for email: " + request.getEmail());
        }

        
        ForgotPassword token = forgotPasswordRepository.findByUserId(user.getId());
        if (token == null) {
            return false; 
        }

        return token.getOtp().equals(request.getOtp());
    }
}
