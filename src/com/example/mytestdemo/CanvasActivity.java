package com.example.mytestdemo;

import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.widget.DrawView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class CanvasActivity extends BaseActivity {
    
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        init();
    }
    
    private void init(){
       layout = (LinearLayout)findViewById(R.id.canvas_layout); 
       final DrawView view=new DrawView(this);  
       view.setMinimumHeight(500);  
       view.setMinimumWidth(300);  
       //通知view组件重绘    
       view.invalidate();  
       layout.addView(view);  
         
    }
}
