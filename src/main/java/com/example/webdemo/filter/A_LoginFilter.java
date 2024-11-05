package com.example.webdemo.filter;
import com.example.webdemo.Entity.User;
import com.example.webdemo.Utils.CheckLogin;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.webdemo.Utils.CheckLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * login之外的所有接口都需要登录校验
 */
@WebFilter(urlPatterns = {"/*"})
public class A_LoginFilter implements Filter
{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        //1.拿到URI
        String requestURI = request.getRequestURI();
        System.out.println("当前请求URI:"+requestURI);
        //2.校验,判断是否是登录路径
        String pattern1 = ".*/TESYS/login.*";
        Pattern p1 = Pattern.compile(pattern1);
        Matcher matcher1 = p1.matcher(requestURI);
        String pattern2 = "/TESYS/";
        if (!matcher1.find()&&!pattern2.equals(requestURI)){
            //3.不是登录则校验是否已登录
            HttpSession session = request.getSession(false);
            boolean isLogin = CheckLogin.isLogin(session);
            if (!isLogin){
                System.out.println("您还未登录");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
