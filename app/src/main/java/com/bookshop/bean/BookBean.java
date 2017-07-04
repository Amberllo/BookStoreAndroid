package com.bookshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

public class BookBean implements Serializable{
    private String bookId;
    private String bookName;
    private String book_image;
    private String book_PRICE;
    private String introduce;

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
        return book_PRICE;
    }

    public void setBookPrice(String bookPrice) {
        this.book_PRICE = bookPrice;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getBook_PRICE() {
        return book_PRICE;
    }

    public void setBook_PRICE(String book_PRICE) {
        this.book_PRICE = book_PRICE;
    }

    public String getBook_image() {
        return book_image;
    }

    public void setBook_image(String book_image) {
        this.book_image = book_image;
    }
}
