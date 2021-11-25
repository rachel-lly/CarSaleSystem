package com.example.carsalesystem.controller;

import com.example.carsalesystem.model.User;

public class UserController {

    private User user;

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

    public void setUser(String id,String name,String sex,int age,String agency_name,String phone){
        if(user==null){
            user = new User(id,name,sex,age,agency_name,phone);
        }
    }


}
