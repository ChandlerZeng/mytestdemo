package com.example.mytestdemo.utils;

import com.example.mytestdemo.BuildConfig;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

/**
 * @author Zengcq
 * @date 2016Äê12ÔÂ23ÈÕ
 * @version 1.0
 * @description
 */
public class LogUtil {
    public static String TAG = "RituNavi";
    
    public static void d(String msg){
        if(BuildConfig.DEBUG){
            d(TAG,msg);
        }
    }
    
    public static void i(String msg){
        if(BuildConfig.DEBUG){
            i(TAG,msg);
        }
    }
    
    public static void e(String msg){
        if(BuildConfig.DEBUG){
            e(TAG,msg);
        }
    }
    
    public static void e(String tag, String msg) 
    {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }
    
    public static void d(String tag, String msg) 
    {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }
    
    public static void i(String tag, String msg) 
    {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }
    
    
    public static void e(Context context, String msg) 
    {
        if (BuildConfig.DEBUG) {
            String tag = context.getClass().getSimpleName().toString();
            Log.e(tag.toString(), msg);
        }
    }
    
    public static void d(Context context, String msg) 
    {
        if (BuildConfig.DEBUG) {
            String tag = context.getClass().getSimpleName().toString();
            Log.d(tag.toString(), msg);
        }
    }
    
    public static void i(Context context, String msg) 
    {
        if (BuildConfig.DEBUG) {
            String tag = context.getClass().getSimpleName().toString();
            Log.i(tag.toString(), msg);
        }
    }
}
