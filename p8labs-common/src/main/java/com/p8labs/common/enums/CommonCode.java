package com.p8labs.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CommonCode {


    @RequiredArgsConstructor
    @Getter
    public enum ResponseCode implements CommonCodeInterface {
        SUCCESS("RC200", "success"),
        FAIL("RC400", "fail");

        private final String code;
        private final String message;
    }


}
