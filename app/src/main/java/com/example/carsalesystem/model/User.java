package com.example.carsalesystem.model;

public class User {

    /**
     * agency_id : 222
     * id : u2003
     * username : 林利莹
     * sex : 女
     * age : 20
     * agency_name : 中升集团
     * phone : 18932792019
     */

    private String agency_id;
    private String id;
    private String username;
    private String sex;
    private int age;
    private String agency_name;
    private String phone;

    public User(String agency_id, String id, String username, String sex, int age, String agency_name, String phone) {
        this.agency_id = agency_id;
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.agency_name = agency_name;
        this.phone = phone;
    }

    public String getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(String agency_id) {
        this.agency_id = agency_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
