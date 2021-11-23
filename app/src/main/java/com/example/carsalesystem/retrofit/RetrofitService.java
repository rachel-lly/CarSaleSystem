package com.example.carsalesystem.retrofit;

import com.example.carsalesystem.model.Car;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RetrofitService {

    @GET("FindCar?")
    Observable<List<Car>> getCar(@Query("agency_id")String agencyId);
}
