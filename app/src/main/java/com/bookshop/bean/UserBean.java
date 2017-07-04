package com.bookshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class UserBean implements Serializable {
    private String userid;
    private String username;
    private String userpsw;
    public String getPassword() {
        return userpsw;
    }

    public String getUserName() {
        return username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUserpsw(String userpsw) {
        this.userpsw = userpsw;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
