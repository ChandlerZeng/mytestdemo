package com.example.mytestdemo.main;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import cn.ritu.bluephone.bean.BtContact;

import com.example.mytestdemo.CanvasActivity;
import com.example.mytestdemo.CustomWidgetActivity;
import com.example.mytestdemo.DimBarcodeActivity;
import com.example.mytestdemo.DragViewActivity;
import com.example.mytestdemo.FileCopyActivity;
import com.example.mytestdemo.MyTestApplication;
import com.example.mytestdemo.R;
import com.example.mytestdemo.ScrollPositionActivity;
import com.example.mytestdemo.SwipeListViewActivity;
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
import com.example.mytestdemo.widget.SwipeListView;
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
	private Button btnFileCopy;
	private Button btnBarcode;
	private Button btnDrawView;
	private Button btnScroll;
	private Button btnPauseMusic;
	private Button btnCustomWidget;
	private Button btnTxzOn;
	private Button btnTxzOff;
	private Button btnContextMenu;
	private Button btnSwipeList;
	private Button btnDragView;
	
	private Spinner spinnerMap;

	boolean first = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_ddt);
		init();
		DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        float density = metric.density;  // ��Ļ�ܶȣ�0.75 / 1.0 / 1.5��
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
		btnFileCopy = (Button) findViewById(R.id.btn_file_copy);
		btnBarcode = (Button) findViewById(R.id.btn_barcode);
		btnDrawView = (Button) findViewById(R.id.btn_draw_view);
		btnScroll = (Button) findViewById(R.id.scroll_position);
		btnPauseMusic = (Button) findViewById(R.id.btn_pause_music);
		btnCustomWidget = (Button) findViewById(R.id.btn_custom_widget);
		btnTxzOn = (Button) findViewById(R.id.switch_on_txz);
		btnTxzOff = (Button) findViewById(R.id.switch_off_txz);
		btnContextMenu = (Button) findViewById(R.id.btn_context_menu);
		btnSwipeList = (Button) findViewById(R.id.btn_swipe_list);
		btnDragView = (Button) findViewById(R.id.btn_drag_view);
		
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
		btnFileCopy.setOnClickListener(this);
		btnBarcode.setOnClickListener(this);
		btnDrawView.setOnClickListener(this);
		btnScroll.setOnClickListener(this);
		btnPauseMusic.setOnClickListener(this);
		btnCustomWidget.setOnClickListener(this);
		btnTxzOn.setOnClickListener(this);
		btnTxzOff.setOnClickListener(this);
		btnContextMenu.setOnClickListener(this);
		btnSwipeList.setOnClickListener(this);
		btnDragView.setOnClickListener(this);

		spinnerMap.setSelection(getSelectedMap());
//		NaviToolInterface.getInstance().setMaps(getSelectedMap());

		btnTxzStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					 showProgress();
					if (TXZConfigManager.getInstance().isInitedSuccess()) {
						Toast.makeText(MainTestActivity.this, "ͬ���������ѳ�ʼ��",
								Toast.LENGTH_SHORT).show();
						 dismissProgress();
					} else {
						TXZTestInterface.getInstance().Init(
								MyTestApplication.getInstance()
										.getApplicationContext());
						Log.d("TXZ TEST", "ͬ����������ʼ��");
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
						Log.d("TXZ TEST", "ͬ���������ر�");
						Toast.makeText(MainTestActivity.this, "ͬ���������ر�",
								Toast.LENGTH_SHORT).show();
						
					} else {
						Toast.makeText(MainTestActivity.this, "ͬ��������δ��ʼ��",
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
								Log.d("RituNavi", "ͬ������������");
								Toast.makeText(MainTestActivity.this,
										"ͬ������������", Toast.LENGTH_SHORT).show();
								// showProgress();
							}
						});
					} else {
						Toast.makeText(MainTestActivity.this, "ͬ��������δ��ʼ��",
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
						Log.d("TXZ TEST", "ͬ������������");
						Toast.makeText(MainTestActivity.this, "ͬ������������",
								Toast.LENGTH_SHORT).show();
						// showProgress();
					} else {
						Toast.makeText(MainTestActivity.this, "ͬ��������δ��ʼ��",
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
				Toast.makeText(MainTestActivity.this, "ͬ��������ָ���", Toast.LENGTH_SHORT).show();
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
		    File dir12=Environment.getExternalStorageDirectory();  
		      if(dir12!=null){
		    	  Log.e("RituNavi","Environment.getExternalStorageDirectory()="+dir12.toString()); 
		      }else{
		    	  Log.e("RituNavi","Environment.getExternalStorageDirectory()=null"); 
		      }
		      
            break;
            
		case R.id.btn_window_params:
            startActivity(new Intent(MainTestActivity.this,WindowParamsActivity.class));
            break;
            
		case R.id.btn_file_copy:
            startActivity(new Intent(MainTestActivity.this,FileCopyActivity.class));
            break;
            
		case R.id.btn_barcode:
		    startActivity(new Intent(MainTestActivity.this,DimBarcodeActivity.class));
		    break;
		    
		case R.id.btn_draw_view:
		    startActivity(new Intent(MainTestActivity.this,CanvasActivity.class));
		    break;
		    
		case R.id.scroll_position:
		    startActivity(new Intent(MainTestActivity.this,ScrollPositionActivity.class));
		    break;
		    
		case R.id.btn_pause_music:
//		    pauseMusic();
		    sendMediaButton(getApplicationContext(),KeyEvent.KEYCODE_MEDIA_STOP );
		    break;
		    
		case R.id.btn_custom_widget:
		    startActivity(new Intent(MainTestActivity.this,CustomWidgetActivity.class));
            break;
            
		case R.id.switch_on_txz:
		    Intent intent3= new Intent();
            intent3.setAction("android.intent.action.ACC_ON");
            sendBroadcast(intent3);
            break;
            
		case R.id.switch_off_txz:
		    Intent intent4= new Intent();
            intent4.setAction("android.intent.action.ACC_OVER");
            sendBroadcast(intent4);
            break;
            
		case R.id.btn_context_menu:
            Intent intent5= new Intent();
            intent5.setAction("com.android.action.DDT_SWIPE_FROM_LEFT");
            intent5.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            sendBroadcast(intent5);
            break;
            
		case R.id.btn_swipe_list:
            startActivity(new Intent(MainTestActivity.this,SwipeListViewActivity.class));
            break;
		case R.id.btn_drag_view:
		    startActivity(new Intent(MainTestActivity.this,DragViewActivity.class));
		    break;
            
        default:
			break;
		}
	}
	private ArrayList<BtContact> getBtContact(){
	    ArrayList<BtContact> lst = new ArrayList<BtContact>();
	    BtContact con = new BtContact();
	    con.setName("����");
        con.setNumber("30001");
        lst.add(con);
        con = new BtContact();
        con.setName("����");
        con.setNumber("30002");
        lst.add(con);
        con = new BtContact();
        con.setName("����");
        con.setNumber("30003");
        lst.add(con);
        con = new BtContact();
        con.setName("����");
        con.setNumber("30100");
        lst.add(con);
        return lst;
	}
	
	private void pauseMusic(){
	    Intent freshIntent = new Intent();  
	    freshIntent.setAction("com.android.music.musicservicecommand.pause");  
	    freshIntent.putExtra("command", "pause");  
	    sendBroadcast(freshIntent);  
	    LogUtil.d("com.android.music.musicservicecommand.pause");
	}
	
	public static void sendMediaButton(Context context, int keyCode) { 
	    AudioManager audioManager= (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); 
	    //���жϺ�̨�Ƿ��ٲ������� 
	    if (audioManager.isMusicActive()){ 
	    KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode); 
	    Intent intent = new Intent(Intent.ACTION_MEDIA_BUTTON); 
	    intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent); 
	    context.sendOrderedBroadcast(intent,null);
	    LogUtil.d("sendMediaButton0 KeyEvent.KEYCODE_MEDIA_STOP");
	    keyEvent = new KeyEvent(KeyEvent.ACTION_UP, keyCode);
        intent = new Intent(Intent.ACTION_MEDIA_BUTTON);
        intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
        context.sendOrderedBroadcast(intent,null);
        LogUtil.d("sendMediaButton1 KeyEvent.KEYCODE_MEDIA_STOP");
	    }
	}	   	
	
}
