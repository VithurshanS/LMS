package org.lms.Exceptions;

import org.lms.Dto.ErrorType;

public class ConflictException extends BaseException{
    public ConflictException(String message){
        super(message, ErrorType.CONFLICT);
    }

}
