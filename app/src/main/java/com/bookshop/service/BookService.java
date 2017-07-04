package com.bookshop.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static android.R.attr.value;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public interface BookService {

    //bookshop/BookServlet?bookClassName=java
    @POST("/bookshop/BookServlet")
    Observable<ResponseBody> getBookList(@Header(value = "device") String device,
                                         @Query(value = "bookClassName") String bookClassName);
}
