package com.handlers;

import com.exceptions.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;

@Component
@Order(-1)//需要设置更高的优先级，数值越小优先级越高
public class ExceptionHandler implements WebExceptionHandler {

    //自定义异常捕捉类，重写handle方法，自定义处理异常
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        DataBufferFactory factory = new DefaultDataBufferFactory();
        return exchange.getResponse().writeWith(Mono.just(factory.wrap(errorMsg(ex).getBytes(StandardCharsets.UTF_8))));
    }

    private String errorMsg(Throwable ex) {
        if (ex instanceof CheckException){
            //已知异常
            CheckException exception = (CheckException) ex;
            return exception.getFieldName() + " invalid value -----> " + exception.getFieldValue();
        } else {
            //未知异常需要打印堆栈
            ex.printStackTrace();
            return ex.toString();
        }
    }
}
