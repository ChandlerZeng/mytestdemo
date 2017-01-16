package com.example.mytestdemo.txz;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.mytestdemo.MyTestApplication;
import com.txznet.sdk.TXZCallManager.CallTool;
import com.txznet.sdk.TXZCallManager.CallToolStatusListener;
import com.txznet.sdk.TXZCallManager.Contact;

public class CallToolInterface implements CallTool{
	public static final String TAG = "CallToolInterface";
	private static CallStatus mLastStatus = null;
	private static Contact mLastContact = null;
	private static CallToolStatusListener mCallToolStatusListener = null;
	
	static TelephonyManager mTelephonyManager = (TelephonyManager) MyTestApplication
			.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
	
	@Override
	public boolean acceptIncoming() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CallStatus getStatus() {
		if (mTelephonyManager != null) {
			switch (mTelephonyManager.getCallState()) {
			case TelephonyManager.CALL_STATE_IDLE: {
				if (mLastStatus != CallStatus.CALL_STATUS_IDLE) {
					onIdle();
				}
				return mLastStatus = CallStatus.CALL_STATUS_IDLE;
			}
			case TelephonyManager.CALL_STATE_OFFHOOK:
				if (mLastStatus != CallStatus.CALL_STATUS_OFFHOOK) {
					onOffhook();
				}
				return mLastStatus = CallStatus.CALL_STATUS_OFFHOOK;
			case TelephonyManager.CALL_STATE_RINGING:
				if (mLastStatus != CallStatus.CALL_STATUS_RINGING) {
					onIncoming(mLastContact);
				}
				return mLastStatus = CallStatus.CALL_STATUS_RINGING;
			}
		}
		return mLastStatus = null;
	}

	@Override
	public boolean hangupCall() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean makeCall(Contact contact) {
//		Uri dataUri = Uri.parse("tel://"+contact.getNumber());
//		Intent intent = new Intent(Intent.ACTION_CALL);
//		intent.setData(dataUri);
//	    ComponentName componentName = new ComponentName("cn.ritu.bluephone","cn.ritu.bluephone.MainActivity");
//	    Intent intent = new Intent();
//	    intent.setComponent(componentName);
//	    intent.putExtra("number", contact.getNumber());
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		MyTestApplication.getInstance().getApplicationContext().startActivity(intent);
		Log.d("RituNavi",contact.getName()+contact.getNumber());
	    Intent intent = new Intent();
	    intent.putExtra("number", contact.getNumber());
	    intent.setAction("android.intent.action.TXZ_CALL_PHONE");
	    MyTestApplication.getInstance().getApplicationContext().sendBroadcast(intent);
		return true;
	}

	@Override
	public boolean rejectIncoming() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStatusListener(CallToolStatusListener listener) {
		// TODO Auto-generated method stub
		mCallToolStatusListener = listener;
		if(listener!=null){
			listener.onEnabled();
			listener.onIdle();
		}
		getStatus();
	}

	public void onIdle() {
		Log.d(TAG, "CallToolInterface onIdle");

		mLastContact = new Contact();
		if (mCallToolStatusListener != null)
			mCallToolStatusListener.onIdle();
		
	}

	public void onIncoming(Contact con) {
		Log.d(TAG, "CallToolInterface onIncoming");

		mLastContact = con;
		if (mCallToolStatusListener != null)
			mCallToolStatusListener.onIncoming(mLastContact, true, true);
		
	}

	public void onMakeCall(Contact contact) {
		Log.d(TAG, "CallToolInterface onMakeCall");

		mLastContact = contact;
		if (mCallToolStatusListener != null)
			mCallToolStatusListener.onMakeCall(mLastContact);
		
	}

	public void onOffhook() {
		Log.d(TAG, "CallToolInterface onOffhook");

		if (mCallToolStatusListener != null){
			mCallToolStatusListener.onOffhook();
		}
		
	}

}
