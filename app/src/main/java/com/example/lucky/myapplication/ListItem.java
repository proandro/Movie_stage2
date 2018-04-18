package com.example.lucky.myapplication;

public class ListItem {

    private String head;
    private String relase_date;
    private String image;
    private String backurl;
    private String desc;
    private String rating;
    private String id;

    public ListItem(String head, String relase_date, String image, String backurl, String desc, String rating, String id) {
        this.head = head;
        this.relase_date = relase_date;
        this.image = image;
        this.backurl = backurl;
        this.desc = desc;
        this.rating = rating;
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public String getrelase_date() {
        return relase_date;
    }

    public String getImage() {
        return image;
    }

    public String getBackurl() {
        return backurl;
    }

    public String getDesc() {
        return desc;
    }

    public String getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }
}
