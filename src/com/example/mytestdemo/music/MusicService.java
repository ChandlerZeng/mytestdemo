package com.example.mytestdemo.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by West on 2015/11/10.
 */
public class MusicService extends Service{

    private String[] musicDir = new String[]{Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music/绅士.aac",
            Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music/喜欢你.aac",
            Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music/丑八怪.aac",
            Environment.getExternalStorageDirectory().getAbsolutePath() +"/Music/泡沫.aac"};
    private int musicIndex = 1;

    public final IBinder binder = new MyBinder();
    public class MyBinder extends Binder{
        MusicService getService() {
            return MusicService.this;
        }
    }
    public static MediaPlayer mp = new MediaPlayer();
    public MusicService() {
        try {
        	if(mp==null)
        	mp = new MediaPlayer();
            mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music/绅士.aac");
            //mp.setDataSource(Environment.getDataDirectory().getAbsolutePath()+"/You.mp3");
            mp.prepare();
            musicIndex = 0;
        } catch (Exception e) {
            Log.e("RituNavi","can't get to the song"+e.toString());
            e.printStackTrace();
        }
    }
    public void playOrPause() {
        if(mp.isPlaying()){
            mp.pause();
        } else {
            mp.start();
        }
    }
    public void stop() {
        if(mp != null) {
            mp.stop();
            try {
                mp.prepare();
                mp.seekTo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void nextMusic() {
        if(mp != null && musicIndex < 3) {
            mp.stop();
            try {
                mp.reset();
                musicIndex++;
                mp.setDataSource(musicDir[musicIndex]);
                mp.prepare();
                mp.seekTo(0);
                mp.start();
            } catch (Exception e) {
                Log.e("RituNavi", "can't jump next music");
                e.printStackTrace();
            }
        }
    }
    public void preMusic() {
        if(mp != null && musicIndex > 0) {
            mp.stop();
            try {
                mp.reset();
                musicIndex--;
                mp.setDataSource(musicDir[musicIndex]);
                mp.prepare();
                mp.seekTo(0);
                mp.start();
            } catch (Exception e) {
                Log.e("RituNavi", "can't jump pre music");
                e.printStackTrace();
            }
        }
    }
    
    
    
    @Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("RituNavi", "MusicService onCreate");
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i("RituNavi", "MusicService onStart");
		/* if(mp.isPlaying()){

	        } else {
	            mp.start();
	        }*/
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("RituNavi", "MusicService onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("RituNavi", "MusicService onUnbind");
		return super.onUnbind(intent);
	}
	@Override
    public void onDestroy() {
		if(mp!=null && mp.isPlaying()){
			mp.stop();
	        mp.release();
//	        mp=null;	
	        Log.i("RituNavi", "MusicService release");
		}    
        super.onDestroy();
        Log.i("RituNavi", "MusicService onDestroy");
    }

    /**
     * onBind 是 Service 的虚方法，因此我们不得不实现它。
     * 返回 null，表示客服端不能建立到此服务的连接。
     */
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

	public String getMusicName() {
		// TODO Auto-generated method stub
	    String musicName = getSubStr(musicDir[musicIndex], 1);
		return musicName;
	}
	
	 private static String getSubStr(String str, int num) {
	        String result = "";
	        int i = 0;
	        while (i < num) {
	            int lastFirst = str.lastIndexOf('/');
	            result = str.substring(lastFirst) + result;
	            System.out.println(result);
	            str = str.substring(0, lastFirst);
	            i++;
	        }
	        int pointIndex = result.indexOf('.');
	        return result.substring(1,pointIndex);
	    }
}
