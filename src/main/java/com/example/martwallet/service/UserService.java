package com.example.martwallet.service;

import com.example.martwallet.dto.requestdto.LoginRequestDto;
import com.example.martwallet.dto.requestdto.SignUpRequestDto;
import com.example.martwallet.dto.responsedto.GenericResponse;

public interface UserService {
    GenericResponse signUp(SignUpRequestDto signUpRequest);

    GenericResponse login(LoginRequestDto loginRequest);
}
