package com.example.mytestdemo.service;

import com.example.mytestdemo.R;
import com.example.mytestdemo.music.MyMusicActivity;
import com.example.mytestdemo.utils.LogUtil;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Zengcq
 * @date 2016年12月23日
 * @version 1.0
 * @description
 */
public class ServiceDemo extends Service{

    
    private MyBinder myBinder = new MyBinder();
    
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Notification notification = new Notification(R.drawable.ic_launcher,  
                "有通知到来", System.currentTimeMillis());  
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent notificationIntent = new Intent(this, MyMusicActivity.class);  
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,  
                notificationIntent, 0);  
        notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容",  
                pendingIntent);  
        startForeground(1, notification); 
        LogUtil.e(this, "onCreate() executed"); 
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        LogUtil.e(this, "onDestroy() executed"); 
    }

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        super.onRebind(intent);
        LogUtil.e(this, "onRebind() executed"); 
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        LogUtil.e(this, "onStart() executed"); 
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        LogUtil.e(this, "onStartCommand() executed"); 
        return super.onStartCommand(intent, flags, startId);
        
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        LogUtil.e(this, "onUnbind() executed");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        LogUtil.e(this, "onBind() executed");
        return myBinder;
    }
    
    public class MyBinder extends Binder{
        public void startDownload(){
            LogUtil.e(ServiceDemo.this, "startDownload() executed");
        }
    }

}
