package com.example.webdemo.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IIndicatorService {
    public String selectAllIndicators(String snumber, String tnumber) throws JsonProcessingException;
}
