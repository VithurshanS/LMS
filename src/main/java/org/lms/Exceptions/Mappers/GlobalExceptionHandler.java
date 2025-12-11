package org.lms.Exceptions.Mappers;

import org.jboss.logging.Logger;
import org.lms.Dto.ErrorType;
import org.lms.Dto.ExceptionResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {
    
    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public Response toResponse(Exception exception) {
        LOG.error("Unhandled exception occurred: " + exception.getMessage(), exception);
        
        ExceptionResponse response = new ExceptionResponse(
            exception.getMessage(),
            ErrorType.SERVER_ERROR
        );
        
        return Response.status(response.getHttpStatus()).entity(response).build();
    }
}
