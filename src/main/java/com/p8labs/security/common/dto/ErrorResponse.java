package com.p8labs.security.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ErrorResponse extends CommonResponse {
    private String exposeMessage;


    public ErrorResponse(int statusCode,
                         String code,
                         String message,
                         String exposeMessage) {
        super(statusCode, code, message);
        this.exposeMessage = exposeMessage;
    }
}