package com.jbn.room.controller;

import com.google.gson.Gson;
import com.jbn.room.service.RoomService;
import com.jbn.room.service.RoomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "selectRomeServlet", value = "/selectRomeServlet")
public class selectRomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //设置输出流
        PrintWriter pw = resp.getWriter();

        RoomService roomService = new RoomServiceImpl();
        ArrayList list = roomService.query(1, roomService.queryRoomNum(),"可入住");

        System.out.println("成功运行");
        //转换为json字符串格式
        Gson gson = new Gson();
        pw.print(gson.toJson(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
