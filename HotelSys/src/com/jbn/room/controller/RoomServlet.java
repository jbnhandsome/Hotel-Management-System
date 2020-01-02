package com.jbn.room.controller;

import com.google.gson.Gson;
import com.jbn.common.PojotoGson;
import com.jbn.room.pojo.Room;
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

/**
 * 与表格相关的全部操作
 * 状态标志： make 0重载 1新增 2修改 3搜索 4删除
 */
@WebServlet(name = "RoomServlet", value = "/RoomServlet")
public class RoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();


        int page = Integer.parseInt(req.getParameter("page")); // 当前页码
        int limit = Integer.parseInt(req.getParameter("limit")); // 每页的数据量
        int make = Integer.parseInt(req.getParameter("make")); //状态标志


        //调用Service
        RoomService service = new RoomServiceImpl();

        //默认输出信息
        String code = "0"; //状态码
        String msg="数据查询正常";//状态信息
        String count="";//数据总数
        ArrayList list = new ArrayList();//数据信息
        ArrayList<Room> searchList = new ArrayList<Room>();//数据内容

        //单个全局属性
        String roomId = ""; //房间编号
        String price; //价格
        String splicPrice; //拼房价格
        int exceedance; //可超预定数
        String isSplice; //是否可拼房
        Room room = null;

        //获取对应的状态属性
        if(make == 1 || make == 2){
            //新增和修改这部分是完全相同的
            roomId = req.getParameter("roomId");
            roomId = req.getParameter("typeName");
            price = req.getParameter("price");
            splicPrice = req.getParameter("splicPrice");
            exceedance = Integer.parseInt(req.getParameter("exceedance"));
            isSplice = req.getParameter("isSplice");
        }else if(make == 4 || make == 3){//删除 和搜索 都光给roomid赋值就行了
            roomId = req.getParameter("roomId");
        }
        // 状态标志 make 0重载 1新增 2修改 3搜索 4删除
        switch (make){
            case 1:break;
            case 2:break;
            case 3:
                list = service.query(1,service.queryRoomNum());
                searchList.clear();
                for(Object o:list){
                    room = (Room)o;
                    if (room.getRoomId().contains(roomId)){
                        //用contains模糊查询 机智啊，这样连mysql的like语句都不用写  -2019-12-25 修改生石
                        searchList.add(room);
                    }
                }
                break;
            case 4:
                if (service.deleteRoom(roomId) == -1){
                    msg = "删除失败";
                    code = "-1";
                }
                break;

        }

        if(make != 3){
            list = service.query(page, limit);
            count = String.valueOf(service.queryRoomNum());
        }else{
            //对搜索的特殊处理
            int size =searchList.size();
            if(size == 0){
                msg="查无此项";
                code = "-1" ;
            }else{
                list = searchList;
                count = Integer.toString(size);
            }
        }
        PojotoGson pojotoGson = new PojotoGson(code, msg, count, list);
        Gson gson = new Gson();
        out.print(gson.toJson(pojotoGson));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
