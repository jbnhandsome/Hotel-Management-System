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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/InsertAndUpdateCheckServlet")
public class InsertAndUpdateCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        CheckInfoService service = new CheckInfoServiceImpl();

        String checkId = request.getParameter("checkId");  //0
        String orderId = request.getParameter("orderId");  //1
        String orderName = request.getParameter("orderName");  //2
        String orderPhone = request.getParameter("orderPhone"); //3
        String orderIDcard = request.getParameter("orderIDcard");  //4
        RoomType typeId = new RoomType(request.getParameter("typeId")); //5
        String arrireDate = request.getParameter("arrireDate"); //6
        String leaveDate = request.getParameter("leaveDate"); //7
        String orderState = request.getParameter("orderState"); //8
        String checkNum = request.getParameter("checkNum"); //9
        String roomId = request.getParameter("roomId"); //10
        String price = request.getParameter("price"); //11
        String checkPrice = request.getParameter("checkPrice"); //12
        String discountReason = request.getParameter("discountReason"); //14
        String addBed = request.getParameter("addBed"); //15
        String addBedPrice = request.getParameter("addBedPrice"); //16
        String orderMoney = request.getParameter("orderMoney"); //17
        String remark = request.getParameter("remark"); //18
        Login operatorId = new Login(request.getParameter("operatorId")); //19
        int make = Integer.parseInt(request.getParameter("make")); // 20 标志啊
        String money = request.getParameter("money"); //21
        int discount;

        try { //对折扣值为空的处理
            discount = Integer.parseInt(request.getParameter("discount")); //13
        } catch (NumberFormatException e) {
            discount = 0;
        }

        CheckInfo checkInfo = new CheckInfo();

        //赋值
        checkInfo.setAddBed(addBed);
        checkInfo.setAddBedPrice(addBedPrice);
        checkInfo.setArrireTime(arrireDate);
        checkInfo.setCheckId(checkId);
        checkInfo.setCheckIDcard(orderIDcard);
        checkInfo.setCheckName(orderName);
        checkInfo.setCheckNum(checkNum);
        checkInfo.setCheckPhone(orderPhone);
        checkInfo.setCheckPrice(checkPrice);
        checkInfo.setCheckState(orderState);
        checkInfo.setDiscount(discount);
        checkInfo.setDiscountReason(discountReason);
        checkInfo.setIsCheck("N");
        checkInfo.setLeaveTime(leaveDate);
        checkInfo.setMoney(money);
        checkInfo.setOperatorId(operatorId.getLoginName());
        checkInfo.setOrderId(orderId);
        checkInfo.setOrderMoney(orderMoney);
        checkInfo.setPrice(price);
        checkInfo.setRemark(remark);
        checkInfo.setRoomId(roomId);
        checkInfo.setTypeId(typeId.getTypeId());

        int code = -1; //返回的状态

        if (make == 1) { //1.新增
            code = service.insertCheckInfo(checkInfo);
        } else if (make == 2) { //修改
            code = service.updateCheckInfo(checkInfo);
        }


        //make=1 -> 1:插入成功 0：存在同名项 -1:插入失败
        //make=2 -> 1:修改成功 -1;修改失败
        if(code > 0){
            //修改预定订单
            if(make == 1){
                OrderInfoService orderInfoService = new OrderInfoServiceImpl();
                OrderInfo orderInfo = orderInfoService.query(orderId);
                if(orderInfo != null){
                    orderInfo.setOrderState("入住");
                    orderInfoService.updateOrderInfo(orderInfo);
                }
                RoomService roomService = new RoomServiceImpl();
                Room room = roomService.query(roomId);
                if(room != null){
                    room.setStatus("已入住");
                    roomService.updateRoom(room);
                }
            }
        }
        Gson gson = new Gson();
        out.print(gson.toJson(code));
    }
}
