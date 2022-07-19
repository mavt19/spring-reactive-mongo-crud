package com.bns.reactive.mongo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody HttpErrorInfo handlerNotFoundException(ServerHttpRequest request, Exception exception){
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, request, exception);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException.class)
    public @ResponseBody HttpErrorInfo handlerInvalidInputException(ServerHttpRequest request, Exception exception){
        return createHttpErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY, request, exception);
    }
    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, ServerHttpRequest request, Exception exception) {
        String path = request.getPath().pathWithinApplication().value();
        return  new HttpErrorInfo(ZonedDateTime.now(), path, httpStatus, exception.getMessage());
    }
}
