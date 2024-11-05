package com.example.webdemo.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class EvaluationIndicator {
    private Long id;
    private String indicator;
    private Integer isDelete;
    private Timestamp createTime;
    private Timestamp updateTime;
}
