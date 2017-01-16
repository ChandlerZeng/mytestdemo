package com.example.mytestdemo.fragment;

import com.example.mytestdemo.utils.LogUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Zengcq
 * @date 2017Äê1ÔÂ3ÈÕ
 * @version 1.0
 * @description
 */
public class TestFragment extends Fragment{

    private String test;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        LogUtil.e("onCreateView");
        test = "hello fragment test!";
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    
    public String showTestString(){
        return test;
    }
}
