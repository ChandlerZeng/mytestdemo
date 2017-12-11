package com.example.mytestdemo.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Zengcq
 * @date 2017Äê5ÔÂ15ÈÕ
 * @version 1.0
 * @description
 */
public class Book implements Parcelable {

    private int price;
    private String bookName;
    private String author;
    
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Book(Parcel in){
        price = in.readInt();
        bookName = in.readString();
        author = in.readString();
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
    public static final Creator<Book> CREATOR = new Creator<Book>() {

        @Override
        public Book createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeInt(price);
        dest.writeString(bookName);
        dest.writeString(author);
    }
    
    public String toString(){
        return "price:"+price+" bookName:"+bookName+" author:"+author;
    }

}
