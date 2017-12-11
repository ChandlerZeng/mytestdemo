package com.example.mytestdemo.service;

import com.zcq.sdk.MyService;
import com.zcq.sdk.bean.Friends;
import com.zcq.sdk.bean.Person;

import android.util.Log;

/** 
* @author  zengcq 
* @date 创建时间：2017年9月6日 下午5:23:16 
* @version 1.0 
*/
public class SubMyService extends MyService{
	private Friends friends;

	@Override
	protected void addFriend(Person arg0) {
		// TODO Auto-generated method stub
		if(friends==null){
			friends = new Friends();
		}
		friends.addFriend(arg0);
	}


	@Override
	protected void initCustomService() {
		// TODO Auto-generated method stub
		Log.i(TAG,"initCustomService");
	}


	@Override
	protected Friends getFriends() {
		// TODO Auto-generated method stub
		return friends;
	}
	
	public String showFriendsInfo(){
		return friends.showFriendsInfo();
	}
	
	public void addFriends(Person person){
		addFriend(person);
	}

}
