package com.bookshop.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private ProgressDialog mProgressView;
    protected void loading(String message){
        if(mProgressView!=null && mProgressView.isShowing()){
            mProgressView.setMessage(message);

        }else{

            mProgressView = new ProgressDialog(this);
            mProgressView.setMessage(message);
            mProgressView.show();
        }

    }

    protected void dismissLoading(){
        if(mProgressView!=null && mProgressView.isShowing()){
            mProgressView.dismiss();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoading();
    }
}
