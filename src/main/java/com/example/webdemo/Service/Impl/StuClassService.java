package com.example.webdemo.Service.Impl;

import com.example.webdemo.Dao.StuClassDao;
import com.example.webdemo.Entity.BaseResponse;
import com.example.webdemo.Entity.Teacher;
import com.example.webdemo.Service.IStuClassService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class StuClassService implements IStuClassService {
    private final StuClassDao stuClassDao = new StuClassDao();
    @Override
    //服务层直接返回jsonstring!
    public String selectTeachersByClassId(String classId) throws JsonProcessingException {
        List<Teacher> teacherList = stuClassDao.selectTeachersByClassId(classId);
        return BaseResponse.success(1, "成功查询到老师", teacherList);
//        return objectMapper.writeValueAsString(teacherList);
    }
}
