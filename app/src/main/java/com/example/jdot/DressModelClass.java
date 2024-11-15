package com.example.jdot;

public class DressModelClass {
    int img;
    String qualtyName="";
    long price;
    public DressModelClass(int img , String qualtyName,long price){
        this.img = img ;
        this.qualtyName = qualtyName;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getQualtyName() {
        return qualtyName;
    }

    public void setQualtyName(String qualtyName) {
        this.qualtyName = qualtyName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
