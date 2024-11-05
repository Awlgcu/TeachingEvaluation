package com.example.webdemo.Controller;

import com.example.webdemo.Dao.EvaluationIndicatorDao;
import com.example.webdemo.Entity.User;
import com.example.webdemo.Service.IIndicatorService;
import com.example.webdemo.Service.IResultsService;
import com.example.webdemo.Service.Impl.IndicatorsService;
import com.example.webdemo.Service.Impl.ResultsService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/evaluation")
public class EvaluationServlet extends HttpServlet {
    private final IIndicatorService indicatorsService = new IndicatorsService();
    private final IResultsService resultsService = new ResultsService();

    /**
     * 学生评价单个老师
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String snumber = user.getSnumber();
        String tnumber = request.getParameter("id");

        String respJson = indicatorsService.selectAllIndicators(snumber, tnumber);
        response.getWriter().write(respJson);
    }

    /**
     * 学生评论提交接口
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("成功发起post请求");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        String snumber = user.getSnumber();
        System.out.println(snumber);
        String tnumber = request.getParameter("tnumber");
        System.out.println(tnumber);
        String[] marks = request.getParameterValues("marks");

        String indicators = request.getParameter("indicators");

        resultsService.insertResult(snumber, tnumber, marks, indicators);
        request.setCharacterEncoding("utf8");
        response.setContentType("application/json;charset=utf8");
        String respJson = "{\"message\":\"评价结果已提交!\"}";
        response.getWriter().write(respJson);
    }
}
