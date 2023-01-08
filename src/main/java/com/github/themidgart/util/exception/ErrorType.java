package com.github.themidgart.util.exception;

import org.springframework.http.HttpStatus;

public enum ErrorType {
    APP_ERROR("AppError", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND("DataNotFound", HttpStatus.UNPROCESSABLE_ENTITY),
    DATA_ERROR("DataError", HttpStatus.CONFLICT),
    VALIDATION_ERROR("ValidationError", HttpStatus.UNPROCESSABLE_ENTITY),
    WRONG_REQUEST("WongRequest", HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final HttpStatus status;

    ErrorType(String errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
