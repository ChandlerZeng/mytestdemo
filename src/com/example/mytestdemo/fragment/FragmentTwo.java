package com.example.mytestdemo.fragment;

import com.example.mytestdemo.R;
import com.example.mytestdemo.utils.LogUtil;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;


/**
 * @author Zengcq
 * @date 2017Äê1ÔÂ13ÈÕ
 * @version 1.0
 * @description
 */
public class FragmentTwo extends Fragment implements OnClickListener{
    Button btnBroadcast;
    
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        LogUtil.e("FragmentTwo onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        LogUtil.e("FragmentTwo onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_two, container,false);
        btnBroadcast = (Button)view.findViewById(R.id.btn_broadcast);
        btnBroadcast.setOnClickListener(this);
        LogUtil.e("FragmentTwo onCreateView");
        return view;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        LogUtil.e("FragmentTwo onDestroy");
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        LogUtil.e("FragmentTwo onDestroyView");
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
        LogUtil.e("FragmentTwo onDetach");
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        LogUtil.e("FragmentTwo onPause");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        LogUtil.e("FragmentTwo onResume");
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        LogUtil.e("FragmentTwo onStart");
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        LogUtil.e("FragmentTwo onStop");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        LogUtil.e("FragmentTwo onViewCreated");
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
       switch (v.getId()) {
    case R.id.btn_broadcast:
        Intent intent = new Intent();
        intent.setAction("change_to_fragment_one_action");
        getActivity().sendBroadcast(intent);
        break;

    default:
        break;
    } 
    }

}
