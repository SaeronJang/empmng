package com.empmng.data.dto;

import lombok.Builder;

public class EmpLoginResultDto extends EmpRegResultDto {
    private String token;

    @Builder
    public EmpLoginResultDto(boolean success, int code, String msg, String token) {
        super(success, code, msg);
        this.token = token;
    }
}
