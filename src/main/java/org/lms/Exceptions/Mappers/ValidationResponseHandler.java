package org.lms.Exceptions.Mappers;

import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;
import org.lms.Dto.ErrorType;
import org.lms.Dto.ExceptionResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ValidationResponseHandler implements ExceptionMapper<ConstraintViolationException> {
    
    private static final Logger LOG = Logger.getLogger(ValidationResponseHandler.class);

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        LOG.warn("Validation error occurred: " + exception.getMessage());
        
        ExceptionResponse exceptionResponse = new ExceptionResponse("Validation error", ErrorType.VALIDATION_ERROR);

        Map<String,String> errors = new HashMap<>();
        for(ConstraintViolation<?> violation : exception.getConstraintViolations()){
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        exceptionResponse.setFieldErrors(errors);

        return Response.status(exceptionResponse.getHttpStatus())
                .entity(exceptionResponse)
                .build();
    }
}
