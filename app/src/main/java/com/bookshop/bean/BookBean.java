package com.bookshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class BookBean implements Serializable{
    private String bookId;
    private String bookName;
    private String bookPrice;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
}
