package com.example.webdemo.Controller;

import com.example.webdemo.Service.IAdminService;
import com.example.webdemo.Service.Impl.AdminService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminResult")
public class AdminResultServlet extends HttpServlet {
    private final IAdminService adminService = new AdminService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        String respJson = adminService.selectAllResults();
        response.getWriter().write(respJson);
    }
}
