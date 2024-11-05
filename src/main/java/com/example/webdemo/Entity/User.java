package com.example.webdemo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String snumber;
    private String name;
    private String password;
    private String gender;
    private Integer age;
    private Integer isDelete;
    private Timestamp createTime;
    private Timestamp updateTime;
    public User(String snumber, String name){
        this.snumber = snumber;
        this.name = name;
    }
    public static void staticMethod(){
        System.out.println("调用了一次静态方法");
    }
}
