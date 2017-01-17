package com.example.mytestdemo.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;

import com.example.mytestdemo.R;
import com.example.mytestdemo.base.BaseActivity;

/**
 * @author Zengcq
 * @date 2017年1月17日
 * @version 1.0
 * @description
 */
public class AndroidFileSystemTest extends BaseActivity{
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.fragment_bt_phone);  
    /*  
     * 1.创建并返回一个目录，在这个目录下存储应用程序的数据文件  
     * 输出结果为： getFilesDir():/data/data/com.ghg.IO/files  
     */  
       File dir= getFilesDir();  
       System.out.println("getFilesDir():"+dir.toString());  
    /*  
     * 2.创建并返回一个目录，在这个目录下存储应用程序的缓冲文件，当系统资源不足时，系统清除这下面的文件  
     * 输出结果为：dir2=/data/data/com.ghg.IO/cache  
     */  
       File dir2=getCacheDir();  
       System.out.println("dir2="+dir2.toString());  
   /*  
    * 3.创建并返回一个指定名称的目录，在这个目录下来存些东西  
    * 输出结果为：dir3=/data/data/com.ghg.IO/app_abc  
    */  
       File dir3=getDir("abc", MODE_PRIVATE);  
       System.out.println("dir3="+dir3.toString());  
    /*  
     * 4.用于返回数据库中指定名字的文件路径，在内部存储中应该保存的路径；  
     * 输出结果为：dir4=/data/data/com.ghg.IO/databases/stu.db  
     */  
       File dir4=getDatabasePath("stu.db");  
       System.out.println("dir4="+dir4.toString());  
   /*  
    * 5.打开一个输出流对象，通过这个输出流对象可以向abc.txt文件中写入一些数据，  
    * abc.txt文件位于/data/data/com.ghg.IO/files下；  
    */  
       try {  
        FileOutputStream  out=openFileOutput("abc.txt", MODE_PRIVATE);  
        out.write("gao hong guo jiajia".getBytes());  
        out.flush();  
        out.close();  
    } catch (FileNotFoundException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    } catch (IOException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }  
       
    /*  
     * 6.获取一个输入流对象，通过这个输入流对对象读取指定文件，这个文件在/data/data/com.ghg.IO/files下；  
     */  
          
      try {  
        FileInputStream is=openFileInput("abc.txt");  
        ByteArrayOutputStream out=new ByteArrayOutputStream();  
        int len=0;  
        byte[] buf=new byte[4];  
        while((len=is.read(buf))>0){  
            out.write(buf, 0, len);  
            out.flush();  
        }  
        out.close();  
        is.close();  
        System.out.println(out.toString());  
    } catch (FileNotFoundException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    } catch (IOException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }  
       
    /*  
     *7. 获取内部存储的数据目录  
     * 输出结果是：dir7=/data  
     */  
   File dir7=Environment.getDataDirectory();  
   System.out.println("dir7="+dir7.toString());  
    /*  
     * 8.获取内部存储下载缓冲目录,隐藏的目录；  
     * 输出结果为：dir8=/cache  
     */  
       File dir8=Environment.getDownloadCacheDirectory();  
       System.out.println("dir8="+dir8.toString());  
    /*  
     * 9.获取内部下载系统的根目录  
     * 输出结果为：dir9=/system  
     */  
       File dir9= Environment.getRootDirectory();  
       System.out.println("dir9="+dir9.toString());  
   /*  
    * 10.创建并返回外部存储文件目录，需要sd卡的的写入数据权限；  
    * 输出结果是：dir10=/mnt/sdcard/Android/data/com.ghg.IO/files/Music  


    */  
       File dir10=getExternalFilesDir(Environment.DIRECTORY_MUSIC); 
       if(dir10!=null)
       System.out.println("dir10="+dir10.toString());  
   /*  
    * 11.创建并返回外部存储缓冲目录，需要sd卡的写入数据权限；  
    * 输出结果是：dir11=/mnt/sdcard/Android/data/com.ghg.IO/cache  
    */         
      File dir11= getExternalCacheDir();  
      if(dir11!=null)
      System.out.println("dir11="+dir11.toString());  
      /*  
       * 12.返回sdcard的根目录；  
       * 结果： dir12=/mnt/sdcard  
       */  
      File dir12=Environment.getExternalStorageDirectory();  
      if(dir12!=null)
      System.out.println("dir12="+dir12.toString());  
      /*  
       * 13.返回sdcard公共存储目录；  
       * 结果：dir13=/mnt/sdcard/Music  
       */  
          File dir13=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);  
          if(dir13!=null)
          System.out.println("dir13="+dir13.toString());  
      /*  
       * 14.返回sdcard的状态，每次使用sdcard时，都会检查其状态；  
       * 结果：status=mounted  
       */  
          String status=Environment.getExternalStorageState(); 
          if(status!=null)
          System.out.println("status="+status);  
        
     /*  
      * 15.对源生资源的访问，在assets下放一个stu.db,读取并写入内部存储数据文件目录下；  
      */  
      File dir1=getDatabasePath("stu.db");  
      if (!dir1.exists()) {  
        if (!dir1.getParentFile().exists()) {  
            dir1.getParentFile().mkdir();  
        }  
          
        AssetManager manager=getAssets();  
        try {  
            InputStream in=manager.open("stu.db");  
            FileOutputStream out=new FileOutputStream(dir1);  
            int len=0;  
            byte[] buf=new byte[512];  
            while ((len=in.read(buf))>0){  
                out.write(buf, 0, len);  
                out.flush();  
            }  
            in.close();  
            out.close();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
          
    }  
        
}  
}
