package com.example.carsalesystem.retrofit;

import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private Retrofit retrofit;
    private static RetrofitHelper instance = null;
    final static String ROOT_URL = "http://49.234.17.22:8080/houtai_Web2/";//历史热力图

    public RetrofitHelper() {
        //解决handshake failed问题
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
                .allEnabledCipherSuites()
                .build();//解决在Android5.0版本以下https无法访问
        ConnectionSpec spec1 = new ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT).build();//兼容http接口
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(60,TimeUnit.SECONDS);
        httpClient.connectionSpecs(Arrays.asList(spec,spec1));

        retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static RetrofitHelper getInstance() {
        if (instance == null){
            instance = new RetrofitHelper();
        }
        return instance;
    }

    public RetrofitService getServer(){
        return retrofit.create(RetrofitService.class);
    }

}

