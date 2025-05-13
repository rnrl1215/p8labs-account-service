package com.p8labs.security.member.dto;

import lombok.Getter;

public record MemberLoginResponse(
        String jwtToken)
{
    public MemberLoginResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}