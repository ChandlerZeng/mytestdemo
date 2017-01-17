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
 * @date 2017��1��17��
 * @version 1.0
 * @description
 */
public class AndroidFileSystemTest extends BaseActivity{
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.fragment_bt_phone);  
    /*  
     * 1.����������һ��Ŀ¼�������Ŀ¼�´洢Ӧ�ó���������ļ�  
     * ������Ϊ�� getFilesDir():/data/data/com.ghg.IO/files  
     */  
       File dir= getFilesDir();  
       System.out.println("getFilesDir():"+dir.toString());  
    /*  
     * 2.����������һ��Ŀ¼�������Ŀ¼�´洢Ӧ�ó���Ļ����ļ�����ϵͳ��Դ����ʱ��ϵͳ�����������ļ�  
     * ������Ϊ��dir2=/data/data/com.ghg.IO/cache  
     */  
       File dir2=getCacheDir();  
       System.out.println("dir2="+dir2.toString());  
   /*  
    * 3.����������һ��ָ�����Ƶ�Ŀ¼�������Ŀ¼������Щ����  
    * ������Ϊ��dir3=/data/data/com.ghg.IO/app_abc  
    */  
       File dir3=getDir("abc", MODE_PRIVATE);  
       System.out.println("dir3="+dir3.toString());  
    /*  
     * 4.���ڷ������ݿ���ָ�����ֵ��ļ�·�������ڲ��洢��Ӧ�ñ����·����  
     * ������Ϊ��dir4=/data/data/com.ghg.IO/databases/stu.db  
     */  
       File dir4=getDatabasePath("stu.db");  
       System.out.println("dir4="+dir4.toString());  
   /*  
    * 5.��һ�����������ͨ�������������������abc.txt�ļ���д��һЩ���ݣ�  
    * abc.txt�ļ�λ��/data/data/com.ghg.IO/files�£�  
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
     * 6.��ȡһ������������ͨ������������Զ����ȡָ���ļ�������ļ���/data/data/com.ghg.IO/files�£�  
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
     *7. ��ȡ�ڲ��洢������Ŀ¼  
     * �������ǣ�dir7=/data  
     */  
   File dir7=Environment.getDataDirectory();  
   System.out.println("dir7="+dir7.toString());  
    /*  
     * 8.��ȡ�ڲ��洢���ػ���Ŀ¼,���ص�Ŀ¼��  
     * ������Ϊ��dir8=/cache  
     */  
       File dir8=Environment.getDownloadCacheDirectory();  
       System.out.println("dir8="+dir8.toString());  
    /*  
     * 9.��ȡ�ڲ�����ϵͳ�ĸ�Ŀ¼  
     * ������Ϊ��dir9=/system  
     */  
       File dir9= Environment.getRootDirectory();  
       System.out.println("dir9="+dir9.toString());  
   /*  
    * 10.�����������ⲿ�洢�ļ�Ŀ¼����Ҫsd���ĵ�д������Ȩ�ޣ�  
    * �������ǣ�dir10=/mnt/sdcard/Android/data/com.ghg.IO/files/Music  


    */  
       File dir10=getExternalFilesDir(Environment.DIRECTORY_MUSIC); 
       if(dir10!=null)
       System.out.println("dir10="+dir10.toString());  
   /*  
    * 11.�����������ⲿ�洢����Ŀ¼����Ҫsd����д������Ȩ�ޣ�  
    * �������ǣ�dir11=/mnt/sdcard/Android/data/com.ghg.IO/cache  
    */         
      File dir11= getExternalCacheDir();  
      if(dir11!=null)
      System.out.println("dir11="+dir11.toString());  
      /*  
       * 12.����sdcard�ĸ�Ŀ¼��  
       * ����� dir12=/mnt/sdcard  
       */  
      File dir12=Environment.getExternalStorageDirectory();  
      if(dir12!=null)
      System.out.println("dir12="+dir12.toString());  
      /*  
       * 13.����sdcard�����洢Ŀ¼��  
       * �����dir13=/mnt/sdcard/Music  
       */  
          File dir13=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);  
          if(dir13!=null)
          System.out.println("dir13="+dir13.toString());  
      /*  
       * 14.����sdcard��״̬��ÿ��ʹ��sdcardʱ����������״̬��  
       * �����status=mounted  
       */  
          String status=Environment.getExternalStorageState(); 
          if(status!=null)
          System.out.println("status="+status);  
        
     /*  
      * 15.��Դ����Դ�ķ��ʣ���assets�·�һ��stu.db,��ȡ��д���ڲ��洢�����ļ�Ŀ¼�£�  
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
