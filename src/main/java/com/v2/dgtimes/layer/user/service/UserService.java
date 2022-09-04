package com.v2.dgtimes.layer.user.service;

import com.v2.dgtimes.config.exception.CustomException;
import com.v2.dgtimes.config.exception.ErrorCode;
import com.v2.dgtimes.config.security.PasswordEncoder;
import com.v2.dgtimes.layer.user.model.User;
import com.v2.dgtimes.layer.user.model.dto.request.SignupRequestDto;
import com.v2.dgtimes.layer.user.model.dto.response.SignupResponseDto;
import com.v2.dgtimes.layer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
설명 : User service
    - 불필요한 @Transaction 삭제

작성일 : 2022.09.04

마지막 수정한 사람 : 안상록

*/
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
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
                .msg("회원가입에 성공했습니다.")
                .status(200)
                .build();
    }

    private void signupDtoValid(SignupRequestDto requestDto){
        if(requestDto.isValidIdLength()){
            throw new CustomException(ErrorCode.ID_VALID_LENGTH_CODE);
        }
        if(!requestDto.isValidIdPatternMatches()){
            throw new CustomException(ErrorCode.ID_VALID_PATTERN_MATCHES_CODE);
        }
        if(requestDto.isValidPasswordLength()){
            throw new CustomException(ErrorCode.PASSWORD_VALID_LENGTH_CODE);
        }
        if(requestDto.isPasswordContainsId()){
            throw new CustomException(ErrorCode.PASSWORD_CONTAIN_ID_CODE);
        }
        if(isExistUser(requestDto)){
            throw new CustomException(ErrorCode.ID_EXIST_USER_CODE);
        }
    }

    boolean isExistUser(SignupRequestDto requestDto){
        return userRepository.existsById(requestDto.getId()) ;
    }
}
