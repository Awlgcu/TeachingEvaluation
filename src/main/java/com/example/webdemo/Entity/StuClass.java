package com.example.webdemo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StuClass {
    private Long id;
    private String classId;
    private String headTeacher;
    private String ChineseTeacher;
    private String MathTeacher;
    private String EnglishTeacher;
    private Integer isDelete;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
