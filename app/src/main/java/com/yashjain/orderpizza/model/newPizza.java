package com.yashjain.orderpizza.model;

public class newPizza {
    private int crustId;
    private int sizeId;
    private int price;
    private String crustName;
    private String sizeName;

    public newPizza(int crustId,int sizeId,int price,String crustName,String sizeName){
        this.crustId=crustId;
        this.sizeId=sizeId;
        this.price=price;
        this.crustName=crustName;
        this.sizeName=sizeName;
    }

    public int getCrustId() {
        return crustId;
    }

    public void setCrustId(int crustId) {
        this.crustId = crustId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCrustName() {
        return crustName;
    }

    public void setCrustName(String crustName) {
        this.crustName = crustName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
}
