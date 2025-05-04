package com.p8labs.security.common.exception;

import com.p8labs.security.common.dto.ErrorResponse;
import com.p8labs.security.common.enums.ExceptionCodeInterface;
import com.p8labs.security.common.enums.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.p8labs.security.common.enums.ExceptionMessage.RETRY;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(GlobalBusinessException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(GlobalBusinessException e) {
        ExceptionCodeInterface exceptionCode = e.getExceptionCode();

        HttpStatus httpStatus = exceptionCode.getHttpStatus();
        String exposeMessage = exceptionCode.getExposeMessage();
        String code = exceptionCode.getCode();
        String message = exceptionCode.getMessage();

        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), code, message, exposeMessage);

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                                        ExceptionMessage.CONTACT_ADMIN.getCode(),
                                                        ExceptionMessage.CONTACT_ADMIN.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
