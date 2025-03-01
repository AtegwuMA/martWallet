package com.example.martwallet.contoller;



import com.example.martwallet.dto.requestdto.LoginRequestDto;
import com.example.martwallet.dto.requestdto.SignUpRequestDto;
import com.example.martwallet.dto.responsedto.GenericResponse;
import com.example.martwallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint for user registration
    @PostMapping("/signup")
    public ResponseEntity<GenericResponse> signUp(@RequestBody SignUpRequestDto signUpRequest) {
        GenericResponse response = userService.signUp(signUpRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@RequestBody LoginRequestDto loginRequest) {
        GenericResponse response = userService.login(loginRequest);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}


//import com.example.martwallet.dto.requestdto.LoginRequestDto;
//import com.example.martwallet.dto.requestdto.SignUpRequestDto;
//import com.example.martwallet.dto.responsedto.GenericResponse;
//import com.example.martwallet.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<GenericResponse> signUp(@RequestBody SignUpRequestDto signUpRequest) {
//        GenericResponse response = userService.signUp(signUpRequest);
//        return new ResponseEntity<>(response, response.getHttpStatus());
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<GenericResponse> login(@RequestBody LoginRequestDto loginRequest) {
//        GenericResponse response = userService.login(loginRequest);
//        return new ResponseEntity<>(response, response.getHttpStatus());
//    }
//}