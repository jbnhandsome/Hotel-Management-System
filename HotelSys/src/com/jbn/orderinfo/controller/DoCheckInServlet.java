package com.jbn.orderinfo.controller;


import com.google.gson.Gson;
import com.jbn.billinfo.pojo.BillInfo;
import com.jbn.billinfo.service.BillInfoService;
import com.jbn.billinfo.service.BillInfoServiceImpl;
import com.jbn.orderinfo.pojo.OrderInfo;
import com.jbn.orderinfo.service.CheckInfoService;
import com.jbn.orderinfo.service.CheckInfoServiceImpl;
import com.jbn.orderinfo.service.OrderInfoService;
import com.jbn.orderinfo.service.OrderInfoServiceImpl;
import com.jbn.room.pojo.Room;
import com.jbn.room.service.RoomService;
import com.jbn.room.service.RoomServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DoCheckInServlet", value = "/DoCheckInServlet")
public class DoCheckInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            this.doGet(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out ;
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        response.setContentType("text/html;charset=utf-8");
        String checkId = request.getParameter("checkId");
        String orderId = request.getParameter("orderId");
        String roomId = request.getParameter("roomId");
        String checkMoney = request.getParameter("checkMoney");
        CheckInfoService service = new CheckInfoServiceImpl();
        OrderInfoService orderService = new OrderInfoServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        Map<String, String> ret = new HashMap<String, String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(service.doCheckin(checkId, checkMoney, sdf.format(new Date())) > 0){

            //生成账单
            BillInfoService billInfoService = new BillInfoServiceImpl();
            BillInfo billInfo = new BillInfo();
            billInfo.setCheckedId(checkId);
            billInfo.setCostMoney(checkMoney);
            billInfo.setCostDate(sdf.format(new Date()));
            billInfo.setRemark("生成账单，入住订单："+checkId);
            billInfoService.insertBillInfo(billInfo);
            //表示结账成功，需要把预定订单态改为结算，房间状态改为打扫中
            OrderInfo orderInfo = orderService.query(orderId);
            if(orderInfo != null){
                orderInfo.setOrderState("结算");
                orderService.updateOrderInfo(orderInfo);
            }
            Room room = roomService.query(roomId);
            if(room != null){
                room.setStatus("打扫中");
                roomService.updateRoom(room);
            }
        }
        ret.put("type", "success");
        ret.put("msg", "结算成功!");
        Gson gson = new Gson();
        out.print(gson.toJson(ret));
    }
}
