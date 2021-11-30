package com.example.carsalesystem.model;

public class Order {

    /**
     * agency_id : 111
     * order_id : o5064
     * customer_id : c5090
     * customer_name : 鲁班七号
     * car_id : s4397
     * car_name : 本田
     * price : 55
     * count : 1
     * order_time : 2021-11-30 21:30:12
     * sellman_name : 李远安
     * agency_name : 广汇汽车
     * sellman_id : u2002
     */

    private String agency_id;
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
    private String sellman_id;

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

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

    public String getSellman_id() {
        return sellman_id;
    }

    public void setSellman_id(String sellman_id) {
        this.sellman_id = sellman_id;
    }
}
