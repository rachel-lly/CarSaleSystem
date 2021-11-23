package com.example.carsalesystem.retrofit;

import com.example.carsalesystem.model.Car;

import java.util.List;

import io.reactivex.Observable;

public class DataManager {

    private static DataManager instance;
    private RetrofitService retrofitService;

    public DataManager(){
        this.retrofitService = RetrofitHelper.getInstance().getServer();
    }

    public static DataManager getInstance(){
        if(instance==null){
            instance = new DataManager();
        }
        return instance;
    }

    public Observable<List<Car>> getCar(String agencyId){
        return retrofitService.getCar(agencyId);
    }
}
