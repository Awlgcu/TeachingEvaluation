package com.example.webdemo.Utils;

import com.example.webdemo.Entity.User;
import jakarta.servlet.http.HttpSession;

public class CheckLogin {
    public  static boolean isLogin(HttpSession session){
        if (session==null){
            System.out.println("未登录,没有session");
            return false;
        } else  {
            User user =(User)session.getAttribute("user");
            if (user==null) {
                System.out.println("未登录,session内没有user");
                return false;
            }
        }
        System.out.println("已登录,啥都有");
        return true;
    }
}
