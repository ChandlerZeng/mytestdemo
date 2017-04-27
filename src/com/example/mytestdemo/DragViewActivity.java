package com.example.mytestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.mytestdemo.utils.LogUtil;
import com.example.mytestdemo.utils.WindowParamUtil;
import com.example.mytestdemo.widget.DragHelperView;

public class DragViewActivity extends Activity {

    private LinearLayout llLayout;
    private TextView mInfo;
    private DragHelperView dragHelperView;

    private int mPointerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view);
        llLayout = (LinearLayout) findViewById(R.id.lv_dragview);
        dragHelperView = (DragHelperView)findViewById(R.id.drag_help_view);
        int height = WindowParamUtil.getContentViewHeight(this);
        LinearLayout.LayoutParams lp = (LayoutParams) llLayout.getLayoutParams();
        lp.height = height-110;
        lp.setMargins(0, -height + 180, 0, 0);
        llLayout.setLayoutParams(lp);
        LogUtil.i("height:" + height);
        
    }

}
