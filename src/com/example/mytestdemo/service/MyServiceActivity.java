package com.example.mytestdemo.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.mytestdemo.R;
import com.example.mytestdemo.service.ServiceDemo.MyBinder;

public class MyServiceActivity extends Activity implements OnClickListener{

    private Button startService;  
    
    private Button stopService;  
  
    private Button bindService;  
  
    private Button unbindService;  
    
    private MyBinder myBinder;
    
    private ServiceConnection connection = new ServiceConnection() {
        
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            
        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            myBinder = (MyBinder)service;
            myBinder.startDownload();
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        startService = (Button) findViewById(R.id.start_service);  
        stopService = (Button) findViewById(R.id.stop_service);  
        bindService = (Button) findViewById(R.id.bind_service);  
        unbindService = (Button) findViewById(R.id.unbind_service);  
        startService.setOnClickListener(this);  
        stopService.setOnClickListener(this);  
        bindService.setOnClickListener(this);  
        unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {  
        case R.id.start_service:  
            Intent startIntent = new Intent(this, ServiceDemo.class);  
            startService(startIntent);  
            break;  
        case R.id.stop_service:  
            Intent stopIntent = new Intent(this, ServiceDemo.class);  
            stopService(stopIntent);  
            break;  
        case R.id.bind_service:  
            Intent bindIntent = new Intent(this, ServiceDemo.class);  
            bindService(bindIntent, connection, BIND_AUTO_CREATE);  
            break;  
        case R.id.unbind_service:  
            unbindService(connection);  
            break;  
        default:  
            break;  
        }  
    }
}
