package com.example.mytestdemo.observer;

/**
 * @author zengcq
 * @date 2016��11��23��
 * @version 1.0
 */
public interface EventObserverInterface {
	/**
     * �����¼��������ݻ���UI�ĸ���
     * @param eventType
     */
    public void dispatchChange(String eventType);
}
