package com.example.mytestdemo.javatest;


/**
 * @author Zengcq
 * @date 2017��2��20��
 * @version 1.0
 * @description
 */

/******************** IOC���Ʒ�ת������ע�� ***************************/
//������Spring��IOCʵ�֣�Bean����
public class IOCTest {
//    ����ֱ���ʾ3��ע����ơ�
//    ����2 ��ע���ҵ�����Content.java
	public static void main(String[] args){
		MyBusiness business = new MyBusiness(new Content());
		business.doBusiness();
		business.doAnotherBusiness();
	}

}

class Content {
    
    public void BusniessContent(){
       System.out.println("do business");
    }
   
    public void AnotherBusniessContent(){
       System.out.println("do another business");
    }
}

//MyBusniess��չʾ��һ��ҵ�����������ʵ����Ҫ����Content��ע�롣
//����3������4������5��6�ֱ���ʾ������ע�루Constructor Injection����
//��ֵע�루Setter Injection���ͽӿ�ע�루Interface Injection�����ַ�ʽ��

//����3������ע�루Constructor Injection��MyBusiness.java
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

//����4��ֵע�루Setter Injection�� MyBusiness.java
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

//����5�ӿ�ע�루Interface Injection��MyBusiness.java
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

