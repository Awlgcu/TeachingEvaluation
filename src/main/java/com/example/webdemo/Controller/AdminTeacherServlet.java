package com.example.webdemo.Controller;

import com.example.webdemo.Entity.Teacher;
import com.example.webdemo.Service.IAdminService;
import com.example.webdemo.Service.Impl.AdminService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminTeacher")
public class AdminTeacherServlet extends HttpServlet {
    private final IAdminService adminService = new AdminService();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        System.out.println("进入查看老师接口");

        String respJson = adminService.SelectAllTeachers();
        response.getWriter().write(respJson);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");

        String respJson = null;
        if ("delete".equals(request.getParameter("action"))){
            System.out.println("进入删除老师接口");
            respJson = adminService.deleteTeacherById(Long.parseLong(request.getParameter("id")));
            response.getWriter().write(respJson);
            return;
        }

        if("insert".equals(request.getParameter("action"))){
            System.out.println("进入添加老师接口");
            System.out.println("待添加老师工号:"+request.getParameter("tnumber"));
            Teacher teacher = new Teacher();
            teacher.setTnumber(request.getParameter("tnumber"));
            teacher.setName(request.getParameter("name"));
            teacher.setGender(request.getParameter("gender"));
            teacher.setAge(Integer.parseInt(request.getParameter("age")));
            teacher.setPhone(request.getParameter("phone"));
            teacher.setIntroduction(request.getParameter("introduction"));
            respJson = adminService.insertTeacherByEntity(teacher);
            response.getWriter().write(respJson);
            return;
        }

        System.out.println("进入修改老师接口");
        Teacher teacher = new Teacher();
        System.out.println(request.getParameter("id"));
        teacher.setId(Long.parseLong(request.getParameter("id")));
        teacher.setName(request.getParameter("name"));
        teacher.setTnumber(request.getParameter("tnumber"));
        teacher.setGender(request.getParameter("gender"));
        teacher.setAge(Integer.parseInt(request.getParameter("age")));
        teacher.setPhone(request.getParameter("phone"));
        teacher.setIntroduction(request.getParameter("introduction"));


        respJson = adminService.updateTeacherByEntity(teacher);
        response.getWriter().write(respJson);
    }
}
