package com.utils;

import com.exceptions.CheckException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/*
Spring 使用一些宽松的规则来绑定属性。因此，以下变体都绑定到属性hostName：
mail.hostName、mail.hostname、mail.host_name、mail.host-name、mail.HOST_NAME
*/
@Component
@ConfigurationProperties(prefix = "cus")
public class CheckUtil {
    //通过ConfigurationProperties注解来 配置属性类
    public static String[] INVALID_NAMES;

    //standard setter方法
    public void setInvalidNames(String[] invalidNames) {
        INVALID_NAMES = invalidNames;
    }
    public static void checkName(String value){
        Arrays.stream(INVALID_NAMES)
                .filter(s->s.equalsIgnoreCase(value))
                .findAny()
                .ifPresent(s->{
                    throw new CheckException("name", value);
                });
    }
    //给静态变量注入值

    /*
    * 1，若要给静态变量赋值，可以使用set()方法注入。
    * 类上加入@Component注解
    * @Value注解标记set方法
    * 方法名（例如setOssUrl）和参数名（例如ossUrl）可以任意命名
    * */

    /*
    * 2，如果你觉得@value注解麻烦。可以使用@ConfigurationProperties注解代替,这样比较简洁
    * 前缀要写合适
    * 方法名（例如setOssUrl）必须和属性保持一致,例如写为setUrl()会注入失败
    * 类上加入@Component注解
    * */

    /*
    * 3，类上加入@Component注解
    * @PostConstruct注解修饰的方法中进行赋值操作
    * */
}
