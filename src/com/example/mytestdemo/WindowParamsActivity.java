package com.example.mytestdemo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.txz.TXZTestInterface;
import com.example.mytestdemo.utils.WindowParamUtil;

public class WindowParamsActivity extends BaseActivity implements OnClickListener{

    private TextView txtWinParams,txtPackName;
    private EditText editText;
    private Button btnPackName;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_params);
        init();
    }
    
    private void init(){
        txtWinParams = (TextView)findViewById(R.id.txt_window_params);
        int width = WindowParamUtil.getWindowWidth(this);
        int height = WindowParamUtil.getWindowHeight(this);
        txtWinParams.setText("手机屏幕分辨率为(宽X高)："+width+"X"+height);
        
        editText = (EditText)findViewById(R.id.editTextPackName);
        txtPackName = (TextView)findViewById(R.id.textViewPackName);
        btnPackName = (Button)findViewById(R.id.btn_get_pack_name);
        btnPackName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_get_pack_name:
            String nameString = editText.getText().toString();
            if(nameString!=null && !nameString.equals("")){
                txtPackName.setText(TXZTestInterface.getInstance().getPackageName(nameString));
            }
            break;

        default:
            break;
        }
        
    }
}
