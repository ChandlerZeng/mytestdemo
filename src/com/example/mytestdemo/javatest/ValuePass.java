package com.example.mytestdemo.javatest;
/** 
* @author  zengcq 
* @date 创建时间：2017年8月2日 上午9:20:39 
* @version 1.0 
*/
public class ValuePass {
	public static void changeValue(int num) {  
		num = num *2;  
		}  

		//调用该函数  
	public static void main(String args[]){
		int num = 5;  
		System.out.println(num);  
		changeValue(num);  
		System.out.println(num); 
		
		A a = new A();  
		a.age = 10;  
		test1(a);  
		System.out.println("main方法中的age="+a.age);  
		
		Example ex = new Example(); 
		Person person = new Person();
		person.age = 30;
//		ex.change(ex.str, ex.ch, ex.a, person);  
		ex.change2(ex, person);
		System.out.print(ex.str + " and ");  
		System.out.print(ex.ch);  
		System.out.print(" and "+ex.a);  
		System.out.print(" and "+person.age);  
	}
	
	public static void test1(A a){  
		a.age = 20;  
		System.out.println("test1方法中的age="+a.age);  
		}  
  
	static class A{  
		public int age = 0;  
		} 
	
	public static class Example {  
		String str = new String("good");  
		char[] ch = { 'a', 'b', 'c' };  
		int a = 3;
		
 
		public void change(String str, char ch[], int a, Person person) {  
		str = "test ok";  
//		ch[0] = 'g';  
		ch = new char[]{ 'b', 'b', 'c' };
		a = 4;
		person = new Person();
		person.age = 20;
		}
		
		public void change2(Example e, Person person) {  
			e.str = "test ok";  
			e.ch = new char[]{ 'b', 'b', 'c' };
			e.a = 4;
			person.age = 20;
			}
		
		}
	static class Person{
		public int age = 10;
	}

}
