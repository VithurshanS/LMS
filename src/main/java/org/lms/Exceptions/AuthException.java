package org.lms.Exceptions;

import org.lms.Dto.ErrorType;

public class AuthException extends BaseException {
    public AuthException(String message) {
        super(message, ErrorType.AUTH_ERROR);

    }

}
