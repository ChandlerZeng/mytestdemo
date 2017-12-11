package com.example.mytestdemo.javatest.javamode.buildtype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zengcq
 * @date 2016年12月13日
 * @version 1.0
 * @description
 * 工厂类模式提供的是创建单个类的模式，而建造者模式则是将各种产品集中起来进行管理，
 * 用来创建复合对象，所谓复合对象就是指某个类具有不同的属性，
 * 其实建造者模式就是前面抽象工厂模式和最后的Test结合起来得到的。我们看一下代码：
 */
public class BuilderMode {
	
	private List<Sender> list = new ArrayList<Sender>();  
	public static void main(String[] args) {  
		BuilderMode builderMode = new BuilderMode();
		builderMode.produceMailSender(10);  
    }  
    public void produceMailSender(int count){  
        for(int i=0; i<count; i++){  
            list.add(new MailSender());  
        }  
    }  
      
    public void produceSmsSender(int count){  
        for(int i=0; i<count; i++){  
            list.add(new SmsSender());  
        }  
    }  
}
