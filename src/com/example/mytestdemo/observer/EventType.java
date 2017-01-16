package com.example.mytestdemo.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zengcq
 * @date 2016Äê11ÔÂ23ÈÕ
 * @version 1.0
 */
public class EventType {
	private static volatile EventType mEventType;
    private final static Set<String> eventsTypes = new HashSet<String>();

    public final static String UPDATE_MAIN="com.updateMain";
    public final static String UPDATE_Text="com.updateText";
    
    private EventType(){
        eventsTypes.add(UPDATE_MAIN);
        eventsTypes.add(UPDATE_Text);
    }

    public static EventType getInstance(){
       if(mEventType==null){
           mEventType=new EventType();
       }
        return mEventType;
    }

    public boolean contains(String eventType){
        return eventsTypes.contains(eventType);
    }
}
