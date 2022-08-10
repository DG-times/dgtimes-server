package com.v1.dgtimes.layer.model;

/*
설명 : User Model 구현햇습니다.

작성일 : 2022.08.06

마지막 수정한 사람 : 공상욱

*/

import com.v1.dgtimes.layer.model.dto.request.SignupRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "User_id",nullable = false)
    private String id;

    @Column(nullable = false)
    private  String pw;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks = new ArrayList<>();

}
