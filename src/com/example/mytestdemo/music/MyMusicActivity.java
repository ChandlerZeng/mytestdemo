package com.example.mytestdemo.music;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import com.example.mytestdemo.MyTestApplication;
import com.example.mytestdemo.R;

public class MyMusicActivity extends Activity implements View.OnClickListener {

    private MusicService musicService;
    private SeekBar seekBar;
    private TextView musicStatus, musicTime, musicName;
    private Button btnPlayOrPause, btnStop, btnQuit, stopAudio;
    private SimpleDateFormat time = new SimpleDateFormat("m:ss");
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName,
                IBinder iBinder) {
            musicService = ((MusicService.MyBinder) iBinder).getService();
            musicName.setText(musicService.getMusicName());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            musicService = null;
        }
    };

    private void bindServiceConnection() {
        Intent intent = new Intent(MyMusicActivity.this, MusicService.class);
         startService(intent);
        bindService(intent, sc, this.BIND_AUTO_CREATE);
    }

    public android.os.Handler handler = new android.os.Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (musicService.mp != null) {
                if (musicService.mp.isPlaying()) {
                    musicStatus.setText(getResources().getString(
                            R.string.playing));
                    btnPlayOrPause.setText(getResources().getString(
                            R.string.pause).toUpperCase());
                } else {
                    musicStatus.setText(getResources()
                            .getString(R.string.pause));
                    btnPlayOrPause.setText(getResources().getString(
                            R.string.playing).toUpperCase());
                }

                musicTime.setText(time.format(musicService.mp
                        .getCurrentPosition())
                        + "/"
                        + time.format(musicService.mp.getDuration()));
                seekBar.setProgress(musicService.mp.getCurrentPosition());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                            int progress, boolean fromUser) {
                        if (fromUser) {
                            musicService.mp.seekTo(seekBar.getProgress());
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                handler.postDelayed(runnable, 100);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Log.d("hint", "ready to new MusicService");
        // musicService = new MusicService();
        Log.d("hint", "finish to new MusicService");
        bindServiceConnection();

        seekBar = (SeekBar) this.findViewById(R.id.MusicSeekBar);
        if(musicService.mp!=null){
            seekBar.setProgress(musicService.mp.getCurrentPosition());
            seekBar.setMax(musicService.mp.getDuration()); 
        }
        musicStatus = (TextView) this.findViewById(R.id.MusicStatus);
        musicTime = (TextView) this.findViewById(R.id.MusicTime);
        musicName = (TextView) this.findViewById(R.id.MusicName);

        btnPlayOrPause = (Button) this.findViewById(R.id.BtnPlayorPause);

        Log.d("hint", Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/You.mp3");
        Log.i("RituNavi", "MyMusicActivity onCreate");
    }

    @Override
    protected void onResume() {
        if (musicService.mp.isPlaying()) {
            musicStatus.setText(getResources().getString(R.string.playing));
        } else {
            musicStatus.setText(getResources().getString(R.string.pause));
        }

        seekBar.setProgress(musicService.mp.getCurrentPosition());
        seekBar.setMax(musicService.mp.getDuration());
        handler.post(runnable);
        Log.d("hint", "handler post runnable");
        super.onResume();
        Log.i("RituNavi", "MyMusicActivity onResume");
    }

    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.BtnPlayorPause:
            musicService.playOrPause();
            break;
        case R.id.BtnStop:
            musicService.stop();
            seekBar.setProgress(0);
            break;
        case R.id.BtnQuit:
            handler.removeCallbacks(runnable);
            unbindService(sc);
            try {
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case R.id.btnPre:
            musicService.preMusic();
            musicName.setText(musicService.getMusicName());
            break;
        case R.id.btnNext:
            musicService.nextMusic();
            musicName.setText(musicService.getMusicName());
            break;
        case R.id.stopAudio:
            pauseMusic();
            Toast.makeText(MyMusicActivity.this, "stopAudio", Toast.LENGTH_SHORT).show();
            break; 
        default:
            break;
        }
    }
    
    private void pauseMusic() {  
        Intent freshIntent = new Intent();  
        freshIntent.setAction("com.android.music.musicservicecommand.pause");  
        freshIntent.putExtra("command", "pause");  
        MyTestApplication.getInstance().sendBroadcast(freshIntent);  
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i("RituNavi", "MyMusicActivity onPause");
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.i("RituNavi", "MyMusicActivity onRestart");
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.i("RituNavi", "MyMusicActivity onStart");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.i("RituNavi", "MyMusicActivity onStop");
    }

    @Override
    public void onDestroy() {
        unbindService(sc);
        super.onDestroy();
        Log.i("RituNavi", "MyMusicActivity onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MyMusicActivity.this, "settings", Toast.LENGTH_SHORT)
                    .show();
            return true;
        } else if (id == R.id.action_filter) {
            Toast.makeText(MyMusicActivity.this, "filter", Toast.LENGTH_SHORT)
                    .show();
            return true;
        } else if (id == R.id.action_sort) {
            Toast.makeText(MyMusicActivity.this, "sort", Toast.LENGTH_SHORT)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
