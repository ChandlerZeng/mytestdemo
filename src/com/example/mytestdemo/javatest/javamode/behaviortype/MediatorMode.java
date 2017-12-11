package com.example.mytestdemo.javatest.javamode.behaviortype;

import com.txznet.sdk.TXZConfigManager.UserConfigListener;

/**
 * @author Zengcq
 * @date 2016年12月15日
 * @version 1.0
 * @description
 * 中介者模式也是用来降低类类之间的耦合的，因为如果类类之间有依赖关系的话，
 * 不利于功能的拓展和维护，因为只要修改一个对象，其它关联的对象都得进行修改。
 * 如果使用中介者模式，只需关心和Mediator类的关系，具体类类之间的关系及调度交给Mediator就行，
 * 这有点像spring容器的作用。
 * 
 * User类统一接口，User1和User2分别是不同的对象，二者之间有关联，如果不采用中介者模式，
 * 则需要二者相互持有引用，这样二者的耦合度很高，为了解耦，引入了Mediator类，提供统一接口，MyMediator为其实现类，
 * 里面持有User1和User2的实例，用来实现对User1和User2的控制。
 * 这样User1和User2两个对象相互独立，他们只需要保持好和Mediator之间的关系就行，剩下的全由MyMediator类来维护！
 */
public class MediatorMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mediator mediator = new MyMeditor();
		mediator.createMediator();
		mediator.workAll();
	}

}

interface Mediator {  
    public void createMediator();  
    public void workAll();  
}  

class MyMeditor implements Mediator{

	private User user1;
	private User user2;
	
	@Override
	public void createMediator() {
		// TODO Auto-generated method stub
//		user1 = new User1(this);
//		user2 = new User2(this);
		user1 = new User1();
		user2 = new User2();
	}

	@Override
	public void workAll() {
		// TODO Auto-generated method stub
		user1.work();
		user2.work();
	}
}

abstract class User{
	private Mediator meditor;

	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Mediator meditor) {
		// TODO Auto-generated constructor stub
		this.meditor = meditor;
	}
	public Mediator getMeditor() {
		return meditor;
	}

	public void setMeditor(Mediator meditor) {
		this.meditor = meditor;
	}
	
	abstract void work();
}

class User1 extends User{

	public User1(){
		
	}
	public User1(Mediator mediator) {
		// TODO Auto-generated constructor stub
		super(mediator);
	}
	@Override
	void work() {
		// TODO Auto-generated method stub
		System.out.println("user1 executed");
	}
	
}

class User2 extends User{

	public User2() {
		// TODO Auto-generated constructor stub
	}
	public User2(Mediator mediator) {
		// TODO Auto-generated constructor stub
		super(mediator);
	}
	@Override
	void work() {
		// TODO Auto-generated method stub
		System.out.println("user2 executed");
	}
	
}
