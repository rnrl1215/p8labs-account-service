package com.p8labs.security.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionMessage {
    RETRY("ER0001", "잠시후 다시 시도해 주세요"),
    CONTACT_ADMIN("ER0002", "지속적인 오류 발생시 관리자에게 문의해주세요.");
    private final String code;
    private final String message;
}
