package org.firespoon.fsbotserver.handler;

import org.firespoon.fsbotserver.model.Result;
import org.firespoon.fsbotserver.model.ResultFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public Result<Void> globalExceptionHandler(Throwable e){
        e.printStackTrace();
        return ResultFactory.fail(e.getMessage());
    }
}
