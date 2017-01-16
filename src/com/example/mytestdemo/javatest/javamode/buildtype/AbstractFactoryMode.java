package com.example.mytestdemo.javatest.javamode.buildtype;

/**
 * @author Zengcq
 * @date 2016��12��13��
 * @version 1.0
 * @description ���󹤳�ģʽ
 * ��ʵ���ģʽ�ĺô����ǣ����������������һ�����ܣ�����ʱ��Ϣ����ֻ����һ��ʵ���࣬
 * ʵ��Sender�ӿڣ�ͬʱ��һ�������࣬ʵ��Provider�ӿڣ���OK�ˣ�
 * ����ȥ�Ķ��ֳɵĴ��롣����������չ�ԽϺã�
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
