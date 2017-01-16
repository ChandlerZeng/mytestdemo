package com.example.mytestdemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytestdemo.R;
import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.database.DemoDBManager;
import com.example.mytestdemo.entity.User;
import com.example.mytestdemo.widget.ListViewForScrollView;
import com.google.gson.Gson;

public class DBUtilActivity extends BaseActivity implements OnClickListener {

	private EditText editTextName1;
	private EditText editTextNick1;
	private EditText editTextAvatar1;
	private EditText editTextName2;
	private EditText editTextNick2;
	private EditText editTextAvatar2;
	private Button btnSaveList;
	private Button btnGetList;
	private Button btnDelete;
	private Button btnSave;
	private ListView listView;

	private List<User> userList;
	private DbUtilAdapter adapter;

	private String userName1;
	private String nick1;
	private String avatar1;
	private String userName2;
	private String nick2;
	private String avatar2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbutil);
		init(this);
	}
	
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		DemoDBManager.getInstance().closeDB();
	}



	private void init(Context context) {
		editTextName1 = (EditText) findViewById(R.id.editTextName);
		editTextNick1 = (EditText) findViewById(R.id.editTextNick);
		editTextAvatar1 = (EditText) findViewById(R.id.editTextAvatar);
		editTextName2 = (EditText) findViewById(R.id.editTextName2);
		editTextNick2 = (EditText) findViewById(R.id.editTextNick2);
		editTextAvatar2 = (EditText) findViewById(R.id.editTextAvatar2);
		btnSaveList = (Button) findViewById(R.id.btn_save_list);
		btnGetList = (Button) findViewById(R.id.btn_get_list);
		btnDelete = (Button) findViewById(R.id.btn_delete);
		btnSave = (Button) findViewById(R.id.btn_saveone);
		listView = (ListViewForScrollView) findViewById(R.id.listView1);

		userList = new ArrayList<User>();
		adapter = new DbUtilAdapter(context, userList);
		listView.setAdapter(adapter);

		btnSaveList.setOnClickListener(this);
		btnGetList.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnSave.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btn_save_list:
			saveContactList();
			Toast.makeText(DBUtilActivity.this, "btn_save_list", Toast.LENGTH_SHORT)
			.show();
			break;

		case R.id.btn_get_list:
			getContactList();
			Toast.makeText(DBUtilActivity.this, "btn_get_list", Toast.LENGTH_SHORT)
			.show();
			break;

		case R.id.btn_delete:
			deleteContact();
			Toast.makeText(DBUtilActivity.this, "btn_delete", Toast.LENGTH_SHORT)
			.show();
			break;

		case R.id.btn_saveone:
			saveContact();
			Toast.makeText(DBUtilActivity.this, "btn_saveone", Toast.LENGTH_SHORT)
			.show();
			break;

		default:

			break;
		}
	}

	private boolean isEditTextNotNull() {
		userName1 = editTextName1.getText().toString();
		nick1 = editTextNick1.getText().toString();
		avatar1 = editTextAvatar1.getText().toString();
		userName2 = editTextName2.getText().toString();
		nick2 = editTextNick2.getText().toString();
		avatar2 = editTextAvatar2.getText().toString();
		if (userName1.equals("")) {
			Toast.makeText(DBUtilActivity.this, "姓名1不能为空", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		if (nick1.equals("")) {
			Toast.makeText(DBUtilActivity.this, "昵称1不能为空", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		if (avatar1.equals("")) {
			Toast.makeText(DBUtilActivity.this, "头像1不能为空", Toast.LENGTH_SHORT)
					.show();
			return false;
		}

		return true;
	}

	private void saveContactList() {
		if (isEditTextNotNull()) {
			User user1 = new User(userName1, nick1, avatar1);
			userList.add(user1);
			if (!userName2.equals("")) {
				User user2 = new User(userName2, nick2, avatar2);
				userList.add(user2);
			}
			DemoDBManager.getInstance().saveContactList(userList);
		}
	}

	private void saveContact() {
		if (isEditTextNotNull()) {
			User user1 = new User(userName1, nick1, avatar1);
			userList.add(user1);
			DemoDBManager.getInstance().saveContactList(userList);
		}
	}

	private List<User> getContactList() {
		// return DemoDBManager.getInstance().getContactList();
		userList = DemoDBManager.getInstance().getContactLists();
//		listView.setAdapter(adapter);
//		adapter.notifyDataSetChanged();
		adapter.setData(userList);
		Gson gson = new Gson();
		String jsonString = gson.toJson(userList);
		Log.d("RituNavi", jsonString);
		return userList;
	}

	private void deleteContact() {
		DemoDBManager.getInstance().deleteContact(userName1);
	}

	class DbUtilAdapter extends BaseAdapter {

		private Context context;
		private List<User> userLists;

		public DbUtilAdapter(Context context, List<User> userList) {
			this.context = context;
			this.userLists = userList;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return userLists.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return userLists.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = View
						.inflate(context, R.layout.item_db_util, null);
				holder.name = (TextView) convertView
						.findViewById(R.id.textViewName);
				holder.nick = (TextView) convertView
						.findViewById(R.id.textViewNick);
				holder.avatar = (TextView) convertView
						.findViewById(R.id.textViewAvatar);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (userLists.get(position).getUserName() != null) {
				holder.name.setText(userLists.get(position).getUserName());
			}
			if (userLists.get(position).getNick() != null) {
				holder.nick.setText(userLists.get(position).getNick());
			}
			if (userLists.get(position).getAvatar() != null) {
				holder.avatar.setText(userLists.get(position).getAvatar());
			}

			return convertView;
		}
		
		public void setData(List<User> users){
		    this.userLists = users;
		    notifyDataSetChanged();
		}

	}

	private static class ViewHolder {
		TextView name;
		TextView nick;
		TextView avatar;
	}

}
