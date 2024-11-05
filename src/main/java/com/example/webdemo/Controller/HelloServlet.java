package com.example.webdemo.Controller;

import java.io.*;

import com.example.webdemo.Entity.BaseResponse;
import com.example.webdemo.Entity.User;
import com.example.webdemo.Service.IHelloService;
import com.example.webdemo.Service.Impl.HelloService;
import com.example.webdemo.Utils.CheckLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/login")
public class HelloServlet extends HttpServlet {
    private final ObjectMapper objectMapper= new ObjectMapper();
    private final IHelloService helloService = new HelloService();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.拿到前端数据
        String snumber = request.getParameter("snumber");
        String password = request.getParameter("password");
        System.out.println("拿到前端数据:"+snumber+"-"+password);

        //2.查询结果并放回
        String  respJson = helloService.login(snumber, password, request, response);
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        response.getWriter().write(respJson);
        System.out.println("完成post请求并已向前端写数据");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");

        HttpSession session = request.getSession(false);
        if(!CheckLogin.isLogin(session)){
            User user = new User();
//            String respJson = objectMapper.writeValueAsString(user);
            String respJson = BaseResponse.success(0, "未登录", user);
            response.getWriter().write(respJson);
            return;
        }
        User user = (User) session.getAttribute("user");
//        String respJson = objectMapper.writeValueAsString(user);
        String respJson = BaseResponse.success(1, "已登录", user);
        response.getWriter().write(respJson);
    }

    public void destroy() {
    }
}