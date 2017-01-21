package com.example.mytestdemo.javatest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * @author Zengcq
 * @date 2017Äê1ÔÂ21ÈÕ
 * @version 1.0
 * @description
 */
public class GocsdkService {
    public static final int MSG_START_SERIAL = 0;
    public static final int MSG_SERIAL_RECEIVED = 1;
    private static BufferedReader reader = null;
    private static BufferedWriter writer = null;
    private Handler handler = new Handler() {
        public void handlerMessage(android.os.Message msg) {
            switch (msg.what) {
            case MSG_START_SERIAL:
                System.out.println("MSG_START_SERIAL");
                break;
                
            case MSG_SERIAL_RECEIVED:
//                Message msgMessage = handler.obtainMessage();
                Bundle bundle = msg.getData();
                byte[] data = (byte[]) bundle.get("data");
                System.out.println("MSG_SERIAL_RECEIVED "+(new String(data)));
                break; 
            }
        }
    };

    public static void main(String[] args) {
       new SerialThread().start();
    }

    public void writeAddressInfo(byte[] buf) {
        char[] buffer = new char[1024];
        int length = -1;
        try {
            reader = new BufferedReader(new StringReader(new String(buf)));
            writer = new BufferedWriter(new FileWriter(
                    "D:/testjava/gocsdkservice.txt"));
            while ((length = reader.read(buffer, 0, 1024)) != -1) {
                writer.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
    public Handler getHandler(){
        return handler;
    }

   
}

class SerialThread extends Thread {

    @Override
    public void run() {
        GocsdkService service = new GocsdkService();
        byte[] source = "nimjkdjsalskdj".getBytes();
        service.writeAddressInfo(source);
        int n = source.length;
        byte[] data = new byte[n];
        System.arraycopy(source, 0, data, 0, n);
        Message msg = service.getHandler().obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putByteArray("data", data);
        msg.setData(bundle);
        msg.sendToTarget();
    }
}

