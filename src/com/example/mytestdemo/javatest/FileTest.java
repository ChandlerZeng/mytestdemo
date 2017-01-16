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
 * @date 2017年1月9日
 * @version 1.0
 * @description
 */
public class FileTest {
    private static final String fileName ="filetest.txt";
    
    /** 
     *@author chenzheng_Java  
     *保存用户输入的内容到文件 
     */  
    public static void save(Context context,String fileName,String content) {    
//        String content = "哈哈哈哈哈，filetest";  
        try {  
            /* 根据用户提供的文件名，以及文件的应用模式，打开一个输出流.文件不存系统会为你创建一个的， 
             * 至于为什么这个地方还有FileNotFoundException抛出，我也比较纳闷。在Context中是这样定义的 
             *   public abstract FileOutputStream openFileOutput(String name, int mode) 
             *   throws FileNotFoundException; 
             * openFileOutput(String name, int mode); 
             * 第一个参数，代表文件名称，注意这里的文件名称不能包括任何的/或者/这种分隔符，只能是文件名 
             *          该文件会被保存在/data/data/应用名称/files/filetest.txt 
             * 第二个参数，代表文件的操作模式 
             *          MODE_PRIVATE 私有（只能创建它的应用访问） 重复写入时会文件覆盖 
             *          MODE_APPEND  私有   重复写入时会在文件的末尾进行追加，而不是覆盖掉原来的文件 
             *          MODE_WORLD_READABLE 公用  可读 
             *          MODE_WORLD_WRITEABLE 公用 可读写 
             *  */  
            FileOutputStream outputStream = context.openFileOutput(fileName,  
                    Activity.MODE_PRIVATE);  
            outputStream.write(content.getBytes());  
            outputStream.flush();  
            outputStream.close();  
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
  
    /** 
     * @author chenzheng_java  
     * 读取刚才用户保存的内容 
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
    

