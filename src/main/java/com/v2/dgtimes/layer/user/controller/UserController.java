package com.v2.dgtimes.layer.user.controller;

import com.v2.dgtimes.layer.user.model.dto.request.SignupRequestDto;
import com.v2.dgtimes.layer.user.model.dto.response.SignupResponseDto;
import com.v2.dgtimes.layer.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<SignupResponseDto> registerUser(@RequestBody SignupRequestDto requestDto) {
        SignupResponseDto responseDto = userService.signupUser(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}

