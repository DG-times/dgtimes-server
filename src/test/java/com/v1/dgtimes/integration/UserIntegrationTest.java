package com.v1.dgtimes.integration;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
public class UserIntegrationTest  extends DefaultIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    @DisplayName("회원가입 성공 케이스")
    public void case1()  {

        // given
        SignupRequestDto signupRequestDto = new  SignupRequestDto("admin", "testtesttest!!", "공상욱");
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
        assertEquals(new DefaultResponseDto("회원가입에 성공했습니다.",200), response.getBody());

    }




    @Test
    @DisplayName("비밀번호 길이 유효 실패 케이스")
    public void case2() {

        // given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin", "testtesttest!!", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<DefaultResponseDto> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        DefaultResponseDto.class
                );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new DefaultResponseDto("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이",400), response.getBody());

    }




    @Test
    @DisplayName("비밀번호 형식 유효 실패 케이스")
    public void case3() {

        // given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin", "testtesttest!!", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<DefaultResponseDto> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        DefaultResponseDto.class
                );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(new DefaultResponseDto("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 형식",400), response.getBody());


    }




    @Test
    @DisplayName("아이디중복 회원가입 실패 케이스")
    public void case4() {


        // given
        SignupRequestDto signupRequestDto = new  SignupRequestDto("admin", "testtesttest!!", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<DefaultResponseDto> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        DefaultResponseDto.class
                );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new DefaultResponseDto("회원가입에 실패했습니다. - 중복된 아이디 입니다",400), response.getBody());

    }

}

