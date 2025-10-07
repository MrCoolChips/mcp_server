package com.mcp.server.common.exception.core;

import org.springframework.http.HttpStatus;

import com.mcp.server.common.exception.common.enums.ExceptionError;

import java.util.Map;

public class BadRequestException extends ApplicationException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, ExceptionError.BAD_REQUEST);
    }

    public BadRequestException(ExceptionError exceptionError) {
        super(HttpStatus.BAD_REQUEST, exceptionError);
    }

    public BadRequestException(ExceptionError exceptionError, Map<String, Object> errorValues) {
        super(HttpStatus.BAD_REQUEST, exceptionError, errorValues);
    }
}
