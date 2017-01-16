package com.example.mytestdemo.observer;

/**
 * @author zengcq
 * @date 2016年11月23日
 * @version 1.0
 */
public interface EventObserverInterface {
	/**
     * 根据事件进行数据或者UI的更新
     * @param eventType
     */
    public void dispatchChange(String eventType);
}
