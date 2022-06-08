package com.repositories;

import com.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {

    /*
    * Jpa的强大之处
    * 可以自动生成sql*/

    Flux<User> findByAgeBetween(int start, int end);

    /*找出年龄段20-30的用户*/
    //使用mongodb的语法
    @Query("{'age':{'$gte':20,'$lte':30}}")
    Flux<User> oldUsers();
}
