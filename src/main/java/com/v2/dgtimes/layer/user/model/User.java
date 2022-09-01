package com.v2.dgtimes.layer.user.model;

import com.v2.dgtimes.layer.bookmark.model.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    private String id;
    private String pw;
    private String username;
    private Date birthday;
    @Email
    private String email;
    @Embedded
    private Address address;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bookmark_id")
    private Bookmark bookmark;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
}
