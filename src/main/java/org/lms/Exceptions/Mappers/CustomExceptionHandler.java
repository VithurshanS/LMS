package org.lms.Exceptions.Mappers;

import org.jboss.logging.Logger;
import org.lms.Dto.ExceptionResponse;
import org.lms.Exceptions.BaseException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<BaseException> {
    
    private static final Logger LOG = Logger.getLogger(CustomExceptionHandler.class);

    @Override
    public Response toResponse(BaseException exception) {
        switch (exception.getErrorType()) {
            case NOT_FOUND:
                LOG.warn("Resource not found: " + exception.getMessage());
                break;
            case BUSINESS_ERROR:
                LOG.error("Business error: " + exception.getMessage());
                break;
            case AUTH_ERROR:
            case FORBIDDEN:
                LOG.warn("Security error: " + exception.getMessage());
                break;
            default:
                LOG.error("Custom exception occurred: " + exception.getMessage(), exception);
        }
        
        ExceptionResponse response = new ExceptionResponse(
            exception.getMessage(),
            exception.getErrorType()
        );
        
        return Response
                .status(response.getHttpStatus())
                .entity(response)
                .build();
    }
}
