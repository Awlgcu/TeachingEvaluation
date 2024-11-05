package com.example.webdemo.Service.Impl;

import com.example.webdemo.Dao.StudentDao;
import com.example.webdemo.Entity.Student;
import com.example.webdemo.Service.IStudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.util.StringUtils;

import java.util.List;

public class StudentService implements IStudentService {
    private final StudentDao studentDao = new StudentDao();
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Student selectStuObjBySnumber(String snumber) {
        return studentDao.selectBySnumber(snumber);
    }
    @Override
    public Student selectStudentById(Long id) {
        Student student = studentDao.selectById(id);
        if (student==null){
            System.out.println("server:未查询到数据");
        }
        return student;
    }

    @Override
    public String selectStudentBySnumber(String snumber) throws JsonProcessingException {
        Student student = studentDao.selectBySnumber(snumber);
        if (student==null){
            return "{\"message\": \"未找到该学生\"}";
        }
        return objectMapper.writeValueAsString(student);
    }

    @Override
    public List<Student> selectAllStudent() {
        List<Student> students = studentDao.selectAll();
        if (students.isEmpty()){
            System.out.println("server:未查询到数据");
        }
        return students;
    }


    @Override
    public void insertStudentByEntity(Student student) {

    }

    @Override
    public String updateStudentByEntity(Student student) {
        //dao层返回false表示没能被修改
        return null;
    }
    @Override
    public String updateSelf(String snumber, String name, String age, String gender) {
        if (StringUtils.isNullOrEmpty(snumber)||StringUtils.isNullOrEmpty(age)||StringUtils.isNullOrEmpty(gender)){
            return "{\"message\":\"所有信息不能为空!\"}";
        }
        boolean res = studentDao.updateSelf(snumber, name, age, gender);
        if (res){
            return "{\"message\":\"个人信息修改成功!\"}";
        }else{
            return "{\"message\":\"修改失败!\"}";
        }
    }



    @Override
    public String updateStudentPassword(String snumber,String oldPassword, String newPassword1, String newPassword2) {
        String respJson = null;
        if (!newPassword1.equals(newPassword2)){
                respJson = "{\"message\":\"两次输入密码不一致!\"}";
                 System.out.println("两次输入密码不一致!");
                return respJson;
        }
        if (!oldPassword.equals(studentDao.selectBySnumber(snumber).getPassword())){
            respJson = "{\"message\":\"旧密码错误!\"}";
            System.out.println("旧密码错误!");
            return respJson;
        }
        studentDao.updatePassword(snumber, oldPassword, newPassword1);
        System.out.println("修改成功,请重新登录");
        return "{\"message\":\"修改成功,请重新登录!\"}";
    }

    @Override
    public void deleteStudentBySnumber(String snumber) {

    }

    @Override
    public String selectClassIdBySnumber(String snumber) {
        String classId = studentDao.selectClassIdBySnumber(snumber);
        return classId;
    }
}
