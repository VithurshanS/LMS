package org.lms.Exceptions;


import org.lms.Dto.ErrorType;

public class BusinessException extends BaseException{
    public BusinessException(String message){
        super(message,ErrorType.BUSINESS_ERROR);
    }
}
