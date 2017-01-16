package com.example.mytestdemo.observer;

/**
 * @author zengcq
 * @date 2016��11��23��
 * @version 1.0
 */
public interface EventSubjectInterface {
	/**
     * ע��۲���
     * @param observer
     */
    public void registerObserver(EventObserver observer);

    /**
     * ��ע��۲���
     * @param observer
     */
    public void removeObserver(EventObserver observer);

    /**
     * ֪ͨע��Ĺ۲��߽������ݻ���UI�ĸ���
     */
    public void notifyObserver(String eventType);
}
