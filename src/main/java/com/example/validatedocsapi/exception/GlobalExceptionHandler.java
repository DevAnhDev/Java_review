package com.example.validatedocsapi.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.xml.bind.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //handler exception custom
    //400
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        String messsage = e.getMessage();
        if(e instanceof MethodArgumentNotValidException) {//400 validate form
            int start = messsage.lastIndexOf("[");
            int end = messsage.lastIndexOf("]");
            messsage = messsage.substring(start + 1, end-1);
            errorResponse.setMessage(messsage);
            errorResponse.setError("Payload Invalid!");

        }else if(e instanceof ConstraintViolationException) {
            messsage = messsage.substring(messsage.indexOf(" ")+1);
            errorResponse.setError("Parameter Invalid!");
            errorResponse.setMessage(messsage);

        }
        return errorResponse;
    }

    //500
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalException(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        if(e instanceof MethodArgumentNotValidException) {
            errorResponse.setMessage("Failed to convert value of type!");
        }
        return errorResponse;
    }

//    @ExceptionHandler({ResourceNotFoundException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorResponse handelNotfoundExeption(ResourceNotFoundException e, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse();
//        errorResponse.setTimestamp(new Date());
//        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
//        errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
//        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
//        errorResponse.setMessage(e.getMessage());
//        return errorResponse;
//    }


}
