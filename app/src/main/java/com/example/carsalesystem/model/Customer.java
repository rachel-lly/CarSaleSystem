package com.example.carsalesystem.model;

public class Customer {

    /**
     * customer_id : c5086
     * name : 李白
     * sex : 男
     * age : 45
     * phone : 17753469982
     */

    private String customer_id;
    private String name;
    private String sex;
    private int age;
    private String phone;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
