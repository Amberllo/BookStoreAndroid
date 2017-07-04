package com.bookshop.service;

import com.bookshop.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public interface LoginService {

    @GET("/bookshop/Submit1")
    Observable<ResponseBody> auth(
            @Header(value = "device") String device,
            @Query(value="username") String username,
            @Query(value = "userpsw") String password);

    @POST("/bookshop/Cegister")
    Observable<ResponseBody> register(
            @Header(value = "device") String device,
            @Query(value="username") String username,
            @Query(value = "password") String password);

}
