package com.example.mytestdemo.javatest.javamode.behaviortype;

import android.R.integer;

/**
 * @author Zengcq
 * @date 2016年12月15日
 * @version 1.0
 * @description
 * 解释器模式是我们暂时的最后一讲，一般主要应用在OOP开发中的编译器的开发中，所以适用面比较窄。
 * 
 * Calculates类是一个上下文环境类，Plus和Minus分别是用来计算的实现，代码如下：
 */
public class InterpreterMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculates plusCalculates = new Calculates(8, 8);
		Calculates minusCalculates = new Calculates(8, 8);
		int result1 = new Pluss().interpret(plusCalculates);
		int result2 = new Minuss().interpret(minusCalculates);
		System.out.println(result1);
		System.out.println(result2);
		
		
		
	}

}

interface Expression{
	public int interpret(Calculates calculates);
}

class Calculates{
	private int num1;
	private int num2;
	
	public Calculates(int number1,int number2) {
		// TODO Auto-generated constructor stub
		this.num1 = number1;
		this.num2 = number2;
	}
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
}

class Pluss implements Expression{

	
	@Override
	public int interpret(Calculates calculates) {
		// TODO Auto-generated method stub
		return calculates.getNum1()+calculates.getNum2();
	}
	
}

class Minuss implements Expression{

	@Override
	public int interpret(Calculates calculates) {
		// TODO Auto-generated method stub
		return calculates.getNum1()-calculates.getNum2();
	}
	
}

//基本就这样，解释器模式用来做各种各样的解释器，如正则表达式等的解释器等等！
