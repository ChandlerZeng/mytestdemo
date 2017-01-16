package com.example.mytestdemo.txz;

import java.util.ArrayList;
import java.util.List;

import com.example.mytestdemo.utils.LogUtil;
import com.txznet.sdk.TXZCallManager;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.sdk.TXZCallManager.Contact;

/**
 * @author Zengcq
 * @date 2016��12��30��
 * @version 1.0
 * @description
 */
public class SyncContacts {
    /**
     * ����ʱ����SDK��ʼ����ɡ����������ϡ���ϵ�����ݷ������
     */
    public static void syncContacts() {
        // TODO ͬ��ǰ���ж��Ƿ��ʼ���ɹ�
        if (!TXZConfigManager.getInstance().isInitedSuccess())
            return;

        List<Contact> lst = new ArrayList<Contact>();
        Contact con;
        con = new Contact();
        con.setName("����");
        con.setNumber("30001");
        lst.add(con);
        con = new Contact();
        con.setName("����");
        con.setNumber("30002");
        lst.add(con);
        con = new Contact();
        con.setName("����");
        con.setNumber("30003");
        lst.add(con);
        con = new Contact();
        con.setName("����");
        con.setNumber("30100");
        lst.add(con);
        con = new Contact();
        con.setName("����");
        con.setNumber("30200");
        lst.add(con);
        con = new Contact();
        con.setName("����");
        con.setNumber("40000");
        lst.add(con);
        con = new Contact();
        con.setName("����-��Ѷ");
        con.setNumber("40001");
        lst.add(con);
        con = new Contact();
        con.setName("����-��Ϊ");
        con.setNumber("40002");
        lst.add(con);
        con = new Contact();
        con.setName("����-�ٶ�");
        con.setNumber("40003");
        lst.add(con);
        con = new Contact();
        con.setName("����");
        con.setNumber("40001");
        lst.add(con);
        con = new Contact();
        con.setName("��ٻ");
        con.setNumber("40002");
        lst.add(con);
        con = new Contact();
        con.setName("��ϣ");
        con.setNumber("40003");
        lst.add(con);
        con = new Contact();
        con.setName("��ٻ");
        con.setNumber("40004");
        lst.add(con);
        con = new Contact();
        con.setName("��ϣ");
        con.setNumber("40005");
        lst.add(con);
        con = new Contact();
        con.setName("�������");
        con.setNumber("15361088570");
        lst.add(con);
        con = new Contact();
        con.setName("�������");
        con.setNumber("5678");
        lst.add(con);
        con = new Contact();
        con.setName("�������");
        con.setNumber("075588888888");
        lst.add(con);
        con = new Contact();
        con.setName("��������Ӫ��");
        con.setNumber("15361088570");
        lst.add(con);
        con = new Contact();
        con.setName("��������Ӫ��");
        con.setNumber("15334565678");
        lst.add(con);
        con = new Contact();
        con.setName("��������Ӫ��");
        con.setNumber("18612997778");
        lst.add(con);
        TXZCallManager.getInstance().syncContacts(lst);
        
        LogUtil.e("��ͬ����ϵ�ˣ����������ġ����硢���ܡ�������ԡ���������Ӫ��");
    }

}
