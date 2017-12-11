package com.example.mytestdemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.javatest.IOStream;
import com.example.mytestdemo.txz.ScreenControl;
import com.example.mytestdemo.txz.SuUtil;
import com.example.mytestdemo.utils.LogUtil;

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
        LogUtil.i("isPackageInstall:com.autonavi.amapautolite "+isPkgInstalled("com.autonavi.amapautolite"));
        LogUtil.i("isPackageInstall:com.baidu.BaiduMap "+isPkgInstalled("com.baidu.BaiduMap"));
        LogUtil.i("isPackageInstall:com.baidu.navi.hd "+isPkgInstalled("com.baidu.navi.hd"));
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
    
    private OnClickListener testClickListener = new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            
        }
    };
    
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
        }
        
    };
    
    private ScreenControl screenControl = new ScreenControl(){

        @Override
        public void turnOffBacklight() {
            // TODO Auto-generated method stub
            super.turnOffBacklight();
        }

        @Override
        public boolean turnOnBacklight() {
            // TODO Auto-generated method stub
            return super.turnOnBacklight();
        }
        
    };
    
    
    private void copyFile(){
        SuUtil.initProcess();
        String pathIn = Environment.getDataDirectory().toString()
                +"/data/com.android.providers.contacts/databases/contacts2.db";
        String pathOut = getDatabasePath("contacts2.db").toString();
        IOStream.fileCopy(pathIn, pathOut);
        txtCopyStatus.setText(pathIn+","+pathOut);
    }
    
    private boolean isPkgInstalled(String pkgName) {  
    	PackageInfo packageInfo = null;  
    	try {  
    	    packageInfo = this.getPackageManager().getPackageInfo(pkgName, 0);  
    	} catch (NameNotFoundException e) {  
    	    packageInfo = null;  
    	    e.printStackTrace();  
    	}  
    	if (packageInfo == null) {  
    	    return false;  
    	} else {  
    	    return true;  
    	}  
    }
}
