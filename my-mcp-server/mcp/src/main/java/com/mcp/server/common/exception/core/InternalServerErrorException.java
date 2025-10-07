package com.mcp.server.common.exception.core;

import org.springframework.http.HttpStatus;

import com.mcp.server.common.exception.common.enums.ExceptionError;

import java.util.Map;

public class InternalServerErrorException extends ApplicationException {
    public InternalServerErrorException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionError.UNEXPECTED);
    }

    public InternalServerErrorException(ExceptionError exceptionError) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, exceptionError);
    }

    public InternalServerErrorException(ExceptionError exceptionError, Map<String, Object> errorValues) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, exceptionError, errorValues);
    }
}
