package com.example.carsalesystem.retrofit;

import com.example.carsalesystem.model.Car;
import com.example.carsalesystem.model.Customer;
import com.example.carsalesystem.model.Order;
import com.example.carsalesystem.model.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
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

    public Observable<List<Car>> getCarList(String agencyId){
        return retrofitService.getCarList(agencyId);
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

    public Observable<List<Order>> getOrders(String customer_id){
        return retrofitService.getOrders(customer_id);
    }


    public Observable<Car> getCar(String carId){
        return retrofitService.getCar(carId);
    }

    public Observable<ResponseBody> addOrder(
            String customer_id,
            String car_id,
            int count,
            String order_time,
            String sellman_id){
        return retrofitService.addOrder(customer_id, car_id, count, order_time, sellman_id);
    }

    public Observable<ResponseBody> addCustomer(
            String name,
            String sex,
            String age,
            String phone){
        return  retrofitService.addCustomer(name, sex, age, phone);
    }

    public Observable<List<Order>> getOrderList(String agencyId){
        return retrofitService.getOrderList(agencyId);
    }

    public Observable<ResponseBody> updateUser(
            String id,
            String name,
            String sex,
            int age,
            String agency_name,
            String phone,
            String agency_id){
        return retrofitService.updateUser(id, name, sex, age, agency_name, phone, agency_id);
    }

    public Observable<ResponseBody> delOrder(String order_id){
        return retrofitService.delOrder(order_id);
    }

    public Observable<Order> getOrder(String order_id){
        return retrofitService.getOrder(order_id);
    }
}
