package com.p8labs.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class MemberException {
    @RequiredArgsConstructor
    @Getter
    public enum Member implements ExceptionCodeInterface {
        NOT_FOUND_EXCEPTION("MER0000", "NOT_FOUND_USER", "사용자가 없습니다."),
        NOT_EQUAL_PASSWORD_EXCEPTION("MER0001", "NOT_EQUAL_PASSWORD_EXCEPTION", "패스워드가 일치 하지 않습니다."),
        DUPLICATION_EXCEPTION("MER0002", "DUPLICATION_EXCEPTION", "이미 데이터가 존재 합니다.");
        private final String code;
        private final String message;
        private final String exposeMessage;
    }
}