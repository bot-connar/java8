package com.controller;

import com.entity.User;
import com.repositories.UserRepository;
import com.utils.CheckUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository repository;
//    @Autowired
//    private UserRepository repository;
//    官方推荐使用构造函数注入

    public UserController(UserRepository repository) {
        this.repository=repository;
    }

    /*
    * 查询方法
    * */

    @GetMapping("/all")
    public Flux<User> getAll() {
        return repository.findAll();
    }

    /*查询方法
    * 流式返回数据*/
    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getStreamAll() {
        return repository.findAll();
    }

    /*根据id查询*/
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getById(@PathVariable("id") String id) {
        return this.repository
                .findById(id)
                .map(user -> new ResponseEntity<>(user,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /*新增方法*/
//    postman返回了一个对象
    @PostMapping("/")
    public Mono<User> add(@Valid @RequestBody User user){
        //对特殊的名称进行校验，校验失败时捕获自定义的异常，并交给切面处理类处理
        CheckUtil.checkName(user.getName());
        //如果数据库中包含传入的user的id，则为修改，不存在则是新增
        //spring data jpa 里，新增和修改都是save 数据库中存在该用户id就修改，没有就是新增
        //根据实际情况置空id
        user.setId(null);
        return this.repository.save(user);
    }

    /*修改方法*/
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> update(@Valid @RequestBody User user, @PathVariable("id")String id) {
        CheckUtil.checkName(user.getName());
        return this.repository
                .findById(id)
                //你要操作数据，并且返回一个Mono这个时候用flatMap
                //如果不是操作数据，只是转换数据用map
                .flatMap(u -> {
                    //这里的u中包含了id，如果用户忘记填id就会导致错误
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.repository.save(u);
                }).map(u->new ResponseEntity<>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /*删除方法*/
    @DeleteMapping ("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {

        /*return this.repository
                .findById(id)
                //你要操作数据，并且返回一个Mono这个时候用flatMap
                //如果不是操作数据，只是转换数据用map
                .flatMap(user -> this.repository.delete(user))
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));*/

        return this.repository
                .findById(id)
                //你要操作数据，并且返回一个Mono这个时候用flatMap
                //如果不是操作数据，只是转换数据用map
                .flatMap(user -> this.repository.delete(user))
                .map(unused -> new ResponseEntity<>(unused,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /*根据年龄查找一个年龄段的用户*/
    @GetMapping("/age/{start}/{end}")
    public Flux<User> findByAgeBetween(@PathVariable("start") int start, @PathVariable("end") int end) {
        return this.repository.findByAgeBetween(start, end);
    }
    /*根据年龄查找一个年龄段的用户*/
    @GetMapping(value = "/stream/age/{start}/{end}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAgeBetween(@PathVariable("start") int start, @PathVariable("end") int end) {
        return this.repository.findByAgeBetween(start, end);
    }


    /*根据年龄查找一个年龄段的用户*/
    @GetMapping("/old")
    public Flux<User> old() {
        return this.repository.oldUsers();
    }
    /*根据年龄查找一个年龄段的用户*/
    @GetMapping(value = "/stream/old",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamOld() {
        return this.repository.oldUsers();
    }
}
