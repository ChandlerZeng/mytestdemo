package com.example.mytestdemo.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016年12月14日
 * @version 1.0
 * @description
 * 桥接模式就是把事物和其具体实现分开，使他们可以各自独立的变化。
 * 桥接的用意是：将抽象化与实现化解耦，使得二者可以独立变化，像我们常用的JDBC桥DriverManager一样，
 * JDBC进行连接数据库的时候，在各个数据库之间进行切换，基本不需要动太多的代码，甚至丝毫不用动，
 * 原因就是JDBC提供统一接口，每个数据库提供各自的实现，用一个叫做数据库驱动的程序来桥接就行了。
 */
public class BridgeMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Bridge bridge = new MyBridge();  
         
	        /*调用第一个对象*/  
	        Sourceable2 source1 = new Source2Sub1();  
	        bridge.setSource(source1);  
	        bridge.method();  
	          
	        /*调用第二个对象*/  
	        Sourceable2 source2 = new Source2Sub2();  
	        bridge.setSource(source2);  
	        bridge.method(); 
	}

}

class Source2Sub1 implements Sourceable2{

	@Override
	public void method() {
		// TODO Auto-generated method stub
		System.out.println("this is sub1");
	}
	
}

class Source2Sub2 implements Sourceable2{

	@Override
	public void method() {
		// TODO Auto-generated method stub
		System.out.println("this is sub2");
	}
	
}

abstract class Bridge {  
    private Sourceable2 source;  
  
    public void method(){  
        source.method();  
    }  
      
    public Sourceable2 getSource() {  
        return source;  
    }  
  
    public void setSource(Sourceable2 source) {  
        this.source = source;  
    }  
}

class MyBridge extends Bridge {  
    public void method(){  
        getSource().method();  
    }  
}  
