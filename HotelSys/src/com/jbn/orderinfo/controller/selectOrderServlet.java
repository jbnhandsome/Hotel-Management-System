package com.jbn.orderinfo.controller;

import com.google.gson.Gson;
import com.jbn.orderinfo.pojo.OrderInfo;
import com.jbn.orderinfo.service.OrderInfoService;
import com.jbn.orderinfo.service.OrderInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "selectOrderServlet", value = "/selectOrderServlet")
public class selectOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 响应输出流
        PrintWriter out = resp.getWriter();

        OrderInfoService service = new OrderInfoServiceImpl();


        ArrayList<OrderInfo> list = service.query(1, service.queryOrderInfoNum());

        List<OrderInfo> retInfos = new ArrayList<OrderInfo>();
        for(OrderInfo oi:list){
            if("预定".equals(oi.getOrderState())){
                retInfos.add(oi);
            }
        }
        //转换为json字符串格式
        Gson gson = new Gson();
        out.print(gson.toJson(retInfos));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
