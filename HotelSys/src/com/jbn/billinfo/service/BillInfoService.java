package com.jbn.billinfo.service;

import com.jbn.billinfo.pojo.BillInfo;

import java.util.ArrayList;

public interface BillInfoService {

    /**
     * 插入一条数据
     *
     * @param billInfo 预订单
     * @return -1失败 0重名 1成功
     */
    int insertBillInfo(BillInfo billInfo);

    //删除
    int deleteBillInfo(int billId);

    //更新
    int updateBillInfo(BillInfo billInfo);

    //查询单条
    BillInfo query(int billId);

    //查询多条
    ArrayList query(int page, int limit);

    //查询长度
    int queryBillInfoNum();


}
