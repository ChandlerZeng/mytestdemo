package com.example.mytestdemo.observer;

import android.os.Handler;
import android.os.Looper;

/**
 * @author zengcq
 * @date 2016Äê11ÔÂ23ÈÕ
 * @version 1.0
 */
public abstract class EventObserver implements EventObserverInterface {
	private Handler mHandler;

    public EventObserver(){
        mHandler=new Handler(Looper.getMainLooper());
    }


    public abstract void onChange(String eventType);

    @Override
    public void dispatchChange(String eventType){
        mHandler.post(new NotificationRunnable(eventType));
    }

    private final class NotificationRunnable implements Runnable{
        private String mEventType;
        public NotificationRunnable(String eventType){
            this.mEventType=eventType;
        }
        @Override
        public void run() {
            EventObserver.this.onChange(mEventType);
        }
    }
}
