package com.v1.dgtimes.layer.service;

import com.v1.dgtimes.layer.model.User;
import com.v1.dgtimes.layer.model.dto.request.SignupRequestDto;
import com.v1.dgtimes.layer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;
/*
설명 : 유저의 회원가입을 위한 서비스 입니다

작성일 : 2022.08.09

마지막 수정한 사람 : 안상록

Todo -
*/
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void signupUser(SignupRequestDto requestDto) {

        String userId = requestDto.getId();
        String password = requestDto.getPw();
        String username = requestDto.getUsername();
        String pattern = "^[a-zA-Z0-9]*$";

        Optional<User> found = userRepository.findAllById(userId);

        if (found.isPresent()){
            throw new RuntimeException("회원가입에 실패했습니다. - 중복된 아이디 입니다");
        }
        if (userId.length() < 3){
            throw new RuntimeException("회원가입에 실패했습니다. - 유효하지 않은 아이디 길이");
        }
        if(!Pattern.matches(pattern, userId)){
            throw new RuntimeException("회원가입에 실패했습니다. - 유효하지 않은 아이디 형식");
        }
        if (password.length() < 4){
            throw new RuntimeException("회원가입에 실패했습니다. - 유효하지 않은 비밀번호 길이");
        }
        if (password.contains(userId)){
            throw new RuntimeException("회원가입에 실패했습니다. - 비밀번호에 아이디 포함");
        }

        // ID 예외처리
//        ID_DUPLICATION_CODE(400, "", "중복된 id가 있습니다"),
//        ID_LENGTH_CODE(400, "", "아이디는 3자 이상 입력해주세요")
//        ID_FORM_CODE(400, "", "아이디는 알파벳 대소문자와 숫자로만 입력해주세요")
        //Pw예외처리
//        PASSWORD_LENGTH_CODE(400, "", "패스워드는 4글자 이상 입력해주세요")
//        PASSWORD_INCLUDE_CODE(400, "", "비밀번호에 아이디가 포함될 수 없습니다")
        User user = User.builder()
                .id(userId)
                .pw(password)
                .username(username)
                .build();

        userRepository.save(user);

    }
}
