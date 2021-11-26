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

interface RetrofitService {

    @GET("FindCar?")
    Observable<List<Car>> getCarList(@Query("agency_id")String agencyId);

    @GET("FindCustomer")
    Observable<List<Customer>> getCustomer();

    @GET("login?")
    Observable<ResponseBody> login(@Query("id")String id, @Query("password")String password);

    @GET("FindUser?")
    Observable<User> getUser(@Query("id")String id);

    @GET("GetCustomer?")
    Observable<Customer> getCustomer(@Query("customer_id")String customer_id);

    @GET("SerOrders?")
    Observable<List<Order>> getOrders(@Query("customer_id")String customer_id);

    @GET("GetCar?")
    Observable<Car> getCar(@Query("car_id")String car_id);

}
