package org.lms.Exceptions;

import org.lms.Dto.ErrorType;

public class BaseException extends RuntimeException{
    private ErrorType errorType;
    public BaseException(String message,ErrorType errorType){
        super(message);
        this.errorType=errorType;

    }

    public ErrorType getErrorType(){
        return errorType;
    }
}
