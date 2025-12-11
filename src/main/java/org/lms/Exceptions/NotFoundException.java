package org.lms.Exceptions;

import org.lms.Dto.ErrorType;

public class NotFoundException extends BaseException{
    public NotFoundException(String message){
        super(message, ErrorType.NOT_FOUND);
    }
}
