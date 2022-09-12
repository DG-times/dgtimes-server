package com.v2.dgtimes.layer.volume.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "news_volume")
public class NewsVolume {

    @Id
    private String keyword;

    private Date date;

    private String count;
}
