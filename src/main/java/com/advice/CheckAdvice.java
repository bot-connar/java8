package com.advice;

import com.exceptions.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

//配合hibernate的参数校验
@ControllerAdvice
public class CheckAdvice {

    //切面捕捉hibernate抛出的异常  @Valid
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> check(WebExchangeBindException exception){
        String res= exception.getFieldErrors()
                .stream()
                .map(e -> e.getField() + " : " + e.getDefaultMessage())
                .reduce("this message from CheckAdvice(配合hibernate的参数校验)\n", (s1, s2) -> s1 + s2 + "\n");
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    //切面捕捉自定义的异常，并自定义返回信息
    /*捕获自定义的异常，结合切面返回异常的处理结果*/
    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> checkName(CheckException exception){
        String res = exception.getFieldName() + " -错误的值(error value)-> " + exception.getFieldValue();
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
