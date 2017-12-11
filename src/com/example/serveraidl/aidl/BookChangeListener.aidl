package com.example.serveraidl.aidl;

import com.example.mytestdemo.entity.book;

interface BookChangeListener{
void OnBookChangeListener(in Book book);
}