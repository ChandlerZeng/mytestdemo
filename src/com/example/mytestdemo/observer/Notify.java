package com.example.mytestdemo.observer;

/**
 * @author zengcq
 * @date 2016Äê11ÔÂ23ÈÕ
 * @version 1.0
 */
public class Notify {
	 private static volatile Notify mNotify;
	    private Notify(){

	    }

	    public static Notify getInstance(){
	        if(mNotify==null){
	            mNotify=new Notify();
	        }
	        return mNotify;
	    }

	    public void NotifyActivity(String eventType){
	        EventSubject eventSubject=EventSubject.getInstance();
	        EventType eventTypes=EventType.getInstance();
	        if(eventTypes.contains(eventType)){
	            eventSubject.notifyObserver(eventType);
	        }
	    }
}
