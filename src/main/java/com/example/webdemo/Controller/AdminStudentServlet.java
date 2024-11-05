package com.example.webdemo.Controller;


import com.example.webdemo.Entity.Student;
import com.example.webdemo.Service.IAdminService;
import com.example.webdemo.Service.Impl.AdminService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/adminStudent")
public class AdminStudentServlet extends HttpServlet {
    private final IAdminService adminService = new AdminService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf8");
        request.setCharacterEncoding("utf8");
        String respJson = adminService.SelectAllStudents();
        response.getWriter().write(respJson);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json;charset=utf8");
        request.setCharacterEncoding("utf8");
        String respJson = null;
        if ("delete".equals(request.getParameter("action"))){
            System.out.println("进入删除学生接口");
            respJson = adminService.deleteStudentById(Long.parseLong(request.getParameter("id")));
            response.getWriter().write(respJson);
            return;
        }

        if("insert".equals(request.getParameter("action"))){
            System.out.println("进入添加学生接口");
            System.out.println("待添加学生学号:"+request.getParameter("snumber"));
            Student student = new Student();
            student.setSnumber(request.getParameter("snumber"));
            student.setName(request.getParameter("name"));
            student.setGender(request.getParameter("gender"));
            student.setAge(Integer.parseInt(request.getParameter("age")));
            student.setClassId(request.getParameter("classId"));
            student.setPassword(request.getParameter("password"));
            System.out.println("待添加学生密码:"+request.getParameter("password"));
            respJson = adminService.insertStudentByEntity(student);
            response.getWriter().write(respJson);
            return;
        }

        System.out.println("进入修改学生接口");
        Student student = new Student();
        System.out.println(request.getParameter("id"));
        student.setId(Long.parseLong(request.getParameter("id")));
        student.setName(request.getParameter("name"));
        student.setSnumber(request.getParameter("snumber"));
        student.setGender(request.getParameter("gender"));
        student.setAge(Integer.parseInt(request.getParameter("age")));
        student.setClassId(request.getParameter("classId"));
        student.setPassword(request.getParameter("password"));
        respJson = adminService.updateStudentByEntity(student);
        response.getWriter().write(respJson);
    }
}
