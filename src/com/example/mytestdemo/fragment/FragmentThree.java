package com.example.mytestdemo.fragment;

import com.example.mytestdemo.R;
import com.example.mytestdemo.utils.LogUtil;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author Zengcq
 * @date 2017Äê1ÔÂ13ÈÕ
 * @version 1.0
 * @description
 */
public class FragmentThree extends Fragment{
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        LogUtil.e("FragmentThree onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        LogUtil.e("FragmentThree onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_three, container,false);
        LogUtil.e("FragmentThree onCreateView");
        return view;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        LogUtil.e("FragmentThree onDestroy");
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        LogUtil.e("FragmentThree onDestroyView");
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
        LogUtil.e("FragmentThree onDetach");
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        LogUtil.e("FragmentThree onPause");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        LogUtil.e("FragmentThree onResume");
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        LogUtil.e("FragmentThree onStart");
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        LogUtil.e("FragmentThree onStop");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        LogUtil.e("FragmentThree onViewCreated");
    }

}
