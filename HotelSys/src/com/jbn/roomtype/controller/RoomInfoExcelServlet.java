package com.jbn.roomtype.controller;

import com.jbn.roomtype.pojo.RoomType;
import com.jbn.roomtype.service.RoomTypeService;
import com.jbn.roomtype.service.RoomTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RoomInfoExcelServlet", value = "/RoomInfoExcelServlet")
public class RoomInfoExcelServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomTypeService service = new RoomTypeServiceImpl();

        ArrayList<RoomType> infoArrayList = null;

        infoArrayList = service.query(1, service.queryRoomTypeNum());

        String[] headers = {"类型编号", "类型名称", "价格", "拼房价格", "可超预定数", "是否可拼房"};
        String fileName = "房间类型信息";


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
