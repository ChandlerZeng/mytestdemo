package com.example.mytestdemo.utils;

import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Zengcq
 * @date 2016��12��15��
 * @version 1.0
 * @description
 * SharedPreferences ������
 */
public class SharedPreferencesUtil {
	 public static String FILLNAME = "config";

	    /**
	     * ����ĳ��key��Ӧ��valueֵ
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
//	        editor.commit(); ͬ��Ч�ʵ�
	    }

	    /**
	     * �õ�ĳ��key��Ӧ��ֵ
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
	     * ������������
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
	     * �Ƴ�ĳ��keyֵ�Ѿ���Ӧ��ֵ
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
//	        editor.commit(); ͬ��Ч�ʵ�
	    }

	    /**
	     * �����������
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
	        editor.apply();//�ǽ��޸ĵ������ύ���ڴ�, �����첽�������ύ��Ӳ������.
//	        editor.commit(); ������ͬ���ύ��Ӳ�����̣���ˣ��ڶ���������ύcommit��ʱ��
//	                     ���ǻ�ȴ����ڴ����commit���浽���̺��ڲ������Ӷ�������Ч�ʡ�
	    }
}


