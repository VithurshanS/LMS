package org.lms.Dto;
import java.util.Map;

public class ExceptionResponse {
    private String message;
    private ErrorType errorType;
    private Map<String,String> fieldErrors;

    public ExceptionResponse(String message, ErrorType errorType){
        this.message=message;
        this.errorType = errorType;
    }

    public int getHttpStatus(){
        return errorType.getHttpStatus();
    }
    public void setFieldErrors(Map<String,String> errors){
        this.fieldErrors = errors;
    }

    public String getMessage() {
        return message;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
