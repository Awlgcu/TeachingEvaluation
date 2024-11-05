package com.example.webdemo.Service.Impl;

import com.example.webdemo.Dao.EvaluationIndicatorDao;
import com.example.webdemo.Dao.EvaluationResultDao;
import com.example.webdemo.Entity.EvaluationIndicator;
import com.example.webdemo.Entity.EvaluationResult;
import com.example.webdemo.Service.IIndicatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class IndicatorsService implements IIndicatorService {
    private EvaluationResultDao erDao = new EvaluationResultDao();
    private EvaluationIndicatorDao eiDao = new EvaluationIndicatorDao();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String selectAllIndicators(String snumber, String tnumber) throws JsonProcessingException {
        String respJson = null;
        List<EvaluationIndicator> indicators = eiDao.selectAll();
        if (indicators.isEmpty()){
            respJson ="{\"message\": \"暂时没有评价指标\"}";
            return respJson;
        }
        List<EvaluationResult> results = erDao.selectBySandT(snumber, tnumber);
        if (results.size()!=0){
            respJson ="{\"message\": \"您已评价该老师\"}";
            return respJson;
        }

        return objectMapper.writeValueAsString(indicators);
    }
}
