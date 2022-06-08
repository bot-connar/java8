package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestController {

    @GetMapping(value = "1")
    public String test1(){
        log.info("test1() start");
        String res = createStr();
        log.info("test1() end");
        return res;
    }

    @GetMapping("2")
    public Mono<String> test2(){
        log.info("test2() start");
        Mono<String> res = Mono.fromSupplier(this::createStr);
        log.info("test2() end");
        return res;
    }


    @GetMapping(value = "3",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> test3() {
        log.info("test3() start");
        String[] t = {"1", "2", "3"};
        Flux<String> res=Flux.fromStream(Arrays.stream(t).map(this::tt));
        log.info("test3() end");
        return res;
    }

    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "String";
    }

    private String tt(String a) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "flux data is " + a;
    }


}
