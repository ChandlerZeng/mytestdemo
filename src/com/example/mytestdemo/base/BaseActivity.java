package com.example.mytestdemo.base;

import com.example.mytestdemo.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Zengcq
 * @date 2016年12月28日
 * @version 1.0
 * @description
 */
public class BaseActivity extends Activity {

    private Dialog progressDialog;

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }

    @Override
    public void startActivity(Intent intent) {
        // TODO Auto-generated method stub
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }

    /**
     * 
     * @Description: TODO 自定义加载提示内容
     * @param @param id
     * @return void 用法buildProgressDialog(R.string.loding)
     * @throws
     * @author Sunday
     * @date 2015年12月25日
     */
    public void showProgressDialog(String content) {
        if (progressDialog == null) {
            progressDialog = new Dialog(this, R.style.progress_dialog);
        }
        progressDialog.setContentView(R.layout.dialog_my);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(
                R.color.transparent);
        TextView msg = (TextView) progressDialog
                .findViewById(R.id.id_tv_loadingmsg);
        msg.setText(content);
        progressDialog.show();
    }

    /**
     * @Description: TODO 固定加载提示内容
     * @author Sunday
     */
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(this, R.style.progress_dialog);
        }
        progressDialog.setContentView(R.layout.dialog_my);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        TextView msg = (TextView) progressDialog
                .findViewById(R.id.id_tv_loadingmsg);
        msg.setText("卖力加载中");
        progressDialog.show();
    }

    /**
     * @Description: TODO 取消加载框
     * @author Sunday
     * @date 2015年12月25日
     */
    public void cancelProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void hideSoftKeybord() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(
                getWindow().getDecorView().getWindowToken(), 0);
    }

    public boolean isEditTextNull(EditText editText) {
        String string = editText.getText().toString();
        if(string==null || string.equals("")){
            return true;
        }
        return false;
    }
}
