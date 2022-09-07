package com.v2.dgtimes.layer.volume.model;

/*
설명 : Volume 작성

작성일 : 2022.09.07

마지막 수정한 사람 : 공상욱

*/

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Volume")
public class Volume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private String count;



}
