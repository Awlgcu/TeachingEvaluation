package com.example.webdemo.Controller;

import com.example.webdemo.Entity.Admin;
import com.example.webdemo.Entity.User;
import com.example.webdemo.Service.IAdminService;
import com.example.webdemo.Service.Impl.AdminService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private  final IAdminService adminService = new AdminService();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        if(request.getParameter("action")!=null&&"update".equals(request.getParameter("action"))){
            System.out.println("进入修改管理员信息界面");
            Admin admin = new Admin();
            admin.setSnumber(request.getParameter("snumber"));
            admin.setName(request.getParameter("name"));
            admin.setAge(Integer.parseInt(request.getParameter("age")));
            admin.setGender(request.getParameter("gender"));
            admin.setPhone(request.getParameter("phone"));
            admin.setPassword(request.getParameter("password"));

            String respJson = adminService.updateAdminSelfByEntity(admin);
            response.getWriter().write(respJson);

            //更新session
            HttpSession session = request.getSession(false);
            if (session!=null){
                session.setAttribute("user", adminService.selectAdminObjBySnumber(request.getParameter("snumber")));
            } else{
                System.out.println("session为空!");
            }
            return;
        }

        System.out.println("进入修改管理员密码界面");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword1 = request.getParameter("newPassword1");
        String newPassword2 = request.getParameter("newPassword2");
        System.out.println("成功发起修改密码请求:"+oldPassword+"-"+newPassword1+"-"+newPassword2);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String snumber = user.getSnumber();

        String respJson = adminService.updateAdminPassword(snumber, oldPassword, newPassword1, newPassword2);

        if (respJson.equals("{\"message\":\"修改成功,请重新登录!\"}")){
            session.removeAttribute("user");
        }
        response.getWriter().write(respJson);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        System.out.println("成功发起获取管理员信息的get请求");
        String snumber = request.getParameter("snumber");
        String respJson = adminService.selectAdminBySnumber(snumber);
        response.getWriter().write(respJson);
    }
}
