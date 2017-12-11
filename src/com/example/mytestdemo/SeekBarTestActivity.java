package com.example.mytestdemo;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.mytestdemo.utils.LogUtil;

public class SeekBarTestActivity extends Activity {

	private TextView txtSeekbar1, txtSeekbar2;
	private SeekBar seekBar1, seekBar2, seekBar3;
	public static final int MIN_FREQUENCY = 8750;
    public static final double FACTOR = 0.01;
    

	protected final static DecimalFormat mDf = new DecimalFormat("#.0");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seek_bar_test);
		
		seekBar1 = (SeekBar) findViewById(R.id.test_seekbar1);
		seekBar2 = (SeekBar) findViewById(R.id.test_seekbar2);
		seekBar3 = (SeekBar) findViewById(R.id.test_seekbar3);
		txtSeekbar1 = (TextView)findViewById(R.id.test_seekbar1_text);
		txtSeekbar2 = (TextView)findViewById(R.id.test_seekbar2_text);

		seekBar1.setOnSeekBarChangeListener(seekBarChangeListener1);
		seekBar2.setOnSeekBarChangeListener(seekBarChangeListener2);
		seekBar3.setOnSeekBarChangeListener(seekBarChangeListener3);
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        MenuInflater inflater = getMenuInflater();  
        inflater.inflate(R.menu.menu_test, menu);  
        return super.onCreateOptionsMenu(menu);
    }

    


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub\
        switch (item.getItemId()) {
        case R.id.action_compose:
            Toast.makeText(SeekBarTestActivity.this, "action_compose", Toast.LENGTH_SHORT).show();
            break;
            
        case R.id.action_delete:
            Toast.makeText(SeekBarTestActivity.this, "action_delete", Toast.LENGTH_SHORT).show();
            break;
            
        case R.id.action_settings:
            Toast.makeText(SeekBarTestActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
            break;

        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
	
	private OnSeekBarChangeListener seekBarChangeListener1 = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			int progress = seekBar.getProgress();
			progress = transferProgress1(progress);
			txtSeekbar1.setText(mDf.format((progress+MIN_FREQUENCY)* FACTOR));
			LogUtil.i("seekbar001 progress001:" + progress);
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
			progress = transferProgress1(progress);
			txtSeekbar1.setText(mDf.format((progress+MIN_FREQUENCY)* FACTOR));
			LogUtil.e("seekbar01 progress01:" + progress);
		}
	};
	
	private int transferProgress1(int progress){
		progress = progress/10;
		progress = progress*10;
		return progress;
	}

	private OnSeekBarChangeListener seekBarChangeListener2 = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			int progress = seekBar.getProgress();
			progress = transferProgress2(progress);
			txtSeekbar2.setText(progress+522+"");
			LogUtil.i("seekbar002 progress002:" + progress);
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
			progress = transferProgress2(progress);
			txtSeekbar2.setText(progress+522+"");
			LogUtil.e("seekbar02 progress02:" + progress);
		}
	};
	
	private int transferProgress2(int progress){
		progress = progress/9;
		progress = progress*9;
		return progress;
	}

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
