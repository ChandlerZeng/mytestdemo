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
public class FragmentFour extends Fragment {
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        LogUtil.e("FragmentFour onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        LogUtil.e("FragmentFour onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_four, container,false);
        LogUtil.e("FragmentFour onCreateView");
        return view;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        LogUtil.e("FragmentFour onDestroy");
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        LogUtil.e("FragmentFour onDestroyView");
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
        LogUtil.e("FragmentFour onDetach");
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        LogUtil.e("FragmentFour onPause");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        LogUtil.e("FragmentFour onResume");
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        LogUtil.e("FragmentFour onStart");
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        LogUtil.e("FragmentFour onStop");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        LogUtil.e("FragmentFour onViewCreated");
    }

}
