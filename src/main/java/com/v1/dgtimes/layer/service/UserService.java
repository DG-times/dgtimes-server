package com.v1.dgtimes.layer.service;

import com.v1.dgtimes.config.security.PasswordEncoder;
import com.v1.dgtimes.layer.model.User;
import com.v1.dgtimes.layer.model.dto.request.SignupRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SignupResponseDto;
import com.v1.dgtimes.layer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
설명 : 유저의 회원가입을 위한 서비스 입니다.

작성일 : 2022.08.09

마지막 수정한 사람 : 안상록

Todo -
*/
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupResponseDto signupUser(SignupRequestDto requestDto) {
        // Valid
        signupDtoValid(requestDto);

        //pw 인코딩
        requestDto.passwordEncoding(passwordEncoder);

        // 모델 변환
        User user = requestDto.toUser();

        // 모델 저장
        userRepository.save(user);

        return SignupResponseDto.builder()
                .mag("회원가입에 성공했습니다.")
                .status(200)
                .build();
    }

    private void signupDtoValid(SignupRequestDto requestDto){
        if(requestDto.isValidIdLength()){
            throw new RuntimeException("회원가입에 실패했습니다. - 유효하지 않은 아이디 길이");
        }
        if(!requestDto.isValidIdPatternMatches()){
            throw new RuntimeException("회원가입에 실패했습니다. - 유효하지 않은 아이디 형식");
        }
        if(requestDto.isValidPasswordLength()){
            throw new RuntimeException("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이");
        }
        if(requestDto.isPasswordContainsId()){
            throw new RuntimeException("회원가입에 실패했습니다. - 비밀번호에 아이디 포함");
        }
        if(isExistUser(requestDto)){
            throw new RuntimeException("회원가입에 실패했습니다. - 중복된 아이디 입니다");
        }
    }

    private boolean isExistUser(SignupRequestDto requestDto){
        return userRepository.existsById(requestDto.getId()) ;
    }
}
