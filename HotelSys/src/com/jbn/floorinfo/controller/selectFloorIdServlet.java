package com.jbn.floorinfo.controller;

import com.google.gson.Gson;
import com.jbn.floorinfo.service.FloorInfoService;
import com.jbn.floorinfo.service.FloorInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "selectFloorIdServlet", value = "/selectFloorIdServlet")
public class selectFloorIdServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        Gson gson = new Gson();
        FloorInfoService floorInfoService = new FloorInfoServiceImpl();
        ArrayList list = floorInfoService.query(1,floorInfoService.queryFloorInfoNum());

        pw.print(gson.toJson(list));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
