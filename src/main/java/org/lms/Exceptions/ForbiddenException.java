package org.lms.Exceptions;

import org.lms.Dto.ErrorType;

public class ForbiddenException extends BaseException{
    public ForbiddenException(String message){
        super(message, ErrorType.FORBIDDEN);
    }

}
