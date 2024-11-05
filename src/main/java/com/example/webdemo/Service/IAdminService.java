package com.example.webdemo.Service;

import com.example.webdemo.Entity.Admin;
import com.example.webdemo.Entity.EvaluationIndicator;
import com.example.webdemo.Entity.Student;
import com.example.webdemo.Entity.Teacher;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IAdminService {
    public String SelectAllStudents() throws JsonProcessingException;
    public String updateStudentByEntity(Student student);
    public String deleteStudentById(Long id);
    public String insertStudentByEntity(Student student);

    public String SelectAllTeachers() throws JsonProcessingException;
    public String updateTeacherByEntity(Teacher teacher);
    public String deleteTeacherById(Long id);
    public String insertTeacherByEntity(Teacher teacher);

    public String SelectAllAdmins() throws JsonProcessingException;
    public String updateAdminByEntity(Admin admin);
    public String deleteAdminById(Long id);
    public String insertAdminByEntity(Admin admin);

    public String SelectAllIndicators() throws JsonProcessingException;
    public String updateIndicatorByEntity(EvaluationIndicator indicator);
    public String deleteIndicatorById(Long id);
    public String insertIndicatorByEntity(EvaluationIndicator indicator);

    public String selectAllResults() throws JsonProcessingException;

    public String updateAdminPassword(String snumber, String oldPassword, String newPassword1,String newPassword2);
    public String selectAdminBySnumber(String snumber) throws JsonProcessingException;

    public String updateAdminSelfByEntity(Admin admin);
    public Admin selectAdminObjBySnumber(String snumber);
}
