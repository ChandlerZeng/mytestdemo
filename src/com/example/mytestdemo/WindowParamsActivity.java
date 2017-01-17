package com.example.mytestdemo;

import android.os.Bundle;
import android.widget.TextView;

import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.utils.WindowParamUtil;

public class WindowParamsActivity extends BaseActivity {

    private TextView txtWinParams;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_params);
        init();
    }
    
    private void init(){
        txtWinParams = (TextView)findViewById(R.id.txt_window_params);
        int width = WindowParamUtil.getWindowWidth(this);
        int height = WindowParamUtil.getWindowHeight(this);
        txtWinParams.setText("手机屏幕分辨率为(宽X高)："+width+"X"+height);
    }
}
