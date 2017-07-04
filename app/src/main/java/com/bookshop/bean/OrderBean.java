package com.bookshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class OrderBean implements Serializable{
    private String order_ID;
    private String user_ID;
    private String book_ID;
    private String book_NUM;
    private String book_NAME;
    private String book_image;
    private String total_NUM;
    private String order_TIME;

    public String getBookNum() {
        return book_NUM;
    }

    public void setBookNum(String bookNum) {
        this.book_NUM = bookNum;
    }

    public String getBookName() {
        return book_NAME;
    }

    public void setBookName(String bookName) {
        this.book_NAME = bookName;
    }

    public String getBookId() {
        return book_ID;
    }

    public void setBookId(String bookId) {
        this.book_ID = bookId;
    }

    public String getOrderId() {
        return order_ID;
    }

    public void setOrderId(String orderId) {
        this.order_ID = orderId;
    }

    public void setUserId(String userId){
        this.user_ID = userId;
    }

    public String getUserId(){
        return user_ID;
    }

    public String getTotal_NUM() {
        return total_NUM;
    }

    public void setTotal_NUM(String total_NUM) {
        this.total_NUM = total_NUM;
    }

    public String getOrder_TIME() {
        return order_TIME;
    }

    public void setOrder_TIME(String order_TIME) {
        this.order_TIME = order_TIME;
    }

    public String getBook_NAME() {
        return book_NAME;
    }

    public void setBook_NAME(String book_NAME) {
        this.book_NAME = book_NAME;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }
}
