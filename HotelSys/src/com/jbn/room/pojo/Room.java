package com.jbn.room.pojo;

public class Room {
    //对应数据库的roomInfo表
    private String roomId;//房间编号

    private String typeId; //类型编号

    private String typeName;//类型名称

    private int floorId;//楼层编号

    private int ratedNum;//可住人数

    private int bedNum;//床位数

    private String roomDescription; //房间描述

    private String remark; //备注

    private String status; //状态

    private String isSplice; //是否可拼房

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getRatedNum() {
        return ratedNum;
    }

    public void setRatedNum(int ratedNum) {
        this.ratedNum = ratedNum;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsSplice() {
        return isSplice;
    }

    public void setIsSplice(String isSplice) {
        this.isSplice = isSplice;
    }
    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                "typeId='" + typeId + '\'' +
                ", floorId='" + floorId + '\'' +
                ", ratedNum='" + ratedNum + '\'' +
                ", bedNum='" + bedNum + '\'' +
                ", roomDescription=" + roomDescription +
                ", remark=" + remark +
                ", status=" + status +
                ", isSplice='" + isSplice + '\'' +
                '}';
    }
}
