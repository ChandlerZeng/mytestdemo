package com.example.mytestdemo.javatest.javaprinciple;

/**
 * @author Zengcq
 * @date 2016年12月7日
 * @version 1.0
 * @description 设计模式6大原则：单一职责原则
 *              有时候，开发人员设计接口的时候会有些问题，比如用户的属性和用户的行为被放在一个接口中声明。
 *              这就造成了业务对象和业务逻辑被放在了一起
 *              ，这样就造成了这个接口有两种职责，接口职责不明确，按照SRP的定义就违背了接口的单一职责原则了。 
 *              单一职责原则（Single
 *              Responsibility Principle），简称SRP。 定义： There should never be more
 *              than one reason for a class to change. 应该有且仅有一个原因引起类的变更。
 */
public class SingleRespPrinciple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ItutuBO tutuBo = new TutuBO();
		ItutuBL tutuBl = new TutuBL();
		tutuBo.setShengao(1.7);
		tutuBo.setTizhong(60);
		tutuBo.toString();
		tutuBl.chiFan(true);
		tutuBl.shangWang(true);
	}

}

interface Itutu {  
    //身高  
    void setShengao(double height);  
    double getShengao();  
    //体重  
    void setTizhong(double weight);  
    double getTizhong();  
    //吃饭  
    boolean chiFan(boolean hungry);  
    //上网  
    boolean shangWang(boolean silly);  
} 

/*上面的例子就存在这个问题，身高、体重属于业务对象，与之相应的方法主要负责用户的属性。
 * 而吃饭、上网是相应的业务逻辑，主要负责用户的行为。但是这就会给人一种不知道这个接口到底是做什么的感觉，
 * 职责不清晰，后期维护的时候也会造成各种各样的问题。
解决办法：单一职责原则，将这个接口分解成两个职责不同的接口即可
ItutuBO.java：负责tutu（涂涂，假如是个人名）的属性*/

interface ItutuBO {  
    //身高  
    void setShengao(double height);  
    double getShengao();  
    //体重  
    void setTizhong(double weight);  
    double getTizhong();  
} 

interface ItutuBL {  
    //吃饭  
    boolean chiFan(boolean hungry);  
    //上网  
    boolean shangWang(boolean silly);  
} 

class TutuBO implements ItutuBO {  
    private double height;  
    private double weight;  
    @Override 
    public double getShengao() {         
        return height;  
    }  
   
    @Override 
    public double getTizhong() {  
        return weight;  
    }  
   
    @Override 
    public void setShengao(double height) {  
        this.height = height;  
    }  
   
    @Override 
    public void setTizhong(double weight) {  
        this.weight = weight;  
    }  
    
    public String toString(){
    	System.out.println("涂涂身高："+height+"米，"+"涂涂体重："+weight+"kg。");
    	return "涂涂身高："+height+"米，"+"涂涂体重："+weight+"kg。";
    }
   
} 

class TutuBL implements ItutuBL {  
	   
    @Override 
    public boolean chiFan(boolean hungry) {  
        if(hungry)  
        {  
            System.out.println("去吃火锅...");  
            return true;  
        }  
        return false;  
    }  
   
    @Override 
    public boolean shangWang(boolean silly) {  
        if(silly)  
        {  
            System.out.println("好无聊啊，上会网...");  
            return true;  
        }  
        return false;  
    }  
   
} 

//这样就清晰了，当需要修改用户属性的时候只需要对ItutuBO这个接口来修改，只会影响到TutuBO这个类，不会影响其他类。
//那么单一职责原则的意义何在呢？
//降低类的复杂性，实现什么样的职责都有清晰的定义
//提高可读性
//提高可维护性
//降低变更引起的风险，对系统扩展性和维护性很有帮助
//但是、使用单一职责原则有一个问题，“职责”没有一个明确的划分标准，
//如果把职责划分的太细的话会导致接口和实现类的数量剧增，反而提高了复杂度，降低了代码的可维护性。
//所以使用这个职责的时候还要具体情况具体分析。建议就是接口一定要采用单一职责原则，
//实现类的设计上尽可能做到单一职责原则，最好是一个原因引起一个类的变化。

