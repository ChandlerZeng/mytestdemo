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
		MailFactory mailFactory = new MailFactory();
		mailFactory.produce().send();
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
