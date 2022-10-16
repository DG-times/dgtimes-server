package com.v2.dgtimes.layer.user.model.dto.request;

import com.v2.dgtimes.config.security.PasswordEncoder;
import com.v2.dgtimes.layer.bookmark.model.Bookmark;
import com.v2.dgtimes.layer.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

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
                .bookmark(new Bookmark(null,"",""))
                .build();
    }
    public void passwordEncoding(PasswordEncoder passwordEncoder){
        this.pw = passwordEncoder.encode(this.pw);
    }

}