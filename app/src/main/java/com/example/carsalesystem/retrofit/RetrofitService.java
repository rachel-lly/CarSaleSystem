package com.example.carsalesystem.retrofit;

import com.example.carsalesystem.model.Agency;
import com.example.carsalesystem.model.Car;
import com.example.carsalesystem.model.Customer;
import com.example.carsalesystem.model.Order;
import com.example.carsalesystem.model.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitService {

    @GET("FindCar?")
    Observable<List<Car>> getCarList(@Query("agency_id")String agencyId);

    @GET("FindOrders?")
    Observable<List<Order>> getOrderList(@Query("agency_id")String agencyId);

    @GET("FindAgency?")
    Observable<Agency> getAgency(@Query("agency_id")String agencyId);

    @GET("GetUser?")
    Observable<List<User>> getAgencyUser(@Query("agency_id")String agencyId);

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

    @GET("GetOrders?")
    Observable<Order> getOrder(@Query("order_id")String order_id);

    @GET("GetCar?")
    Observable<Car> getCar(@Query("car_id")String car_id);


    @GET("AddOrders?")
    Observable<ResponseBody> addOrder(
            @Query("customer_id")String customer_id,
            @Query("car_id")String car_id,
            @Query("count")int count,
            @Query("order_time")String order_time,
            @Query("sellman_id")String sellman_id);

    @GET("AddCustomer?")
    Observable<ResponseBody> addCustomer(
            @Query("name")String name,
            @Query("sex")String sex,
            @Query("age")String age,
            @Query("phone")String phone);

    @GET("UpdUser?")
    Observable<ResponseBody> updateUser(
            @Query("id")String id,
            @Query("username")String name,
            @Query("sex")String sex,
            @Query("age")int age,
            @Query("agency_name")String agency_name,
            @Query("phone")String phone,
            @Query("agency_id")String agency_id);


    @GET("DelOrders?")
    Observable<ResponseBody> delOrder(@Query("order_id")String order_id);

}
