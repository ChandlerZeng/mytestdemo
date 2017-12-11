package com.example.mytestdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.R.integer;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class HandlerActivity extends Activity implements OnClickListener {

    private static final int MSG_SHOW = 0;
    private static final int MSG_DISMISS = 1;
    private static final int MSG_SHOW_ONE = 2;
    private static final int MSG_SHOW_TWO = 3;
    private static final int MSG_SHOW_THREE = 4;
    private static final int MSG_SHOW_FOUR = 5;
    private static final int MSG_DISMISS_ONE = 6;
    private static final int MSG_DISMISS_TWO = 7;
    private static final int MSG_DISMISS_THREE = 8;
    private static final int MSG_DISMISS_FOUR = 9;
    private static final int DURATION_SHORT = 2000;
    private static final int DURATION_LONG = 6000;
    private Button btnShow;
    private Button btnShow1;
    private Button btnShow2;
    private Button btnShow3;
    private Button btnShows;
    private Button btnDismiss;
    private TextView txtToast;
    private TextView txtToast1;
    private TextView txtToast2;
    private TextView txtToast3;
    private TextView txtToast4;
    private TextView txtMsg;
    private Handler mHandler;
    private Handler mHandler2;
    private int i = 0;
    private String[] toastStrings;
    private String olderString;
    private String oldString;
    private String curString;
    private String curString1;
    private String curString2;
    private Animation animationIn;
    private Animation animationOut;
    private TextSwitcher textSwitcher;
    private TextSwitcher textSwitcher2;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        init();
    }

    private void init() {
        toastStrings = getResources().getStringArray(R.array.toasts);
        btnShow = (Button) findViewById(R.id.btn_show);
        btnShow1 = (Button) findViewById(R.id.btn_show1);
        btnShow2 = (Button) findViewById(R.id.btn_show2);
        btnShow3 = (Button) findViewById(R.id.btn_show3);
        btnShows = (Button) findViewById(R.id.btn_shows);
        btnDismiss = (Button) findViewById(R.id.btn_dismiss);
        txtToast = (TextView) findViewById(R.id.toast_text);
        txtToast1 = (TextView) findViewById(R.id.toast_text1);
        txtToast2 = (TextView) findViewById(R.id.toast_text2);
        txtToast3 = (TextView) findViewById(R.id.toast_text3);
        txtToast4 = (TextView) findViewById(R.id.toast_text4);
        txtMsg = (TextView) findViewById(R.id.msg_text);
        textSwitcher = (TextSwitcher) findViewById(R.id.text_switcher1);
        textSwitcher2 = (TextSwitcher) findViewById(R.id.text_switcher2);

        btnShow.setOnClickListener(this);
        btnShow1.setOnClickListener(this);
        btnShow2.setOnClickListener(this);
        btnShow3.setOnClickListener(this);
        btnShows.setOnClickListener(this);
        btnDismiss.setOnClickListener(this);
        animationIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_top);
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                final TextView tv = new TextView(HandlerActivity.this);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tv.setTextColor(getResources().getColor(R.color.white));
                // tv.setPadding(30, 20, 30, 20);
                return tv;
            }
        });
        textSwitcher.setInAnimation(animationIn);
        textSwitcher.setOutAnimation(animationOut);
        textSwitcher2.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                final TextView tv = new TextView(HandlerActivity.this);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tv.setTextColor(getResources().getColor(R.color.white));
                tv.setGravity(Gravity.CENTER);
                return tv;
            }
        });
        textSwitcher2.setInAnimation(animationIn);
        textSwitcher2.setOutAnimation(animationOut);
        // handler1();
        // handler2();
        handler3();
        new AsyncProcess().execute(dataList);

    }

    private void handler1() {
        mHandler2 = new Handler();
        mHandler2.post(new Runnable() {

            @Override
            public void run() {
                curString1 = toastStrings[new Random().nextInt(toastStrings.length)];
                curString2 = toastStrings[new Random().nextInt(toastStrings.length)];
                textSwitcher.setVisibility(View.VISIBLE);
                textSwitcher.setText(curString1);
                textSwitcher2.setVisibility(View.VISIBLE);
                textSwitcher2.setText(curString2); 
                mHandler2.postDelayed(this, DURATION_LONG);
            }
        });
    }

    private void handler2() {
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                case MSG_SHOW:
                    i++;
                    showToasts(i);
                    showMsg(i);
                    break;

                case MSG_DISMISS:
                    dismissToast();
                    dimissMsg();
                    break;

                default:
                    break;
                }
            }

        };
    }

    private void handler3() {
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                curString = toastStrings[new Random().nextInt(toastStrings.length)];
                if (i < toastStrings.length - 1) {
                    i++;
                } else {
                    i = 0;
                }
                switch (msg.what) {
                case MSG_SHOW_ONE:
                    if (olderString != null) {
                        txtToast1.startAnimation(animationOut);
                        txtToast1.setVisibility(View.VISIBLE);
                        txtToast1.setText(olderString);
                        txtToast1.startAnimation(animationIn);
                    }
                    if (oldString != null) {
                        txtToast2.startAnimation(animationOut);
                        txtToast2.setVisibility(View.VISIBLE);
                        txtToast2.setText(oldString);
                        txtToast2.startAnimation(animationIn);
                    }
                    txtToast3.startAnimation(animationOut);
                    txtToast3.setVisibility(View.VISIBLE);
                    txtToast3.setText(curString);
                    txtToast3.startAnimation(animationIn);
                    mHandler.sendEmptyMessageDelayed(MSG_SHOW_ONE,
                            DURATION_SHORT);
                    break;

                case MSG_SHOW_TWO:
                    txtToast2.setVisibility(View.VISIBLE);
                    txtToast2.setText(curString);
                    mHandler.sendEmptyMessageDelayed(MSG_SHOW_THREE,
                            DURATION_SHORT);
                    mHandler.sendEmptyMessage(MSG_DISMISS_FOUR);
                    break;

                case MSG_SHOW_THREE:
                    txtToast3.setVisibility(View.VISIBLE);
                    txtToast3.setText(curString);
                    mHandler.sendEmptyMessageDelayed(MSG_SHOW_ONE,
                            DURATION_SHORT);
                    break;

                case MSG_DISMISS_ONE:
                    txtToast1.setVisibility(View.GONE);
                    break;

                case MSG_DISMISS_TWO:
                    txtToast2.setVisibility(View.GONE);
                    break;

                case MSG_DISMISS_THREE:
                    txtToast3.setVisibility(View.GONE);
                    break;

                default:
                    break;
                }
                olderString = oldString;
                oldString = curString;
            }

        };
    }

    private Runnable showRunnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            dismissToast();
        }
    };

    private Runnable showRunnables = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            mHandler.removeCallbacks(showRunnables);
            mHandler.postDelayed(showRunnables, DURATION_LONG);
            i++;
            showToasts("show toasts in 2s recycle " + i, DURATION_SHORT);
        }
    };

    private Runnable dimissRunnables = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            dismissToast();
        }
    };

    private void showToast(String msg, int duration) {
        txtToast.setVisibility(View.VISIBLE);
        txtToast.setText(msg);
        mHandler.removeCallbacks(showRunnable);
        mHandler.postDelayed(showRunnable, duration);
    }

    private void showToasts(String msg, int duration) {
        txtToast.setVisibility(View.VISIBLE);
        txtToast.setText(msg);
        mHandler.removeCallbacks(dimissRunnables);
        mHandler.postDelayed(dimissRunnables, duration);
    }

    private void showToasts(int count) {
        txtToast.setVisibility(View.VISIBLE);
        txtToast.setText("show toasts in 2s recycle " + i);
    }

    private void dismissToast() {
        txtToast.setVisibility(View.GONE);
    }

    private void showMsg(int count) {
        txtMsg.setVisibility(View.VISIBLE);
        txtMsg.setText("msg show " + count);
        mHandler.removeMessages(MSG_DISMISS);
        mHandler.sendEmptyMessageDelayed(MSG_DISMISS, DURATION_LONG);
    }

    private void dimissMsg() {
        txtMsg.setVisibility(View.GONE);
        mHandler.removeMessages(MSG_SHOW);
        mHandler.sendEmptyMessageDelayed(MSG_SHOW, DURATION_SHORT);
    }

    private void showFunToasts() {
        mHandler.sendEmptyMessage(MSG_SHOW_ONE);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
        }
        if(mHandler2!=null){
            mHandler2.removeCallbacksAndMessages(null);
        }
    }
    
    private class AsyncProcess extends AsyncTask<ArrayList<String>, Void, Integer>{

        
        
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(ArrayList<String>... params) {
            // TODO Auto-generated method stub
            return 0;
        }
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_show:
            showFunToasts();
//            handler1();
            break;

        case R.id.btn_show1:
//            showToast("btn_show1 clicked:show toast1 in 4s ", DURATION_LONG);
            handler1();
            break;

        case R.id.btn_show2:
            showToast("btn_show2 clicked:show toast2 in 2s", DURATION_SHORT);
            break;

        case R.id.btn_show3:
            showToast("btn_show3 clicked:show toast3 in 4s", DURATION_LONG);
            break;

        case R.id.btn_shows:
            // mHandler.post(showRunnables);
            mHandler.sendEmptyMessage(MSG_SHOW);
            break;

        case R.id.btn_dismiss:
            dismissToast();
            break;

        default:
            break;
        }

    }
}
