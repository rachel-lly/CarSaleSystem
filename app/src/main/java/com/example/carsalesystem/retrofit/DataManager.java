package com.example.carsalesystem.retrofit;

import com.example.carsalesystem.model.Car;
import com.example.carsalesystem.model.Customer;
import com.example.carsalesystem.model.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Query;

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

    public Observable<List<Customer>> getCustomer(){
        return retrofitService.getCustomer();
    }

    public Observable<ResponseBody> login(String id, String password){
        return retrofitService.login(id,password);
    }

    public Observable<User> getUser(String id){
        return retrofitService.getUser(id);
    }

    public  Observable<Customer> getCustomer(String customer_id){
        return retrofitService.getCustomer(customer_id);
    }
}