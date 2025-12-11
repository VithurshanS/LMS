package org.lms.Dto;

public enum ErrorType {
    VALIDATION_ERROR(400),
    BUSINESS_ERROR(400),
    NOT_FOUND(404),
    AUTH_ERROR(401),
    FORBIDDEN(403),
    CONFLICT(409),
    SERVER_ERROR(500);

    private final int httpStatus;

    ErrorType(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
