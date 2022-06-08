package com;

import com.utils.CheckUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class Java8Application {
    public static void main(String[] args) {
        SpringApplication.run(Java8Application.class, args);
        System.out.println(Arrays.toString(CheckUtil.INVALID_NAMES));
    }
}
