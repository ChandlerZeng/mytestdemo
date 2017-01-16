package com.example.mytestdemo.txz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.example.mytestdemo.main.MainTestActivity;
import com.txznet.sdk.TXZAsrManager;

/**
 * @author zengcq
 * @date 2016年11月29日
 * @version 1.0
 */
public class TXZNaviSettingReceiver extends BroadcastReceiver {

	public static final String SELECT_MAP_ACTION = "cn.ritu.rtnavi.select_map_action";
	public static final String SELECT_MAP_TYPE = "select_map_type";
	public static final String PREFS_SELECTED_MAP = "prefs_selected_map";
	public static final String KEY_SELECTED_MAP = "key_selected_map";
	public static final String TXZ_SCREEN_SHOW_ACTION = "com.txznet.txz.record.show";
	public static final String TXZ_SCREEN_DISMISS_ACTION = "com.txznet.txz.record.dismiss";
	
	private SharedPreferences sharedPreferences;
	private boolean isScreenShow = false;
	public static boolean isFirstInit = false;

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		
		if (action.equals(SELECT_MAP_ACTION)) {
			int mapType = intent.getIntExtra(SELECT_MAP_TYPE, 0);
			saveSelectedMap(mapType, context);
			NaviToolInterface.getInstance().setMaps(mapType);
			Log.d("RituNavi", SELECT_MAP_ACTION + " " + mapType);
			Log.i("RituNavi", SELECT_MAP_ACTION + " " + context.toString());
			Log.i("RituNavi", SELECT_MAP_ACTION + " " + context.getClass().toString());
		}else if (action.equals(MainTestActivity.ACTION_VOICHELPER)){		
//			changeTxzStatus(!isChecked);
//			saveTxzStatus(!isChecked, context);
//			Intent intent2 = new Intent();
//			intent2.putExtra(KEY_TXZ_SWITCH_STATUS, !isChecked);
//			intent2.setAction(SYSTEM_TXZ_SWITCH_ACTION);
//			context.sendBroadcast(intent2);
			if(isScreenShow){
				closeTxzScreen();
				Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " " + context.toString());
				Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " " + context.getClass().toString());
				Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " processName: " + context.getApplicationInfo().processName);
				Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " pid: " + android.os.Process.myPid());
				Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " Tid: " + android.os.Process.myTid());
                Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " threadid: " + Thread.currentThread().getId());
				Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " uid: " + android.os.Process.myUid());
			}else{
				showTxzScreen();
				Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " " + context.toString());
				Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " " + context.getClass().toString());
				Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " processName: " + context.getApplicationInfo().processName);
                Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " pid: " + android.os.Process.myPid());
                Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " Tid: " + android.os.Process.myTid());
                Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " threadid: " + Thread.currentThread().getId());
                Log.i("RituNavi", MainTestActivity.ACTION_VOICHELPER + " uid: " + android.os.Process.myUid());
			}
			Log.d("RituNavi", MainTestActivity.ACTION_VOICHELPER + " isShow " + isScreenShow);
		}else if (action.equals(TXZ_SCREEN_SHOW_ACTION)){
			isScreenShow = true;
			Log.d("RituNavi", TXZ_SCREEN_SHOW_ACTION + " isShow " + isScreenShow);
		}else if (action.equals(TXZ_SCREEN_DISMISS_ACTION)){
			isScreenShow = false;
			Log.d("RituNavi", TXZ_SCREEN_DISMISS_ACTION + " isShow " + isScreenShow);
		}else if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
		    isFirstInit = true;
		    Log.d("RituNavi", Intent.ACTION_BOOT_COMPLETED + " isFirstInit " + isFirstInit);
		}else if(action.equals(Intent.ACTION_SHUTDOWN)){
            isFirstInit = false;
            Log.d("RituNavi", Intent.ACTION_SHUTDOWN + " isFirstInit " + isFirstInit);
        }
	}

	private void saveSelectedMap(int mapType, Context context) {
		sharedPreferences = context.getSharedPreferences(PREFS_SELECTED_MAP,
				Context.MODE_PRIVATE);
		Editor mapSelectedEditor = sharedPreferences.edit();
		mapSelectedEditor.putInt(KEY_SELECTED_MAP, mapType);
		mapSelectedEditor.commit();
	}
	
	// 启动声控界面
	private void showTxzScreen() {
		TXZAsrManager.getInstance().triggerRecordButton();
//		TXZAsrManager.getInstance().start();
	}

	// 关闭声控界面
	private void closeTxzScreen() {
		TXZAsrManager.getInstance().cancel();
	}

}
