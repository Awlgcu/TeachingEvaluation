package com.example.webdemo.Service.Impl;

import com.example.webdemo.Dao.EvaluationResultDao;
import com.example.webdemo.Dao.StudentDao;
import com.example.webdemo.Dao.TeacherDao;
import com.example.webdemo.Entity.EvaluationResult;
import com.example.webdemo.Service.IResultsService;

public class ResultsService implements IResultsService {
    private final EvaluationResultDao erDao = new EvaluationResultDao();
    private final StudentDao studentDao = new StudentDao();
    private final TeacherDao teacherDao = new TeacherDao();
    @Override
    public void insertResult(String snumber, String tnumber, String[] marks, String indicators) {
        String[] newIndicators = indicators.split(",");
        String tname = teacherDao.selectByTnumber(tnumber).getName();
        String sname = studentDao.selectBySnumber(snumber).getName();
        EvaluationResult er = new EvaluationResult();
        er.setTnumber(tnumber);
        er.setTname(tname);
        er.setSname(sname);
        er.setSnumber(snumber);
        for(int i=0;i<marks.length;i++){
            er.setResults(marks[i]);
            er.setIndicatorId(Long.parseLong(newIndicators[i]));
            erDao.insertByEntity(er);
        }
    }
}
