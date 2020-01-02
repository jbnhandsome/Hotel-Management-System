package com.jbn.orderinfo.controller;

import com.google.gson.Gson;
import com.jbn.login.pojo.Login;
import com.jbn.orderinfo.pojo.CheckInfo;
import com.jbn.orderinfo.pojo.OrderInfo;
import com.jbn.orderinfo.service.CheckInfoService;
import com.jbn.orderinfo.service.CheckInfoServiceImpl;
import com.jbn.orderinfo.service.OrderInfoService;
import com.jbn.orderinfo.service.OrderInfoServiceImpl;
import com.jbn.room.pojo.Room;
import com.jbn.room.service.RoomService;
import com.jbn.room.service.RoomServiceImpl;
import com.jbn.roomtype.pojo.RoomType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InsertAndUpdateServlet", value = "/InsertAndUpdateServlet")
public class InsertAndUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          req.setCharacterEncoding("utf-8");
          resp.setContentType("text/html;charset=utf-8");
          PrintWriter out = resp.getWriter();

                  OrderInfoService service = new OrderInfoServiceImpl();

                  String orderId = req.getParameter("orderId");  //1
                  String orderName = req.getParameter("orderName");  //2
                  String orderPhone = req.getParameter("orderPhone"); //3
                  String orderIDcard = req.getParameter("orderIDcard");  //4
                  RoomType typeId = new RoomType(req.getParameter("typeId")); //5
                  String arrireDate = req.getParameter("arrireDate"); //6
                  String leaveDate = req.getParameter("leaveDate"); //7
                  String orderState = req.getParameter("orderState"); //8
                  String checkNum = req.getParameter("checkNum"); //9
                  String roomId = ""; //10
                  String price = req.getParameter("price"); //11
                  String checkPrice = req.getParameter("checkPrice"); //12
                  String discountReason = req.getParameter("discountReason"); //14
                  String addBed = req.getParameter("addBed"); //15
                  String addBedPrice = req.getParameter("addBedPrice"); //16
                  String orderMoney = req.getParameter("orderMoney"); //17
                  String remark = req.getParameter("remark"); //18
                  Login operatorId = new Login(req.getParameter("operatorId")); //19
                  int make = Integer.parseInt(req.getParameter("make")); // 20 标志啊

                  int discount;

                  try { //对折扣值为空的处理
                      discount = Integer.parseInt(req.getParameter("discount")); //13
                  } catch (NumberFormatException e) {
                      discount = 0;
                  }

                  OrderInfo orderInfo = new OrderInfo(orderId, orderName, orderPhone, orderIDcard, typeId, arrireDate, leaveDate, orderState, checkNum, roomId, price, checkPrice, discount, discountReason, addBed, addBedPrice, orderMoney, remark, operatorId);

                  int code = -1; //返回的状态

                  if (make == 1) { //1.新增
                      code = service.insertOrderInfo(orderInfo);
                  } else if (make == 2) { //修改
                      code = service.updateOrderInfo(orderInfo);
                  }


                  //make=1 -> 1:插入成功 0：存在同名项 -1:插入失败
                  //make=2 -> 1:修改成功 -1;修改失败
                  Gson gson = new Gson();
                  out.print(gson.toJson(code));



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
