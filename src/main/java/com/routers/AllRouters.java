package com.routers;

import com.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Component
public class AllRouters {
    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler){
        //nest表示层级关系
        return RouterFunctions
                .nest(path("/user"),
                        //route和andRoute来映射路径--》路由函数(HandlerFunction)
                        RouterFunctions
                                .route(GET("/all"),userHandler::getAll)
                                .andRoute(GET("/{id}"),userHandler::getOne)
                                .andRoute(POST("/").and(accept(MediaType.APPLICATION_JSON)),userHandler::add)
                                .andRoute(DELETE("/{id}"),userHandler::deleteById)
                                .andRoute(PUT("/{id}").and(accept(MediaType.APPLICATION_JSON)),userHandler::update)
                );
    }
}
