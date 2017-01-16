package com.example.mytestdemo.javatest.javaprinciple;

/**
 * @author Zengcq
 * @date 2016年12月7日
 * @version 1.0
 * @description 设计模式6大原则：接口隔离原则 接口隔离原则要求的是在一个模块应该只依赖它需要的接口，以保证接口的小纯洁。
 *              而且需要保证接口应该尽量小，即设计接口的时候应该让接口尽量细化，
 *              不要定义太臃肿的接口（比如接口中有很多不相干的逻辑的方法声明）。 首先看看接口隔离原则的定义，有两种定义 
 *              第一种：Clients should not be forced to depend upon interfaces that they don't
 *              use.(客户端不应该强行以来它不需要的接口) 第二种：The dependency of one class to
 *              another one should depend on the smallest possible
 *              interface.（类间的依赖关系应该建立在最小的接口上）
 *              而这里的接口，却不仅仅指的是通过interface关键字定义的接口，接口分为2种： 
 *              1、对象接口（Object Interface） Java中声明的一个类，通过new关键字产生的一个实例，
 *              它是对一个类型的事物的描述，这也是一种接口。例如： Phone phone = new
 *              Phone();这里的类Person就是实例phone的一个接口 2、类接口（Class Interface）
 *              这种接口就是通过interface关键字定义的接口了
 * 
 *              百度知道比较合理的解释：
 *              接口隔离原则表明客户端不应该被强迫实现一些他们不会使用的接口，应该把胖接口中的方法分组，
 *              然后用多个接口代替它
 *              ，每个接口服务于一个子模块。 接口隔离原则 不应该强迫客户端依赖于他们不会使用的接口。 实例
 *              下面是一个违反了接口隔离原则的例子。
 *              我们使用Manager类代表一个管理工人的管理者。有两种类型的工人：普通的和高效的，这两种工人都需要吃午饭
 *              。现在来了一批机器人，它们同样为公司工作
 *              ，但是他们不需要吃午饭。一方面Robot类需要实现IWoker接口，因为他们要工作，另一方面
 *              ，它们又不需要实现IWorker接口，因为它们不需要吃饭。 在这种情况下IWorker就被认为是一个被污染了的接口。
 *              如果我们保持现在的设计
 *              ，那么Robot类将被迫实现eat()方法，我们可以写一个哑类它什么也不做（比如说它只用一秒钟的时间吃午饭）
 *              ，但是这会对程序造成不可预料的结果（例如管理者看到的报表中显示被带走的午餐多于实际的人数）。
 */
public class InterfaceSegregationPrinciple {

	public static void main(String[] args) {
		 IPrettyGirl jiajia = new PrettyGirl("杨霜");  
	        AMan man = new Man(jiajia);  
	        man.findGirl();  
	        IGreatBody canlaoshi = new BeautifulGirl("苍老师");
	        AMan man2 = new Man(canlaoshi); 
	        man2.findGirl2();
	}

}

// 在使用接口隔离原则的时候需要有一些规范：
// 1.接口尽量小
// 接口尽量小主要是为了保证一个接口只服务一个子模块或者业务逻辑
// 2.接口高内聚
// 接口高内聚是对内高度依赖，对外尽可能隔离。即一个接口内部的声明的方法相互之间都与某一个子模块相关，且是这个子模块必须的。
// 3.接口设计是有限度的
// 但是如果完全遵循接口隔离原则的话，会出现一个问题。即接口的设计力度会越来越小，这样就造成了接口数量剧增，
//系统复杂度一下子增加了，而这不是真实项目所需要的，所以在使用这个原则的时候还要在特定的项目，根据经验或者尝试判断，
//不过没有一个固定的标准。

//定义美女接口  
interface IPrettyGirl {  
    //长相好  
    public void greatLooks();  
    //好身材  
    public void greatFigure();  
    //气质佳  
    public void greatTemperament();  
} 

//实现美女类
class PrettyGirl implements IPrettyGirl {  
    private String name;  
    //构造函数，美女名字  
    public PrettyGirl(String name)  
    {  
        this.name = name;  
    }  
    //好身材  
    @Override 
    public void greatFigure() {  
        System.out.println(name+":身材非常好");  
    }  
    //好长相  
    @Override 
    public void greatLooks() {  
        System.out.println(name+":长相非常好");  
    }  
    //好气质  
    @Override 
    public void greatTemperament() {  
        System.out.println(name+":气质非常好");  
    }  
   
} 

abstract class AMan {  
    protected IPrettyGirl prettyGirl;  
    protected IGreatBody iGreatBody;  
    public AMan(IPrettyGirl prettyGirl)  
    {  
        this.prettyGirl = prettyGirl;  
    }  
    
    public AMan(IGreatBody iGreatBody)  
    {  
        this.iGreatBody = iGreatBody;  
    }  
       
    //帅哥开始找美女啦  
    public abstract void findGirl();  
    public abstract void findGirl2();  
} 

class Man extends AMan {  
	   
    public Man(IPrettyGirl prettyGirl) {  
        super(prettyGirl);  
    }  
    public Man(IGreatBody iGreatBody) {  
        super(iGreatBody);  
    }  
   
    @Override 
    public void findGirl() {  
        System.out.println("美女在这里：----------------------");  
        super.prettyGirl.greatLooks();  
        super.prettyGirl.greatFigure();  
        super.prettyGirl.greatTemperament();  
           
    }
	@Override
	public void findGirl2() {
		// TODO Auto-generated method stub
		super.iGreatBody.greatFigure();
		super.iGreatBody.greatLooks();
	}  
   
} 


//接口隔离
interface IGreatBody {  
    //好长相  
    public void greatLooks();  
    //身材  
    public void greatFigure();  
} 

interface IGreatTemperament {  
    //气质好  
    public void greatTemperament();  
} 

class BeautifulGirl implements IGreatBody{

	String nameString;
	
	public BeautifulGirl(String name){
		this.nameString = name;
	}
	@Override
	public void greatLooks() {
		// TODO Auto-generated method stub
		 System.out.println(nameString+":长相非常好");  
	}

	@Override
	public void greatFigure() {
		// TODO Auto-generated method stub
		 System.out.println(nameString+":身材非常好");  
	}
	
}



