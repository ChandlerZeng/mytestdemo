package com.example.mytestdemo.observer;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author zengcq
 * @date 2016��11��23��
 * @version 1.0
 */
public abstract class BaseObserverActivity extends Activity{
	
	private ActivityEventObserver mActivityEventObserver;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityEventObserver=new ActivityEventObserver(this);
        registerObserver(mActivityEventObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeObserver(mActivityEventObserver);
    }


    public void registerObserver(EventObserver observer) {
        final String[] observerEventTypes=getObserverEventType();//��ȡ������Ҫ������ҵ������
        if(observerEventTypes!=null && observerEventTypes.length>0){
            final EventSubject eventSubject=EventSubject.getInstance();
            eventSubject.registerObserver(observer);

        }

    }

    public void removeObserver(EventObserver observer) {
        final String[] observerEventTypes=getObserverEventType();//��ȡ������Ҫ������ҵ������
        if(observerEventTypes!=null && observerEventTypes.length>0){
            final EventSubject eventSubject=EventSubject.getInstance();
            eventSubject.removeObserver(observer);
        }
    }

    /**
     * �÷������ھ���Ĺ۲��߶����е��ã����Ը����¼������������¶�Ӧ��UI�����������UI�߳��б����ã�
     * �����ڸ÷����в��ܽ��к�ʱ�������������⿪�߳�
     * @param eventType �¼�����
     */
    protected abstract void onChange(String eventType);

    /**
     * ͨ��������������߾���Ĺ۲�����Ҫ������ҵ������
     * @return
     */
    protected abstract String[] getObserverEventType();

    private static class ActivityEventObserver extends EventObserver {
        //��������ã���ֹ�����ܱ�����
        private final WeakReference<BaseObserverActivity> mActivity;
        public ActivityEventObserver(BaseObserverActivity activity){
            super();
            mActivity=new WeakReference<BaseObserverActivity>(activity);
        }

        @Override
        public void onChange(String eventType) {
            BaseObserverActivity activity=mActivity.get();
            if(activity!=null){
                activity.onChange(eventType);
            }
        }
    }
}
