package com.example.mytestdemo.txz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import cn.ritu.bluephone.bean.BtContact;

import com.google.gson.Gson;
import com.txznet.sdk.TXZCallManager;
import com.txznet.sdk.TXZCallManager.Contact;
import com.txznet.sdk.TXZTtsManager;

/**
 * @author Zengcq
 * @date 2016��12��30��
 * @version 1.0
 * @description
 */
public class SyncBlueToothReceiver extends BroadcastReceiver{

    private static SyncBlueToothReceiver instance;
    private ACache aCache;
    private static boolean isBtDisconnected = true;
    
    public static SyncBlueToothReceiver getInstance() {
        if(instance==null){
            instance = new SyncBlueToothReceiver();
        }
        Log.e("RituNavi","SyncBlueToothReceiver getInstance()");
        return instance;
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals("txz_sync_btcontacts_action")){
            @SuppressWarnings("unchecked")
            List<BtContact> btContacts =  (ArrayList<BtContact>)intent.getSerializableExtra("bt_contacts"); 
            String btAddress = intent.getStringExtra("bt_address");
            syncBtContacts(btContacts);
            if(aCache==null){
                aCache = ACache.get(context);   
            }           
            aCache.put(btAddress, (Serializable)btContacts);
            TXZTtsManager.getInstance().speakText("��ͬ����ϵ��");
            
        }else if(action.equals("bluetooth_connect_address_action")){
            if(isBtDisconnected){
                Log.e("RituNavi", "isBtDisconnected "+isBtDisconnected);
                isBtDisconnected = false;
                String btAddress2 = intent.getStringExtra("bt_address");
                getContactsByAddress(context, btAddress2);  
                TXZTtsManager.getInstance().speakText("�����������豸"); 
            }                     
        }else if(action.equals("bluetooth_disconnect_action")){
            if(!isBtDisconnected){
                TXZTtsManager.getInstance().speakText("�ѶϿ���������");
                TXZCallManager.getInstance().syncContacts(new ArrayList<Contact>());   
                Log.e("RituNavi", "dis isBtDisconnected "+isBtDisconnected);
                isBtDisconnected = true;  
            }                              
        }else if (action.equals("bluetooth_disconnected_info_action")){
            TXZTtsManager.getInstance().speakText("���������������豸");
        }
    }
    
    private void syncBtContacts(List<BtContact> btContactLists){
        List<Contact> list = new ArrayList<Contact>();
        for(int i = 0;i<btContactLists.size();i++){
            Contact contact = new Contact();
            String bt_name = btContactLists.get(i).getName();
            String bt_number = btContactLists.get(i).getNumber(); 
            if(bt_name!=null){
                contact.setName(bt_name);  
            }
            if(bt_number!=null){
                contact.setNumber(bt_number); 
            }
            list.add(contact);
        }
        TXZCallManager.getInstance().syncContacts(list);   
    }
    
    @SuppressWarnings("unchecked")
    public void getContactsByAddress(Context context,String address){
        if(aCache==null){
            aCache = ACache.get(context);   
        } 
        List<BtContact> btContacts = (ArrayList<BtContact>) aCache.getAsObject(address);        
        if(btContacts!=null){
            syncBtContacts(btContacts);   
            Gson gson = new Gson();
            String gsonString = gson.toJson(btContacts);
            Log.e("RituNavi",gsonString);
        }else{
            TXZTtsManager.getInstance().speakText("��ͬ����ϵ��");
            TXZCallManager.getInstance().syncContacts(new ArrayList<Contact>());
        }
    }
    
}
