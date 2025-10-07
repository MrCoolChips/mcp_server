package com.mcp.server.common.exception.common.enums;

import lombok.Getter;

@Getter
public enum ExceptionError {

    // ========= GENERAL ERRORS ========= //
    UNEXPECTED("GEN_001", "An unexpected internal server error occurred."),
    BAD_REQUEST("GEN_002", "Bad request. Please check the input parameters."),
    BAD_GATEWAY("GEN_003", "Bad gateway error."),
    UNAUTHORIZED("GEN_004", "Authorization failed."),
    FORBIDDEN("GEN_005", "Access forbidden."),
    CONFLICT("GEN_006", "Conflict error."),
    TOO_MANY_REQUESTS("GEN_007", "Too many requests."),
    MISSING_PROPERTY("GEN_008", "Required property is missing from the request."),
    DATA_INTEGRITY_VIOLATION("GEN_009", "The operation violates data integrity constraints."),
    OPERATION_NOT_ALLOWED("GEN_010", "This operation is not allowed in the current state."),
    UNREACHABLE("GEN_011", "A code path marked as unreachable has been executed. This indicates a programming or control flow error."),
    GATEWAY_TIMEOUT("GEN_012", "The gateway has timed out."),
    EXTERNAL_API_CALL_FAILED("INT_202", "An error occurred while calling an external system.");

    private final String code;
    private final String message;

    ExceptionError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
