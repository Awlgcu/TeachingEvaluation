package com.example.webdemo.Controller;


import com.example.webdemo.Entity.Admin;
import com.example.webdemo.Service.IAdminService;
import com.example.webdemo.Service.Impl.AdminService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/adminAdmin")
public class AdminAdminServlet extends HttpServlet {
    private final IAdminService adminService = new AdminService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        String respJson = adminService.SelectAllAdmins();
        response.getWriter().write(respJson);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        String respJson = null;
        if ("delete".equals(request.getParameter("action"))){
            System.out.println("进入删除管理员接口");
            respJson = adminService.deleteAdminById(Long.parseLong(request.getParameter("id")));
            response.getWriter().write(respJson);
            return;
        }

        if("insert".equals(request.getParameter("action"))){
            System.out.println("进入添加管理员接口");
            System.out.println("待添加管理员工号:"+request.getParameter("snumber"));
            Admin admin = new Admin();
            admin.setSnumber(request.getParameter("snumber"));
            admin.setName(request.getParameter("name"));
            admin.setGender(request.getParameter("gender"));
            admin.setAge(Integer.parseInt(request.getParameter("age")));
            admin.setPhone(request.getParameter("phone"));
            admin.setPassword(request.getParameter("password"));
            System.out.println("待添加管理员密码:"+request.getParameter("password"));
            respJson = adminService.insertAdminByEntity(admin);
            response.getWriter().write(respJson);
            return;
        }

        System.out.println("进入修改管理员接口");
        Admin admin = new Admin();
        System.out.println(request.getParameter("id"));
        admin.setId(Long.parseLong(request.getParameter("id")));
        admin.setName(request.getParameter("name"));
        admin.setSnumber(request.getParameter("snumber"));
        admin.setGender(request.getParameter("gender"));
        admin.setAge(Integer.parseInt(request.getParameter("age")));
        admin.setPhone(request.getParameter("phone"));
        admin.setPassword(request.getParameter("password"));
        respJson = adminService.updateAdminByEntity(admin);
        response.getWriter().write(respJson);
    }
}
