package com.lexolent.carservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lexolent.carservice.exception.UserException;
import com.lexolent.carservice.model.ForgotPassword;
import com.lexolent.carservice.model.User;
import com.lexolent.carservice.repository.ForgotPasswordRepository;
import com.lexolent.carservice.repository.UserRepository;
import com.lexolent.carservice.request.OTPVerificationRequest;
import com.lexolent.carservice.request.UpdatePasswordRequest;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    

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

        List<ForgotPassword> tokens = forgotPasswordRepository.findRecentTokenByUserId(user.getId());
        if (tokens.isEmpty()) {
            return false; 
        }

        ForgotPassword token = tokens.get(0); // Get the most recent token
        return token.getOtp().equals(request.getOtp());
    }
    
    @Override
    public User updatePassword(UpdatePasswordRequest request) throws UserException {
     
        User findUser = userService.findUserByEmail(request.getEmail());

        if (findUser == null) {
            throw new UserException("User not found with email " + request.getEmail());
        }

        findUser.setPassword(passwordEncoder.encode(request.getNewPassword()));

        return userRepository.save(findUser);
    }


}
