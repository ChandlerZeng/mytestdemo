package com.example.mytestdemo.javatest;


/**
 * @author Zengcq
 * @date 2017年2月20日
 * @version 1.0
 * @description
 */

/******************** IOC控制反转和依赖注入 ***************************/
//下面是Spring的IOC实现：Bean工厂
public class IOCTest {
//    下面分别演示3中注入机制。
//    代码2 待注入的业务对象Content.java

}

class Content {
    
    public void BusniessContent(){
       System.out.println("do business");
    }
   
    public void AnotherBusniessContent(){
       System.out.println("do another business");
    }
}

//MyBusniess类展示了一个业务组件，它的实现需要对象Content的注入。
//代码3，代码4，代码5，6分别演示构造子注入（Constructor Injection），
//设值注入（Setter Injection）和接口注入（Interface Injection）三种方式。

//代码3构造子注入（Constructor Injection）MyBusiness.java
class MyBusiness {
    private Content myContent;
 
    public MyBusiness(Content content) {
       myContent = content;
    }
   
    public void doBusiness(){
       myContent.BusniessContent();
    }
   
    public void doAnotherBusiness(){
       myContent.AnotherBusniessContent();
    }
}

//代码4设值注入（Setter Injection） MyBusiness.java
class MyBusiness2 {
    private Content myContent;
 
    public void setContent(Content content) {
       myContent = content;
    }
   
    public void doBusiness(){
       myContent.BusniessContent();
    }
   
    public void doAnotherBusiness(){
       myContent.AnotherBusniessContent();
    }
}

//代码5接口注入（Interface Injection）MyBusiness.java
interface InContent {
    void createContent(Content content);
}

class MyBusiness3 implements InContent{
    private Content myContent;
 
    public void createContent(Content content) {
       myContent = content;
    }
   
    public void doBusniess(){
       myContent.BusniessContent();
    }
   
    public void doAnotherBusniess(){
       myContent.AnotherBusniessContent();
    }
}

