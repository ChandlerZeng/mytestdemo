package com.example.mytestdemo.fragment;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.ritu.bluephone.bean.BtContact;
import cn.ritu.bluephone.bean.CallRecord;

import com.example.mytestdemo.R;
import com.example.mytestdemo.adapter.CallRecordListAdapter;
import com.example.mytestdemo.adapter.PbAdapter;
import com.example.mytestdemo.adapter.PbAdapter.OnLetterChangedListener;
import com.example.mytestdemo.database.DemoDBManager;
import com.example.mytestdemo.utils.DateUtil;
import com.example.mytestdemo.utils.LogUtil;
import com.example.mytestdemo.utils.PinyinComparator;
import com.example.mytestdemo.widget.SideBar;
import com.example.mytestdemo.widget.SideBar.OnTouchingLetterChangedListener;

/**
 * @author Zengcq
 * @date 2017年1月13日
 * @version 1.0
 * @description
 */
public class FragmentThree extends Fragment implements OnClickListener,IDialPannelView{

    private static final int PHONE_LAYOUT = 11;
    private static final int CALL_RECORD_LAYOUT = 12;
    private static final int CONTACT_BOOK_LAYOUT = 13;

    private DialPannel dialPannel;

    private FrameLayout content;
    private LinearLayout callRecordLayout;
    private LinearLayout contactBookLayout;
    private int curLayout = PHONE_LAYOUT;
    private LinearLayout linearLayout1, linearLayout2, linearLayout3,deleteDialLayout,searchLayout;
    private ImageView imageview1, imageview2, imageview3;
    private TextView textView1, textView2, textView3, dialNumTextView,dialog;
    private Button dialNum[] = new Button[12];
    private ListView callRecordLV;
    private ListView pbListView;
    private SideBar sideBar;
    private EditText editText;
    private ImageButton dialImageButton;
    
    private CallRecordListAdapter mRecordAdapter; 
    private PbAdapter pbAdapter;
    private List<BtContact> listContacts;
    private List<CallRecord> listCallRecord;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        LogUtil.e("FragmentThree onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        LogUtil.e("FragmentThree onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_bt_phone, container,
                false);
        LogUtil.e("FragmentThree onCreateView");

        init(inflater, view);

        return view;
    }

    private void init(LayoutInflater inflater, View view) {
        dialPannel = new DialPannel(this);
        content = (FrameLayout) view.findViewById(R.id.content);
        callRecordLayout = (LinearLayout) inflater.inflate(
                R.layout.bt_phone_call_record_layout, null);
        contactBookLayout = (LinearLayout) inflater.inflate(
                R.layout.bt_phone_contact_book_layout, null);
        curLayout = PHONE_LAYOUT;

        linearLayout1 = (LinearLayout) view.findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout2);
        linearLayout3 = (LinearLayout) view.findViewById(R.id.linearLayout3);
        deleteDialLayout = (LinearLayout) view.findViewById(R.id.img_delete_bt);
        
        imageview1 = (ImageView) view.findViewById(R.id.imageview1);
        imageview2 = (ImageView) view.findViewById(R.id.imageview2);
        imageview3 = (ImageView) view.findViewById(R.id.imageview3);
        dialImageButton = (ImageButton) view.findViewById(R.id.dial_btn);
        textView1 = (TextView) view.findViewById(R.id.textview1);
        textView2 = (TextView) view.findViewById(R.id.textview2);
        textView3 = (TextView) view.findViewById(R.id.textview3);
        dialNumTextView = (TextView) view.findViewById(R.id.dial_num_text);
        

        dialNum[0] = (Button) view.findViewById(R.id.bt_dial_0);
        dialNum[1] = (Button) view.findViewById(R.id.bt_dial_1);
        dialNum[2] = (Button) view.findViewById(R.id.bt_dial_2);
        dialNum[3] = (Button) view.findViewById(R.id.bt_dial_3);
        dialNum[4] = (Button) view.findViewById(R.id.bt_dial_4);
        dialNum[5] = (Button) view.findViewById(R.id.bt_dial_5);
        dialNum[6] = (Button) view.findViewById(R.id.bt_dial_6);
        dialNum[7] = (Button) view.findViewById(R.id.bt_dial_7);
        dialNum[8] = (Button) view.findViewById(R.id.bt_dial_8);
        dialNum[9] = (Button) view.findViewById(R.id.bt_dial_9);
        dialNum[10] = (Button) view.findViewById(R.id.bt_dial_star);
        dialNum[11] = (Button) view.findViewById(R.id.bt_dial_pound);
        
        dialNum[0].setOnClickListener(dialNumClickListener);
        dialNum[1].setOnClickListener(dialNumClickListener);
        dialNum[2].setOnClickListener(dialNumClickListener);
        dialNum[3].setOnClickListener(dialNumClickListener);
        dialNum[4].setOnClickListener(dialNumClickListener);
        dialNum[5].setOnClickListener(dialNumClickListener);
        dialNum[6].setOnClickListener(dialNumClickListener);
        dialNum[7].setOnClickListener(dialNumClickListener);
        dialNum[8].setOnClickListener(dialNumClickListener);       
        dialNum[9].setOnClickListener(dialNumClickListener);
        dialNum[10].setOnClickListener(dialNumClickListener);
        dialNum[11].setOnClickListener(dialNumClickListener);
        
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        deleteDialLayout.setOnClickListener(this);
        dialImageButton.setOnClickListener(this);
        
        deleteDialLayout.setOnLongClickListener(new OnLongClickListener() {
            
            @Override
            public boolean onLongClick(View v) {
                dialPannel.deleteAllBtnum();
                return true;
            }
        });

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        LogUtil.e("FragmentThree onDestroy");
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        LogUtil.e("FragmentThree onDestroyView");
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        super.onDetach();
        LogUtil.e("FragmentThree onDetach");
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        LogUtil.e("FragmentThree onPause");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        LogUtil.e("FragmentThree onResume");
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        LogUtil.e("FragmentThree onStart");
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        LogUtil.e("FragmentThree onStop");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        LogUtil.e("FragmentThree onViewCreated");
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.linearLayout1:
            switchLayout(PHONE_LAYOUT);
            break;

        case R.id.linearLayout2:
            switchLayout(CALL_RECORD_LAYOUT);
            break;

        case R.id.linearLayout3:
            switchLayout(CONTACT_BOOK_LAYOUT);
            break;
            
        case R.id.img_delete_bt:
            dialPannel.deleteBtnum();
            break;
            
        case R.id.dial_btn:
            String number = dialPannel.getBtNum();
            if(number == null || number.equals("")){
                Toast.makeText(getActivity(), "电话号码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            makeCall(number, "2");
            break;

        default:
            break;
        }
    }

    private OnClickListener dialNumClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.bt_dial_0:
                dialPannel.inputBtNum(0);
                break;
                
            case R.id.bt_dial_1:
                dialPannel.inputBtNum(1);
                break;
                
            case R.id.bt_dial_2:
                dialPannel.inputBtNum(2);
                break;
                
            case R.id.bt_dial_3:
                dialPannel.inputBtNum(3);
                break;
                
            case R.id.bt_dial_4:
                dialPannel.inputBtNum(4);
                break;
                
            case R.id.bt_dial_5:
                dialPannel.inputBtNum(5);
                break;
                
            case R.id.bt_dial_6:
                dialPannel.inputBtNum(6);
                break;
                
            case R.id.bt_dial_7:
                dialPannel.inputBtNum(7);
                break;
                
            case R.id.bt_dial_8:
                dialPannel.inputBtNum(8);
                break;
                
            case R.id.bt_dial_9:
                dialPannel.inputBtNum(9);
                break;
                
            case R.id.bt_dial_star:
                dialPannel.inputBtNum(10);
                break;
                
            case R.id.bt_dial_pound:
                dialPannel.inputBtNum(11);
                break;
            }

        }
    };

    public void switchLayout(int type) {
        if (curLayout == type) {
            return;
        }
        imageview1.setImageResource(R.drawable.dial_keyboard_white);
        imageview2.setImageResource(R.drawable.call_record_white);
        imageview3.setImageResource(R.drawable.contacts_white);

        if (isAdded()) {
            linearLayout1.setBackgroundColor(getResources().getColor(
                    R.color.left_button_unselect));
            linearLayout2.setBackgroundColor(getResources().getColor(
                    R.color.left_button_unselect));
            linearLayout3.setBackgroundColor(getResources().getColor(
                    R.color.left_button_unselect));

            textView1.setTextColor(getResources().getColor(R.color.white));
            textView2.setTextColor(getResources().getColor(R.color.white));
            textView3.setTextColor(getResources().getColor(R.color.white));

            switch (type) {
            case PHONE_LAYOUT:
                imageview1.setImageResource(R.drawable.dial_keyboard_blue);
                linearLayout1.setBackgroundColor(getResources().getColor(
                        R.color.left_button_select));
                textView1.setTextColor(getResources().getColor(R.color.blue));
                if (getCurLayout() != null) {
                    content.removeView(getCurLayout());
                }
                curLayout = PHONE_LAYOUT;
                break;
            case CALL_RECORD_LAYOUT:
                imageview2.setImageResource(R.drawable.call_record_blue);
                linearLayout2.setBackgroundColor(getResources().getColor(
                        R.color.left_button_select));
                textView2.setTextColor(getResources().getColor(R.color.blue));
                if (curLayout == CONTACT_BOOK_LAYOUT) {
                    content.removeView(contactBookLayout);
                }
                content.addView(callRecordLayout);
                initCallLayout(callRecordLayout);
                curLayout = CALL_RECORD_LAYOUT;
                break;
            case CONTACT_BOOK_LAYOUT:
                imageview3.setImageResource(R.drawable.contacts_blue);
                linearLayout3.setBackgroundColor(getResources().getColor(
                        R.color.left_button_select));
                textView3.setTextColor(getResources().getColor(R.color.blue));
                if (curLayout == CALL_RECORD_LAYOUT) {
                    content.removeView(callRecordLayout);
                }
                content.addView(contactBookLayout);
                initContactLayout(contactBookLayout);
                curLayout = CONTACT_BOOK_LAYOUT;
                break;
            }
        }

    }

    private LinearLayout getCurLayout() {
        if (curLayout == CALL_RECORD_LAYOUT) {
            return callRecordLayout;
        } else if (curLayout == CONTACT_BOOK_LAYOUT) {
            return contactBookLayout;
        } else {
            return null;
        }
    }
    
    private void initCallLayout(View view){
        callRecordLV = (ListView) view.findViewById(R.id.call_record_list);
        listCallRecord = DemoDBManager.getInstance().getCallRecord();
        mRecordAdapter = new CallRecordListAdapter(getActivity(), listCallRecord);
        callRecordLV.setAdapter(mRecordAdapter);
        callRecordLV.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                // TODO Auto-generated method stub
                makeCall(listCallRecord.get(arg2).getNumber(), "3");
            }
        });
    }
    
    private void initContactLayout(View view){
        pbListView = (ListView) view.findViewById(R.id.pb_listview);
        dialog = (TextView) view.findViewById(R.id.dialog);
        sideBar = (SideBar) view.findViewById(R.id.sidebar);
        searchLayout = (LinearLayout) view.findViewById(R.id.search_layout);
        editText = (EditText)view.findViewById(R.id.search_edittext);
        listContacts = new ArrayList<BtContact>();
        listContacts = DemoDBManager.getInstance().getLoacalPhoneContacts(getActivity());
        PinyinComparator pinyinComparator = new PinyinComparator();
        Collections.sort(listContacts, pinyinComparator);
        pbAdapter = new PbAdapter(getActivity(), listContacts);
        pbListView.setAdapter(pbAdapter);
        sideBar.setTextView(dialog);
        pbListView.setFriction(ViewConfiguration.getScrollFriction() * 2);
        
        sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() 
        {
            @Override
            public void onTouchingLetterChanged(String s) 
            {
                int position = pbAdapter.getPositionForSection(s.charAt(0));
                if(position != -1)
                {
                    pbListView.setSelection(position);
                }
                
            }
        });
        
        pbAdapter.setLetterChange(new OnLetterChangedListener() 
        {
            @Override
            public void onLetterChanged(String s) 
            {
//                sideBar.setPosition(s);
            }
        });
        
        editText.setText("");
        searchLayout.setVisibility(View.VISIBLE);
        editText.addTextChangedListener(new TextWatcher() 
        {
            
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) 
            {
                
            }
            
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) 
            {
                searchLayout.setVisibility(View.GONE);
            }
            
            @Override
            public void afterTextChanged(Editable arg0) 
            {                
                listContacts = DemoDBManager.getInstance().getLoacalPhoneContactsByKey(getActivity(), arg0.toString());
                PinyinComparator pinyinComparator = new PinyinComparator();
                Collections.sort(listContacts, pinyinComparator);
                pbAdapter.updateListView(listContacts);
            }
        });
        
        pbListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                makeCall(listContacts.get(arg2).getNumber(),"1");                                
            }
        });
    }

    @Override
    public void setDialText(String num) {
        dialNumTextView.setText(num);
        
    }

    @Override
    public void numInvalidAlarm() {
        Toast.makeText(getActivity(), "输入号码无效",Toast.LENGTH_SHORT ).show();       
    }
    
    private void makeCall(String number,String type){
        Intent intent = new Intent(Intent.ACTION_CALL);  
        intent.setData(Uri.parse("tel:"+number));  
        LogUtil.i("tel:"+number);
        saveCallRecord(number, type);
        startActivity(intent);  
    }
    
    private void saveCallRecord(String number,String type){
        CallRecord callRecord = new CallRecord();
        long date = System.currentTimeMillis()/1000;
        callRecord.setNumber(number);
        callRecord.setDate(String.valueOf(date));
        callRecord.setType(type);
        String name = DemoDBManager.getInstance().getNameByNumber(number, getActivity());
        if(name!=null){
            callRecord.setName(name);
        }
        DemoDBManager.getInstance().saveCallRecord(callRecord);
        listCallRecord = DemoDBManager.getInstance().getCallRecord();
        if(mRecordAdapter!=null)
        mRecordAdapter.setCallRecords(listCallRecord);
    }

}
