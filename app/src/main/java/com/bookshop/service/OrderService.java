package com.bookshop.service;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public interface OrderService {


    @POST("/bookshop/OrderListServlet")
    Observable<ResponseBody> orderList(@Header(value = "device") String device,
                                   @Query(value="userid") String userid);

    @POST("/bookshop/TrolleyServlet")
    Observable<ResponseBody> addOrder(@Header(value = "device") String device,
                                      @Query(value = "bookid") String bookid,
                                      @Query(value = "bookPrice") String bookPrice,
                                      @Query(value="bookCount") String bookCount,
                                      @Query(value="userid") String userid);

}
