package com.jbn.floorinfo.service;

import com.jbn.floorinfo.dao.FloorInfoDao;
import com.jbn.floorinfo.pojo.FloorInfo;

import java.sql.SQLException;
import java.util.ArrayList;

public class FloorInfoServiceImpl implements FloorInfoService{
    FloorInfoDao dao = new FloorInfoDao();

    @Override
    public int insertFloorInfo(String floorName) {
        FloorInfo floorInfoQuery = new FloorInfo();
        floorInfoQuery.setFloorName(floorName);

        FloorInfo floorInfo;
        try {
            floorInfo = (FloorInfo) dao.query(floorInfoQuery);
            if (floorInfo.isNull())
                dao.insertData(floorInfoQuery);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;
    }

    @Override
    public int deleteFloorInfo(int floorId) {
        FloorInfo floorInfoQuery = new FloorInfo();
        floorInfoQuery.setFloorId(floorId);

        try {
            dao.deleteData(floorInfoQuery);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;
    }

    @Override
    public int updateFloorInfo(FloorInfo floorInfo) {
        try {
            dao.updateData(floorInfo);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;
    }

    @Override
    public ArrayList query(int page, int limit) {
        //返回所有状态时status 的方将room对象
        int start = (page * limit)- limit + 1;//每一页的起始位置
        if (start < 1){
            start =1 ;
        }
        try {
            return dao.query(start,limit);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FloorInfo query(String floorName) {
        FloorInfo floorInfoQuery = new FloorInfo();
        floorInfoQuery.setFloorName(floorName);

        try {
            return (FloorInfo) dao.query(floorInfoQuery);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return null;
        }
    }

    @Override
    public int queryFloorInfoNum() {

        //查询楼层数目
        try {
            return dao.queryDataNum();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int queryRepeat(String newName, String oldName) {
        FloorInfo floorInfoQuery = new FloorInfo();
        floorInfoQuery.setFloorName(newName);
        FloorInfo floorInfo;
        try {
            floorInfo = (FloorInfo) dao.query(floorInfoQuery);
            if (!floorInfo.isNull()) { //表示存在同名项
                if (floorInfo.getFloorName().equals(oldName))
                    return 2; //表示存在同名项，但是是与传递来的相同
                return 0;
            } else
                return 1;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
    }
}
