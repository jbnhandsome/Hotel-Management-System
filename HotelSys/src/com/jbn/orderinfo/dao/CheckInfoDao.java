package com.jbn.orderinfo.dao;

import com.jbn.common.CommonDao;
import com.jbn.common.DBUtil;
import com.jbn.orderinfo.pojo.CheckInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * * 另提供queryOrder-> 对预定人名称和房间类型的查询
 */
public class CheckInfoDao implements CommonDao {

    /**
     * 插入函数关联内容如下：
     * 1.外键关联： roomtype表
     * 2.属性关联  login字段
     * 3.pojo对象的无参构造函数
     * String类型无赋值则其值为NULL
     * Int类型为0
     * typeId与operatorId需要负值对象
     *
     * @param o OrderInfo字段信息
     * @throws SQLException 数据库
     */
    @Override
    public void insertData(Object o) throws SQLException {
        CheckInfo checkInfo = (CheckInfo) o;

        Connection conn = DBUtil.getConnection();

        String sql = "INSERT INTO checkinfo VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, checkInfo.getCheckId());
        pstmt.setString(2, checkInfo.getOrderId());
        pstmt.setString(3, checkInfo.getCheckName());
        pstmt.setString(4, checkInfo.getCheckPhone());
        pstmt.setString(5, checkInfo.getCheckIDcard());
        pstmt.setString(6, checkInfo.getTypeId());
        pstmt.setString(7, checkInfo.getArrireTime());
        pstmt.setString(8, checkInfo.getLeaveTime());
        pstmt.setString(9, checkInfo.getCheckState());
        pstmt.setString(10, checkInfo.getCheckNum());
        pstmt.setString(11, checkInfo.getRoomId());
        pstmt.setString(12, checkInfo.getPrice());
        pstmt.setString(13, checkInfo.getCheckPrice());
        pstmt.setInt(14, checkInfo.getDiscount());
        pstmt.setString(15, checkInfo.getDiscountReason());
        pstmt.setString(16, checkInfo.getAddBed());
        pstmt.setString(17, checkInfo.getAddBedPrice());
        pstmt.setString(18, checkInfo.getOrderMoney());
        pstmt.setString(19, checkInfo.getMoney());
        pstmt.setString(20, checkInfo.getIsCheck());
        pstmt.setString(21, checkInfo.getCheckMoney());
        pstmt.setString(22, checkInfo.getCheckDate());
        pstmt.setString(23, checkInfo.getRemark());
        pstmt.setString(24, checkInfo.getOperatorId());

        pstmt.executeUpdate();

        pstmt.close();
    }

    @Override
    public void deleteData(Object o) throws SQLException {
        CheckInfo checkInfo = (CheckInfo) o;

        Connection conn = DBUtil.getConnection();

        String sql = "DELETE FROM checkinfo WHERE checkId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, checkInfo.getCheckId());

        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public void updateData(Object o) throws SQLException {
        CheckInfo checkInfo = (CheckInfo) o;

        Connection conn = DBUtil.getConnection();

        String sql = "UPDATE checkinfo SET checkName = ? ,checkPhone = ? ,checkIDcard = ? ,typeId = ? " +
                ",arrireTime = ? ,leaveTime = ? ,checkState = ? ,checkNum = ? ,roomId = ? ,price = ? ,checkPrice = ? " +
                ",discount = ? ,discountReason = ? ,addBed = ? ,addBedPrice = ? ,orderMoney = ? ,money = ?,isCheck = ?,checkMoney = ?,checkDate = ?,remark = ? " +
                ",operatorId = ?,orderId = ? WHERE checkId = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, checkInfo.getCheckName());
        pstmt.setString(2, checkInfo.getCheckPhone());
        pstmt.setString(3, checkInfo.getCheckIDcard());
        pstmt.setString(4, checkInfo.getTypeId());
        pstmt.setString(5, checkInfo.getArrireTime());
        pstmt.setString(6, checkInfo.getLeaveTime());
        pstmt.setString(7, checkInfo.getCheckState());
        pstmt.setString(8, checkInfo.getCheckNum());
        pstmt.setString(9, checkInfo.getRoomId());
        pstmt.setString(10, checkInfo.getPrice());
        pstmt.setString(11, checkInfo.getCheckPrice());
        pstmt.setInt(12, checkInfo.getDiscount());
        pstmt.setString(13, checkInfo.getDiscountReason());
        pstmt.setString(14, checkInfo.getAddBed());
        pstmt.setString(15, checkInfo.getAddBedPrice());
        pstmt.setString(16, checkInfo.getOrderMoney());
        pstmt.setString(17, checkInfo.getMoney());
        pstmt.setString(18, checkInfo.getIsCheck());
        pstmt.setString(19, checkInfo.getCheckMoney());
        pstmt.setString(20, checkInfo.getCheckDate());
        pstmt.setString(21, checkInfo.getRemark());
        pstmt.setString(22, checkInfo.getOperatorId());
        pstmt.setString(23, checkInfo.getOrderId());
        pstmt.setString(24, checkInfo.getCheckId());

        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public int queryDataNum() throws SQLException {

        Connection conn = DBUtil.getConnection();

        String sql = "select count(*) from checkinfo;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        int num;
        if (rs.next()) num = rs.getInt("count(*)");
        else num = 0;

        rs.close();
        pstmt.close();

        return num;
    }

    @Override
    public ArrayList query(int start, int length) throws SQLException {

        Connection conn = DBUtil.getConnection();

        String sql = "select * from checkinfo limit ?, ?;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, start - 1);
        pstmt.setInt(2, length);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<CheckInfo> list = new ArrayList<CheckInfo>();


        while (rs.next()) {
            CheckInfo checkInfo = new CheckInfo();
            checkInfo.setAddBed(rs.getString("addBed"));
            checkInfo.setAddBedPrice(rs.getString("addBedPrice"));
            checkInfo.setArrireTime(rs.getString("arrireTime"));
            checkInfo.setCheckDate(rs.getString("checkDate"));
            checkInfo.setCheckId(rs.getString("checkId"));
            checkInfo.setCheckIDcard(rs.getString("checkIDcard"));
            checkInfo.setCheckMoney(rs.getString("checkMoney"));
            checkInfo.setCheckName(rs.getString("checkName"));
            checkInfo.setCheckNum(rs.getString("checkNum"));
            checkInfo.setCheckPhone(rs.getString("checkPhone"));
            checkInfo.setCheckPrice(rs.getString("checkPrice"));
            checkInfo.setCheckState(rs.getString("checkState"));
            checkInfo.setDiscount(rs.getInt("discount"));
            checkInfo.setDiscountReason(rs.getString("discountReason"));
            checkInfo.setIsCheck(rs.getString("isCheck"));
            checkInfo.setLeaveTime(rs.getString("leaveTime"));
            checkInfo.setMoney(rs.getString("money"));
            checkInfo.setOperatorId(rs.getString("operatorId"));
            checkInfo.setOrderId(rs.getString("OrderId"));
            checkInfo.setOrderMoney(rs.getString("orderMoney"));
            checkInfo.setPrice(rs.getString("price"));
            checkInfo.setRemark(rs.getString("remark"));
            checkInfo.setRoomId(rs.getString("roomId"));
            checkInfo.setTypeId(rs.getString("typeId"));
            list.add(checkInfo);
        }

        rs.close();
        pstmt.close();

        return list;
    }

    @Override
    public Object query(Object o) throws SQLException {
        CheckInfo orderInfoQuery = (CheckInfo) o;

        Connection conn = DBUtil.getConnection();

        //根据ID的查询
        String sql = "SELECT * FROM checkinfo WHERE checkId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, orderInfoQuery.getCheckId());
        ResultSet rs = pstmt.executeQuery();

        CheckInfo checkInfo = null;
        while (rs.next()) {
            checkInfo = new CheckInfo();
            checkInfo.setAddBed(rs.getString("addBed"));
            checkInfo.setAddBedPrice(rs.getString("addBedPrice"));
            checkInfo.setArrireTime(rs.getString("arrireTime"));
            checkInfo.setCheckDate(rs.getString("checkDate"));
            checkInfo.setCheckId(rs.getString("checkId"));
            checkInfo.setCheckIDcard(rs.getString("checkIDcard"));
            checkInfo.setCheckMoney(rs.getString("checkMoney"));
            checkInfo.setCheckName(rs.getString("checkName"));
            checkInfo.setCheckNum(rs.getString("checkNum"));
            checkInfo.setCheckPhone(rs.getString("checkPhone"));
            checkInfo.setCheckPrice(rs.getString("checkPrice"));
            checkInfo.setCheckState(rs.getString("checkState"));
            checkInfo.setDiscount(rs.getInt("discount"));
            checkInfo.setDiscountReason(rs.getString("discountReason"));
            checkInfo.setIsCheck(rs.getString("isCheck"));
            checkInfo.setLeaveTime(rs.getString("leaveTime"));
            checkInfo.setMoney(rs.getString("money"));
            checkInfo.setOperatorId(rs.getString("operatorId"));
            checkInfo.setOrderId(rs.getString("OrderId"));
            checkInfo.setOrderMoney(rs.getString("orderMoney"));
            checkInfo.setPrice(rs.getString("price"));
            checkInfo.setRemark(rs.getString("remark"));
            checkInfo.setRoomId(rs.getString("roomId"));
            checkInfo.setTypeId(rs.getString("typeId"));
        }


        rs.close();
        pstmt.close();

        return checkInfo;
    }

    /**
     * 查询啊,模糊查询呀
     *
     * @param make   1：查名称 2：查类型
     * @param select 待查的值
     * @return 查询对象
     * @throws SQLException 抛给业务层
     */
    public ArrayList queryCheck(int make, String select) throws SQLException {
        Connection conn = DBUtil.getConnection();

        String sql;
        if (make == 1)
            sql = "select * from checkinfo WHERE checkName LIKE ?;";
        else
            sql = "select * from checkinfo WHERE typeId LIKE ?;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "%" + select + "%");

        ResultSet rs = pstmt.executeQuery();
        ArrayList<CheckInfo> list = new ArrayList<CheckInfo>();

        while (rs.next()) {
            CheckInfo checkInfo = new CheckInfo();
            checkInfo.setAddBed(rs.getString("addBed"));
            checkInfo.setAddBedPrice(rs.getString("addBedPrice"));
            checkInfo.setArrireTime(rs.getString("arrireTime"));
            checkInfo.setCheckDate(rs.getString("checkDate"));
            checkInfo.setCheckId(rs.getString("checkId"));
            checkInfo.setCheckIDcard(rs.getString("checkIDcard"));
            checkInfo.setCheckMoney(rs.getString("checkMoney"));
            checkInfo.setCheckName(rs.getString("checkName"));
            checkInfo.setCheckNum(rs.getString("checkNum"));
            checkInfo.setCheckPhone(rs.getString("checkPhone"));
            checkInfo.setCheckPrice(rs.getString("checkPrice"));
            checkInfo.setCheckState(rs.getString("checkState"));
            checkInfo.setDiscount(rs.getInt("discount"));
            checkInfo.setDiscountReason(rs.getString("discountReason"));
            checkInfo.setIsCheck(rs.getString("isCheck"));
            checkInfo.setLeaveTime(rs.getString("leaveTime"));
            checkInfo.setMoney(rs.getString("money"));
            checkInfo.setOperatorId(rs.getString("operatorId"));
            checkInfo.setOrderId(rs.getString("OrderId"));
            checkInfo.setOrderMoney(rs.getString("orderMoney"));
            checkInfo.setPrice(rs.getString("price"));
            checkInfo.setRemark(rs.getString("remark"));
            checkInfo.setRoomId(rs.getString("roomId"));
            checkInfo.setTypeId(rs.getString("typeId"));
            list.add(checkInfo);
        }

        rs.close();
        pstmt.close();

        return list;
    }


    public int doCheckin(String checkId,String checkMoney,String checkDate){
        Connection conn;
        int ret = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update checkinfo set checkMoney = ?,checkDate = ?,isCheck = 'Y',checkState='结算' WHERE checkId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, checkMoney);
            pstmt.setString(2, checkDate);
            pstmt.setString(3, checkId);
            ret = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return ret;
    }
}