package com.RouWeis.carrental;

public class Cars {
private String title;
private String Desc;
private String boite;
private String Adress;
private double price;
private int img;


public Cars(){

}
    public Cars(String title, String desc, String boite, String adress, double price, int img) {
        this.title = title;
        Desc = desc;
        this.boite = boite;
        Adress = adress;
        this.price = price;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getBoite() {
        return boite;
    }

    public void setBoite(String boite) {
        this.boite = boite;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
