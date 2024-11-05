package com.example.webdemo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class EvaluationResult {
    private Long id;
    private String tnumber;
    private String tname;
    private String snumber;
    private String sname;
    //一条对应一个分数
    private String results;
    private Long indicatorId;

    private Integer isDelete;
    private Timestamp createTime;
    private Timestamp updateTime;
}
