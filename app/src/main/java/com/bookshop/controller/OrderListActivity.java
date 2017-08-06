package com.bookshop.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bookshop.R;
import com.bookshop.bean.OrderBean;
import com.bookshop.bean.UserBean;
import com.bookshop.service.ServiceFactory;
import com.bookshop.util.StoreUtil;
import com.bookshop.view.OrderListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class OrderListActivity extends AppCompatActivity {


    OrderListView list_order;
    UserBean userBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("我的订单");
        setSupportActionBar(toolbar);

        userBean = StoreUtil.getStoreUser(this);
        list_order = (OrderListView)findViewById(R.id.order_list);

        ServiceFactory.orderService().orderList("Android",userBean.getUserid())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String json = responseBody.string();
                        try{
                            List<OrderBean> books = new Gson().fromJson(json, new TypeToken<List<OrderBean>>(){}.getType());
                            list_order.setData(books);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });

    }

}
