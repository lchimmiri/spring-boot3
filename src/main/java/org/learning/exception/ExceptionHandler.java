package org.learning.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

   @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ProblemDetail onException(RuntimeException runtimeException){
        var pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), runtimeException.getMessage());
        return pd;
    }
}
