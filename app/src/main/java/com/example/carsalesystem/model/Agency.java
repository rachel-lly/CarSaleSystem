package com.example.carsalesystem.model;

public class Agency {

    /**
     * id : 222
     * name : 中升集团
     * phone : (022)23833035
     */

    private String id;
    private String name;
    private String phone;

    public Agency(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
