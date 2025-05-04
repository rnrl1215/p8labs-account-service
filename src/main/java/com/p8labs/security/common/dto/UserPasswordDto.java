package com.p8labs.security.common.dto;

import lombok.Getter;

@Getter
public class UserPasswordDto {
    private String salt;
    private String password;

    public UserPasswordDto(String salt, String password) {
        this.salt = salt;
        this.password = password;
    }
}
