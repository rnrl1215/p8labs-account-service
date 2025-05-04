package com.p8labs.security.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class CommonException {
    @RequiredArgsConstructor
    @Getter
    public enum Member implements ExceptionCodeInterface {
        NOT_FOUND_EXCEPTION("MER0000", "NOT_FOUND_USER", "사용자가 없습니다."),
        NOT_EQUAL_PASSWORD_EXCEPTION("MER0001", "NOT_EQUAL_PASSWORD_EXCEPTION", "패스워드가 일치 하지 않습니다.");
        private final String code;
        private final String message;
        private final String exposeMessage;
    }
}