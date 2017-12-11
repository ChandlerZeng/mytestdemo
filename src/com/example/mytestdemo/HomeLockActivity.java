package com.example.mytestdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mytestdemo.utils.HomeKeyLocker;
import com.example.mytestdemo.utils.LogUtil;

public class HomeLockActivity extends Activity implements OnClickListener {

	private Button mBtnLock;
	private Button mBtnUnLock;
	private HomeKeyLocker mHomeKeyLocker;
	private static final String TAG = HomeLockActivity.class.getSimpleName();
	public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000; //需要自己定义标志

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//关键代码
//		getWindow().addPrivateFlags(WindowManager.LayoutParams.PRIVATE_FLAG_HOMEKEY_DISPATCHED); 
		setContentView(R.layout.activity_home_lock);
		mHomeKeyLocker = new HomeKeyLocker();
		mHomeKeyLocker.lock(this);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mHomeKeyLocker.unlock();
			}
		}, 2000);
		init();
	}

	private void init() {
		mBtnLock = (Button) findViewById(R.id.btn_lock);
		mBtnUnLock = (Button) findViewById(R.id.btn_unlock);
		mBtnLock.setOnClickListener(this);
		mBtnUnLock.setOnClickListener(this);
	}
	
	@Override
    public boolean onKeyDown( int keyCode, KeyEvent event) {
           // TODO Auto-generated method stub
           if (keyCode == event. KEYCODE_HOME) {
        	   LogUtil.i(TAG, "KEYCODE_HOME");
                 return true;
          }
           return super.onKeyDown(keyCode, event);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_lock:

			
			LogUtil.i(TAG, "LOCK");
			
			break;

		case R.id.btn_unlock:

			mHomeKeyLocker.unlock();
			LogUtil.i(TAG, "UNLOCK");

			break;

		default:
			break;
		}
	}

}
