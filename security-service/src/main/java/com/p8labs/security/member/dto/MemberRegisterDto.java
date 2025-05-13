package com.p8labs.security.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberRegisterDto extends MemberDto {

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;


    public MemberRegisterDto(String memberId,
                             String password,
                             String nickname,
                             String email) {
        super(memberId, password);
        this.nickname = nickname;
        this.email = email;
    }
}