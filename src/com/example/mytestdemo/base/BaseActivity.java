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
 * @date 2016��12��28��
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
     * @Description: TODO �Զ��������ʾ����
     * @param @param id
     * @return void �÷�buildProgressDialog(R.string.loding)
     * @throws
     * @author Sunday
     * @date 2015��12��25��
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
     * @Description: TODO �̶�������ʾ����
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
        msg.setText("����������");
        progressDialog.show();
    }

    /**
     * @Description: TODO ȡ�����ؿ�
     * @author Sunday
     * @date 2015��12��25��
     */
    public void cancelProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void hideSoftKeybord() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // ���������
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
