package com.app.code;

public class Featureslist_method {

    private String description;
    private String name;
    private String price;
    private String url;

    public Featureslist_method() {
    }

    public Featureslist_method(String description, String name, String price, String url) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
