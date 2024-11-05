package com.example.webdemo.Controller;

import com.example.webdemo.Entity.User;
import com.example.webdemo.Service.Impl.StuClassService;
import com.example.webdemo.Service.Impl.StudentService;
import com.example.webdemo.Utils.CheckLogin;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "stuEvaluationServlet", value = "/stuEvaluation")
public class StuEvaluationServlet extends HttpServlet {

    private final StuClassService stuClassService = new StuClassService();
    private final StudentService studentService = new StudentService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean login = CheckLogin.isLogin(request.getSession(false));
        if (!login){
            System.out.println("未登录");
            response.sendRedirect("index.jsp");
            return;
        }

        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        System.out.println("成功发起查询班级老师的get请求");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String snumber = user.getSnumber();

        String classId = studentService.selectClassIdBySnumber(snumber);
        System.out.println("成功查询到班级号:"+classId);

        String respJson = stuClassService.selectTeachersByClassId(classId);

        response.getWriter().write(respJson);
    }

}
