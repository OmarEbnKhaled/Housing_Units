package com.omar.housingunits.Models;

public class CategoryModel {

    private String title, no_ads;
    private int icon;

    public CategoryModel(String title, String no_ads, int icon) {
        this.title = title;
        this.no_ads = no_ads;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNo_ads() {
        return no_ads;
    }

    public void setNo_ads(String no_ads) {
        this.no_ads = no_ads;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
