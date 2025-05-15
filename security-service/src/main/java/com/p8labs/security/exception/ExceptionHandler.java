package com.p8labs.security.exception;

import com.p8labs.common.dto.ErrorResponse;
import com.p8labs.common.enums.ExceptionCodeInterface;
import com.p8labs.common.enums.ExceptionMessage;
import com.p8labs.common.exception.GlobalBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
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
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                                        ExceptionMessage.CONTACT_ADMIN.getCode(),
                                                        ExceptionMessage.CONTACT_ADMIN.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleException(AuthorizationDeniedException e) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                ExceptionMessage.CONTACT_ADMIN.getCode(),
                ExceptionMessage.CONTACT_ADMIN.getMessage());
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }


}
