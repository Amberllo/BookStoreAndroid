package com.bookshop.service;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public interface BookService {
    @POST("/bookshop/BookServlet")
    Call<Response> getBookList(@Header(value = "device") String device);
}
