package com.phxc.notenayhandmade.Models;

public class Test1 {
    private String title;
    private int imgID;

    public Test1(String title, int imgID) {
        this.title = title;
        this.imgID = imgID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
