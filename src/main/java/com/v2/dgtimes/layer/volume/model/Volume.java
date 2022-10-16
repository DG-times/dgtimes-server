package com.v2.dgtimes.layer.volume.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


public interface Volume {

    String getDate();

    Integer getCount();
}
