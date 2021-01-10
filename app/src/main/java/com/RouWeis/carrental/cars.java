package com.RouWeis.carrental;

public class cars {
private String title;
private String Desc;
private String boite;
private String Adress;
private String price;
private String img;


public cars(){

}

    public cars(String title, String desc, String boite, String adress, String price, String img) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
