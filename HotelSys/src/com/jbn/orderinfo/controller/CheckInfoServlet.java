package com.jbn.orderinfo.controller;


import com.google.gson.Gson;
import com.jbn.common.PojotoGson;
import com.jbn.orderinfo.pojo.CheckInfo;
import com.jbn.orderinfo.service.CheckInfoService;
import com.jbn.orderinfo.service.CheckInfoServiceImpl;
import com.jbn.roomtype.service.RoomTypeService;
import com.jbn.roomtype.service.RoomTypeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CheckInfoServlet", value = "/CheckInfoServlet")
public class CheckInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        int page = Integer.parseInt(request.getParameter("page")); // 当前页码
        int limit = Integer.parseInt(request.getParameter("limit")); // 每页的数据量
        int make = Integer.parseInt(request.getParameter("make")); //状态标志

        // 调用service
        CheckInfoService service = new CheckInfoServiceImpl();
        RoomTypeService roomTypeService = new RoomTypeServiceImpl();

        // 默认输出信息
        String code = "0"; //状态码
        String msg = "数据查询正常"; //状态信息
        String count; //数据总数
        ArrayList<CheckInfo> list = new ArrayList<CheckInfo>(); //数据内容

        //单个全局属性
        String checkId; //预定单号
        String select; //查询值

        //获取对应状态属性
        if (make == 1 || make == 2) { //1和2这部分完全是相同的
            select = request.getParameter("select");
            list = service.queryCheck(make, select);
            count = String.valueOf(list.size());
            msg = "结果如下";
        } else if (make == 4) {
            checkId = request.getParameter("checkId");
            if (service.deleteCheckInfo(checkId) == -1) {
                msg = "删除失败";
                code = "-1";
            }
            count = String.valueOf(service.queryCheckInfoNum());
        } else {
            list = service.query(page, limit);
            count = String.valueOf(service.queryCheckInfoNum());
        }

        for (CheckInfo checkInfo : list) { //转换成前端界面可接收的类型 主要是转 房间类型 和操作员
            checkInfo.setTypeId(roomTypeService.query(checkInfo.getTypeId()).getTypeName());
        }

        PojotoGson pojotoGson = new PojotoGson(code, msg, count, list);
        Gson gson = new Gson();
        out.print(gson.toJson(pojotoGson));
    }
}
