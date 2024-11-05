package com.example.webdemo.filter;
import com.example.webdemo.Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 给所有请求统一设置请求响应格式
 */
@WebFilter(urlPatterns = {"/*"})
public class B_AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        //1.拿到URI
        String requestURI = request.getRequestURI();
        System.out.println("当前请求URI:"+requestURI);
        //2.校验,判断是否是管理员专属路径
        String pattern = ".*admin.*";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(requestURI);
        if (matcher.find()){
            //3.是管理员接口则干嘛
            System.out.println("管理员接专属口");
            HttpSession session = request.getSession(Boolean.parseBoolean("false"));
            User user = (User)session.getAttribute("user");
            System.out.println("工号:"+user.getSnumber()+"姓名:"+user.getName());
            if (user.getSnumber().length()!=4){
                System.out.println("您不是管理员!!!");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        int status = response.getStatus();
        System.out.println("当前返回的状态码:"+status);
    }
}
