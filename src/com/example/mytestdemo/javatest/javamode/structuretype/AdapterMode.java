package com.example.mytestdemo.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description 
 * ������ģʽ��ĳ����Ľӿ�ת���ɿͻ�����������һ���ӿڱ�ʾ��Ŀ�����������ڽӿڲ�ƥ������ɵ���ļ��������⡣
 * ��Ҫ��Ϊ���ࣺ���������ģʽ�������������ģʽ���ӿڵ�������ģʽ��
 * ���������ģʽ:����˼����ǣ���һ��Source�࣬ӵ��һ�������������䣬Ŀ��ӿ�ʱTargetable��
 * ͨ��Adapter�࣬��Source�Ĺ�����չ��Targetable��
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
	  
    /* ��ԭ���еķ�����ͬ */  
    public void method1();  
  
    /* ����ķ��� */  
    public void method2();  
}  

//�����������ģʽ
//����˼·�����������ģʽ��ͬ��ֻ�ǽ�Adapter�����޸ģ�
//��β��̳�Source�࣬���ǳ���Source���ʵ�����Դﵽ��������Ե����⡣
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
 * ������������ģʽ�ǽӿڵ�������ģʽ���ӿڵ��������������ģ���ʱ����д��һ���ӿ����ж�����󷽷���
 * ������д�ýӿڵ�ʵ����ʱ������ʵ�ָýӿڵ����з�������������ʱ�Ƚ��˷ѣ���Ϊ���������еķ�����������
 * ��Ҫ�ģ���ʱֻ��ҪĳһЩ���˴�Ϊ�˽��������⣬���������˽ӿڵ�������ģʽ��������һ�������࣬
 * �ó�����ʵ���˸ýӿڣ� ʵ�������еķ����������ǲ���ԭʼ�Ľӿڴ򽻵���
 * ֻ�͸ó�����ȡ����ϵ����������дһ���࣬�̳иó����࣬��д������Ҫ�ķ������С�
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

