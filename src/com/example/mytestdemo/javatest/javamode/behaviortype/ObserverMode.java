package com.example.mytestdemo.javatest.javamode.behaviortype;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author Zengcq
 * @date 2016年12月14日
 * @version 1.0
 * @description
 * 包括这个模式在内的接下来的四个模式，都是类和类之间的关系，不涉及到继承，学的时候应该 记得归纳，
 * 记得本文最开始的那个图。观察者模式很好理解，类似于邮件订阅和RSS订阅，当我们浏览一些博客或wiki时，
 * 经常会看到RSS图标，就这的意思是，当你订阅了该文章，如果后续有更新，会及时通知你。其实，
 * 简单来讲就一句话：当一个对象变化时，其它依赖该对象的对象都会收到通知，并且随着变化！对象之间是一种一对多的关系。
 */
public class ObserverMode {
    public static void main(String[] args){
    	Subject subject = new MySubject();
    	subject.add(new Observer1());
    	subject.add(new Observer2());
    	subject.add(new Observer3());
    	subject.operations();
    }
}

/*我解释下这些类的作用：MySubject类就是我们的主对象，Observer1和Observer2是依赖于MySubject的对象，
当MySubject变化时，Observer1和Observer2必然变化。AbstractSubject类中定义着需要监控的对象列表，
可以对其进行修改：增加或删除被监控对象，且当MySubject变化时，负责通知在列表内存在的对象。*/

interface Observer{
	void update();
}

class Observer3 implements Observer{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Observer3 has received update!");
	}
	
}

class Observer1 implements Observer{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Observer1 has received update!");
	}
	
}

class Observer2 implements Observer{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Observer2 has received update!");
	}
	
}

interface Subject{
	//增加观察者
	void add(Observer observer);
	//删除观察者
	void delete(Observer observer);
	//通知观察者
	void notifyObservers();
	//自身操作
	void operations();
}

abstract class AbstractSubject implements Subject{
	private Vector<Observer> vector = new Vector<Observer>();

	@Override
	public void add(Observer observer) {
		// TODO Auto-generated method stub
		vector.add(observer);
	}

	@Override
	public void delete(Observer observer) {
		// TODO Auto-generated method stub
		vector.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		Enumeration<Observer> enumo = vector.elements();
		while(enumo.hasMoreElements()){
			enumo.nextElement().update();
		}
	}
		
	
}

class MySubject extends AbstractSubject{

	@Override
	public void operations() {
		// TODO Auto-generated method stub
		System.out.println("update self!");  
        notifyObservers();  

	}
	
}