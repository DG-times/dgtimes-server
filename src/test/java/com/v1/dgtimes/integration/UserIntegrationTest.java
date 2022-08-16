package com.v1.dgtimes.integration;


import com.v1.dgtimes.config.exception.RestApiException;
import com.v1.dgtimes.layer.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/*
설명 : UserIntegrationTest 작성 하였습니다. case7 - user pw 인코딩 테스트 추가.

작성일 : 2022.08.11

마지막 수정한 사람 : 안상록

*/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserIntegrationTest  extends DefaultIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setupDB(){
        userRepository.save(new User("admin4", "test!text", "독고민수"));
    }

    @AfterEach
    public void resetDB(){
        userRepository.deleteAll();
    }

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
        assertEquals("회원가입에 성공했습니다.", response.getBody().getMag());
        assertEquals(new SignupResponseDto("회원가입에 성공했습니다.", 200), response.getBody());

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
        assertEquals("U003", responsebody.getCode());
        assertEquals("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이", responsebody.getMsg());

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
        assertEquals("U002", responsebody.getCode());
        assertEquals("회원가입에 실패했습니다. - 유효하지 않은 아이디 형식", responsebody.getMsg());

    }
    @Test
    @DisplayName("아이디 중복 회원가입 실패 케이스")
    public void case4() {

        // given
        SignupRequestDto signupRequestDto = new  SignupRequestDto("admin4", "testtesttest!!", "공상욱");
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
        assertEquals("U005", responsebody.getCode());
        assertEquals("회원가입에 실패했습니다. - 중복된 아이디 입니다", responsebody.getMsg());

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
        assertEquals("U001", responsebody.getCode());
        assertEquals("회원가입에 실패했습니다. - 유효하지 않은 아이디 길이", responsebody.getMsg());

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
        assertEquals("U004", responsebody.getCode());
        assertEquals("회원가입에 실패했습니다. - 비밀번호에 아이디 포함", responsebody.getMsg());

    }
    @Test
    @DisplayName("비밀번호에 암호화")
    public void case7() {

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
        User user = userRepository.findOneById(signupRequestDto.getId());
        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new SignupResponseDto("회원가입에 성공했습니다.", 200), response.getBody());
        assertNotEquals(user.getPw(), signupRequestDto.getPw());

    }

}
