package com.example.mytestdemo.txz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * @author zengcq
 * 
 * @date 2016年11月29日
 * @version 1.0
 */
public class TXZSettingReceiver extends BroadcastReceiver {

    // 同行者语音开关广播
    public static final String TXZ_SWITCH_ON_ACTION = "android.intent.action.ACC_ON";
    public static final String TXZ_SWITCH_OFF_ACTION = "android.intent.action.ACC_OVER";

    private SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
//        if (action.equals(TXZ_SWITCH_ON_ACTION)) {
//            saveTxzStatus(true, context);
//            Log.d("RituNavi", TXZ_SWITCH_ON_ACTION + " ");
//        } else if (action.equals(TXZ_SWITCH_OFF_ACTION)) {
//            saveTxzStatus(false, context);
//            Log.d("RituNavi", TXZ_SWITCH_OFF_ACTION + " ");
//        }
    }

    // 保存同行者语音开关状态
//    private void saveTxzStatus(boolean isChecked, Context context) {
//        sharedPreferences = context.getSharedPreferences(TxzSettingActivity.PREFS_SELECTED_MAP,
//                Context.MODE_PRIVATE);
//        Editor mapSelectedEditor = sharedPreferences.edit();
//        mapSelectedEditor.putBoolean(TxzSettingActivity.KEY_TXZ_SWITCH_STATUS, isChecked);
//        mapSelectedEditor.commit();
//    }
}
