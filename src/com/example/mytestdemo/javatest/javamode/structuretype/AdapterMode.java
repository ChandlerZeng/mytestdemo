package com.example.mytestdemo.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016年12月14日
 * @version 1.0
 * @description 
 * 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题。
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式。
 * 类的适配器模式:核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口时Targetable，
 * 通过Adapter类，将Source的功能扩展到Targetable里
 */
public class AdapterMode extends Source implements Targetable{

	public static void main(String[] args) {
		AdapterMode target = new AdapterMode();  
        target.method1();  
        target.method2();  
        
        Source source = new Source();
        Targetable targetable = new Wrapper(source);
        targetable.method1();
        targetable.method2();
        
        Sourceable source1 = new SourceSub1();  
        Sourceable source2 = new SourceSub2();         
        source1.method1();  
        source1.method2();  
        source2.method1();  
        source2.method2(); 
	}
	@Override
	public void method2() {
		// TODO Auto-generated method stub
		System.out.println("this is the targetable method!");  
	}

}

class Source {  
	  
    public void method1() {  
        System.out.println("this is original method!");  
    }  
}  

interface Targetable {  
	  
    /* 与原类中的方法相同 */  
    public void method1();  
  
    /* 新类的方法 */  
    public void method2();  
}  

//对象的适配器模式
//基本思路和类的适配器模式相同，只是将Adapter类作修改，
//这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题。
class Wrapper implements Targetable {  
	  
    private Source source;  
      
    public Wrapper(Source source){  
        super();  
        this.source = source;  
    }  
    @Override  
    public void method2() {  
        System.out.println("this is the targetable method!");  
    }  
  
    @Override  
    public void method1() {  
        source.method1();  
    }  
}  

/*
 * 第三种适配器模式是接口的适配器模式，接口的适配器是这样的：有时我们写的一个接口中有多个抽象方法，
 * 当我们写该接口的实现类时，必须实现该接口的所有方法，这明显有时比较浪费，因为并不是所有的方法都是我们
 * 需要的，有时只需要某一些，此处为了解决这个问题，我们引入了接口的适配器模式，借助于一个抽象类，
 * 该抽象类实现了该接口， 实现了所有的方法，而我们不和原始的接口打交道，
 * 只和该抽象类取得联系，所以我们写一个类，继承该抽象类，重写我们需要的方法就行。
 */

interface Sourceable {  
    
    public void method1();  
    public void method2();  
}  

abstract class Wrapper2 implements Sourceable{  
    
    public void method1(){}  
    public void method2(){}  
}  

class SourceSub1 extends Wrapper2 {  
    public void method1(){  
        System.out.println("the sourceable interface's first Sub1!");  
    }  
}  

class SourceSub2 extends Wrapper2 {  
    public void method2(){  
        System.out.println("the sourceable interface's second Sub2!");  
    }  
}  

