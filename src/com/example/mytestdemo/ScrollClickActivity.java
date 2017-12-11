package com.example.mytestdemo;

import com.example.mytestdemo.utils.LogUtil;
import com.example.mytestdemo.utils.MyToast;
import com.example.mytestdemo.widget.ScrollClickLayout;
import com.example.mytestdemo.widget.ScrollClickLayout.SetOnSlideListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

public class ScrollClickActivity extends Activity implements OnClickListener,SetOnSlideListener,OnTouchListener{

    private Button btnScroll1;
    private Button btnScroll2;
    private ScrollClickLayout scrollClickLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_click);
        btnScroll1 = (Button) findViewById(R.id.scroll_click_btn1);
        btnScroll2 = (Button) findViewById(R.id.scroll_click_btn2);
        btnScroll1.setOnClickListener(this);
        btnScroll2.setOnClickListener(this);
        scrollClickLayout = (ScrollClickLayout) findViewById(R.id.scroll_click_layout);
        scrollClickLayout.setSlideListener(this);
        btnScroll1.setOnTouchListener(this);
        btnScroll2.setOnTouchListener(this);
        btnScroll1.requestFocus();
        btnScroll1.requestFocusFromTouch();
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.scroll_click_btn1:
//            MyToast.makeText(ScrollClickActivity.this, "btn_scroll_click1 clicked!", MyToast.LENGTH_SHORT).show();
            break;
            
        case R.id.scroll_click_btn2:
//            MyToast.makeText(ScrollClickActivity.this, "btn_scroll_click2 clicked!", MyToast.LENGTH_SHORT).show();
            break;

        default:
            break;
        }
    }
    
    
    @Override
    public void onRightToLeftSlide() {
        // TODO Auto-generated method stub
        MyToast.makeText(ScrollClickActivity.this, "scrollclicklayout onRightToLeftSlide", MyToast.LENGTH_SHORT).show();
    }
    @Override
    public void onLeftToRightSlide() {
        MyToast.makeText(ScrollClickActivity.this, "scrollclicklayout onLeftToRightSlide", MyToast.LENGTH_SHORT).show();
        
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            MyToast.makeText(ScrollClickActivity.this, v.getId()+" MotionEvent.ACTION_DOWN", MyToast.LENGTH_SHORT).show();
            LogUtil.i(v.getId()+" onTouchEvent ACTION_DOWN");
            break;
            
        case MotionEvent.ACTION_MOVE:
            MyToast.makeText(ScrollClickActivity.this, v.getId()+" MotionEvent.ACTION_MOVE", MyToast.LENGTH_SHORT).show();
            LogUtil.i(v.getId()+" onTouchEvent ACTION_MOVE");
            break;
            
        case MotionEvent.ACTION_UP:
            MyToast.makeText(ScrollClickActivity.this, v.getId()+" MotionEvent.ACTION_UP", MyToast.LENGTH_SHORT).show();
            LogUtil.i(v.getId()+" onTouchEvent ACTION_UP");
            break;

        }
        return super.onTouchEvent(event);
    }
}
