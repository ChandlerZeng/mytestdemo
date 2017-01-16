package com.example.mytestdemo.fragment;

import com.example.mytestdemo.utils.LogUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author Zengcq
 * @date 2017Äê1ÔÂ13ÈÕ
 * @version 1.0
 * @description
 */
public class FragmentChangeReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String action = intent.getAction();
        if(action.equals("change_to_fragment_one_action")){
            if(!FragmentTestActivity.isCreated){
                Intent intent2 = new Intent(context,FragmentTestActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.putExtra("from", "from broadcast");
                context.startActivity(intent2);
                LogUtil.e("FragmentTestActivity.isCreated false");
            }else{               
                if(FragmentTestActivity.getInstance()!=null){
                    FragmentTestActivity.getInstance().switchToFragmentOne();
                    LogUtil.e("FragmentTestActivity.isCreated true");
                }
            }
        }
    }
}
