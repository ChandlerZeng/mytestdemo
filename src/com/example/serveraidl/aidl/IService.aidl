package com.example.serveraidl.aidl;

import com.example.mytestdemo.entity.book;
import com.example.serveraidl.aidl.BookChangeListener;

interface IService{
String hello(String name);
int helloTime(int time);
Book getBook();
int price();
String bookName();
String author();
void registerListener(BookChangeListener listener);
void unRegisterListener(BookChangeListener listener);
}