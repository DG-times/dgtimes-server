package com.v1.dgtimes.integration;



import com.v1.dgtimes.config.exception.RestApiException;
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
설명 : UserIntegrationTest 작성 하였습니다. 테스트 케이스 추가 작성했습니다.

작성일 : 2022.08.09

마지막 수정한 사람 : 안상록

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
        SignupRequestDto signupRequestDto = new  SignupRequestDto("admin", "testtest!!", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<SignupResponseDto> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        SignupResponseDto.class
                );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new SignupResponseDto("회원가입에 성공했습니다.", 200), response.getBody()); //responseEntity body 한글 인코딩 오류추정
//        assertEquals(new DefaultResponseDto("회원가입에 성공했습니다.",200), new DefaultResponseDto(response.getBody(), response.getStatusCodeValue()));

    }




    @Test
    @DisplayName("비밀번호 길이 유효 실패 케이스")
    public void case2() {

        // given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin1", "!", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<RestApiException> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        RestApiException.class
                );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        RestApiException responsebody = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responsebody.getHttpStatus());
        assertEquals("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이", responsebody.getErrorMessage());
//        assertEquals(new DefaultResponseDto("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이",400), response.getBody());

    }

    @Test
    @DisplayName("아이디 형식 유효 실패 케이스")
    public void case3() {

        // given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin@#", "testtesttest!!", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<RestApiException> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        RestApiException.class
                );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        RestApiException responsebody = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responsebody.getHttpStatus());
        assertEquals("회원가입에 실패했습니다. - 유효하지 않은 아이디 형식", responsebody.getErrorMessage());

    }


    @Test
    @DisplayName("아이디 중복 회원가입 실패 케이스")
    public void case4() {

        // given
        SignupRequestDto signupRequestDto = new  SignupRequestDto("admin", "testtesttest!!", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<RestApiException> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        RestApiException.class
                );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        RestApiException responsebody = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responsebody.getHttpStatus());
        assertEquals("회원가입에 실패했습니다. - 중복된 아이디 입니다", responsebody.getErrorMessage());
    }

    @Test
    @DisplayName("아이디 길이 유효 실패 케이스")
    public void case5() {

        // given
        SignupRequestDto signupRequestDto = new SignupRequestDto("ad", "testtesttest!!", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<RestApiException> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        RestApiException.class
                );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        RestApiException responsebody = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responsebody.getHttpStatus());
        assertEquals("회원가입에 실패했습니다. - 유효하지 않은 아이디 길이", responsebody.getErrorMessage());
//        assertEquals(new DefaultResponseDto("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이",400), response.getBody());

    }


    @Test
    @DisplayName("비밀번호에 아이디 포함 유효 실패 케이스")
    public void case6() {

        // given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin2", "admin2testtest!!", "공상욱");
        HttpEntity<SignupRequestDto> signupRequest = new HttpEntity<>(signupRequestDto);

        // when
        ResponseEntity<RestApiException> response = testRestTemplate
                .postForEntity(
                        "/users/signup",
                        signupRequest,
                        RestApiException.class
                );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        RestApiException responsebody = response.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, responsebody.getHttpStatus());
        assertEquals("회원가입에 실패했습니다. - 비밀번호에 아이디 포함", responsebody.getErrorMessage());
//        assertEquals(new DefaultResponseDto("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이",400), response.getBody());

    }


}

