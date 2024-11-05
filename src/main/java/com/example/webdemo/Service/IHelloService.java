package com.example.webdemo.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IHelloService {
    public String login(String userId, String password, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
