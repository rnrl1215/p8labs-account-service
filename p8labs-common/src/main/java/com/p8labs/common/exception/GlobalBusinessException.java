package com.p8labs.common.exception;

import com.p8labs.common.enums.ExceptionCodeInterface;
import lombok.Getter;

@Getter
public class GlobalBusinessException extends RuntimeException {
    private ExceptionCodeInterface exceptionCode;

    public GlobalBusinessException(ExceptionCodeInterface exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
