package com.example.mytestdemo.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mytestdemo.R;
import com.example.mytestdemo.base.BaseActivity;
import com.example.mytestdemo.utils.LogUtil;

public class FragmentTestActivity extends BaseActivity implements OnClickListener {

    private static FragmentTestActivity instance = null;
    private TextView fragmentTextOne, fragmentTextTwo, fragmentTextThree,
            fragmentTextFour;
    private FrameLayout contentLayout;
    private Fragment contentFragment,  fragmentTwo, fragmentThree,
            fragmentFour;
    private FragmentOne fragmentOne;
    
    public static boolean isCreated = false;
    private String from;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        from = getIntent().getStringExtra("from");
        init();
        instance = this;
        isCreated = true;
        LogUtil.i("FragmentActivity onCreate");
    }
    
    

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        isCreated = false;
        LogUtil.i("FragmentActivity onDestroy");
    }



    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        LogUtil.i("FragmentActivity onPause");
    }



    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        LogUtil.i("FragmentActivity onRestart");
    }



    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        LogUtil.i("FragmentActivity onResume");
    }



    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        LogUtil.i("FragmentActivity onStart");
    }



    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        LogUtil.i("FragmentActivity onStop");
    }



    private void init() {
        fragmentTextOne = (TextView) findViewById(R.id.bottom1);
        fragmentTextTwo = (TextView) findViewById(R.id.bottom2);
        fragmentTextThree = (TextView) findViewById(R.id.bottom3);
        fragmentTextFour = (TextView) findViewById(R.id.bottom4);
        contentLayout = (FrameLayout) findViewById(R.id.content);

        fragmentTextOne.setOnClickListener(this);
        fragmentTextTwo.setOnClickListener(this);
        fragmentTextThree.setOnClickListener(this);
        fragmentTextFour.setOnClickListener(this);

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        fragmentFour = new FragmentFour();

        contentFragment = fragmentOne;
        if(from!=null && from.equals("from broadcast")){
            Bundle bundle = new Bundle();  
            bundle.putString("from", from);  
            contentFragment.setArguments(bundle);   
        }       
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content, contentFragment);
        transaction.commit();

    }

    public void switchContent(Fragment fragment) {

        if (contentFragment == fragment) {
            return;
        } else {
            contentFragment = fragment;
            Bundle bundle = new Bundle();  
            bundle.putString("from", "switchContent");  
            contentFragment.setArguments(bundle);
            getFragmentManager().beginTransaction()
                    .replace(R.id.content, fragment).commit();
        }
    }
    
    public void switchToFragmentOne() {

        if (contentFragment == fragmentOne) {
            fragmentOne.showSwitchStatus("from FragmentOne 0");
        } else {
            contentFragment = fragmentOne;
            Bundle bundle = new Bundle();  
            bundle.putString("from", "from FragmentTwo 1");  
            contentFragment.setArguments(bundle);  
            getFragmentManager().beginTransaction()
                    .replace(R.id.content, contentFragment).commit();
            switchTextColor();
            fragmentTextOne.setBackgroundColor(getResources()
                    .getColor(R.color.blue));
        }
    }

    public void switchTextColor() {
        fragmentTextOne.setBackgroundColor(getResources()
                .getColor(R.color.transparent));
        fragmentTextTwo.setBackgroundColor(getResources().getColor(
                R.color.transparent));
        fragmentTextThree.setBackgroundColor(getResources().getColor(
                R.color.transparent));
        fragmentTextFour.setBackgroundColor(getResources().getColor(
                R.color.transparent));
    }

    public static FragmentTestActivity getInstance() {
        if (instance == null) {
            instance = new FragmentTestActivity();
        }
        return instance;
    }

    @Override
    public void onClick(View v) {
        switchTextColor();
        switch (v.getId()) {
        case R.id.bottom1:
            fragmentTextOne.setBackgroundColor(getResources()
                    .getColor(R.color.blue));
            switchContent(fragmentOne);
            break;

        case R.id.bottom2:
            fragmentTextTwo.setBackgroundColor(getResources()
                    .getColor(R.color.blue));
            switchContent(fragmentTwo);
            break;

        case R.id.bottom3:
            fragmentTextThree.setBackgroundColor(getResources()
                    .getColor(R.color.blue));
            switchContent(fragmentThree);
            break;

        case R.id.bottom4:
            fragmentTextFour.setBackgroundColor(getResources()
                    .getColor(R.color.blue));
            switchContent(fragmentFour);
            break;
        }
    }
}
