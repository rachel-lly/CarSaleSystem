package com.example.carsalesystem.model;

public class Customer {

    private String customer_id;
    private String name;
    private String sex;
    private String age;
    private String phone;

    public Customer(String customer_id, String name, String sex, String age, String phone) {
        this.customer_id = customer_id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
    }

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
