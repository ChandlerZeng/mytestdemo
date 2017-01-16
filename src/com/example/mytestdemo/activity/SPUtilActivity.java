package com.example.mytestdemo.activity;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytestdemo.R;
import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.utils.SharedPreferencesUtil;

public class SPUtilActivity extends BaseActivity implements OnClickListener {

	private TextView textView;
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private EditText editText4;
	private Button saveButton;
	private Button clearOneButton;
	private Button clearAllButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sputil);
		init();
	}

	private void init() {
		textView = (TextView) findViewById(R.id.textView1);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		saveButton = (Button) findViewById(R.id.button1);
		clearOneButton = (Button) findViewById(R.id.button2);
		clearAllButton = (Button) findViewById(R.id.button3);
		saveButton.setOnClickListener(this);
		clearOneButton.setOnClickListener(this);
		clearAllButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			saveData();
			break;

		case R.id.button2:
			SharedPreferencesUtil.remove(SPUtilActivity.this,"name");
			SharedPreferencesUtil.remove(SPUtilActivity.this,"name","config2");
			editText1.setText("");
			Toast.makeText(SPUtilActivity.this, "clear name succeed!",
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.button3:
			SharedPreferencesUtil.clear(SPUtilActivity.this);
			editText1.setText("");
			editText2.setText("");
			editText3.setText("");
			editText4.setText("");
			Toast.makeText(SPUtilActivity.this, "clear all data succeed!",
					Toast.LENGTH_SHORT).show();
			break;
			
		default:
			break;
		}
	}

	private void saveData() {
		String s1 = editText1.getText().toString();
		String s2 = editText2.getText().toString();
		String s3 = editText3.getText().toString();
		String s4 = editText4.getText().toString();
		if (s1.equals("")) {
			Toast.makeText(SPUtilActivity.this, "姓名不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (s2.equals("")) {
			Toast.makeText(SPUtilActivity.this, "密码不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (s3.equals("")) {
			Toast.makeText(SPUtilActivity.this, "金钱不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (s4.equals("")) {
			Toast.makeText(SPUtilActivity.this, "是否富有不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (!(s4.equals("true") || s4.equals("false"))) {
			Toast.makeText(SPUtilActivity.this, "是否富有数据类型有误" + s4,
					Toast.LENGTH_SHORT).show();
			return;
		}

		int password = Integer.parseInt(s2);
		float money = Float.parseFloat(s3);
		boolean isRich = Boolean.parseBoolean(s4);

		SharedPreferencesUtil.put(SPUtilActivity.this, "name", s1);
		SharedPreferencesUtil.put(SPUtilActivity.this, "password", password);
		SharedPreferencesUtil.put(SPUtilActivity.this, "money", money);
		SharedPreferencesUtil.put(SPUtilActivity.this, "isRich", isRich);

		SharedPreferencesUtil.put(SPUtilActivity.this, "name2", s1,
				Context.MODE_APPEND);
		SharedPreferencesUtil.put(SPUtilActivity.this, "password2", password,
				Context.MODE_APPEND);
		SharedPreferencesUtil.put(SPUtilActivity.this, "money2", money,
				Context.MODE_APPEND);
		SharedPreferencesUtil.put(SPUtilActivity.this, "isRich2", isRich,
				Context.MODE_APPEND);

		SharedPreferencesUtil.put(SPUtilActivity.this, "name2", s1, "config2");
		SharedPreferencesUtil.put(SPUtilActivity.this, "password2", password,
				"config2");
		SharedPreferencesUtil.put(SPUtilActivity.this, "money2", money,
				"config2");
		SharedPreferencesUtil.put(SPUtilActivity.this, "isRich2", isRich,
				"config2");
		Toast.makeText(SPUtilActivity.this, "saving data succeed!",
				Toast.LENGTH_SHORT).show();

		Map<String, ?> map = SharedPreferencesUtil.getAll(SPUtilActivity.this);
		textView.setText("name:" + map.get("name") + "password:"
				+ map.get("password") + "money:" + map.get("money") + "isRich:"
				+ map.get("isRich"));

	}
}
