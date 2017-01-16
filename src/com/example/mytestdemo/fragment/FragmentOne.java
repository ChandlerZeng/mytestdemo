package com.example.mytestdemo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mytestdemo.R;
import com.example.mytestdemo.utils.LogUtil;


/**
 * @author Zengcq
 * @date 2017Äê1ÔÂ13ÈÕ
 * @version 1.0
 * @description
 */
public class FragmentOne extends Fragment implements OnClickListener{

    Button buttonTxz,btnBroadSelf;
    private static FragmentOne instance;
    private TextView txtSwitchStatus;
    private String from;
    Bundle bundle;
    
    public static FragmentOne getInstance(){
        if(instance==null){
            instance = new FragmentOne();
            LogUtil.e("instance = null");
        }
        return instance;
    }
    
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        LogUtil.e("FragmentOne onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        LogUtil.e("FragmentOne onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        
        View view = inflater.inflate(R.layout.fragment_one, container,false);        
        buttonTxz = (Button)view.findViewById(R.id.btn_txz);
        btnBroadSelf = (Button)view.findViewById(R.id.btn_broadcast_self);
        txtSwitchStatus = (TextView)view.findViewById(R.id.text_switch_status);
        buttonTxz.setOnClickListener(this);
        btnBroadSelf.setOnClickListener(this);
        instance = this;
        boolean instancenotnull = instance==null?false:true;
        LogUtil.e("FragmentOne onCreateView instancenotnull = "+instancenotnull);
        
        bundle = getArguments();
        if(bundle!=null){
            from = bundle.getString("from");
            if(from!=null && !from.equals("switchContent")){
                showSwitchStatus(from);                
            }
            LogUtil.i("from:"+from);
        }
        return view;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        LogUtil.e("FragmentOne onDestroy");
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        bundle = null;
        LogUtil.e("FragmentOne onDestroyView");
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
        LogUtil.e("FragmentOne onDetach");
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        LogUtil.e("FragmentOne onPause");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        LogUtil.e("FragmentOne onResume");
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        LogUtil.e("FragmentOne onStart");
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        LogUtil.e("FragmentOne onStop");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        LogUtil.e("FragmentOne onViewCreated");
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.btn_txz:
            Intent intent = new Intent();
            intent.setAction("com.android.action.DDT_VOICHELPER");
            getActivity().sendBroadcast(intent);
            break;
            
        case R.id.btn_broadcast_self:
            Intent intent2 = new Intent();
            intent2.setAction("change_to_fragment_one_action");
            getActivity().sendBroadcast(intent2);
            break;

        default:
            break;
        }
    }
    
    public void showSwitchStatus(String s){
        if(txtSwitchStatus!=null){
            txtSwitchStatus.setText(s);
            LogUtil.i("showSwitchStatus executed");  
        }else{
            LogUtil.i("showSwitchStatus not executed");  
        }
    }

}
