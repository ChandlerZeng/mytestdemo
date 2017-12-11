package com.example.mytestdemo.widget;

import com.example.mytestdemo.utils.LogUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * @author Zengcq
 * @date 2017Äê5ÔÂ10ÈÕ
 * @version 1.0
 * @description
 */
public class ScrollClickLayout extends LinearLayout{
    private boolean isScrolling;
    private float touchDownX;
    
    private float touchUpX;
    private SetOnSlideListener mSetOnSlideListener;

    public ScrollClickLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            touchDownX = ev.getX();
            isScrolling = false;
            LogUtil.i("scrolllayout onInterceptTouchEvent ACTION_DOWN");
            break;
            
        case MotionEvent.ACTION_MOVE:
            if(Math.abs(touchDownX-ev.getX())>ViewConfiguration.get(getContext()).getScaledTouchSlop()){
                isScrolling = true;
            }else{
                isScrolling = false;
            }
            LogUtil.i("scrolllayout onInterceptTouchEvent ACTION_MOVE");
            break;
            
        case MotionEvent.ACTION_UP:
            isScrolling = false;
            LogUtil.i("scrolllayout onInterceptTouchEvent ACTION_UP");
            break;

        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            LogUtil.i("scrolllayout onTouchEvent ACTION_DOWN");
            return true;
            
        case MotionEvent.ACTION_MOVE:
            touchUpX = event.getX();
            if (touchDownX - touchUpX > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                if(mSetOnSlideListener!=null){
                    mSetOnSlideListener.onRightToLeftSlide();
                }
            }
            if (touchDownX - touchUpX < -ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                if(mSetOnSlideListener!=null){
                    mSetOnSlideListener.onLeftToRightSlide();
                }
            }
            LogUtil.i("scrolllayout onTouchEvent ACTION_MOVE");
            break;
            
        case MotionEvent.ACTION_UP:
            LogUtil.i("scrolllayout onTouchEvent ACTION_UP");
            break;

        }
        return isScrolling;
    }
    
    public void setSlideListener(SetOnSlideListener setOnSlideListener){
        this.mSetOnSlideListener = setOnSlideListener;
    }
    
    public interface SetOnSlideListener{
        void onRightToLeftSlide();
        void onLeftToRightSlide();
    }
    
    

}
