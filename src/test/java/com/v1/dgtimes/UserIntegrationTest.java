package com.v1.dgtimes;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;




/*
설명 : UserIntegrationTest 작성 하였습니다.

작성일 : 2022.08.08

마지막 수정한 사람 : 공상욱

*/


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;




    @Test
    @DisplayName("회원가입 성공 케이스")
    public void case1()  {

        // given
        SignupRequestDto signupRequestDto = new  SignupRequestDto("ksu", "123456", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<Object> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        Object.class
                );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("회원가입에 성공했습니다.", "200");

    }



    @Test
    @DisplayName("비밀번호 길이 유효 실패 케이스")
    public void case2() {

        // given
        LoginRequestDto loginRequestDto = new LoginRequestDto("ksu","123456");
        HttpEntity<LoginRequestDto> loginRequest = new HttpEntity<>(loginRequestDto);

        // when
        ResponseEntity<LoginResponseDto> response = testRestTemplate
                .postForEntity(
                        "/users/login",
                        loginRequest,
                        LoginResponseDto.class
                );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이", "400");





    }


    @Test
    @DisplayName("비밀번호 형식 유효 실패 케이스")
    public void case3() {

        // given
        LoginRequestDto loginRequestDto = new LoginRequestDto("ksu","123456");
        HttpEntity<LoginRequestDto> loginRequest = new HttpEntity<>(loginRequestDto);

        // when
        ResponseEntity<LoginResponseDto> response = testRestTemplate
                .postForEntity(
                        "/users/login",
                        loginRequest,
                        LoginResponseDto.class
                );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 형식", "400");


    }




    @Test
    @DisplayName("아이디중복 회원가입 실패 케이스")
    public void case4() {


        // given
        SignupRequestDto signupRequestDto = new  SignupRequestDto("ksu", "123456", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<String> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        String.class
                );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        String responseBody = response.getBody();
        assertEquals("회원가입에 실패했습니다. - 중복된 아이디 입니다","200" );

    }



    // User DTO
    @Getter
    @Builder
    static class SignupRequestDto {
        private String id;
        private String pw;
        private String username;
    }

    @Getter
    @Builder
    static class LoginRequestDto {
        private String id;
        private String pw;
    }

    @Getter
    @Builder
    static class LoginResponseDto {
        private int status;
        private String msg;
        private String token;
    }

//    @Getter
//    @Builder
//    static class SignUpPwRequestDto {
//
//        @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$)", message = "비밀번호는 영문 대,소문자와 특수기호가 적어도 1개 ")
//        @Size(min = 8, message = "pw 8~16자로 입력해주세요")
//        private String pw;
//    }


}

