package com.example.mytestdemo.javatest;
/** 
* @author  zengcq 
* @date 创建时间：2017年11月28日 上午10:04:49 
* @version 1.0 
*/
public class SyncThread implements Runnable{
	private int num = 10;
	private long time;
	public static void main(String[] args){
		SyncThread syncThread = new SyncThread();
		new Thread(syncThread).start();
		syncThread.secondMethod();
	}
	
	private void firstMethod(){
		synchronized (this) {
			num +=100;
			System.out.println("firstMethod:"+num);
//			notify();
		}
	}
	
	private void secondMethod(){
		synchronized (this) {
			try {
//				Thread.sleep(1000);
				time = System.currentTimeMillis();
				wait(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			num *= 200;
			time = System.currentTimeMillis()-time;
			System.out.println("secondMethod:"+num+" time:"+time);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		firstMethod();
	}

}
