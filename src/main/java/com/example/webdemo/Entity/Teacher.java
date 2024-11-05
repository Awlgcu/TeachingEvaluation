package com.example.webdemo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Teacher {
    private Long Id;
    private String tnumber;
    private String name;
    private String gender;
    private Integer age;
    private String phone;
    private String introduction;
    private Integer isDelete;
    private Timestamp createTime;
    private Timestamp updateTime;
}
