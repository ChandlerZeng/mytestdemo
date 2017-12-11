package com.example.mytestdemo.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016年12月14日
 * @version 1.0
 * @description
 * 顾名思义，装饰模式就是给一个对象增加一些新的功能，而且是动态的，
 * 要求装饰对象和被装饰对象实现同一个接口，装饰对象持有被装饰对象的实例
 */
public class DecoratorMode implements Sourceable2{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Sourceable2 source = new DecoratorMode();  
	        Sourceable2 obj = new Decorator(source);  
	        obj.method();  
	}

	@Override
	public void method() {
		// TODO Auto-generated method stub
		  System.out.println("the original method!");  
	}

}

interface Sourceable2 {  
    public void method();  
}  

class Decorator implements Sourceable2 {  
	  
    private Sourceable2 source;  
      
    public Decorator(Sourceable2 source){  
        super();  
        this.source = source;  
    }  
    @Override  
    public void method() {  
        System.out.println("before decorator!");  
        source.method();  
        System.out.println("after decorator!");  
    }  
}  