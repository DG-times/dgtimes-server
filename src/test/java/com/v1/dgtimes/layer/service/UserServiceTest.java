package com.v1.dgtimes.layer.service;

import com.v1.dgtimes.config.security.PasswordEncoder;
import com.v1.dgtimes.layer.model.User;
import com.v1.dgtimes.layer.model.dto.request.SignupRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SignupResponseDto;
import com.v1.dgtimes.layer.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/*
설명 : 유저의 회원가입을 위한 Unit Test 입니다.

작성일 : 2022.08.13

마지막 수정한 사람 : 안상록

Todo -
*/

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    public UserRepository userRepository;

    @Mock
    public PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("유저 회원가입 성공")
    public void test1() {
        //Given
        SignupResponseDto signupResponseDto = SignupResponseDto.builder()
                .mag("회원가입에 성공했습니다.")
                .status(200)
                .build();
        User find_user = new User();
        when(passwordEncoder.encode("testtesttest!!")).thenReturn(find_user.getPw());

        //when
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin", "testtesttest!!","스파르타");
        SignupResponseDto responseDto = ReflectionTestUtils.invokeMethod(userService, "signupUser", signupRequestDto);

        // then
        assertEquals(responseDto.getMag(), signupResponseDto.getMag());
    }

    @Test
    @DisplayName("signupDtoValid - 유효하지 않은 아이디 길이")
    public void test2() {
        //Given
        SignupRequestDto signupRequestDto = new SignupRequestDto("ad", "testtesttest!!","스파르타");

        //when
        RuntimeException restApiException = assertThrows(RuntimeException.class, () ->  ReflectionTestUtils.invokeMethod(userService, "signupDtoValid", signupRequestDto));

        //then
        Assertions.assertEquals("회원가입에 실패했습니다. - 유효하지 않은 아이디 길이", restApiException.getMessage());

    }



    @Test
    @DisplayName("signupDtoValid - 유효하지 않은 아이디 형식")
    public void test3() {
        //Given
        SignupRequestDto signupRequestDto = new SignupRequestDto("ad!@", "testtesttest!!","스파르타");

        //when
        RuntimeException restApiException = assertThrows(RuntimeException.class, () ->  ReflectionTestUtils.invokeMethod(userService, "signupDtoValid", signupRequestDto));

        //then
        Assertions.assertEquals("회원가입에 실패했습니다. - 유효하지 않은 아이디 형식", restApiException.getMessage());

    }

    @Test
    @DisplayName("signupDtoValid - 유효하지 않은 비밀번호 길이")
    public void test4(){
        //Given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin", "!!","스파르타");

        //when
        RuntimeException restApiException = assertThrows(RuntimeException.class, () ->  ReflectionTestUtils.invokeMethod(userService, "signupDtoValid", signupRequestDto));

        //then
        Assertions.assertEquals("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이", restApiException.getMessage());


    }

    @Test
    @DisplayName("signupDtoValid - 비밀번호에 아이디 포함")
    public void test5() {
        //Given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin", "admin!!","스파르타");

        //when
        RuntimeException restApiException = assertThrows(RuntimeException.class, () ->  ReflectionTestUtils.invokeMethod(userService, "signupDtoValid", signupRequestDto));

        //then
        Assertions.assertEquals("회원가입에 실패했습니다. - 비밀번호에 아이디 포함", restApiException.getMessage());


    }

    @Test
    @DisplayName("signupDtoValid - 성공")
    public void test6() {
        //Given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin", "testtest!!","스파르타");

        //when
        ReflectionTestUtils.invokeMethod(userService, "signupDtoValid", signupRequestDto);

        //then

    }

    @Test
    @DisplayName("isExistUser - 실패")
    public void test7() {
        //Given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin", "testtesttest!!","스파르타");
        when(userRepository.existsById(signupRequestDto.getId())).thenReturn(true);

        //when
        RuntimeException restApiException = assertThrows(RuntimeException.class, () ->  ReflectionTestUtils.invokeMethod(userService, "signupDtoValid", signupRequestDto));

        //then
        Assertions.assertEquals("회원가입에 실패했습니다. - 중복된 아이디 입니다", restApiException.getMessage());

    }
    @Test
    @DisplayName("isExistUser - 성공")
    public void test8() {
        //Given
        SignupRequestDto signupRequestDto = new SignupRequestDto("admin", "testtesttest!!","스파르타");
        when(userRepository.existsById(signupRequestDto.getId())).thenReturn(false);

        //when
        ReflectionTestUtils.invokeMethod(userService, "signupDtoValid", signupRequestDto);

        //then
    }
}