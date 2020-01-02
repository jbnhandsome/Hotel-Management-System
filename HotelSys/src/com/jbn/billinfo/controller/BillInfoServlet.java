package com.jbn.billinfo.controller;

import com.google.gson.Gson;
import com.jbn.billinfo.pojo.BillInfo;
import com.jbn.billinfo.service.BillInfoService;
import com.jbn.billinfo.service.BillInfoServiceImpl;
import com.jbn.common.PojotoGson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "BillInfoServlet", value = "/BillInfoServlet")
public class BillInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        int page = Integer.parseInt(request.getParameter("page")); // 当前页码
        int limit = Integer.parseInt(request.getParameter("limit")); // 每页的数据量

        // 调用service
        BillInfoService service = new BillInfoServiceImpl();

        // 默认输出信息
        String code = "0"; //状态码
        String msg = "数据查询正常"; //状态信息
        String count; //数据总数
        ArrayList<BillInfo> list = new ArrayList<BillInfo>(); //数据内容

        list = service.query(page, limit);
        count = String.valueOf(service.queryBillInfoNum());
        msg = "结果如下";
        PojotoGson pojotoGson = new PojotoGson(code, msg, count, list);
        Gson gson = new Gson();
        out.print(gson.toJson(pojotoGson));
    }
}
