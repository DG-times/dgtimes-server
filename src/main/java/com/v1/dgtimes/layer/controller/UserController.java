package com.v1.dgtimes.layer.controller;

import com.v1.dgtimes.layer.model.dto.request.SignupRequestDto;
import com.v1.dgtimes.layer.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.signupUser(requestDto);
//        RestApiException restApiException = new RestApiException();
//        restApiException.setHttpStatus(HttpStatus.OK);
//        restApiException.setErrorMessage("회원가입에 성공했습니다.");
        return new ResponseEntity("회원가입에 성공했습니다.",HttpStatus.OK);
    }
}
