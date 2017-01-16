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
	         * ��������������һ����Ƶ������Ƶ�ļ� 
	         * videoView.setVideoURI(Uri uri) 
	         * videoView.setVideoPath(String path) 
	         * �����������������ԡ� 
	         */  
	        videoView.setVideoPath(Environment.getExternalStorageDirectory()+"/Video/videoplayback_4_start=44000.mp4");  
	          
	        /** 
	         * wΪ���ṩһ������������������ͣ�����š����ȹ��� 
	         */  
	        videoView.setMediaController(new MediaController(this));  
	          
	        /** 
	         * ��Ƶ������Ƶ����βʱ�����ķ��� 
	         */  
	        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {  
	            @Override  
	            public void onCompletion(MediaPlayer mp) {  
	                Log.i("֪ͨ", "���");  
	            }  
	        });  
	          
	        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {  
	              
	            @Override  
	            public boolean onError(MediaPlayer mp, int what, int extra) {  
	                Log.i("֪ͨ", "�����г��ִ���");  
	                return false;  
	            }  
	        });  
	}
}
