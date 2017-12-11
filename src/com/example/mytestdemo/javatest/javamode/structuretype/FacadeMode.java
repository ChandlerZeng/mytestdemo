package com.example.mytestdemo.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * ���ģʽ��Ϊ�˽��������֮�ҵ�������ϵ�ģ���springһ����
 * ���Խ������֮��Ĺ�ϵ���õ������ļ��У������ģʽ���ǽ����ǵĹ�ϵ����һ��Facade���У�
 * ����������֮�����϶ȣ���ģʽ��û���漰���ӿڣ�������ͼ����������һ�����������������Ϊ����
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
 * �������û��Computer�࣬��ô��CPU��Memory��Disk����֮�佫���໥����ʵ����������ϵ��
 * ������������ص��������޸�һ���࣬���ܻ������������޸ģ��ⲻ��������Ҫ�����ģ�
 * ����Computer�࣬����֮��Ĺ�ϵ��������Computer������������˽�������ã��⣬�������ģʽ��
 */


