package com.jbn.billinfo.dao;

import com.jbn.billinfo.pojo.BillInfo;
import com.jbn.common.CommonDao;
import com.jbn.common.DBUtil;
import com.jbn.orderinfo.pojo.CheckInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 账单类dao
 */
public class BillInfoDao implements CommonDao {


    @Override
    public void insertData(Object o) throws SQLException {
        BillInfo billInfo = (BillInfo) o;

        java.sql.Connection conn = DBUtil.getConnection();

        String sql = "INSERT INTO billinfo VALUES (null,?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, billInfo.getCheckedId());
        pstmt.setString(2, billInfo.getCostMoney());
        pstmt.setString(3, billInfo.getCostDate());
        pstmt.setString(4, billInfo.getRemark());

        pstmt.executeUpdate();

        pstmt.close();
    }

    @Override
    public void deleteData(Object o) throws SQLException {
        BillInfo billInfo = (BillInfo) o;

        java.sql.Connection conn = DBUtil.getConnection();

        String sql = "DELETE FROM billinfo WHERE billId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, billInfo.getBillId());

        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public void updateData(Object o) throws SQLException {
        BillInfo billInfo = (BillInfo) o;

        java.sql.Connection conn = DBUtil.getConnection();

        String sql = "UPDATE billinfo SET checkedId = ? ,costMoney = ? ,costDate = ? ,remark = ? " +
                " WHERE billId = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, billInfo.getCheckedId());
        pstmt.setString(2, billInfo.getCostMoney());
        pstmt.setString(3, billInfo.getCostDate());
        pstmt.setString(4, billInfo.getRemark());
        pstmt.setInt(5, billInfo.getBillId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    @Override
    public int queryDataNum() throws SQLException {

        java.sql.Connection conn = DBUtil.getConnection();

        String sql = "select count(*) from billinfo;";
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

        java.sql.Connection conn = DBUtil.getConnection();

        String sql = "select * from billinfo limit ?, ?;";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, start - 1);
        pstmt.setInt(2, length);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<BillInfo> list = new ArrayList<BillInfo>();


        while (rs.next()) {
            BillInfo billInfo = new BillInfo();
            billInfo.setBillId(rs.getInt("billId"));
            billInfo.setCheckedId(rs.getString("checkId"));
            billInfo.setCostDate(rs.getString("costDate"));
            billInfo.setCostMoney(rs.getString("costMoney"));
            billInfo.setRemark(rs.getString("remark"));
            list.add(billInfo);
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
        String sql = "SELECT * FROM billinfo WHERE billId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, orderInfoQuery.getCheckId());
        ResultSet rs = pstmt.executeQuery();

        BillInfo billInfo = null;
        while (rs.next()) {
            billInfo = new BillInfo();
            billInfo.setBillId(rs.getInt("billId"));
            billInfo.setCheckedId(rs.getString("checkId"));
            billInfo.setCostDate(rs.getString("costDate"));
            billInfo.setCostMoney(rs.getString("costMoney"));
            billInfo.setRemark(rs.getString("remark"));
        }


        rs.close();
        pstmt.close();

        return billInfo;
    }


}
