package com.example.mytestdemo;

import java.util.List;
import java.util.logging.MemoryHandler;

import com.example.mytestdemo.service.SubMyService;
import com.example.mytestdemo.utils.LogUtil;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import cn.ritu.txztest.aidl.Friends;
import cn.ritu.txztest.aidl.IMyService;
import cn.ritu.txztest.aidl.IMyServiceCallback;
import cn.ritu.txztest.aidl.Person;

public class MySdkActivity extends Activity implements ServiceConnection{

	private EditText etFriends;
	private RadioGroup rgFreinds;
	private Button btnAddFriends;
	private TextView tvShowFriends;
	private SubMyService mMyService;
	private IMyService mIService;
	private boolean mIsRegistered = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_sdk);
		init();
		initService();
	}
	
	private void init(){
		etFriends = (EditText)findViewById(R.id.edt_friend_name);
		rgFreinds = (RadioGroup)findViewById(R.id.radio_gp_friends);
		btnAddFriends = (Button) findViewById(R.id.btn_add_friends);
		tvShowFriends = (TextView) findViewById(R.id.tv_friends_info);
		btnAddFriends.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String name = etFriends.getText().toString().trim();
				if(name==null || name.equals("")){
					Toast.makeText(MySdkActivity.this, "name is null not allowed", Toast.LENGTH_SHORT).show();
					return;
				}
				if(mIService==null){
					Toast.makeText(MySdkActivity.this, "MyService is null, unable to handle", Toast.LENGTH_SHORT).show();
					return;
				}
				
				Person person = new Person();
				person.setName(name);
				person.setSex(rgFreinds.getCheckedRadioButtonId()==R.id.radioButton1_friends?0:1);
				try {
					mIService.addFriend(person);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
	
	private IMyServiceCallback mCallback = new IMyServiceCallback.Stub() {
		
		@Override
		public void updateFriends(final List<Person> friends) throws RemoteException {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					tvShowFriends.setText(showFriendsInfo(friends));;				
				}
			});
			LogUtil.i("MyAidl remote","updateFriends");
		}
	};
	
	public String showFriendsInfo(List<Person> friendList){
		StringBuffer buffer = new StringBuffer();		
		if(friendList!=null && friendList.size()>0){
			for(int i=0; i<friendList.size(); i++){
				buffer.append("friend member"+i+" name:"+friendList.get(i).getName()+" sex:"+(friendList.get(i).getSex()==0?"man":"woman"));
				buffer.append("\n");
			}
			if(friendList.size()>5){
				buffer.append("Amazing! you have so many friends, you must be much rich!!!\n can we be friends any way?");
				buffer.append("\n");
			}
		}else{
			buffer.append("your friends is null, loser!");
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	private DeathRecipient mDeathRecipient = new DeathRecipient() {
		
		@Override
		public void binderDied() {
			LogUtil.e("MySdkActivity","IService died");
			try {
				if(mIService!=null)
				mIService.unregisterCallback(mCallback);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mIService = null;
			
		}
	};
	
	private void initService(){
		Intent intent = new Intent();
		intent.setAction("cn.ritu.txztest.service.SubMyService");
		intent.setComponent(new ComponentName("cn.ritu.txztest","cn.ritu.txztest.SubMyService"));
		if(mIService==null){
			startService(intent);
		}
		bindService(intent, this, BIND_AUTO_CREATE);
		if(mIService!=null && !mIsRegistered){
			try {
				mIService.registerCallback(mCallback);
				mIsRegistered = true;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initService();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unBindService();
	}
	
	private void unBindService(){
		if(mIService!=null && mIsRegistered){
			try {
				mIService.unregisterCallback(mCallback);
				mIsRegistered = false;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		unbindService(this);
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		// TODO Auto-generated method stub
		try {
			service.linkToDeath(mDeathRecipient, 0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mIService = IMyService.Stub.asInterface(service);
		try {
			mIService.registerCallback(mCallback);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mIsRegistered = true;
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		LogUtil.e("MySdkActivity","IService onServiceDisconnected");
		try {
			if(mIService!=null)
			mIService.unregisterCallback(mCallback);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mIService = null;
		mIsRegistered = false;
	}
}
