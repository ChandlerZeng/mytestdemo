package com.example.mytestdemo;

import java.text.DecimalFormat;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.mytestdemo.utils.LogUtil;
import com.example.mytestdemo.utils.WindowParamUtil;
import com.example.mytestdemo.widget.DragHelperView;

public class DragViewActivity extends Activity {

	private LinearLayout llLayout;
	private TextView mInfo;
	private TextView txtDragShortcut;
	private TextView txtSeekbar1, txtSeekbar2;
	private DragHelperView dragHelperView;
	private SeekBar seekBar1, seekBar2, seekBar3;
	public static final int MIN_FREQUENCY = 8750;
    public static final double FACTOR = 0.01;

	private int mPointerId;
	private int windowHeight;
	private int marginTop;
	protected final static DecimalFormat mDf = new DecimalFormat("#.0");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drag_view);
		llLayout = (LinearLayout) findViewById(R.id.lv_dragview);	
	    dragHelperView = (DragHelperView) findViewById(R.id.drag_help_view);
		windowHeight = WindowParamUtil.getContentViewHeight(this);
//		LinearLayout.LayoutParams lp = (LayoutParams) llLayout
//				.getLayoutParams();
//		lp.height = windowHeight - 110;
//		lp.setMargins(0, -windowHeight + 180, 0, 0);
//		llLayout.setLayoutParams(lp);
		LogUtil.i("height:" + windowHeight);
		setDragLayoutParams(50);
		seekBar1 = (SeekBar) findViewById(R.id.test_seekbar1);
		seekBar2 = (SeekBar) findViewById(R.id.test_seekbar2);
		seekBar3 = (SeekBar) findViewById(R.id.test_seekbar3);
		txtSeekbar1 = (TextView)findViewById(R.id.test_seekbar1_text);
		txtSeekbar2 = (TextView)findViewById(R.id.test_seekbar2_text);
		txtDragShortcut = (TextView)findViewById(R.id.txt_drag_shortcut);
//		txtDragShortcut.setOnTouchListener(dragTouchListener);

		seekBar1.setOnSeekBarChangeListener(seekBarChangeListener1);
		seekBar2.setOnSeekBarChangeListener(seekBarChangeListener2);
		seekBar3.setOnSeekBarChangeListener(seekBarChangeListener3);
		
		seekBar1.incrementProgressBy(20);
		seekBar2.incrementProgressBy(9);
		ActionBar actionBar = getActionBar(); 
		actionBar.hide();

	}
	
	private void showDragView(){
	    llLayout.setVisibility(View.VISIBLE);	    
        LogUtil.i("height:" + windowHeight);
        setDragLayoutParams(50);
        txtDragShortcut.setVisibility(View.GONE);
	}
	
	private void setDragLayoutParams(int marginTop){
	    LinearLayout.LayoutParams lp = (LayoutParams) llLayout
                .getLayoutParams();
        lp.height = windowHeight;
        lp.setMargins(0, -windowHeight+marginTop, 0, 0);
        llLayout.setLayoutParams(lp);
	}
	
	private void dismissDragView(){
	    llLayout.setVisibility(View.GONE);
//	    dragHelperView = (DragHelperView) findViewById(R.id.drag_help_view);
//	    LinearLayout.LayoutParams lp = (LayoutParams) llLayout
//                .getLayoutParams();
//        lp.height = windowHeight - 110;
//        lp.setMargins(0, -windowHeight + 110, 0, 0);
//        llLayout.setLayoutParams(lp);
//        LogUtil.i("height:" + windowHeight);
	    setDragLayoutParams(50);
        txtDragShortcut.setVisibility(View.VISIBLE);
	}
	
	private OnTouchListener dragTouchListener = new OnTouchListener() {
        
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                showDragView();
                LogUtil.e("ACTION_DOWN");
                break;
                
            case MotionEvent.ACTION_MOVE:
//                showDragView();
                float x = event.getRawX();
                marginTop = (int)x;
                setDragLayoutParams(marginTop);
                LogUtil.e("ACTION_MOVE marginTop:"+marginTop);
                break;
                
            case MotionEvent.ACTION_CANCEL:
                dismissDragView();
                LogUtil.e("ACTION_CANCEL");
                break;
                
            case MotionEvent.ACTION_UP:
//                dismissDragView();
                if(marginTop>windowHeight/2){
                    setDragLayoutParams(windowHeight-50);
                }else{
                    dismissDragView();
                }
                LogUtil.e("ACTION_UP");
                break;

            default:
                break;
            }
            return true;
        }
    };
	
    private OnSeekBarChangeListener seekBarChangeListener1 = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			LogUtil.e("seekbar1 progress1:" + progress);
//			txtSeekbar1.setText(mDf.format((progress+MIN_FREQUENCY)* FACTOR));

		}
	};

	private OnSeekBarChangeListener seekBarChangeListener2 = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			LogUtil.e("seekbar2 progress2:" + progress);
//			txtSeekbar2.setText(progress+522+"");
		}
	};

	private OnSeekBarChangeListener seekBarChangeListener3 = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			LogUtil.e("seekbar3 progress3:" + progress);
		}
	};

}
