package com.p8labs.security.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberDto implements Serializable {
    @NotBlank(message = "아이디는 필수 입니다.")
    private String memberId;

    @NotBlank(message = "패스워드는 필수 입니다.")
    private String password;

    public MemberDto(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}