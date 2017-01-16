package com.example.mytestdemo.txz;

import java.io.IOException;
import java.io.OutputStream;

import android.util.Log;

/**
 * @author Zengcq
 * @date 2016��12��12��
 * @version 1.0
 * @description
 */
public class SuUtil {
		  
	    private static Process process;  
	  
	    /** 
	     * ��������,ִ�в������ü��� 
	     */  
	    public static void kill(String packageName) {  
	        initProcess();  
	        killProcess(packageName);  
	        close();  
	    }  
	  
	    /** 
	     * ��ʼ������ 
	     */  
	    private static void initProcess() {  
	        if (process == null)  
	            try {  
	                process = Runtime.getRuntime().exec("su");  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	                Log.d("RituNavi", "su "+e.toString());
	            }  
	    }  
	  
	    /** 
	     * �������� 
	     */  
	    private static void killProcess(String packageName) {  
	        OutputStream out = process.getOutputStream();  
	        String cmd = "am force-stop " + packageName + " \n";  
	        try {  
	            out.write(cmd.getBytes());  
	            out.flush();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            Log.d("RituNavi", "cmd "+e.toString());
	        }  
	    }  
	  
	    /** 
	     * �ر������ 
	     */  
	    private static void close() {  
	        if (process != null)  
	            try {  
	                process.getOutputStream().close();  
	                process = null;  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	                Log.d("RituNavi", "close "+e.toString());
	            }  
	    }  
}
