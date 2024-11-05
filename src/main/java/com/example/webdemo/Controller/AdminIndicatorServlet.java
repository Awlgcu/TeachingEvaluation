package com.example.webdemo.Controller;

import com.example.webdemo.Entity.EvaluationIndicator;
import com.example.webdemo.Service.IAdminService;
import com.example.webdemo.Service.Impl.AdminService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adminIndicator")
public class AdminIndicatorServlet extends HttpServlet {
    private final IAdminService adminService = new AdminService();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        String respJson = adminService.SelectAllIndicators();
        response.getWriter().write(respJson);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        String respJson = null;
        if ("delete".equals(request.getParameter("action"))){
            System.out.println("进入删除评价指标接口");
            respJson = adminService.deleteIndicatorById(Long.parseLong(request.getParameter("id")));
            response.getWriter().write(respJson);
            return;
        }

        if("insert".equals(request.getParameter("action"))){
            System.out.println("进入添加评价指标接口");
            System.out.println("待添加评价指标:"+request.getParameter("indicator"));
            EvaluationIndicator indicator = new EvaluationIndicator();
            indicator.setIndicator(request.getParameter("indicator"));
            respJson = adminService.insertIndicatorByEntity(indicator);
            response.getWriter().write(respJson);
            return;
        }

        System.out.println("进入修改评价指标接口");

        EvaluationIndicator indicator = new EvaluationIndicator();
        indicator.setIndicator(request.getParameter("indicator"));
        indicator.setId(Long.parseLong(request.getParameter("id")));
        respJson = adminService.updateIndicatorByEntity(indicator);

        response.getWriter().write(respJson);
    }
}
