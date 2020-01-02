package com.jbn.room.service;

import com.jbn.room.dao.RoomDao;
import com.jbn.room.pojo.Room;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 将异常放在了本层处理
 * 如果出现数据库相关异常，则返回-1或者null
 */
public class RoomServiceImpl implements RoomService{
    RoomDao dao = new RoomDao();
    @Override
    public int insertRoom(Room room) {
        try {
            String name ="1";
            name = room.getRoomId();
            if (queryRepeat(name) != 1) {
                return 0;
            }
            dao.insertData(room);
            System.out.println(room);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;

    }

    @Override
    public int deleteRoom(String roomId) {
        Room room = new Room();
        room.setRoomId(roomId);

        try {
            dao.deleteData(room);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;
    }

    @Override
    public int updateRoom(Room room) {
        try {
            dao.updateData(room);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;
    }

    @Override
    public Room query(String roomId) {
        Room room = new Room();
        room.setRoomId(roomId);

        try {
            return (Room) dao.query(room);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList query(int page, int limit) {
        int start = (page * limit) - limit + 1; //每一页的起始位置

        if (start < 1)  //小于1的话会触发一个错误
            start = 1;  //但是理论上page和limit是由table表格生成的参数

        try {
            return dao.query(start, limit);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList query(int page, int limit, String status) {
        //返回所有状态时status 的方将room对象
        int start = (page * limit)- limit + 1;//每一页的起始位置
        if (start < 1){
            start =1 ;
        }
        try {
            return dao.query(start,limit,status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int queryRoomNum() {
        //查询房间数目
        try {
            return dao.queryDataNum();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int queryRepeat(String newName, String oldName) {
        Room room;

        try {
            room = dao.queryName(newName);
            if (room != null) {//表示存在同名项
                if (room.getRoomId().equals(oldName))
                    return 2;           //表示存在同名项，但是是与传递来的相同
                return 0;
            } else
                return 1;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
    }

    @Override
    public int queryRepeat(String newName) {
        //查询重复的房间
        Room room;
        RoomDao dao = new RoomDao();

        try {
            room=dao.queryName(newName);
            if(room.getRoomId()==null){
                //1表示没有重名
                return 1;
            }else{
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
}
