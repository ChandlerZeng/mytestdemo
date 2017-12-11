package com.example.mytestdemo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.mytestdemo.BuildConfig;

/**
 * @author Zengcq
 * @date 2016Äê12ÔÂ23ÈÕ
 * @version 1.0
 * @description
 */
public class LogUtil {
    public static boolean isWriteLogToFile = true;
    private static String TAG = "RituNavi";
    private static String PACKAGE_NAME = "com.example.mytestdemo";
    private static final int DEFAULT_BUFFER_SIZE = 20 * 1024;
    String SDPATH = Environment.getExternalStorageDirectory().getPath() + "//";
    File f = new File(SDPATH +"/Manual/test.pdf");
    private static Context context;
    
    public static void setContext(Context context){
        LogUtil.context = context;
    }
    
    public static void d(String msg){
        if(BuildConfig.DEBUG){
            d(TAG,msg);
            if(isWriteLogToFile){
                write(" loglevel D:/"+msg);
            }
        }
    }
    
    public static void i(String msg){
        if(BuildConfig.DEBUG){
            i(TAG,msg);
            if(isWriteLogToFile){
                write(" loglevel I:/"+msg);
            }
        }
    }
    
    public static void e(String msg){
        if(BuildConfig.DEBUG){
            e(TAG,msg);
            if(isWriteLogToFile){
                write(" loglevel E:/"+msg);
            }
        }
    }
    
    public static void e(String tag, String msg) 
    {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
            if(isWriteLogToFile){
                write(" loglevel E:/"+msg);
            }
        }
    }
    
    public static void d(String tag, String msg) 
    {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
            if(isWriteLogToFile){
                write(" loglevel D:/"+msg);
            }
        }
    }
    
    public static void i(String tag, String msg) 
    {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
            if(isWriteLogToFile){
                write(" loglevel I:/"+msg);
            }
        }
    }
    
    
    public static void e(Context context, String msg) 
    {
        if (BuildConfig.DEBUG) {
            String tag = context.getClass().getSimpleName().toString();
            Log.e(tag.toString(), msg);
            if(isWriteLogToFile){
                write(" loglevel E:/"+msg);
            }
        }
    }
    
    public static void d(Context context, String msg) 
    {
        if (BuildConfig.DEBUG) {
            String tag = context.getClass().getSimpleName().toString();
            Log.d(tag.toString(), msg);
            if(isWriteLogToFile){
                write(" loglevel D:/"+msg);
            }
        }
    }
    
    public static void i(Context context, String msg) 
    {
        if (BuildConfig.DEBUG) {
            String tag = context.getClass().getSimpleName().toString();
            Log.i(tag.toString(), msg);
            if(isWriteLogToFile){
                write(" loglevel I:/"+msg);
            }
        }
    }
    
    public static String getPackageName(Context context) {
        String packageName = context.getPackageName();
        return packageName;
    }
    
    /**
     * log write to file
     * @param logStr 
     */
    public static void write(final String logStr) 
    {
        if (!BuildConfig.DEBUG) {
            return ;
        }
        try {
            if (isSdcardReady()) {
                final File file = new File(getPublicDir(context), "_" + new SimpleDateFormat("yyyyMMdd").format(new Date())
                        + "_log.txt");
                new Thread(new Runnable() {
                    
                    @Override
                    public void run()
                    {
                        writeStringToFile(file, logStr, true);
                    }
                }).start();
            }
        }
        catch (Exception e) {
        }
        
    }
    
    /**
     * sdcard
     * 
     * @return
     */
    public static boolean isSdcardReady() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
    
    public static File getPublicDir(Context context) {
        return Environment.getExternalStoragePublicDirectory(getPackageName(context));
    }
    
    /**
     * @param file
     * @param content
     * @param isAppend
     * @return
     */
    public static boolean writeStringToFile(File file, String content, boolean isAppend) {
        boolean isWriteOk = false;
        char[] buffer = null;
        int count = 0;
        BufferedReader br = null;
        BufferedWriter bw = null;
        String date = new SimpleDateFormat("yy/MM/dd HH:mm").format(new Date());
        content = TAG+":"+ date+"_" +content;
        try {
            if (!file.exists()) {
                createNewFileAndParentDir(file);
            }
            if (file.exists()) {
                br = new BufferedReader(new StringReader(content));
                bw = new BufferedWriter(new FileWriter(file, isAppend));
                buffer = new char[DEFAULT_BUFFER_SIZE];
                int len = 0;
                while ((len = br.read(buffer, 0, DEFAULT_BUFFER_SIZE)) != -1) {
                    bw.write(buffer, 0, len);
                    count += len;
                }
                bw.newLine();
                bw.flush();
            }
            isWriteOk = content.length() == count;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                    bw = null;
                }
                if (br != null) {
                    br.close();
                    br = null;
                }
                buffer = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isWriteOk;
    }
    
    /**
     * @param file
     * @return
     */
    public static boolean createNewFileAndParentDir(File file) {
        boolean isCreateNewFileOk = true;
        isCreateNewFileOk = createParentDir(file);
        
        if (isCreateNewFileOk) {
            if (!file.exists()) {
                try {
                    isCreateNewFileOk = file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    isCreateNewFileOk = false;
                }
            }
        }
        return isCreateNewFileOk;
    }
    
    /**
     * @param file
     * @return
     */
    public static boolean createParentDir(File file) {
        boolean isMkdirs = true;
        if (!file.exists()) {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                isMkdirs = dir.mkdirs();
            }
        }
        return isMkdirs;
    }
}
