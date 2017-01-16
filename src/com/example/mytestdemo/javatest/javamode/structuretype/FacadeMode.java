package com.example.mytestdemo.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016年12月14日
 * @version 1.0
 * @description
 * 外观模式是为了解决类与类之家的依赖关系的，像spring一样，
 * 可以将类和类之间的关系配置到配置文件中，而外观模式就是将他们的关系放在一个Facade类中，
 * 降低了类类之间的耦合度，该模式中没有涉及到接口，看下类图：（我们以一个计算机的启动过程为例）
 */
public class FacadeMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Programer computerProgramer = new Computer();
		computerProgramer.startUp();
		computerProgramer.shutDown();
	}

}

interface Programer{
	void startUp();
	void shutDown();
}

class Cpu implements Programer{

	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		System.out.println("cpu startup!");  
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		System.out.println("cpu shutDown!");  
	}
	
}

class Disk implements Programer{

	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		System.out.println("Disk startup!");
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		System.out.println("Disk shutDown!");
	}
	
}

class Memory implements Programer{

	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		System.out.println("Memory startup!");
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		System.out.println("Memory shutDown!");
	}
	
}

class Computer implements Programer{

	private Cpu cpu;
	private Disk disk;
	private Memory memory;
	
	public Computer(){
		cpu = new Cpu();
		disk = new Disk();
		memory = new Memory();
	}
	
	@Override
	public void startUp() {
		// TODO Auto-generated method stub
		System.out.println("Computer startUp!");
		cpu.startUp();
		disk.startUp();
		memory.startUp();
		System.out.println("Computer startUp finished!");
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		System.out.println("Computer shutDown!");
		cpu.shutDown();
		disk.shutDown();
		memory.shutDown();
		System.out.println("Computer shutDown finished!");
	}
	
}

/*
 * 如果我们没有Computer类，那么，CPU、Memory、Disk他们之间将会相互持有实例，产生关系，
 * 这样会造成严重的依赖，修改一个类，可能会带来其他类的修改，这不是我们想要看到的，
 * 有了Computer类，他们之间的关系被放在了Computer类里，这样就起到了解耦的作用，这，就是外观模式！
 */


