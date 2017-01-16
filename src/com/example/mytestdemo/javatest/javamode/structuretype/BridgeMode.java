package com.example.mytestdemo.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * �Ž�ģʽ���ǰ�����������ʵ�ַֿ���ʹ���ǿ��Ը��Զ����ı仯��
 * �Žӵ������ǣ���������ʵ�ֻ����ʹ�ö��߿��Զ����仯�������ǳ��õ�JDBC��DriverManagerһ����
 * JDBC�����������ݿ��ʱ���ڸ������ݿ�֮������л�����������Ҫ��̫��Ĵ��룬����˿�����ö���
 * ԭ�����JDBC�ṩͳһ�ӿڣ�ÿ�����ݿ��ṩ���Ե�ʵ�֣���һ���������ݿ������ĳ������ŽӾ����ˡ�
 */
public class BridgeMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Bridge bridge = new MyBridge();  
         
	        /*���õ�һ������*/  
	        Sourceable2 source1 = new Source2Sub1();  
	        bridge.setSource(source1);  
	        bridge.method();  
	          
	        /*���õڶ�������*/  
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
