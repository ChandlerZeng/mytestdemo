package com.example.mytestdemo.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.mytestdemo.R;
import com.example.mytestdemo.R.id;
import com.example.mytestdemo.R.layout;
import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.entity.Person;

public class ContentProviderActivity extends BaseActivity implements
		OnClickListener {
	private EditText nameET;
	private EditText numberET;
	private Button insertBtn;
	private Button deleteBtn;
	private Button queryBtn;
	private ListView contentView;

	// [content://com.android.contacts/raw_contacts]
	private static final Uri RAW_CONTACTS_URI = ContactsContract.RawContacts.CONTENT_URI;
	// [content://com.android.contacts/data]
	private static final Uri DATA_URI = ContactsContract.Data.CONTENT_URI;

	private static final String ACCOUNT_TYPE = ContactsContract.RawContacts.ACCOUNT_TYPE;
	private static final String ACCOUNT_NAME = ContactsContract.RawContacts.ACCOUNT_NAME;

	private static final String RAW_CONTACT_ID = ContactsContract.Data.RAW_CONTACT_ID;
	private static final String MIMETYPE = ContactsContract.Data.MIMETYPE;

	private static final String NAME_ITEM_TYPE = ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE;
	private static final String DISPLAY_NAME = ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME;

	private static final String PHONE_ITEM_TYPE = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE;
	private static final String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
	private static final String PHONE_TYPE = ContactsContract.CommonDataKinds.Phone.TYPE;
	private static final int PHONE_TYPE_HOME = ContactsContract.CommonDataKinds.Phone.TYPE_HOME;
	private static final int PHONE_TYPE_MOBILE = ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;

	private static final String EMAIL_ITEM_TYPE = ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE;
	private static final String EMAIL_DATA = ContactsContract.CommonDataKinds.Email.DATA;
	private static final String EMAIL_TYPE = ContactsContract.CommonDataKinds.Email.TYPE;
	private static final int EMAIL_TYPE_HOME = ContactsContract.CommonDataKinds.Email.TYPE_HOME;
	private static final int EMAIL_TYPE_WORK = ContactsContract.CommonDataKinds.Email.TYPE_WORK;

	private static final String AUTHORITY = ContactsContract.AUTHORITY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_provider);
		init();
	}

	private void init() {
		nameET = (EditText) findViewById(R.id.editText1);
		numberET = (EditText) findViewById(R.id.editText2);
		insertBtn = (Button) findViewById(R.id.buttonInsert);
		deleteBtn = (Button) findViewById(R.id.buttonDelete);
		queryBtn = (Button) findViewById(R.id.buttonQuery);
		// ������ʾ����
		contentView = (ListView) findViewById(R.id.listView1);
		insertBtn.setOnClickListener(this);
		deleteBtn.setOnClickListener(this);
		queryBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String name = nameET.getText().toString();
		String number = numberET.getText().toString();
		Person p = new Person(name, number);
		switch (v.getId()) {

		case R.id.buttonInsert:
			Log.d("RituNavi", "" + p.getName() + p.getNumber());
			try {
				insert(p);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OperationApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view();
			break;

		case R.id.buttonDelete:
			Log.d("RituNavi", "" + p.getName() + p.getNumber());
			delete(name);
			view();
			break;

		case R.id.buttonQuery:
			Log.d("RituNavi", "" + p.getName() + p.getNumber());
			view();
			break;

		default:
			break;
		}
	}

	// ��ʾ����
	private void view() {
		Cursor c = query("");
		Log.d("RituNavi", "" + c.getColumnNames());
		@SuppressWarnings("deprecation")
		ListAdapter listAdapter = new SimpleCursorAdapter(this,
				R.layout.listview_content_provider, c, new String[] {
				RAW_CONTACT_ID, DISPLAY_NAME, PHONE_NUMBER }, new int[] {
						R.id.id, R.id.name, R.id.number });
		contentView.setAdapter(listAdapter);
	}

	// ������ϵ��
	private void insert(Person p) throws RemoteException,
			OperationApplicationException {
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();

		ContentProviderOperation operation = ContentProviderOperation
				.newInsert(RAW_CONTACTS_URI).withValue(ACCOUNT_TYPE, null)
				.withValue(ACCOUNT_NAME, null).build();
		operations.add(operation);

		// �����ϵ�����Ʋ���
		operation = ContentProviderOperation.newInsert(DATA_URI)
				.withValueBackReference(RAW_CONTACT_ID, 0)
				.withValue(MIMETYPE, NAME_ITEM_TYPE)
				.withValue(DISPLAY_NAME, p.getName()).build();
		operations.add(operation);

		// ��Ӽ�ͥ��������
		operation = ContentProviderOperation.newInsert(DATA_URI)
				.withValueBackReference(RAW_CONTACT_ID, 0)
				.withValue(MIMETYPE, PHONE_ITEM_TYPE)
				.withValue(PHONE_TYPE, PHONE_TYPE_HOME)
				.withValue(PHONE_NUMBER, p.getNumber()).build();
		operations.add(operation);

		// ����ƶ��ֻ�����
		operation = ContentProviderOperation.newInsert(DATA_URI)
				.withValueBackReference(RAW_CONTACT_ID, 0)
				.withValue(MIMETYPE, PHONE_ITEM_TYPE)
				.withValue(PHONE_TYPE, PHONE_TYPE_MOBILE)
				.withValue(PHONE_NUMBER, p.getNumber()).build();
		operations.add(operation);

		// ��Ӽ�ͥ����
		operation = ContentProviderOperation.newInsert(DATA_URI)
				.withValueBackReference(RAW_CONTACT_ID, 0)
				.withValue(MIMETYPE, EMAIL_ITEM_TYPE)
				.withValue(EMAIL_TYPE, EMAIL_TYPE_HOME)
				.withValue(EMAIL_DATA, "zcq@android.com").build();
		operations.add(operation);

		// ��ӹ�������
		operation = ContentProviderOperation.newInsert(DATA_URI)
				.withValueBackReference(RAW_CONTACT_ID, 0)
				.withValue(MIMETYPE, EMAIL_ITEM_TYPE)
				.withValue(EMAIL_TYPE, EMAIL_TYPE_WORK)
				.withValue(EMAIL_DATA, "zcq@msapple.com").build();
		operations.add(operation);

		ContentResolver resolver = this.getContentResolver();
		// ����ִ��,����ִ�н����
		ContentProviderResult[] results = resolver.applyBatch(AUTHORITY,
				operations);
		for (ContentProviderResult result : results) {
			Log.d("RituNavi", result.uri.toString());
		}
	}

	// ������ϵ��
	private void delete(String name) {
		// ���ContentResolver����
		ContentResolver cr = getContentResolver();
		Uri url = ContactsContract.Contacts.CONTENT_URI;
		// ����ɾ������
		String where = ContactsContract.Contacts.DISPLAY_NAME + "=?";
		String[] selectionArgs = { name };
		cr.delete(url, where, selectionArgs);
	}

	// ��ѯ��ϵ��
	private Cursor query(String name) {
		// ���ContentResolver����
		ContentResolver cr = getContentResolver();
		Uri uri = ContactsContract.Contacts.CONTENT_URI;
		// ��ѯ����
		String[] projection = { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };
		// ���ò�ѯ�����������Ұ�selection��selectionArgs��������Ϊnull����ʾ��ѯȫ������
		String selection = null;
		String[] selectionArgs = null;
		if (!"".equals(name)) {
			selection = ContactsContract.Contacts.DISPLAY_NAME + "=?";
			selectionArgs = new String[] { name };
		}
		// ������������
		String sortOrder = ContactsContract.Contacts._ID;
		Cursor c = cr.query(uri, projection, selection, selectionArgs,
				sortOrder);
		// if (c.moveToFirst()) {
		// for (int i = 0; i < c.getCount(); i++) {
		// c.moveToPosition(i);
		// String name = c.getString(c.getColumnIndexOrThrow(People.NAME));
		// String number = c.getString(c
		// .getColumnIndexOrThrow(People.NUMBER));
		// }
		// }
		return c;
	}

	private StringBuilder getContacts() {
		// StringBuilderΪ���̰߳�ȫ�ģ�StringBufferΪ�̰߳�ȫ�ģ���Ϊͬ���ģ�
		// StringBuilderЧ�ʸ���StringBuffer,�ʺϵ��߳�ʹ��

		StringBuilder sbLog = new StringBuilder();
		// �õ�ContentResolver����
		ContentResolver cr = this.getContentResolver();
		// ȡ�õ绰���п�ʼһ��Ĺ��,��Ҫ���ǲ�ѯ"contacts"��
		Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);
		if (!cursor.moveToFirst()) {
			sbLog.append("��ȡ����Ϊ�գ�");
			return sbLog;
		}
		if (cursor.moveToFirst()) {

			// ȡ����ϵ������ (��ʾ����������)��ʵ�������� ContactsContract.Contacts��
			int nameIndex = cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
			String name = cursor.getString(nameIndex);
			sbLog.append("name=" + name + ";");

			// ȡ����ϵ��ID
			String contactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));

			// ������ϵ��ID��ѯ��Ӧ�ĵ绰����
			Cursor phoneNumbers = cr.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
							+ contactId, null, null);
			// ȡ�õ绰����(���ܴ��ڶ������)
			while (phoneNumbers.moveToNext()) {
				String strPhoneNumber = phoneNumbers
						.getString(phoneNumbers
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				sbLog.append("Phone=" + strPhoneNumber + ";");
			}
			phoneNumbers.close();

			// ������ϵ��ID��ѯ��Ӧ��email
			Cursor emails = cr.query(
					ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = "
							+ contactId, null, null);
			// ȡ��email(���ܴ��ڶ��email)
			while (emails.moveToNext()) {
				String strEmail = emails
						.getString(emails
								.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
				sbLog.append("Email=" + strEmail + ";");
			}
			emails.close();

		}
		cursor.close();
		Log.e("-------------------", sbLog.toString());
		return sbLog;
	}
}
