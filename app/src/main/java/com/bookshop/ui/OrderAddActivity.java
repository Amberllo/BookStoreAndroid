package com.bookshop.ui;

import android.os.Bundle;

import com.bookshop.R;
import com.bookshop.bean.BookBean;
import com.bookshop.view.FormTextInputView;

public class OrderAddActivity extends BaseActivity {

    FormTextInputView view_bookname;
    FormTextInputView view_buycount;
    FormTextInputView view_bookprice;
    BookBean bookBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_add);
        getSupportActionBar().setTitle("购买书本");

        bookBean = (BookBean) getIntent().getSerializableExtra("bookBean");
        if(bookBean==null)return;

        view_bookname = (FormTextInputView)findViewById(R.id.order_add_bookname);
        view_buycount = (FormTextInputView)findViewById(R.id.order_add_buycount);
        view_bookprice = (FormTextInputView)findViewById(R.id.order_add_bookprice);

        view_bookname.setTitle("书名");
        view_bookname.setReadonly(true);
        view_bookname.refresh(bookBean.getBookName());

        view_bookprice.setTitle("书本价格");
        view_bookprice.setReadonly(true);
        view_bookprice.refresh(bookBean.getBookPrice());

        view_buycount.setTitle("购买数量");
        view_buycount.setHint("请输入购买数量");
        view_buycount.setRequire(true);

    }
}
