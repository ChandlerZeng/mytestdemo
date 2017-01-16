package com.example.mytestdemo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.mytestdemo.R;
import com.example.mytestdemo.R.id;
import com.example.mytestdemo.R.layout;
import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.database.DemoDBManager;
import com.example.mytestdemo.entity.RandomId;
import com.example.mytestdemo.javatest.RandomNum;
import com.example.mytestdemo.utils.LogUtil;

public class RandomIdsActivity extends BaseActivity implements OnClickListener {

    private static final int MSG_UPDATE = 0;
    private ListView listView;
    private EditText editText1, editText2;
    private Button btnRandomIds, btnRandomIds1, btnRandomIds10, btn_clear_all,btnRandomIdsNumber10;
    // private ArrayAdapter<String> adapter;
    private SimpleAdapter adapter;
    private List<Map<String, String>> listItems;
    private int nums, lens;
    
    private MyReceiver receiver;
    private boolean isOneIdGen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_ids);
        init();
        hideSoftKeybord();
    }

    private void init() {
        myRegisterReceiver();
        listView = (ListView) findViewById(R.id.random_ids_listView1);
        editText1 = (EditText) findViewById(R.id.editText_random_ids_1);
        editText2 = (EditText) findViewById(R.id.editText_random_ids_2);
        btnRandomIds = (Button) findViewById(R.id.btn_random_ids);
        btnRandomIds1 = (Button) findViewById(R.id.btn_random_ids_1);
        btnRandomIds10 = (Button) findViewById(R.id.btn_random_ids_10);
        btnRandomIdsNumber10 = (Button) findViewById(R.id.btn_random_ids_number_10);
        btn_clear_all = (Button) findViewById(R.id.btn_clear_all);
        setData(0);
        btnRandomIds.setOnClickListener(this);
        btnRandomIds1.setOnClickListener(this);
        btnRandomIds10.setOnClickListener(this);
        btnRandomIdsNumber10.setOnClickListener(this);
        btn_clear_all.setOnClickListener(this);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                    final int arg2, long arg3) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        RandomIdsActivity.this); // 先得到构造器
                builder.setTitle("提示"); // 设置标题
                builder.setMessage("确定删除该条ID号?"); // 设置内容
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() { // 设置确定按钮
                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                String id = listItems.get(arg2).get("id");
                                clearOneId(id);
                            }
                        });
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() { // 设置取消按钮
                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                dialog.dismiss();
                                Toast.makeText(RandomIdsActivity.this,
                                        "取消" + which, Toast.LENGTH_SHORT)
                                        .show();
                            }
                        });

                builder.create().show();

            }
        });
    }
    
    

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.btn_random_ids:
            if(!editTextIsNull())
            setData(50);
            break;

        case R.id.btn_random_ids_1:
            isOneIdGen = true;
            setData(1);
            break;

        case R.id.btn_random_ids_10:
            isOneIdGen = false;
            setData(10);
            break;
            
        case R.id.btn_random_ids_number_10:
            isOneIdGen = false;
            setData(11);
            break;

        case R.id.btn_clear_all:
            clearAll();
            break;

        default:

            break;
        }

    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            case MSG_UPDATE:
                updateData();
                break;

            default:
                break;
            }
        }
    };

    private void updateData() {
        cancelProgressDialog();
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    
    private boolean editTextIsNull(){
        String length = editText1.getText().toString();
        if (length.equals("")) {
            Toast.makeText(RandomIdsActivity.this, "请输入生成随机ID位数",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        String num = editText2.getText().toString();
        if (num.equals("")) {
            Toast.makeText(RandomIdsActivity.this, "请输入生成随机ID个数",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

         nums = Integer.parseInt(num);
         lens = Integer.parseInt(length);

        if (lens <= 0 || lens > 20) {
            Toast.makeText(RandomIdsActivity.this, "输入生成随机ID位数必须大于0或小于等于20",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        if (nums <= 0 || nums > 50) {
            Toast.makeText(RandomIdsActivity.this, "输入生成随机ID个数必须大于0或小于等于50",
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        if(nums==1){
            isOneIdGen = true;
        }else{
            isOneIdGen = false;
        }
        return false;
    }

    private List<String> generateRandomIds() {
      
        List<String> list = RandomNum.getRandomNums(nums, lens);
        return list;
    }

    // private void generateRandomIds1() {
    // List<String> list = RandomNum.getRandomNums1();
    // DemoDBManager.getInstance().saveRandomIds(list);
    // setData();
    // }
    //
    // private void generateRandomIds10() {
    // List<String> list = RandomNum.getRandomNums10();
    // DemoDBManager.getInstance().saveRandomIds(list);
    // setData();
    // }

    private void setData(final int type) {
        if(type!=0){
            showProgressDialog("生成随机ID中..."); 
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                List<String> list = new ArrayList<String>();
                if (type == 0) {

                } else {
                    if (type == 1) {
                        list = RandomNum.getRandomNums1();
                    } else if (type == 10) {
                        list = RandomNum.getRandomNums10();
                    } else if (type == 11) {
                        list = RandomNum.getRandomNumber();
                    } else if (type == 50) {
                        list = generateRandomIds();
                    }
                    if (list != null) {
                        DemoDBManager.getInstance().saveRandomIds(list);
                    }
                }

                List<RandomId> lists = DemoDBManager.getInstance()
                        .getRandomIds();
                if (lists != null) {
                    listItems = new ArrayList<Map<String, String>>();
                    for (int i = 0; i < lists.size(); i++) {
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("id", lists.get(i).getId());
                        map.put("randomid", lists.get(i).getRandomId());
                        listItems.add(map);
                    }
                    adapter = new SimpleAdapter(RandomIdsActivity.this,
                            listItems, R.layout.random_id_simple_list_item,
                            new String[] { "id", "randomid" }, new int[] {
                                    R.id.txt_random_auto_id,
                                    R.id.txt_random_gen_id });
                    handler.sendEmptyMessage(MSG_UPDATE);
                    LogUtil.e(lists.toString());
                }

            }
        }).start();

    }

    private void clearAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                RandomIdsActivity.this); // 先得到构造器
        builder.setTitle("提示"); // 设置标题
        builder.setMessage("确定删除所有ID号?"); // 设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { // 设置确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DemoDBManager.getInstance().deleteAllRandomIds();
                        setData(0);
                    }
                });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // 设置取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(RandomIdsActivity.this, "取消",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        builder.create().show();

    }

    private void clearOneId(String id) {
        DemoDBManager.getInstance().deleteRandomId(id);
        setData(0);
    }
    
    private void myRegisterReceiver() 
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction("id_duplication_indication_action");
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);
    }
    
    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if(action.equals("id_duplication_indication_action")){
                int count = intent.getIntExtra("count", -1);
                LogUtil.e("count:"+count);
                if(count==1 && isOneIdGen){
//                    Toast.makeText(RandomIdsActivity.this, "此ID号重复，请重新生成", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            RandomIdsActivity.this); // 先得到构造器
                    builder.setTitle("提示"); // 设置标题
                    builder.setMessage("此ID号重复，请重新生成"); // 设置内容
                    builder.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() { // 设置确定按钮
                                @Override
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                dialog.dismiss();
                                }
                            });
                    builder.create().show();
                }else if(count>0){
//                    Toast.makeText(RandomIdsActivity.this, "有"+count+"个ID号重复，请重新生成", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            RandomIdsActivity.this); // 先得到构造器
                    builder.setTitle("提示"); // 设置标题
                    builder.setMessage("有"+count+"个ID号重复，请重新生成"); // 设置内容
                    builder.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() { // 设置确定按钮
                                @Override
                                public void onClick(DialogInterface dialog,
                                        int which) {
                                dialog.dismiss();
                                }
                            });
                    builder.create().show();
                }
            }
        }
        
    }
}
