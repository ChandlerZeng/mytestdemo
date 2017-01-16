package com.example.mytestdemo.observer;

/**
 * @author zengcq
 * @date 2016年11月23日
 * @version 1.0
 */
public interface EventSubjectInterface {
	/**
     * 注册观察者
     * @param observer
     */
    public void registerObserver(EventObserver observer);

    /**
     * 反注册观察者
     * @param observer
     */
    public void removeObserver(EventObserver observer);

    /**
     * 通知注册的观察者进行数据或者UI的更新
     */
    public void notifyObserver(String eventType);
}
