package com.example.webdemo.Service.Impl;

import com.example.webdemo.Dao.AdminDao;
import com.example.webdemo.Dao.StudentDao;
import com.example.webdemo.Entity.Admin;
import com.example.webdemo.Entity.BaseResponse;
import com.example.webdemo.Entity.Student;
import com.example.webdemo.Service.IHelloService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class HelloService implements IHelloService {
    private final StudentDao studentDao = new StudentDao();
    private final AdminDao adminDao = new AdminDao();
    @Override
    public String login(String snumber, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String respJson = null;
        if(snumber==null|| snumber.isEmpty() ||password==null|| password.isEmpty()){
            System.out.println("登录失败!用户名或密码为空");
//            respJson = "{\"message\":\"登录失败!用户名或密码为空\"}";
//                        return respJson;
//            System.out.println(BaseResponse.fail(0, "登录失败!用户名或密码为空"));
            return BaseResponse.fail(0, "登录失败!用户名或密码为空");
        } else {
            Admin admin = adminDao.selectBySnumber(snumber);
            if (admin!=null&&admin.getPassword().equals(password)){
                //登录成功创建session保存登录状态
                HttpSession session = request.getSession(true);
                //true表示存在则用旧的,不存在则新建一个;false表示存在则用旧的,不存在返回一个null
                session.setAttribute("user", admin);
                System.out.println("登录成功");
                response.sendRedirect("admin.html");
                System.out.println("去到admin.html");
                return  "{\"message\":\"登录成功!\"}";
            }

            Student student = studentDao.selectBySnumber(snumber);
            if(student==null||!student.getPassword().equals(password)){
                return  BaseResponse.fail(0, "登录失败,用户名或密码错误");
            }
            //登录成功创建session保存登录状态
            HttpSession session = request.getSession(true);
            //true表示存在则用旧的,不存在则新建一个;false表示存在则用旧的,不存在返回一个null
            session.setAttribute("user", student);
            System.out.println("登录成功");
            response.sendRedirect("student.html");
            System.out.println("去到student.html");
            return  "{\"message\":\"登录成功!\"}";
        }
    }
}
