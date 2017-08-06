package com.bookshop.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bookshop.R;
import com.bookshop.bean.BookBean;
import com.bookshop.bean.UserBean;
import com.bookshop.service.ServiceFactory;
import com.bookshop.util.StoreUtil;
import com.bookshop.view.FormTextInputView;
import com.bumptech.glide.Glide;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class OrderAddActivity extends BaseActivity {

    FormTextInputView view_bookname;
    FormTextInputView view_buycount;
    FormTextInputView view_bookprice;
    ImageView img_bookicon;
    Button btn_submit;
    BookBean bookBean;
    UserBean userBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_add);
        getSupportActionBar().setTitle("购买书本");
        userBean = StoreUtil.getStoreUser(this);
        bookBean = (BookBean) getIntent().getSerializableExtra("bookBean");
        if(bookBean==null)return;
        btn_submit = (Button)findViewById(R.id.order_add_submit);
        view_bookname = (FormTextInputView)findViewById(R.id.order_add_bookname);
        view_buycount = (FormTextInputView)findViewById(R.id.order_add_buycount);
        view_bookprice = (FormTextInputView)findViewById(R.id.order_add_bookprice);
        img_bookicon = (ImageView)findViewById(R.id.order_add_bookicon);
        view_bookname.setTitle("书名");
        view_bookname.setReadonly(true);
        view_bookname.refresh(bookBean.getBookName());

        view_bookprice.setTitle("书本价格");
        view_bookprice.setReadonly(true);
        view_bookprice.refresh(bookBean.getBookPrice());

        view_buycount.setTitle("购买数量");
        view_buycount.setHint("请输入购买数量");
        view_buycount.setRequire(true);

        Glide.with(this).load("file:///android_asset/img/"+bookBean.getBook_image()).into(img_bookicon);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view_buycount.onValidate()){
                    ServiceFactory.orderService().addOrder("Android",
                            bookBean.getBookId(),bookBean.getBook_PRICE(),
                            view_buycount.getData(),userBean.getUserid())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            Toast.makeText(OrderAddActivity.this,"提交订单成功！",Toast.LENGTH_SHORT);
                            finish();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });
                }
            }
        });
    }
}
