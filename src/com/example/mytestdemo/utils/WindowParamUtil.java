package com.example.mytestdemo.utils;

import android.app.Activity;
import android.content.Context;
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
    
}
