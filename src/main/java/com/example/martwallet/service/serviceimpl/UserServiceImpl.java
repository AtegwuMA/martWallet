package com.example.martwallet.service.serviceimpl;

import com.example.martwallet.dto.requestdto.LoginRequestDto;
import com.example.martwallet.dto.requestdto.SignUpRequestDto;
import com.example.martwallet.dto.responsedto.GenericResponse;
import com.example.martwallet.model.User;
import com.example.martwallet.model.Wallet;
import com.example.martwallet.repository.UserRepository;
import com.example.martwallet.repository.WalletRepository;
import com.example.martwallet.security.util.JwtUtil;
import com.example.martwallet.service.UserService;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public GenericResponse signUp(SignUpRequestDto signUpRequest) {
        // Validate fields using the validator
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(signUpRequest);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<SignUpRequestDto> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(sb.toString().trim());
        }

        // Check if the email already exists
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new GenericResponse(01, "Email is already in use", HttpStatus.BAD_REQUEST, null);
        }
        // Create and save a new user
        User newUser = new User();
        newUser.setFirstName(signUpRequest.getFirstName());
        newUser.setLastName(signUpRequest.getLastName());
        newUser.setMiddleName(signUpRequest.getMiddleName());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword())); // Encode password
        newUser.setDateOfBirth(signUpRequest.getDateOfBirth());
        newUser.setAddress(signUpRequest.getAddress());
        newUser.setBvn(signUpRequest.getBvn());

        userRepository.save(newUser); // Save user to the database

        Wallet wallet = new Wallet();
        wallet.setUser(newUser); // Link wallet to user
        wallet.setBalance(0.0);
        wallet.setTier(signUpRequest.getWalletTier());
        walletRepository.save(wallet);
        return new GenericResponse(00, "User registered successfully", HttpStatus.CREATED, null);

    }

    public GenericResponse login(LoginRequestDto loginRequest) {
        try {
            // Authenticate the user
            User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Check password
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return new GenericResponse(02, "Invalid email or password", HttpStatus.UNAUTHORIZED, null);
            }

            // Generate JWT token
            String jwt = jwtUtil.generateToken(user.getEmail());

            // Return response with token
            return new GenericResponse(00, "Login successful", HttpStatus.OK, jwt);
        } catch (Exception e) {
            return new GenericResponse(02, "Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
}


//import com.example.martwallet.dto.requestdto.SignUpRequestDto;
//import com.example.martwallet.dto.responsedto.GenericResponse;
//import com.example.martwallet.repository.UserRepository;
//import com.example.martwallet.security.util.JwtUtil;
//import com.example.martwallet.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public GenericResponse signUp(SignUpRequestDto signUpRequest) {
//        // Check if the email already exists
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return new GenericResponse(01, "Email is already in use", HttpStatus.BAD_REQUEST, null);
//        }
//
//        // Create a new user
//        User newUser = new User();
//        newUser.setFirstName(signUpRequest.getFirstName());
//        newUser.setLastName(signUpRequest.getLastName());
//        newUser.setMiddleName(signUpRequest.getMiddleName());
//        newUser.setEmail(signUpRequest.getEmail());
//        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword())); // Encode password
//        newUser.setDateOfBirth(signUpRequest.getDateOfBirth());
//        newUser.setAddress(signUpRequest.getAddress());
//        newUser.setBvn(signUpRequest.getBvn());
//        newUser.setWalletTier(signUpRequest.getWalletTier());
//
//        userRepository.save(newUser); // Save user to the database
//
//        return new GenericResponse("00", "User registered successfully", HttpStatus.CREATED, null);
//    }
//
//    public GenericResponse login(LoginRequestDto loginRequest) {
//        try {
//            // Authenticate user
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//            );
//
//            // Load user details
//            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
//            String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//            return new GenericResponse("00", "Login successful", HttpStatus.OK, jwt);
//        } catch (Exception e) {
//            return new GenericResponse("02", "Invalid email or password", HttpStatus.UNAUTHORIZED, null);
//        }
//    }
//}