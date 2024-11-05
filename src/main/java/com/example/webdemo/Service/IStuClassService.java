package com.example.webdemo.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IStuClassService {
    public String selectTeachersByClassId(String id) throws JsonProcessingException;
}
