package com.p8labs.security.common.enums;

import org.springframework.http.HttpStatus;

public interface ExceptionCodeInterface extends CommonCodeInterface{
    default HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
    default String getExposeMessage() {
        return ExceptionMessage.RETRY.getMessage();
    }
}