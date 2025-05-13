package com.p8labs.common.dto;

import com.p8labs.common.enums.CommonCode;
import com.p8labs.common.enums.CommonCodeInterface;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonResponse {
    private int statusCode = HttpStatus.OK.value();
    private String code = CommonCode.ResponseCode.SUCCESS.getCode();
    private String message = CommonCode.ResponseCode.SUCCESS.getMessage();

    public CommonResponse(int statusCode,
                          CommonCodeInterface commonCodeInterface) {
        this.statusCode = statusCode;
        this.code = commonCodeInterface.getCode();
        this.message = commonCodeInterface.getMessage();
    }



    public CommonResponse() {
        this.statusCode = HttpStatus.OK.value();;
        this.code = CommonCode.ResponseCode.SUCCESS.getCode();
        this.message = CommonCode.ResponseCode.SUCCESS.getMessage();
    }

    public CommonResponse(int statusCode,
                          String code,
                          String message) {
        this.statusCode = statusCode;
        this.code = code;
        this.message = message;
    }

    public static CommonResponse createSuccess() {
        return new CommonResponse(HttpStatus.OK.value()
                                  ,CommonCode.ResponseCode.SUCCESS);
    }
}