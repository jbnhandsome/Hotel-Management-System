package com.jbn.roomtype.pojo;

public class RoomType {
    private String typeId; //类型编号

    private String typeName; //类型名称

    private String price; //价格

    private String splicPrice; //拼房价格

    private int exceedance; //可超预定数

    private String isSplice; //是否可拼房

    private boolean isNull; //是否为空值对象

    public RoomType() {
        super();
    }

    public RoomType(String typeId) {
        this.typeId = typeId;
    }

    public RoomType(String typeId, String typeName, boolean isNull) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.isNull = isNull;
    }

    public RoomType(String typeId, String typeName, String price, String splicPrice, int exceedance, String isSplice) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.price = price;
        this.splicPrice = splicPrice;
        this.exceedance = exceedance;
        this.isSplice = isSplice;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSplicPrice() {
        return splicPrice;
    }

    public void setSplicPrice(String splicPrice) {
        this.splicPrice = splicPrice;
    }

    public int getExceedance() {
        return exceedance;
    }

    public void setExceedance(int exceedance) {
        this.exceedance = exceedance;
    }

    public String getIsSplice() {
        return isSplice;
    }

    public void setIsSplice(String isSplice) {
        this.isSplice = isSplice;
    }

    public boolean isNull() {
        return isNull;
    }

    /**
     * 如果为null，则设置为true，否则false
     *
     * @param aNull 对象是否为空
     */
    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", price='" + price + '\'' +
                ", splicPrice='" + splicPrice + '\'' +
                ", exceedance=" + exceedance +
                ", isSplice='" + isSplice + '\'' +
                ", isNull=" + isNull +
                '}';
    }
}
