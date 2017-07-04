package com.bookshop.service;

import com.bookshop.BuildConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class ServiceFactory {

    private static String ServiceHost = "http://192.168.100.103:8080";
    static Retrofit createRetrofit() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceHost)
                .client(client)////set debug
                .addConverterFactory(GsonConverterFactory.create())//primitive type
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static LoginService loginService(){
        return createRetrofit().create(LoginService.class);
    }

    public static BookService bookService(){
        return createRetrofit().create(BookService.class);
    }

    public static OrderService orderService(){
        return createRetrofit().create(OrderService.class);
    }
}
