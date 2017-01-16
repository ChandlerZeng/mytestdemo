package com.example.mytestdemo.javatest.javaprinciple;

/**
 * @author Zengcq
 * @date 2016年12月7日
 * @version 1.0
 * @description 依赖倒置原则（Dependence Inversion Principle），简称DIP 定义 High level
 *              modules should depend upon low level modules. Both should depend
 *              upon abstractions. Abstractions should not depend upon details.
 *              Details should depend upon abstractions. 即
 *              1、高层模块不应该依赖低层模块，两者都应该依赖于抽象（抽象类或接口） 
 *              2、抽象（抽象类或接口）不应该依赖于细节（具体实现类）
 *              3、细节（具体实现类）应该依赖抽象 抽象：即抽象类或接口，两者是不能够实例化的。
 *              细节：即具体的实现类，实现接口或者继承抽象类所产生的类，两者可以通过关键字new直接被实例化。
 *              而依赖倒置原则的本质骑士就是通过抽象
 *              （抽象类或接口）使各个类或模块的实现彼此独立，不相互影响，实现模块间的松耦合。
 *              但是这个原则也是6个设计原则中最难以实现的了
 *              ，如果没有实现这个原则，那么也就意味着开闭原则（对扩展开发，对修改关闭）也无法实现。
 */
public class DIPPrinciple {

	public static void main(String[] args) {
		Tutus tutu = new Tutus();  
        IFood food = new Noodles();  
        IFood food2 = new Rice();  
        tutu.cook(food);  
        tutu.cook(food2);  

	}

}

//依赖倒置有三种方式来实现
//1、通过构造函数传递依赖对象
//比如在构造函数中的需要传递的参数是抽象类或接口的方式实现。
//2、通过setter方法传递依赖对象
//即在我们设置的setXXX方法中的参数为抽象类或接口，来实现传递依赖对象
//3、接口声明实现依赖对象

/*class Tutu {  
    //涂涂是个女孩，会煮面  
    public void cook(Noodles noodles)  
    {  
        noodles.eat();  
    }  
} 

class Noodles {  
    //吃面条  
    public void eat()  
    {  
        System.out.println("涂涂吃面条...");  
    }  
} */

class Tutus {  
    //涂涂是个女孩，会煮面  
    public void cook(IFood food)  
    {  
        food.eat();  
    }  
} 

interface IFood{
	void eat();
}

class Noodles implements IFood{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		 System.out.println("涂涂吃面条...");  
	}
	
}

class Rice implements IFood{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		 System.out.println("涂涂吃米饭...");  
	}
	
}

