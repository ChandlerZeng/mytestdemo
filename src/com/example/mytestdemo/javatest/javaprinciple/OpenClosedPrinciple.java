package com.example.mytestdemo.javatest.javaprinciple;

import java.util.ArrayList;

/**
 * @author Zengcq
 * @date 2016年12月7日
 * @version 1.0
 * @description
 * 开闭原则（Open Closed Principle）
开闭原则的核心是：对扩展开放，对修改关闭。
白话意思就是我们改变一个软件时（比如扩展其他功能），应该通过扩展的方式来达到软件的改变，
而不应爱修改原有代码来实现变化。
开闭原则算是前5中原则的一个抽象总结，前五种是开闭原则的一些具体实现，所以如果使用开闭原则，
其实有点虚，因为它没有一个固定的模式，但是最终保证的是提高程序的复用性、可维护性等要求。
要使用这一原则还需要结合着它的思想“对扩展开放，对修改关闭”与其他的五大设计原则根据经验来开发项目。
大体是这个意思，如果想深究，还需要看看其他的书籍。
下面是使用开闭原则的一个简单示例，虽有些不准确，但是是这个意思（领会精神）。
定义一个接口，寻找美女
 */
public class OpenClosedPrinciple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Street street = new Street();
		System.out.println("----------美女在这里----------");  
        for(IForeigner girl:street.girls)  
        {  
            System.out.println("姓名:"+girl.getName()+" 年龄:"+girl.getAge()+" 国籍："+girl.getCountry()+
                    "  长相:"+girl.getFace()+"  身材:"+girl.getFigure());  
        }  
	}

}

interface IFindGirl {  
    //年龄  
    public int getAge();  
    //姓名  
    public String getName();  
    //长相  
    public String getFace();  
    //身材  
    public String getFigure();  
} 

class FindGirl implements IFindGirl {  
    private String name;  
    private int age;  
    private String face;  
    private String figure;  
       
    public FindGirl(String name, int age, String face, String figure)  
    {  
        this.name = name;  
        this.age = age;  
        this.face = face;  
        this.figure = figure;  
    }  
   
    @Override 
    public int getAge() {  
        return age;  
    }  
   
    @Override 
    public String getFace() {  
        return face;  
    }  
   
    @Override 
    public String getFigure() {  
        return figure;  
    }  
   
    @Override 
    public String getName() {  
        return name;  
    }  
       
   
} 

class Street {  
//     final static ArrayList<IFindGirl> girls = new ArrayList<IFindGirl>();  
	final static ArrayList<IForeigner> girls = new ArrayList<IForeigner>();  
    //静态初始化块  
    static 
    {  
//        girls.add(new FindGirl("张含韵",23,"可爱型","165cm/47kg"));  
//        girls.add(new FindGirl("高圆圆",33,"时尚型","165cm/48kg"));  
//        girls.add(new FindGirl("章泽天",19,"清纯型","168cm/47kg"));  
    	girls.add(new ForeignerGirl("张含韵",23,"德国","可爱型","165cm/47kg"));  
        girls.add(new ForeignerGirl("高圆圆",33,"中国","时尚型","165cm/48kg"));  
        girls.add(new ForeignerGirl("章泽天",19,"中国","清纯型","168cm/47kg"));  
        girls.add(new ForeignerGirl("Avirl",28,"美国","性感型","160cm/45kg"));
    }  
}

//但是如果想独立分出一个外国美女的类别的话（比如增加一个国籍），可以通过修改接口、修改实现类、通过扩展来实现。
//如果修改接口，也就意味着修改实现类，这样对项目的变动太大了，所以不推荐
//如果修改实现类，这样虽能解决问题，但是明显有些牵强，如果需要其他变动的时候会显得逻辑混乱
//所以，通过扩展来实现是最简单的方式
//如何扩展：
//可以定义一个IForeigner接口继承自IFindGirl，在IForeigner接口中添加国籍属性getCountry()，
//然后实现这个接口即可，然后就只需要在场景类中做稍微修改就可以了

interface IForeigner extends IFindGirl {  
    //国籍  
    public String getCountry();  
} 

class ForeignerGirl implements IForeigner {  
    private String name;  
    private int age;  
    private String country;  
    private String face;  
    private String figure;  
       
    public ForeignerGirl(String name, int age, String country, String face, String figure)  
    {  
        this.name = name;  
        this.age = age;  
        this.country = country;  
        this.face =face;  
        this.figure = figure;  
    }  
    @Override 
    public String getCountry() {  
        // TODO Auto-generated method stub  
        return country;  
    }  
   
    @Override 
    public int getAge() {  
        // TODO Auto-generated method stub  
        return age;  
    }  
   
    @Override 
    public String getFace() {  
        // TODO Auto-generated method stub  
        return face;  
    }  
   
    @Override 
    public String getFigure() {  
        // TODO Auto-generated method stub  
        return figure;  
    }  
   
    @Override 
    public String getName() {  
        // TODO Auto-generated method stub  
        return name;  
    }  
   
} 