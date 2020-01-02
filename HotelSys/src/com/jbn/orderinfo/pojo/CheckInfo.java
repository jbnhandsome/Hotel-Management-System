package com.jbn.orderinfo.pojo;

public class CheckInfo {
    private String checkId;//入住单号

    private String orderId; //预订单号

    private String checkName; //预定人 2

    private String checkPhone; //联系电话 3

    private String checkIDcard; // 4身份证号

    private String arrireTime; //抵店时间 5

    private String leaveTime; //离店时间 6

    private String typeId; //房间类型->对应roomtype表 7<-------------------

    private String roomId;//房间id

    private String checkNum; //入住人数 8

    private String price; //客房价格 9

    private String checkPrice; //入住价格 10

    private int discount; //折扣 11<-------------------

    private String discountReason; //折扣原因 12

    private String addBed; //是否加床 13

    private String addBedPrice; //加床价格 14

    private String orderMoney; //预收款 15

    private String money; //应收款 15

    private String checkMoney; //结账收款 15

    private String checkState; //单据状态->该字段前端做下拉框写死 16

    private String checkDate;//结账时间

    private String isCheck;//是否结账

    private String remark; //备注 17

    private String operatorId; //操作员->登录 19<-------------------
    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getCheckPhone() {
        return checkPhone;
    }

    public void setCheckPhone(String checkPhone) {
        this.checkPhone = checkPhone;
    }

    public String getCheckIDcard() {
        return checkIDcard;
    }

    public void setCheckIDcard(String checkIDcard) {
        this.checkIDcard = checkIDcard;
    }

    public String getArrireTime() {
        return arrireTime;
    }

    public void setArrireTime(String arrireTime) {
        this.arrireTime = arrireTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCheckPrice() {
        return checkPrice;
    }

    public void setCheckPrice(String checkPrice) {
        this.checkPrice = checkPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public String getAddBed() {
        return addBed;
    }

    public void setAddBed(String addBed) {
        this.addBed = addBed;
    }

    public String getAddBedPrice() {
        return addBedPrice;
    }

    public void setAddBedPrice(String addBedPrice) {
        this.addBedPrice = addBedPrice;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCheckMoney() {
        return checkMoney;
    }

    public void setCheckMoney(String checkMoney) {
        this.checkMoney = checkMoney;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
}
