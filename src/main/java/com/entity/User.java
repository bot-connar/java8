package com.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "user")
@Data
public class User {

    @Id
    private String id;

    //hibernate 参数校验
    @NotBlank
    private String name;

    //hibernate 参数校验
    @Range(min = 10, max = 90)
    private int age;
}
