package com.p8labs.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CommonCode {


    @RequiredArgsConstructor
    @Getter
    public enum ResponseCode implements CommonCodeInterface {
        SUCCESS("RC200", "Success"),
        FAIL("RC400", "Fail");

        private final String code;
        private final String message;
    }


}
