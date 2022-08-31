package com.v2.dgtimes.layer.bookmark.model;

import com.v2.dgtimes.layer.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    private Boolean isInclude;
    @OneToOne(mappedBy = "bookmark")
    private User user;
}
