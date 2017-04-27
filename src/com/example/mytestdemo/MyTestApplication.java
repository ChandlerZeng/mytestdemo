package com.example.mytestdemo;

import com.example.mytestdemo.txz.TXZTestInterface;
import com.example.mytestdemo.utils.LogUtil;
import com.example.mytestdemo.utils.LogcatHelper;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * @author zengcq
 *
 */
public class MyTestApplication extends Application{
	
	private static MyTestApplication instance;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		TXZTestInterface.getInstance().Init(this);
		LogUtil.setContext(this);
		LogcatHelper.getInstance(this).start();
		Log.d("RituNavi", "MyTestApplication onCreate");		

	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		Log.d("RituNavi", "MyTestApplication onLowMem");
	}



	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		Log.d("RituNavi", "MyTestApplication onTerminate");
	}



	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		super.onTrimMemory(level);
		Log.d("RituNavi", "MyTestApplication onTrimMem");
	}



	@Override
	public void registerActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		// TODO Auto-generated method stub
		super.registerActivityLifecycleCallbacks(callback);
		Log.d("RituNavi", "MyTestApplication registerAct");
	}



	@Override
	public void registerComponentCallbacks(ComponentCallbacks callback) {
		// TODO Auto-generated method stub
		super.registerComponentCallbacks(callback);
		Log.d("RituNavi", "MyTestApplication registerCom");
	}




	@Override
	public void unregisterActivityLifecycleCallbacks(
			ActivityLifecycleCallbacks callback) {
		// TODO Auto-generated method stub
		super.unregisterActivityLifecycleCallbacks(callback);
		Log.d("RituNavi", "MyTestApplication unregisterAct");
	}



	@Override
	public void unregisterComponentCallbacks(ComponentCallbacks callback) {
		// TODO Auto-generated method stub
		super.unregisterComponentCallbacks(callback);
		Log.d("RituNavi", "MyTestApplication unregisterCom");
	}




	public static MyTestApplication getInstance(){
		return instance;
	}
}