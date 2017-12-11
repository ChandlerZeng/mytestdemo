package com.example.mytestdemo;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytestdemo.entity.Book;
import com.example.serveraidl.aidl.BookChangeListener;
import com.example.serveraidl.aidl.IService;

public class AidlActivity extends Activity implements OnClickListener{

    private Button btnGetBookInfo;
    private TextView txtPrice,txtBookName,txtAuthor;
    
    private IService remoteService;
    private final int MSG_BOOK_CHANGE = 0;
    
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
            case MSG_BOOK_CHANGE:
                Book book = (Book)msg.obj;
                txtPrice.setText("price:"+book.getPrice());
                txtBookName.setText("book name:"+book.getBookName());
                txtAuthor.setText("book author:"+book.getAuthor());
                break;

            default:
                break;
            }
        }
        
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        init();
//        initService();
    }
    
    private void init(){
        btnGetBookInfo = (Button)findViewById(R.id.btn_book_info);
        txtPrice = (TextView)findViewById(R.id.txt_book_price);
        txtBookName = (TextView)findViewById(R.id.txt_book_name);
        txtAuthor = (TextView)findViewById(R.id.txt_book_author);
        
        btnGetBookInfo.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                initService();
            }
        });
    }
    
    private ServiceConnection aidlServiceConnection = new ServiceConnection() {
        
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            
        }
        
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            remoteService = IService.Stub.asInterface(service);
            try {
                String helloString = remoteService.hello("√¿≈Æƒ„∫√∞°");
                int time = remoteService.helloTime(5);
                for(int i=0;i<time;i++){
                    Toast.makeText(getApplicationContext(), helloString+i, Toast.LENGTH_SHORT).show();
                }
                txtPrice.setText("price:"+remoteService.price());
                txtBookName.setText(remoteService.bookName());
                txtAuthor.setText(remoteService.author());
                remoteService.registerListener(onBookChangeListener);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "remote service connect exception", Toast.LENGTH_SHORT).show();
            }
        }
    };
    
    private void initService(){
        Intent intent = new Intent("android.intent.action.ServerService");
        bindService(intent, aidlServiceConnection, Context.BIND_AUTO_CREATE);
    }
    
    private void releaseService() throws RemoteException{
        unbindService(aidlServiceConnection);
        aidlServiceConnection=null;
        remoteService.unRegisterListener(onBookChangeListener);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        try {
            releaseService();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private BookChangeListener onBookChangeListener = new BookChangeListener.Stub() {
        
        @Override
        public void OnBookChangeListener(Book book) throws RemoteException {
            // TODO Auto-generated method stub
            handler.obtainMessage(MSG_BOOK_CHANGE,book).sendToTarget();
        }
    };

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        
    }
}
