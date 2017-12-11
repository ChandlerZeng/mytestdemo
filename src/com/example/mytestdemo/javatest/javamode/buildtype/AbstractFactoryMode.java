package com.example.mytestdemo.javatest.javamode.buildtype;

/**
 * @author Zengcq
 * @date 2016年12月13日
 * @version 1.0
 * @description 抽象工厂模式
 * 其实这个模式的好处就是，如果你现在想增加一个功能：发及时信息，则只需做一个实现类，
 * 实现Sender接口，同时做一个工厂类，实现Provider接口，就OK了，
 * 无需去改动现成的代码。这样做，拓展性较好！
 */
public class AbstractFactoryMode {
	public static void main(String[] args) {
		Provider mailFactory = new MailFactory();
		mailFactory.produce().send();
		
		Provider provider = new ImageFactory();
		Sender sender = provider.produce();
		sender.send();
		
		HumanFactory humanFactory = new HumanFactory();
		Human human1 = humanFactory.getHuman("chinese");
		Human human2 = humanFactory.getHuman("america");
		human1.sayHelloWorld("迪丽热巴");
		human2.sayHelloWorld("Chandler");
	}
}

interface Sender{
	void send();
}

class SmsSender implements Sender{

	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("this is smssender");
	}
	
}

class MailSender implements Sender{

	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("this is mailsender");
	}
	
}

class VideoSender implements Sender{

	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("this is videosender");
	}
	
}

class ImageSender implements Sender{

    @Override
    public void send() {
        // TODO Auto-generated method stub
        System.out.println("this is imagesender");
    }
    
}

interface Provider{
	Sender produce();
}

class SmsFactory implements Provider{

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new SmsSender();
	}
	
}

class MailFactory implements Provider{

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new MailSender();
	}
	
}

class VideoFactory implements Provider{

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new VideoSender();
	}
	
}

class ImageFactory implements Provider{

    @Override
    public Sender produce() {
        // TODO Auto-generated method stub
        return new ImageSender();
    }
    
}

interface Human{
    void sayHelloWorld(String name);
}

class Chinese implements Human{

    @Override
    public void sayHelloWorld(String name) {
        // TODO Auto-generated method stub
        String helloWorld = "你好，" + name;
        System.out.println(helloWorld); 
    }
    
}

class America implements Human{

    @Override
    public void sayHelloWorld(String name) {
        // TODO Auto-generated method stub
        String helloWorld = "Hello，" + name;
        System.out.println(helloWorld);
    }
    
}

class HumanFactory {
     public Human getHuman(String type){
         if(type.equals("chinese")){
             return new Chinese();
         }else {
             return new America();
         }
     }
}
