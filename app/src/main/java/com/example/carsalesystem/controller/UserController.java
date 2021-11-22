package com.example.carsalesystem.controller;

public class UserController {

    private static UserController sInstance;

    public static UserController getsInstance() {
        if(sInstance==null){
            sInstance = new UserController();
        }
        return sInstance;
    }


}
