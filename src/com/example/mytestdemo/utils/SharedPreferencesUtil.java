package com.example.mytestdemo.utils;

import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Zengcq
 * @date 2016年12月15日
 * @version 1.0
 * @description
 * SharedPreferences 工具类
 */
public class SharedPreferencesUtil {
	 public static String FILLNAME = "config";

	    /**
	     * 存入某个key对应的value值
	     * 
	     * @param context
	     * @param key
	     * @param value
	     */
	    public static void put(Context context, String key, Object value){
	    	put( context,  key,  value, FILLNAME, Context.MODE_PRIVATE);
	    }
	 
	    public static void put(Context context, String key, Object value, String fileName){
	    	put( context,  key,  value, fileName, Context.MODE_PRIVATE);
	    }
	    
	    public static void put(Context context, String key, Object value, int mode){
	    	put( context,  key,  value, FILLNAME, mode);
	    }
	    
	    public static void put(Context context, String key, Object value, String fileName, int mode) {
	        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
	        Editor editor = sp.edit();
	        if (value instanceof String) {
	        	editor.putString(key, (String) value);
	        } else if (value instanceof Integer) {
	        	editor.putInt(key, (Integer) value);
	        } else if (value instanceof Boolean) {
	        	editor.putBoolean(key, (Boolean) value);
	        } else if (value instanceof Float) {
	        	editor.putFloat(key, (Float) value);
	        } else if (value instanceof Long) {
	        	editor.putLong(key, (Long) value);
	        }
	        editor.apply();
//	        editor.commit(); 同步效率低
	    }

	    /**
	     * 得到某个key对应的值
	     * 
	     * @param context
	     * @param key
	     * @param defValue
	     * @return
	     */
	    
	    public static Object get(Context context, String key, Object defValue){
	    	return get( context,  key,  defValue, FILLNAME, Context.MODE_PRIVATE);
	    }
	    
	    public static Object get(Context context, String key, Object defValue, String fileName){
	    	return get( context,  key,  defValue, fileName, Context.MODE_PRIVATE);
	    }
	    
	    public static Object get(Context context, String key, Object defValue, int mode){
	    	return get( context,  key,  defValue, FILLNAME, mode);
	    }
	    
	    public static Object get(Context context, String key, Object defValue, String fileName, int mode) {
	        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
	        if (defValue instanceof String) {
	            return sp.getString(key, (String) defValue);
	        } else if (defValue instanceof Integer) {
	            return sp.getInt(key, (Integer) defValue);
	        } else if (defValue instanceof Boolean) {
	            return sp.getBoolean(key, (Boolean) defValue);
	        } else if (defValue instanceof Float) {
	            return sp.getFloat(key, (Float) defValue);
	        } else if (defValue instanceof Long) {
	            return sp.getLong(key, (Long) defValue);
	        }
	        return null;
	    }

	    /**
	     * 返回所有数据
	     * 
	     * @param context
	     * @return
	     */
	    public static Map<String, ?> getAll(Context context){
	    	return getAll(context,FILLNAME, Context.MODE_PRIVATE);
	    }
	    
	    public static Map<String, ?> getAll(Context context,String fileName){
	    	return getAll(context,fileName, Context.MODE_PRIVATE);
	    }
	    
	    public static Map<String, ?> getAll(Context context,String fileName,int mode) {
	        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
	        return sp.getAll();
	    }

	    /**
	     * 移除某个key值已经对应的值
	     * 
	     * @param context
	     * @param key
	     */
	    public static void remove(Context context, String key){
	    	remove(context, key,FILLNAME,Context.MODE_PRIVATE);
	    }
	    
	    public static void remove(Context context, String key,String fileName){
	    	remove(context, key,fileName,Context.MODE_PRIVATE);
	    }
	    
	    public static void remove(Context context, String key, String fileName,int mode) {
	        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
	        Editor editor = sp.edit();
	        editor.remove(key);
	        editor.apply();
//	        editor.commit(); 同步效率低
	    }

	    /**
	     * 清除所有内容
	     * 
	     * @param context
	     */
	    public static void clear(Context context){
	    	clear(context, FILLNAME,  Context.MODE_PRIVATE);
	    }
	    
	    public static void clear(Context context,String fileName){
	    	clear(context, fileName,  Context.MODE_PRIVATE);
	    }
	    
	    public static void clear(Context context, String fileName, int mode) {
	        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
	        Editor editor = sp.edit();
	        editor.clear();
	        editor.apply();//是将修改的数据提交到内存, 而后异步真正的提交到硬件磁盘.
//	        editor.commit(); 方法是同步提交到硬件磁盘，因此，在多个并发的提交commit的时候，
//	                     他们会等待正在处理的commit保存到磁盘后在操作，从而降低了效率。
	    }
}


