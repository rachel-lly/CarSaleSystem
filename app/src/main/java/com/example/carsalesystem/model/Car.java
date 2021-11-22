package com.example.carsalesystem.model;

public class Car {

    /**
     * agency_id : 111
     * car_id : s4389
     * car_name : 布加迪
     * price : 25
     * count : 5
     * sell_number : 1
     * description : 布加迪的车子就像是艺术品一般，它车辆的引擎全是由手工制造和调校，所有可以轻量化的零件都不放过，布加迪注重车辆的细节与平衡。
     * url : http://p0.ifengimg.com/pmop/2018/0319/44D5B117CDE99FCBFF08BD53FC77908873FEA74E_size410_w1080_h1920.jpeg
     */

    private String agency_id;
    private String car_id;
    private String car_name;
    private String price;
    private int count;
    private int sell_number;
    private String description;
    private String url;

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSell_number() {
        return sell_number;
    }

    public void setSell_number(int sell_number) {
        this.sell_number = sell_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
