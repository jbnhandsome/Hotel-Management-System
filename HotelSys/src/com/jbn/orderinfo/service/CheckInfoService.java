package com.jbn.orderinfo.service;

import com.jbn.orderinfo.pojo.CheckInfo;

import java.util.ArrayList;

public interface CheckInfoService {
    /**
     * 插入一条数据
     *
     * @param checkInfo 预订单
     * @return -1失败 0重名 1成功
     * checkInfo是已经结算完的
     */
    int insertCheckInfo(CheckInfo checkInfo);

    //删除
    int deleteCheckInfo(String checkId);

    //更新
    int updateCheckInfo(CheckInfo checkInfo);

    //查询单条
    CheckInfo query(String checkId);

    //查询多条
    ArrayList query(int page, int limit);

    //查询长度
    int queryCheckInfoNum();

    //俩查询 1：查名称 2：查类型
    ArrayList queryCheck(int make, String select);

    int doCheckin(String checkId,String checkMoney,String checkDate);
    /**
     * 查重函数
     *
     * @param newName oldName 房间类型名称
     * @return 0:已经存在 1：未存在 2：与自身相同
     */
    int queryRepeat(String newName, String oldName);

}
