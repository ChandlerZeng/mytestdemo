package com.example.mytestdemo.txz;

import android.util.Log;

import com.example.mytestdemo.main.MainTestActivity;
import com.txznet.sdk.TXZAsrManager;
import com.txznet.sdk.TXZAsrManager.AsrCallback;
import com.txznet.sdk.TXZAsrManager.AsrOption;

/**
 * @author Zengcq
 * @date 2016Äê12ÔÂ22ÈÕ
 * @version 1.0
 * @description
 */
public class AsrToolInterface implements TXZAsrManager.AsrTool{

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		TXZTestInterface.isTxzScreenShow = false;
		Log.d("RituNavi", "cancel, isShow " + TXZTestInterface.isTxzScreenShow);
	}

	@Override
	public void start(AsrOption arg0, AsrCallback arg1) {
		// TODO Auto-generated method stub
		TXZTestInterface.isTxzScreenShow = true;
		arg0.setBOS(4000);
		arg0.setEOS(1000);
		arg1.onBeginRecord();
		arg1.onBeginSpeech();
		arg1.onEndRecord();
		arg1.onEndSpeech();
		Log.d("RituNavi", "start, isShow " + TXZTestInterface.isTxzScreenShow);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		TXZTestInterface.isTxzScreenShow = false;
		Log.d("RituNavi", "stop, isShow " + TXZTestInterface.isTxzScreenShow);
	}

}
