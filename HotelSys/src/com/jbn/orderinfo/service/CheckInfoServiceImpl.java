package com.jbn.orderinfo.service;

import com.jbn.orderinfo.dao.CheckInfoDao;
import com.jbn.orderinfo.pojo.CheckInfo;
import com.jbn.roomtype.dao.RoomTypeDao;
import com.jbn.roomtype.pojo.RoomType;

import java.sql.SQLException;
import java.util.ArrayList;

public class CheckInfoServiceImpl implements CheckInfoService {

    private CheckInfoDao dao = new CheckInfoDao();

    @Override
    public int insertCheckInfo(CheckInfo checkInfo) {

        try {
            String checkId = checkInfo.getCheckId();
            if (queryRepeat(checkId, checkId) != 1)
                return 0;
            dao.insertData(checkInfo);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;
    }

    @Override
    public int deleteCheckInfo(String checkId) {
        CheckInfo checkInfo = new CheckInfo();
        checkInfo.setCheckId(checkId);

        try {
            dao.deleteData(checkInfo);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
        return 1;
    }

    @Override
    public int updateCheckInfo(CheckInfo checkInfo) {
        try {
            dao.updateData(checkInfo);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public CheckInfo query(String checkId) {
        CheckInfo checkInfo = new CheckInfo();
        checkInfo.setCheckId(checkId);

        try {
            return (CheckInfo) dao.query(checkInfo);
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
    public int queryCheckInfoNum() {
        try {
            return dao.queryDataNum();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1;
        }
    }

    @Override
    public ArrayList queryCheck(int make, String select) {

        if (make == 2) { //2：查类型
            try {
                // 然而这样写失去了对类型的模糊查询
                RoomType roomType = new RoomTypeDao().queryName(select);
                select = roomType.getTypeId();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            return dao.queryCheck(make, select);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return null;
        }
    }

    @Override
    public int queryRepeat(String newName, String oldName) {
        CheckInfo checkInfoQuery = new CheckInfo();
        checkInfoQuery.setCheckId(newName);
        CheckInfo checkInfo;
        try {
            checkInfo = (CheckInfo) dao.query(checkInfoQuery);
            if (checkInfo != null) {
                if (checkInfo.getCheckId().equals(oldName))
                    return 2; //和旧名称重复
                return 0; //重复
            } else
                return 1; //不重复
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
            return -1; //异常
        }
    }

    @Override
    public int doCheckin(String checkId,String checkMoney,String checkDate) {
        // TODO Auto-generated method stub
        return dao.doCheckin(checkId, checkMoney, checkDate);
    }

}
