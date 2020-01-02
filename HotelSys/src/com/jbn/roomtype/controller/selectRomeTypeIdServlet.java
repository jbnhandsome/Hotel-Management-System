package com.jbn.roomtype.controller;


import com.google.gson.Gson;
import com.jbn.room.service.RoomService;
import com.jbn.room.service.RoomServiceImpl;
import com.jbn.roomtype.service.RoomTypeService;
import com.jbn.roomtype.service.RoomTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "selectRomeTypeIdServlet", value = "/selectRomeTypeIdServlet")
public class selectRomeTypeIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //设置输出流
        PrintWriter pw = resp.getWriter();
        Gson gson = new Gson();
        RoomTypeService service = new RoomTypeServiceImpl();
        ArrayList list = service.query(1, service.queryRoomTypeNum());


        pw.print(gson.toJson(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
