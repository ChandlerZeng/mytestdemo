package com.example.mytestdemo.txz;

import com.txznet.sdk.TXZSysManager.AppMgrTool;

/**
 * @author Zengcq
 * @date 2016Äê12ÔÂ14ÈÕ
 * @version 1.0
 * @description
 */
public class AppMgrToolInterface extends AppMgrTool{

	public static AppMgrToolInterface instance = null;
	
	public static AppMgrToolInterface getInstance(){
		if(instance == null){
			instance = new AppMgrToolInterface();
		}
		return instance;
	}
	@Override
	public void closeApp(String arg0) {
		// TODO Auto-generated method stub
		super.closeApp(arg0);
//		SuUtil.kill(arg0);
	}

	@Override
	public void openApp(String arg0) {
		// TODO Auto-generated method stub
		super.openApp(arg0);
	}

}
