package com.example.webdemo.Service;

import com.example.webdemo.Entity.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.Filter;

import java.util.List;

public interface IStudentService {
    public Student selectStudentById(Long id);
    public String selectStudentBySnumber(String snumber) throws JsonProcessingException;
    public List<Student> selectAllStudent();
    public void insertStudentByEntity(Student student);
    public String updateStudentByEntity(Student student);
    public String updateStudentPassword(String snumber, String oldPassword, String newPassword1, String newPassword2);
    public void deleteStudentBySnumber(String snumber);
    public String selectClassIdBySnumber(String snumber);

    public String updateSelf(String snumber, String name, String age, String gender);

    public Student selectStuObjBySnumber(String snumber);


}
