package com.example.carsalesystem.application;

import android.app.Application;

public class MyApplication extends Application {
    private static Application context;

    public static Application getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
