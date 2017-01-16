package com.example.mytestdemo.carlife;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.mytestdemo.R;

public class VideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		init();
	}
	
	private void init(){
		 VideoView videoView = (VideoView)findViewById(R.id.videoView1);  
	        /*** 
	         * 将播放器关联上一个音频或者视频文件 
	         * videoView.setVideoURI(Uri uri) 
	         * videoView.setVideoPath(String path) 
	         * 以上两个方法都可以。 
	         */  
	        videoView.setVideoPath(Environment.getExternalStorageDirectory()+"/Video/videoplayback_4_start=44000.mp4");  
	          
	        /** 
	         * w为其提供一个控制器，控制其暂停、播放……等功能 
	         */  
	        videoView.setMediaController(new MediaController(this));  
	          
	        /** 
	         * 视频或者音频到结尾时触发的方法 
	         */  
	        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {  
	            @Override  
	            public void onCompletion(MediaPlayer mp) {  
	                Log.i("通知", "完成");  
	            }  
	        });  
	          
	        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {  
	              
	            @Override  
	            public boolean onError(MediaPlayer mp, int what, int extra) {  
	                Log.i("通知", "播放中出现错误");  
	                return false;  
	            }  
	        });  
	}
}
