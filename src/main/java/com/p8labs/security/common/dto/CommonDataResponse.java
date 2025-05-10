package com.p8labs.security.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommonDataResponse<T> extends CommonResponse {
    private T data;

    public CommonDataResponse(T data) {
        super();
        this.data = data;
    }
}