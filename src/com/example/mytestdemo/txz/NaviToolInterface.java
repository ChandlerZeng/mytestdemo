package com.example.mytestdemo.txz;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mytestdemo.MyTestApplication;
import com.txznet.sdk.TXZNavManager;
import com.txznet.sdk.TXZNavManager.NavToolStatusListener;
import com.txznet.sdk.TXZNavManager.NavToolType;
import com.txznet.sdk.bean.Poi;

/**
 * @author zengcq
 * @date 2016年11月24日
 * @version 1.0
 */
public class NaviToolInterface implements TXZNavManager.NavTool {
	public static final String ACTION_UNISOUND_NAME = "android.intent.action.ritu.keyword.name";
	public static final String KEY_NAVI_KEYWORD_NAME = "navi_keyword_name";

	public static final int MAP_TYPE_DDT = 0;
	public static final int MAP_TYPE_BAIDU_NAV = 1;
	public static final int MAP_TYPE_GAODE_NAV = 2;

	private static NaviToolInterface instance;

	public static NaviToolInterface getInstance() {
		if (instance == null) {
			instance = new NaviToolInterface();
		}
		return instance;
	}

	@Override
	public void enterNav() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ComponentName component = new ComponentName("cn.ritu.rtnavi",
				"cn.ritu.rtnavi.main.MainActivity");
		intent.setComponent(component);
		Log.d("RituNavi", "enterNav");
		if (intent.resolveActivity(MyTestApplication.getInstance()
				.getPackageManager()) != null) {
			MyTestApplication.getInstance().getApplicationContext()
					.startActivity(intent);
		}
		Log.d("RituNavi", "enterNav already");

	}

	@Override
	public void exitNav() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInNav() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void navCompany() {
		// TODO Auto-generated method stub

	}

	@Override
	public void navHome() {
		// TODO Auto-generated method stub

	}

	@Override
	public void navToLoc(Poi position) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setAction(ACTION_UNISOUND_NAME);
		Bundle bundle = new Bundle();
		String lngString = doubleToLongString(position.getLng());
		String latString = doubleToLongString(position.getLat());
		String dstInfo = lngString + "," + latString + "," + position.getName();
		bundle.putString(KEY_NAVI_KEYWORD_NAME, dstInfo);
		intent.putExtras(bundle);
		MyTestApplication.getInstance().getApplicationContext()
				.sendBroadcast(intent);
		Log.d("RituNavi", position.toString());
		Log.d("RituNavi", dstInfo.toString());
	}

	@Override
	public void setStatusListener(NavToolStatusListener arg0) {
		// TODO Auto-generated method stub

	}

	private String doubleToLongString(double d) {
		// 云知声协议
		// 乘以 3600 * 2560 的原因见 UnisoundProtocol.java 中的注释
		long l = (long) (d * (3600 * 2560));
		String lStr = String.valueOf(l);
		return lStr;
	}

	public void setMaps(int mapType) {
		switch (mapType) {

		case MAP_TYPE_DDT:
			TXZNavManager.getInstance().setNavTool(
					NaviToolInterface.getInstance());
			Log.d("RituNavi", MAP_TYPE_DDT + "");
			break;

		case MAP_TYPE_BAIDU_NAV:
			TXZNavManager.getInstance().setNavTool(
					NavToolType.NAV_TOOL_BAIDU_MAP);
			Log.d("RituNavi", MAP_TYPE_BAIDU_NAV + "");
			break;

		case MAP_TYPE_GAODE_NAV:
			TXZNavManager.getInstance().setNavTool(
					NavToolType.NAV_TOOL_GAODE_MAP);
			Log.d("RituNavi", MAP_TYPE_GAODE_NAV + "");
			break;

		}
	}

}
