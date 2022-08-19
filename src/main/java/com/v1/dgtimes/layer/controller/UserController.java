package com.v1.dgtimes.layer.controller;

import com.v1.dgtimes.layer.model.dto.request.SignupRequestDto;
import com.v1.dgtimes.layer.model.dto.response.DefaultResponseDto;
import com.v1.dgtimes.layer.model.dto.response.SignupResponseDto;
import com.v1.dgtimes.layer.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
설명 : 유저의 회원가입을 위한 컨트롤러 입니다

작성일 : 2022.08.09

마지막 수정한 사람 : 안상록

Todo -
*/

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<SignupResponseDto> registerUser(@RequestBody SignupRequestDto requestDto) {
        SignupResponseDto responseDto = userService.signupUser(requestDto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
