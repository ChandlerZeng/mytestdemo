package com.example.mytestdemo.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * @author Zengcq
 * @date 2017Äê1ÔÂ17ÈÕ
 * @version 1.0
 * @description
 */
public class WindowParamUtil {
    public static int getWindowWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics); 
        return metrics.widthPixels;
    }
    
    public static int getWindowHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
    
    public static int getStatusBarHeight(Activity activity){
        Rect frame = new Rect();  
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);  
        int statusBarHeight = frame.top;        
        return statusBarHeight;
    }
    
    public static int getActionBarHeight(Activity activity){
        int actionBarHeight = activity.getActionBar().getHeight(); 
        return actionBarHeight;
        
    }
    
    public static int getContentViewHeight(Activity activity){
        int contentHeight = getWindowHeight(activity)-getStatusBarHeight(activity)-getActionBarHeight(activity);
        return contentHeight;
    }
    
}
