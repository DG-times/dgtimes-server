package com.v2.dgtimes.layer.user.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupResponseDto {
    private String msg;
    private int status;
}
