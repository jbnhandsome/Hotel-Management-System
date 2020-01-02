package com.jbn.login.controller;

import com.google.gson.Gson;
import com.jbn.login.pojo.Login;
import com.jbn.login.service.LoginService;
import com.jbn.login.service.LoginServiceImpl;
import com.mysql.cj.log.Log;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * 此servlet是根据登录名返回login对象，这个对象是包含密码数据的
 * 所以为了安全，将servlet也放入了过滤器的过滤范围
 */
@WebServlet(value = "/QueryLoginInfoServlet", name = "QueryLoginInfoServlet")
public class QueryLoginInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
      //  resp.setContentType("text/html;charaset=utf-8");

        //我们要从session里获取 loginName 并且把他赋值给
        HttpSession session = req.getSession();
        LoginService service = new LoginServiceImpl();
        String loginName=(String)session.getAttribute("loginName");

        try {
            Login login=service.queryLogin(loginName);
            PrintWriter pw = resp.getWriter(); //设置返回值
            Gson gson = new Gson();
            pw.print(gson.toJson(login));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
