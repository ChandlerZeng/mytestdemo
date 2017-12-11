package com.example.mytestdemo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AudioManagerTestActivity extends Activity implements
        OnClickListener {

    private static final String TAG = "AudioManagerTestActivity";
    private Button mDefVolPlus;
    private Button mDefVolMinus;
    private Button mMusicVolPlus;
    private Button mMusicVolMinus;
    private Button mVoiceCallVolPlus;
    private Button mVoiceCallVolMinus;
    private Button mRequestAudioFocus;
    private Button mRequestAudioFocusTransient;
    private Button mAbandonAudioFocus;
    private AudioManager mAudioManager;
    private boolean mIsAudioFoucus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager_test);
        init();
    }

    private void init() {
        mDefVolPlus = (Button) findViewById(R.id.btn_def_vol_plus);
        mDefVolMinus = (Button) findViewById(R.id.btn_def_vol_minus);
        mMusicVolPlus = (Button) findViewById(R.id.btn_music_vol_plus);
        mMusicVolMinus = (Button) findViewById(R.id.btn_music_vol_minus);
        mVoiceCallVolPlus = (Button) findViewById(R.id.btn_voice_call_vol_plus);
        mVoiceCallVolMinus = (Button) findViewById(R.id.btn_voice_call_vol_minus);
        mRequestAudioFocus = (Button) findViewById(R.id.btn_request_audio_focus);
        mRequestAudioFocusTransient = (Button) findViewById(R.id.btn_request_audio_focus_transient);
        mAbandonAudioFocus = (Button) findViewById(R.id.btn_abandon_audio_focus);
        mDefVolPlus.setOnClickListener(this);
        mDefVolMinus.setOnClickListener(this);
        mMusicVolMinus.setOnClickListener(this);
        mMusicVolPlus.setOnClickListener(this);
        mVoiceCallVolMinus.setOnClickListener(this);
        mVoiceCallVolPlus.setOnClickListener(this);
        mRequestAudioFocus.setOnClickListener(this);
        mRequestAudioFocusTransient.setOnClickListener(this);
        mAbandonAudioFocus.setOnClickListener(this);
        mAudioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.btn_def_vol_plus:
            volDefPlus();
            break;

        case R.id.btn_def_vol_minus:
            volDefMinus();
            break;
            
        case R.id.btn_music_vol_plus:
            volMusicPlus();
            break;

        case R.id.btn_music_vol_minus:
            volMusicMinus();
            break;
            
        case R.id.btn_voice_call_vol_plus:
            volVoiceCallPlus();
            break;

        case R.id.btn_voice_call_vol_minus:
            volVoiceCallMinus();
            break;
            
        case R.id.btn_request_audio_focus:
            requestAudioFocus();
            break;
            
        case R.id.btn_request_audio_focus_transient:
            requestAudioFocusTransient();
            break;
            
        case R.id.btn_abandon_audio_focus:
            abandonAudioFocus();
            break;

        default:
            break;
        }
    }
    
    private void volDefPlus(){
        mAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }
    
    private void volDefMinus(){
        mAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }
    
    private void volMusicPlus(){
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }
    
    private void volMusicMinus(){
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }
    
    private void volVoiceCallPlus(){
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL,AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }
    
    private void volVoiceCallMinus(){
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_VOICE_CALL,AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }
    
    private void requestAudioFocus(){        
           
        // Request audio focus for playback   
        int result = mAudioManager.requestAudioFocus(afChangeListener,   
        // Use the music stream.   
        AudioManager.STREAM_MUSIC,   
        // Request permanent focus.   
        AudioManager.AUDIOFOCUS_GAIN);   
           
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {   
//            mAudioManager.unregisterMediaButtonEventReceiver(RemoteControlReceiver);   
            // Start playback.   
            // 开始播放音乐文件  
            Log.i(TAG,"requestAudioFocus result:"+result);
        }   
    }
    
    private void requestAudioFocusTransient(){        
        
        // Request audio focus for playback   
        int result = mAudioManager.requestAudioFocus(afChangeListener,   
        // Use the music stream.   
        AudioManager.STREAM_MUSIC,   
        // Request permanent focus.   
        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);   
           
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {     
            // Start playback.   
            // 开始播放音乐文件   
            Log.i(TAG,"requestAudioFocusTransient result:"+result);
        }   
    }
    
    private void abandonAudioFocus(){
        if(mIsAudioFoucus)
        mAudioManager.abandonAudioFocus(afChangeListener);
    }
    
    private OnAudioFocusChangeListener afChangeListener = new OnAudioFocusChangeListener() {   
        public void onAudioFocusChange(int focusChange) {   
          if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){   
             // Pause playback 
              mAudioManager.abandonAudioFocus(afChangeListener); 
              mIsAudioFoucus = false;
              Log.i(TAG,"afChangeListener:AUDIOFOCUS_LOSS_TRANSIENT");
          } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {   
            // Resume playback   
              mIsAudioFoucus = true;
              Log.i(TAG,"afChangeListener:AUDIOFOCUS_GAIN");
          } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {   
//            mAudioManager.unregisterMediaButtonEventReceiver(RemoteControlReceiver);   
              mAudioManager.abandonAudioFocus(afChangeListener);   
            // Stop playback   
              mIsAudioFoucus = false;
              Log.i(TAG,"afChangeListener:AUDIOFOCUS_LOSS");
          }   
        }   
      }; 
      
      private static int currVolume = 0;

      //打开扬声器
      public void OpenSpeaker() {
          try{     
          //判断扬声器是否在打开
          mAudioManager.setMode(AudioManager.ROUTE_SPEAKER);

          //获取当前通话音量
          currVolume =mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

         if(!mAudioManager.isSpeakerphoneOn()) {
             mAudioManager.setSpeakerphoneOn(true);
             mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                     mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC ),
                  AudioManager.STREAM_MUSIC);
            Toast.makeText(AudioManagerTestActivity.this,"扬声器已经打开",Toast.LENGTH_SHORT).show();
          }
         } catch (Exception e) {
             e.printStackTrace();
         }
      }

     //关闭扬声器
     public void CloseSpeaker() {    
         try {
            if(mAudioManager != null) {
                if(mAudioManager.isSpeakerphoneOn()) {
                    mAudioManager.setSpeakerphoneOn(false);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,currVolume,
                             AudioManager.STREAM_MUSIC);
                }
             }
         } catch (Exception e) {
            e.printStackTrace();
         }
          Toast.makeText(AudioManagerTestActivity.this,"扬声器已经关闭",Toast.LENGTH_SHORT).show();
     } 
     
     private void playSound(){
       //实例化一个SoundPool对象  
         SoundPool soundPool =new SoundPool(10, AudioManager.STREAM_MUSIC, 5);  
         //加载声音  
//         int  id = soundPool.load(this,R.raw.beep,5);  
         //播放声音  
//          soundPool.play(id, 1, 1, 0, 0, 1); 
     }
}
