package com.example.mytestdemo.javatest.javaprinciple;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zengcq
 * @date 2016年12月7日
 * @version 1.0
 * @description 里氏置换原则 里氏置换原则（Liskov Substitution Principle），简称LSP 
 * 定义： Functions that use pointers or references to base classes must be able to use objects 
 * of derived classes without knowing it.
 * 所有引用基类的地方必须能够透明的使用其子类对象。
 * 也就是说，只要父类出现的地方子类就能够出现，而且替换为子类不会产生任何错误或异常。
 * 但是反过来，子类出现的地方，替换为父类就可能出现问题了。
 */
public class LiskSubPrinciple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tutu tutu = new Tutu();
		tutu.setViewPoint(new Lijiang());
		tutu.setViewPoint(new Zhangjiajie());
		tutu.travelTo();
		invoke();
	}
	public static void invoke()  
    {  
        //父类存在的地方，子类就应该能够存在  
        Father f = new Father();  
        Son s = new Son();  
        HashMap map = new HashMap();  
        f.say(map);  
        s.say(map);  
    }  

}

//一、子类必须完全实现父类的方法
abstract class ViewPoint {  
    //去丽江旅游  
    public abstract void where();  
} 
//下面两个类是实现这个抽象类
class Lijiang extends ViewPoint {  
	   
    @Override 
    public void where() {  
        System.out.println("欢迎来到丽江...");  
    }  
   
}  

class Zhangjiajie extends ViewPoint {  
	   
    @Override 
    public void where() {  
        System.out.println("欢迎来到张家界...");  
    }  
   
}  

//人物是涂涂，在里面设置类类型来传递参数。此时涂涂要去的旅游景点还是抽象的
class Tutu {  
    //定义要旅游的景点  
    private ViewPoint viewpoint;  
    //涂涂要去的景点  
    public void setViewPoint(ViewPoint viewpoint)  
    {  
        this.viewpoint = viewpoint;  
    }  
       
    public void travelTo()  
    {  
        System.out.println("涂涂要去旅游了");  
        viewpoint.where();  
    }  
} 

//二、子类可以有自己的特性
//也就是说在类的子类上，可以定义其他的方法或属性
//三、覆盖或者实现父类的方法时输入参数可以被放大
//父类能够存在的地方，子类就能存在，并且不会对运行结果有变动。反之则不行。
//父类，say()里面的参数是HashMap类型，是Map类型的子类型。（因为子类的范围应该比父类大）

class Father {  
    public Collection say(HashMap map)  
    {  
        System.out.println("父类被执行...");  
        return map.values();  
    }  
} 

//子类，say()里面的参数变成了Map类型，Map范围比HashMap类型大，符合LSP原则。
//注意这里的say不是覆写父类的say，因为参数类型不同。而是重载。

class Son extends Father {  
    //方法输入参数类型  
    public Collection say(Map map)  
    {  
        System.out.println("子类被执行...");  
        return map.values();  
    }  
} 

//四、覆写或者实现父类的方法时输出结果可以被缩小
//其实与上面的类似，也就是父类能出现的地方子类就可以出现，而且替换为子类不会产生任何错误或者异常，
//使用者也无需知道是父类还是子类。但是反过来就不行了，有子类出现的地方，父类未必就适应。（毕竟子类的范围要>=父类的范围）




