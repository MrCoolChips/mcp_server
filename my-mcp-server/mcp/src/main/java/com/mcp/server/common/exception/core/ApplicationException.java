package com.mcp.server.common.exception.core;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import com.mcp.server.common.exception.common.enums.ExceptionError;

import java.util.Map;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String errorMessage;
    private final String errorCode;
    private final Map<String, Object> errorValues;

    public ApplicationException(HttpStatus statusCode, ExceptionError exceptionError) {
        super(exceptionError.getMessage());
        this.statusCode = statusCode;
        this.errorMessage = exceptionError.getMessage();
        this.errorCode = exceptionError.getCode();
        this.errorValues = null;
    }

    public ApplicationException(HttpStatus statusCode, ExceptionError exceptionError, Map<String, Object> errorValues) {
        super(exceptionError.getMessage());
        this.statusCode = statusCode;
        this.errorMessage = exceptionError.getMessage();
        this.errorCode = exceptionError.getCode();
        this.errorValues = errorValues;
    }

}
