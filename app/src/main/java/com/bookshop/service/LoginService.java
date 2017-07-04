package com.bookshop.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public interface LoginService {

    @POST("/bookshop/Submit1")
    Call<ResponseBody> auth(
                            @Header(value = "device") String device,
                            @Field(value="username") String username,
                            @Field(value = "password") String password);

    @POST("/bookshop/Cegister")
    Call<ResponseBody> register(
            @Header(value = "device") String device,
            @Field(value="username") String username,
            @Field(value = "password") String password);

}
