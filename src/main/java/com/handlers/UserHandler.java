package com.handlers;

import com.entity.User;
import com.repositories.UserRepository;
import com.utils.CheckUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private UserRepository repository;
    public UserHandler(UserRepository repository) {
        this.repository = repository;
    }

    /*查询所有用户*/
    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(this.repository.findAll(), User.class);
    }

    /*添加用户*/
    //postman返回了一个list
    public Mono<ServerResponse> add(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        /*
        //使用block获取用户会阻塞线程，在2.0.1中会报错
        User user = userMono.block();
        //参数校验
        CheckUtil.checkName(user.getName());
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(this.repository.saveAll(userMono), User.class);
        */
        return userMono.flatMap(user -> {
            CheckUtil.checkName(user.getName());
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(this.repository.save(user), User.class);
        });
    }

    /*根据id删除一个用户*/
    public Mono<ServerResponse> deleteById(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.repository.findById(id)
                .flatMap(user -> this.repository.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getOne(ServerRequest request) {
        String id = request.pathVariable("id");
        //Mono<Mono<ServerResponse>> map = this.repository.findById(id).map(user -> ServerResponse.ok().body(user, User.class));
        //map将1个Mono中的元素转化为另一个元素，并放入Mono中
        //Mono<Integer>       map1 = Mono.just("1").map(Integer::valueOf);

        //flatMap将2个Mono转化为1个Mono，解决Mono嵌套
        //Mono<Integer>       map2 = Mono.just("1").flatMap(o -> Mono.just(Integer.valueOf(o)));

        //解决Mono嵌套
        //Mono<Mono<Integer>> map3 = Mono.just("1").map(o -> Mono.just(Integer.valueOf(o)));

        return this.repository.findById(id).flatMap(user ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(user), User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        return request.bodyToMono(User.class).flatMap(user ->
             this.repository.findById(id).flatMap(u->{
                CheckUtil.checkName(user.getName());
                 u.setName(user.getName());
                 u.setAge(user.getAge());
                return ServerResponse.ok().body(this.repository.save(u), User.class);
            }).switchIfEmpty(ServerResponse.notFound().build()));

        /*return this.repository.findById(id).flatMap(user ->
            userMono.flatMap(u -> {
                CheckUtil.checkName(user.getName());
                user.setName(u.getName());
                user.setAge(u.getAge());
                return ServerResponse.ok().body(this.repository.save(user), User.class);
            })).switchIfEmpty(ServerResponse.notFound().build());*/
    }
}
