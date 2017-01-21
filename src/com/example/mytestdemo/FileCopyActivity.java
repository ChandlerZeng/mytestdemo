package com.example.mytestdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.javatest.IOStream;
import com.example.mytestdemo.txz.SuUtil;

public class FileCopyActivity extends BaseActivity implements OnClickListener{

    private Button btnFileCopy;
    private TextView txtCopyStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_copy);
        init();
    }
    
    private void init(){
        
        btnFileCopy = (Button)findViewById(R.id.btn_contacts_copy);
        txtCopyStatus = (TextView)findViewById(R.id.txt_copy_success);
        btnFileCopy.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
        case R.id.btn_contacts_copy:
            copyFile();
            break;

        default:
            break;
        }
    }
    
    private void copyFile(){
        SuUtil.initProcess();
        String pathIn = Environment.getDataDirectory().toString()
                +"/data/com.android.providers.contacts/databases/contacts2.db";
        String pathOut = getDatabasePath("contacts2.db").toString();
        IOStream.fileCopy(pathIn, pathOut);
        txtCopyStatus.setText(pathIn+","+pathOut);
    }
}
