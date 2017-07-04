package com.bookshop.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bookshop.BuildConfig;
import com.bookshop.bean.UserBean;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/7/4 0004.
 */

public class StoreUtil {

    private static String PREFS_PRIVATE = BuildConfig.APPLICATION_ID;
    public static void storeUser(Context context, UserBean bean){
        if(bean==null)return;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsPrivateEditor = sharedPreferences.edit();
        prefsPrivateEditor.putString("UserBean", new Gson().toJson(bean));
        prefsPrivateEditor.commit();
    }

    public static UserBean getStoreUser(Context context){
        UserBean userBean=null;

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_PRIVATE, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("UserBean","");
        if(!TextUtils.isEmpty(json)){
            try {
                userBean = new Gson().fromJson(json,UserBean.class);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return userBean;
    }

}
