package com.example.mytestdemo.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import cn.ritu.bluephone.bean.BtContact;

import com.example.mytestdemo.MyTestApplication;
import com.example.mytestdemo.R;
import com.example.mytestdemo.WindowParamsActivity;
import com.example.mytestdemo.activity.AndroidFileSystemTest;
import com.example.mytestdemo.activity.ContentProviderActivity;
import com.example.mytestdemo.activity.DBUtilActivity;
import com.example.mytestdemo.activity.FileTestActivity;
import com.example.mytestdemo.activity.RandomIdsActivity;
import com.example.mytestdemo.activity.SPUtilActivity;
import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.carlife.VideoActivity;
import com.example.mytestdemo.fragment.FragmentTestActivity;
import com.example.mytestdemo.fragment.TestFragment;
import com.example.mytestdemo.music.MyMusicActivity;
import com.example.mytestdemo.service.MyServiceActivity;
import com.example.mytestdemo.txz.NaviToolInterface;
import com.example.mytestdemo.txz.TXZNaviSettingReceiver;
import com.example.mytestdemo.txz.TXZTestInterface;
import com.example.mytestdemo.utils.LogUtil;
import com.example.mytestdemo.utils.LogcatHelper;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.sdk.TXZPowerManager;
import com.txznet.sdk.TXZCallManager.Contact;
import com.txznet.sdk.TXZPowerManager.PowerAction;

public class MainTestActivity extends BaseActivity implements OnClickListener{

	public static final int MAP_TYPE_DDT = 0;
	public static final int MAP_TYPE_BAIDU_NAV = 1;
	public static final int MAP_TYPE_GAODE_NAV = 2;
	public static final int MAP_TYPE_BAIDU_MAP = 3;
	public static final int MAP_TYPE_BAIDU_NAV_HD = 4;
	public static final int MAP_TYPE_GAODE_MAP = 5;
	
	public static final int MAP_TYPE_GAODE_MAP_CAR = 6;
	
	public static final String ACTION_VOICHELPER = "com.android.action.DDT_VOICHELPER";

	private SharedPreferences sharedPreferences;

	public static ProgressBar progressBar;
	private Button btnTxzStart;
	private Button btnTxzClose;
	private Button btnTxzReStart;
	private Button btnTxzSleep;
	private Button btnTXZSystemControl;
	private Button btnTest;
	private Button btnSPUtil;
	private Button btnDBUtil;
	private Button btnVideoPlay;
	private Button btnMusicPlay;
	private Button btnServiceDemo;
	private Button btnBtContact;
	private Button btnFragment;
	private Button randIds;
	private Button fileTest;
	private Button btnBroadCast;
	private Button aFileSystem;
	private Button btnWinParams;
	
	private Spinner spinnerMap;

	boolean first = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_ddt);
		init();
		Log.d("RituNavi", "MainTestActivity onCreate");
	}
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("RituNavi", "MainTestActivity onDestroy");
		LogcatHelper.getInstance(getApplicationContext()).stop();
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("RituNavi", "MainTestActivity onPause");
	}



	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d("RituNavi", "MainTestActivity onRestart");
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d("RituNavi", "MainTestActivity onResume");
	}



	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("RituNavi", "MainTestActivity onStart");
	}



	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("RituNavi", "MainTestActivity onStop");
	}



	private void init() {
		btnTxzStart = (Button) findViewById(R.id.btnTXZStart);
		btnTxzClose = (Button) findViewById(R.id.btnTXZClose);
		btnTxzReStart = (Button) findViewById(R.id.btnTXZReStart);
		btnTxzSleep = (Button) findViewById(R.id.btnTXZSleep);
		btnTXZSystemControl = (Button) findViewById(R.id.btnTXZSystemControl);
		btnTest = (Button) findViewById(R.id.buttonTest);
		btnSPUtil = (Button) findViewById(R.id.btn_sp_util);
		btnDBUtil = (Button) findViewById(R.id.btn_db_util);
		btnVideoPlay = (Button) findViewById(R.id.btn_video_play);
		btnMusicPlay = (Button) findViewById(R.id.btn_music_play);
		btnServiceDemo = (Button) findViewById(R.id.btn_service_demo);
		btnBtContact = (Button) findViewById(R.id.btn_bt_contact);
		btnFragment = (Button) findViewById(R.id.btn_test_fragment);
		randIds = (Button) findViewById(R.id.btn_random_ids);
		fileTest = (Button) findViewById(R.id.btn_file_test);
		btnBroadCast = (Button) findViewById(R.id.btn_frag_broadcast);
		aFileSystem = (Button) findViewById(R.id.btn_afile_system);
		btnWinParams = (Button) findViewById(R.id.btn_window_params);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		spinnerMap = (Spinner) findViewById(R.id.spinnerMap);
		
		btnSPUtil.setOnClickListener(this);
		btnDBUtil.setOnClickListener(this);
		btnVideoPlay.setOnClickListener(this);
		btnMusicPlay.setOnClickListener(this);
		btnServiceDemo.setOnClickListener(this);
		btnBtContact.setOnClickListener(this);
		btnFragment.setOnClickListener(this);
		randIds.setOnClickListener(this);
		fileTest.setOnClickListener(this);
		btnBroadCast.setOnClickListener(this);
		aFileSystem.setOnClickListener(this);
		btnWinParams.setOnClickListener(this);

		spinnerMap.setSelection(getSelectedMap());
//		NaviToolInterface.getInstance().setMaps(getSelectedMap());

		btnTxzStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					 showProgress();
					if (TXZConfigManager.getInstance().isInitedSuccess()) {
						Toast.makeText(MainTestActivity.this, "同行者语音已初始化",
								Toast.LENGTH_SHORT).show();
						 dismissProgress();
					} else {
						TXZTestInterface.getInstance().Init(
								MyTestApplication.getInstance()
										.getApplicationContext());
						Log.d("TXZ TEST", "同行者语音初始化");
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(MainTestActivity.this, e.toString(),
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		btnTxzClose.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
//					 showProgress();
					if (TXZConfigManager.getInstance().isInitedSuccess()) {
						TXZPowerManager.getInstance().notifyPowerAction(
								PowerAction.POWER_ACTION_POWER_OFF);
						TXZPowerManager.getInstance().releaseTXZ();
						Log.d("TXZ TEST", "同行者语音关闭");
						Toast.makeText(MainTestActivity.this, "同行者语音关闭",
								Toast.LENGTH_SHORT).show();
						
					} else {
						Toast.makeText(MainTestActivity.this, "同行者语音未初始化",
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(MainTestActivity.this, e.toString(),
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		btnTxzReStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if (TXZConfigManager.getInstance().isInitedSuccess()) {
						TXZPowerManager.getInstance().reinitTXZ(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								TXZPowerManager
										.getInstance()
										.notifyPowerAction(
												PowerAction.POWER_ACTION_WAKEUP);
								Log.d("RituNavi", "同行者语音重启");
								Toast.makeText(MainTestActivity.this,
										"同行者语音重启", Toast.LENGTH_SHORT).show();
								// showProgress();
							}
						});
					} else {
						Toast.makeText(MainTestActivity.this, "同行者语音未初始化",
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(MainTestActivity.this, e.toString(),
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		btnTxzSleep.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if (TXZConfigManager.getInstance().isInitedSuccess()) {
						TXZPowerManager.getInstance().notifyPowerAction(
								PowerAction.POWER_ACTION_BEFORE_SLEEP);
						Log.d("TXZ TEST", "同行者语音休眠");
						Toast.makeText(MainTestActivity.this, "同行者语音休眠",
								Toast.LENGTH_SHORT).show();
						// showProgress();
					} else {
						Toast.makeText(MainTestActivity.this, "同行者语音未初始化",
								Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(MainTestActivity.this, e.toString(),
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		
		btnTXZSystemControl.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(ACTION_VOICHELPER);
				MainTestActivity.this.sendBroadcast(intent);
				Toast.makeText(MainTestActivity.this, "同行者语音指令发送", Toast.LENGTH_SHORT).show();
			}
			
		});
		
		btnTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainTestActivity.this,ContentProviderActivity.class));
			}
		});

		spinnerMap
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						if (first) {
							first = false;
						} else {
							String[] maps = getResources().getStringArray(
									R.array.maps);
							Toast.makeText(MainTestActivity.this,
									"you selected " + maps[arg2],
									Toast.LENGTH_SHORT).show();
							NaviToolInterface.getInstance().setMaps(arg2);
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

	/*
	 * private void setMaps(int mapType) { switch (mapType) {
	 * 
	 * case MAP_TYPE_DDT:
	 * TXZNavManager.getInstance().setNavTool(NaviToolInterface.getInstance());
	 * Log.d("RituNavi", MAP_TYPE_DDT+""); break;
	 * 
	 * case MAP_TYPE_BAIDU_NAV:
	 * TXZNavManager.getInstance().setNavTool(NavToolType.NAV_TOOL_BAIDU_MAP);
	 * Log.d("RituNavi", MAP_TYPE_BAIDU_NAV+""); break;
	 * 
	 * case MAP_TYPE_GAODE_NAV:
	 * TXZNavManager.getInstance().setNavTool(NavToolType.NAV_TOOL_GAODE_MAP);
	 * Log.d("RituNavi", MAP_TYPE_GAODE_NAV+""); break;
	 * 
	 * case MAP_TYPE_BAIDU_MAP: //this one
	 * TXZNavManager.getInstance().setNavTool(NavToolType.NAV_TOOL_BAIDU_MAP);
	 * Log.d("RituNavi", MAP_TYPE_BAIDU_MAP+""); break;
	 * 
	 * case MAP_TYPE_GAODE_MAP: //this one
	 * TXZNavManager.getInstance().setNavTool(NavToolType.NAV_TOOL_GAODE_MAP);
	 * Log.d("RituNavi", MAP_TYPE_GAODE_MAP+""); break;
	 * 
	 * case MAP_TYPE_GAODE_MAP_CAR:
	 * TXZNavManager.getInstance().setNavTool(NavToolType
	 * .NAV_TOOL_GAODE_MAP_CAR); Log.d("RituNavi", MAP_TYPE_GAODE_MAP_CAR+"");
	 * break;
	 * 
	 * } }
	 */

	private int getSelectedMap() {

		sharedPreferences = getApplicationContext()
				.getSharedPreferences(
						TXZNaviSettingReceiver.PREFS_SELECTED_MAP,
						Context.MODE_PRIVATE);
		int mapType = sharedPreferences.getInt(
				TXZNaviSettingReceiver.KEY_SELECTED_MAP, 0);
		sharedPreferences = null;
		return mapType;
	}

	public static void showProgress() {
		if (progressBar != null && !progressBar.isShown())
			progressBar.setVisibility(View.VISIBLE);
	}

	public static void dismissProgress() {
		if (progressBar != null && progressBar.isShown()) {
			progressBar.setVisibility(View.GONE);
			Log.d("RituNavi", progressBar.isShown() + "");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.btn_sp_util:
			startActivity(new Intent(MainTestActivity.this,SPUtilActivity.class));
			break;
			
		case R.id.btn_db_util:
			startActivity(new Intent(MainTestActivity.this,DBUtilActivity.class));
			break;
			
		case R.id.btn_video_play:
			startActivity(new Intent(MainTestActivity.this,VideoActivity.class));
			break;
			
		case R.id.btn_music_play:
			startActivity(new Intent(MainTestActivity.this,MyMusicActivity.class));
			break;
			
		case R.id.btn_service_demo:
            startActivity(new Intent(MainTestActivity.this,MyServiceActivity.class));
            break;
            
		case R.id.btn_bt_contact:
		    List<BtContact> lists = getBtContact();
		    Intent intent = new Intent();
	        intent.setAction("txz_sync_btcontacts_action");
	        intent.putExtra("bt_contacts", (Serializable)lists);
	        sendBroadcast(intent);
	        break;

		case R.id.btn_test_fragment:
//		    TestFragment testFragment = new TestFragment();
//		    LogUtil.e(testFragment.showTestString());
//		    Toast.makeText(MainTestActivity.this,testFragment.showTestString(), Toast.LENGTH_SHORT).show();
		    startActivity(new Intent(MainTestActivity.this,FragmentTestActivity.class));
		    break;
		    
		case R.id.btn_random_ids:
            startActivity(new Intent(MainTestActivity.this,RandomIdsActivity.class));
            break;
            
		case R.id.btn_file_test:
            startActivity(new Intent(MainTestActivity.this,FileTestActivity.class));
            break;
            
		case R.id.btn_frag_broadcast:
            Intent intent2 = new Intent();
            intent2.setAction("change_to_fragment_one_action");
            sendBroadcast(intent2);
            break;
            
		case R.id.btn_afile_system:
		    startActivity(new Intent(MainTestActivity.this,AndroidFileSystemTest.class));
            break;
            
		case R.id.btn_window_params:
            startActivity(new Intent(MainTestActivity.this,WindowParamsActivity.class));
            break;
            
        default:
			break;
		}
	}
	private ArrayList<BtContact> getBtContact(){
	    ArrayList<BtContact> lst = new ArrayList<BtContact>();
	    BtContact con = new BtContact();
	    con.setName("张三");
        con.setNumber("30001");
        lst.add(con);
        con = new BtContact();
        con.setName("张三");
        con.setNumber("30002");
        lst.add(con);
        con = new BtContact();
        con.setName("张三");
        con.setNumber("30003");
        lst.add(con);
        con = new BtContact();
        con.setName("章三");
        con.setNumber("30100");
        lst.add(con);
        return lst;
	}
}
