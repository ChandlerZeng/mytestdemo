package com.example.mytestdemo.javatest;

/**
 * @author zengcq
 * @date 创建时间：2017年8月17日 上午9:24:51
 * @version 1.0
 */
public class SyncTest {
	private static SyncTest instace;
	private static int count = 0;

	public static void main(String[] args) {
		SyncThread2 syncThread1 = new SyncThread2();
		SyncThread2 syncThread2 = new SyncThread2();
		SyncThread3.start();
		Thread thread1 = new Thread(syncThread1, "SyncThread1");
		Thread thread2 = new Thread(syncThread1, "SyncThread2");
		thread1.start();
		thread2.start();
		
//		SyncThread syncThread3 =  getInstace().new SyncThread();
//		SyncThread syncThread4 = getInstace().new SyncThread();
//		Thread thread3 = new Thread(syncThread3, "SyncThread3");
//		Thread thread4 = new Thread(syncThread4, "SyncThread4");
//		thread3.start();
//		thread4.start();
	}
	
	static class SyncThread3 implements Runnable{
		 private int ticket=10;  
		    public SyncThread3(){       
		    }  
		    @Override  
		    public void run() {  
		        for(int i=0;i<20;i++){  
		        	synchronized (this) {
		        		if(this.ticket>0){  
		                    //休眠1s秒中，为了使效果更明显，否则可能出不了效果  
		                    try {  
//		                        Thread.sleep(0);		                    	
		                    } catch (Exception e) {  
		                        e.printStackTrace();  
		                    }  
		                    System.out.println(Thread.currentThread().getName()+"号窗口卖出："+this.ticket--+"号票");  
		                }  
					}	        	            
		        }  
		    }  
		      
		     public static void start(){  
		    	 SyncThread3 demo=new SyncThread3();  
		         //基于火车票创建三个窗口  
		         new Thread(demo,"a").start();  
		         new Thread(demo,"b").start();  
		         new Thread(demo,"c").start();  
		     }  
	}
	
	static class SyncThread2 implements Runnable {
		private static int count;
		private Byte[] byte1 = new Byte[0];

		public SyncThread2() {
			count = 0;
		}

		public void run() {
//			method1();
			// method2();
			method4();
			method3();
		}

		public void method3() {
			synchronized (byte1) {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + "method3:" + " count:" + count++);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}

		public void method4() {
			synchronized (this) {
				for (int i = 0; i < 5; i++) {
					System.out.println(Thread.currentThread().getName() + ":" + "method4:" + " count:" + count++);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
				}
			}
			
		}
	}

	public static SyncTest getInstace() {
		for (int i = 0; i < 5; i++) {
			boolean instanceNull = instace == null;
			System.out.println(
					Thread.currentThread().getName() + ":" + "instance==null:" + instanceNull + " count:" + count++);
		}
		if (instace == null) {
			synchronized (SyncTest.class) {
				if (instace == null) {
					instace = new SyncTest();
				}
			}
		}
		return instace;
	}

	public synchronized void method1() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + "method1:" + " count:" + count++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized static void method2() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + "method2:" + " count:" + count++);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 同步线程
	 */
	class SyncThread implements Runnable {
		private int count;
		private Byte[] byte1 = new Byte[0];

		public SyncThread() {
			count = 0;
		}

		public void run() {
			synchronized (byte1) {
				for (int i = 0; i < 5; i++) {
					try {
						System.out.println(Thread.currentThread().getName() + ":" + (count++));
						getInstace();
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public int getCount() {
			return count;
		}
	}

}
