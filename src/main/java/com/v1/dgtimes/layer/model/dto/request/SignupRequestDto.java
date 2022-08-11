package com.v1.dgtimes.layer.model.dto.request;

import com.v1.dgtimes.config.security.PasswordEncoder;
import com.v1.dgtimes.layer.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;


/*
설명 : 유저의 회원가입을 위한 요청된 정보입니다. pw 인코딩 추가

작성일 : 2022.08.11

마지막 수정한 사람 : 안상록

Todo -
*/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

    private String id;
    private String pw;
    private String username;


    private static String pattern = "^[a-zA-Z0-9]*$";

    public boolean isValidIdLength(){
        return this.id.length() < 3;
    }

    public boolean isValidIdPatternMatches(){
        return Pattern.matches(pattern, this.id);
    }

    public boolean isValidPasswordLength(){
        return this.pw.length() < 4;
    }

    public boolean isPasswordContainsId(){
        return this.pw.contains(this.id);
    }

    public User toUser(){
        return User.builder()
                .id(this.id)
                .pw(this.pw)
                .username(this.username)
                .build();
    }
    public void passwordEncoding(PasswordEncoder passwordEncoder){
        this.pw = passwordEncoder.encode(this.pw);
    }

}