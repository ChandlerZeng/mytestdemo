package com.example.mytestdemo.activity;

import com.example.mytestdemo.R;
import com.example.mytestdemo.R.id;
import com.example.mytestdemo.R.layout;
import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.javatest.FileTest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FileTestActivity extends BaseActivity implements OnClickListener {

    private EditText editTextFile;
    private Button btnSave, btnRead;
    private TextView textFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test);
        init();
    }

    private void init() {
        editTextFile = (EditText) findViewById(R.id.edittext_file);
        btnSave = (Button) findViewById(R.id.btn_file_save);
        btnRead = (Button) findViewById(R.id.btn_file_read);
        textFile = (TextView) findViewById(R.id.text_file_show);

        btnSave.setOnClickListener(this);
        btnRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.btn_file_save:
            if(isEditTextNull(editTextFile)){
                Toast.makeText(FileTestActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
            }else{
                FileTest.save(FileTestActivity.this,editTextFile.getText().toString());
            }
            break;

        case R.id.btn_file_read:
            String string = FileTest.read(FileTestActivity.this);
            if(string!=null)
            textFile.setText(string);
            break;

        default:
            break;
        }

    }
    
   
}
