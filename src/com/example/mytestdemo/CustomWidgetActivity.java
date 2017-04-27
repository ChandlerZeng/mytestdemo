package com.example.mytestdemo;

import android.os.Bundle;

import com.example.mytestdemo.base.BaseActivity;

public class CustomWidgetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_widget);
    }
}
