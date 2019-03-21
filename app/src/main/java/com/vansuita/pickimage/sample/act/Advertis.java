package com.vansuita.pickimage.sample.act;


import java.util.HashMap;

public class Advertis {


    private int id;
    private String title;
    private String decription;
    private String phones;
    private byte[] image;

    public Advertis(int id, String title, String decription, String phones, byte[] image) {

        this.id = id;
        this.title = title;
        this.decription = decription;
        this.phones = phones;
        this.image = image;


    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}















