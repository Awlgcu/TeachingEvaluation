package com.example.webdemo.Controller;

import com.example.webdemo.Entity.User;
import com.example.webdemo.Service.Impl.StudentService;
import com.example.webdemo.Utils.CheckLogin;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private  final StudentService studentService = new StudentService();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        boolean login = CheckLogin.isLogin(request.getSession(false));
        if (!login){
            System.out.println("未登录");
            response.sendRedirect("index.jsp");
            return;
        }
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        if(request.getParameter("action")!=null&&"update".equals(request.getParameter("action"))){
            String snumber = request.getParameter("snumber");
            System.out.println(snumber);
            String name = request.getParameter("name");
            System.out.println(name);
            String age = request.getParameter("age");
            System.out.println(age);
            String gender = request.getParameter("gender");
            System.out.println(gender);

            String respJson = studentService.updateSelf(snumber, name, age, gender);
            response.getWriter().write(respJson);

            //更新session
            HttpSession session = request.getSession(false);
            if (session!=null){
                session.setAttribute("user", studentService.selectStuObjBySnumber(snumber));
            } else{
                System.out.println("session为空!");
            }
            return;
        }


        String oldPassword = request.getParameter("oldPassword");
        String newPassword1 = request.getParameter("newPassword1");
        String newPassword2 = request.getParameter("newPassword2");
        System.out.println("成功发起修改密码请求:"+oldPassword+"-"+newPassword1+"-"+newPassword2);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String snumber = user.getSnumber();

        String respJson = studentService.updateStudentPassword(snumber, oldPassword, newPassword1, newPassword2);

        if (respJson.equals("{\"message\":\"修改成功,请重新登录!\"}")){
            session.removeAttribute("user");
        }
        response.getWriter().write(respJson);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        boolean login = CheckLogin.isLogin(request.getSession(false));
        if (!login){
            System.out.println("未登录");
            response.sendRedirect("index.jsp");
            
            return;
        }
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        System.out.println("成功发起获取学生信息的get请求");
        String snumber = request.getParameter("snumber");
        System.out.println("收到前端传来的"+snumber);
        String respJson = studentService.selectStudentBySnumber(snumber);
        response.getWriter().write(respJson);
    }
}
