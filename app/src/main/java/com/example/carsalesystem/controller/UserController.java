package com.example.carsalesystem.controller;

import com.example.carsalesystem.model.Agency;
import com.example.carsalesystem.model.User;

public class UserController {

    private User user;
    private Agency agency;
    private boolean isUser;

    private static UserController sInstance;

    public static UserController getsInstance() {
        if(sInstance==null){
            sInstance = new UserController();
        }
        return sInstance;
    }

    public String getUserId(){
        return user.getId();
    }

    public User getUser(){
        return user;
    }

    public void setUser(String agency_id,String id,String name,String sex,int age,String agency_name,String phone){
        user = new User(agency_id,id,name,sex,age,agency_name,phone);
        isUser = true;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public Agency getAgency(){
        return agency;
    }

    public void setAgency(String id,String name,String phone){
        agency = new Agency(id,name,phone);
        isUser = false;
    }

}
