package com.example.mytestdemo.javatest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * @author Zengcq
 * @date 2017��1��9��
 * @version 1.0
 * @description
 */
public class FileTest {
    private static final String fileName ="filetest.txt";
    
    /** 
     *@author chenzheng_Java  
     *�����û���������ݵ��ļ� 
     */  
    public static void save(Context context,String fileName,String content) {    
//        String content = "������������filetest";  
        try {  
            /* �����û��ṩ���ļ������Լ��ļ���Ӧ��ģʽ����һ�������.�ļ�����ϵͳ��Ϊ�㴴��һ���ģ� 
             * ����Ϊʲô����ط�����FileNotFoundException�׳�����Ҳ�Ƚ����ơ���Context������������� 
             *   public abstract FileOutputStream openFileOutput(String name, int mode) 
             *   throws FileNotFoundException; 
             * openFileOutput(String name, int mode); 
             * ��һ�������������ļ����ƣ�ע��������ļ����Ʋ��ܰ����κε�/����/���ַָ�����ֻ�����ļ��� 
             *          ���ļ��ᱻ������/data/data/Ӧ������/files/filetest.txt 
             * �ڶ��������������ļ��Ĳ���ģʽ 
             *          MODE_PRIVATE ˽�У�ֻ�ܴ�������Ӧ�÷��ʣ� �ظ�д��ʱ���ļ����� 
             *          MODE_APPEND  ˽��   �ظ�д��ʱ�����ļ���ĩβ����׷�ӣ������Ǹ��ǵ�ԭ�����ļ� 
             *          MODE_WORLD_READABLE ����  �ɶ� 
             *          MODE_WORLD_WRITEABLE ���� �ɶ�д 
             *  */  
            FileOutputStream outputStream = context.openFileOutput(fileName,  
                    Activity.MODE_PRIVATE);  
            outputStream.write(content.getBytes());  
            outputStream.flush();  
            outputStream.close();  
            Toast.makeText(context, "����ɹ�", Toast.LENGTH_SHORT).show();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
  
    /** 
     * @author chenzheng_java  
     * ��ȡ�ղ��û���������� 
     */  
    public static String read(Context context,String fileName) {  
        try {  
            FileInputStream inputStream = context.openFileInput(fileName);  
            byte[] bytes = new byte[1024];  
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();  
            while (inputStream.read(bytes) != -1) {  
                arrayOutputStream.write(bytes, 0, bytes.length);  
            }  
            inputStream.close();  
            arrayOutputStream.close();  
            String content = new String(arrayOutputStream.toByteArray());  
            return content;
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        return null;  
  
    }
    
    public static void save(Context context,String content){
        save(context, fileName, content);
    }
    
    public static String read(Context context){
       return read(context,fileName);
    }
  
}  
    

