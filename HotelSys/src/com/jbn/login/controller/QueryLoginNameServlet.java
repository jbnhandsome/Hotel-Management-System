package com.jbn.login.controller;

import com.google.gson.Gson;
import com.jbn.common.MD5;
import com.jbn.login.pojo.Login;
import com.jbn.login.service.LoginService;
import com.jbn.login.service.LoginServiceImpl;

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
 * 此servlet是登录界面使用的，根据用户登录名和用户密码进行登录判断。
 * 如果登录结果判断成功就在session中写入当前的登录名值
 * 通过ajax返回给判断的结果。
 */

@WebServlet(value = "/QueryLoginNameServlet", name = "QueryLoginNameServlet")
public class QueryLoginNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
      //  resp.setContentType("text/html;charaset=utf-8");
        PrintWriter out = resp.getWriter();

        LoginService loginService = new LoginServiceImpl();

        //获取用户名 密码
        String loginName = req.getParameter("loginName");

        String loginPwd = req.getParameter("loginPwd");
        try {
            int check=loginService.queryByName(loginName,loginPwd);
            if(check==1){
                //设置session
                HttpSession session =req.getSession();
                session.setAttribute("LoginName",loginName);
                Login login =loginService.queryLogin(loginName);//返回Login对象

            }
            Gson gson=new Gson();
            out.print(gson.toJson(check));//返回data值
            //out.print(check);//返回data值
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
