package com.example.carsalesystem.model;

public class Order {

    /**
     * order_id : o3006
     * customer_id : c5097
     * customer_name : 李清照
     * car_id : s4392
     * car_name : 雪佛兰5系
     * price : 46
     * count : 1
     * order_time : 2014-02-27
     * sellman_name : 李远安
     * agency_name : 广汇汽车
     */

    private String order_id;
    private String customer_id;
    private String customer_name;
    private String car_id;
    private String car_name;
    private String price;
    private int count;
    private String order_time;
    private String sellman_name;
    private String agency_name;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getSellman_name() {
        return sellman_name;
    }

    public void setSellman_name(String sellman_name) {
        this.sellman_name = sellman_name;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }
}
