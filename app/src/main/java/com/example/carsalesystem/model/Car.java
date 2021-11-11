package com.example.carsalesystem.model;

public class Car {

    private String car_id;
    private String car_name;
    private String price;
    private int count;
    private int sell_number;

    private String url;
    private String carDes;

    public Car(String car_id, String car_name, String price, int count, int sell_number, String url, String carDes) {
        this.car_id = car_id;
        this.car_name = car_name;
        this.price = price;
        this.count = count;
        this.sell_number = sell_number;
        this.url = url;
        this.carDes = carDes;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCarDes() {
        return carDes;
    }

    public void setCarDes(String carDes) {
        this.carDes = carDes;
    }
}
