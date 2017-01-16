package com.example.mytestdemo.txz;

import java.util.ArrayList;
import java.util.List;

import com.example.mytestdemo.utils.LogUtil;
import com.txznet.sdk.TXZCallManager;
import com.txznet.sdk.TXZConfigManager;
import com.txznet.sdk.TXZCallManager.Contact;

/**
 * @author Zengcq
 * @date 2016年12月30日
 * @version 1.0
 * @description
 */
public class SyncContacts {
    /**
     * 调用时机：SDK初始化完成、蓝牙连接上、联系人数据发生变更
     */
    public static void syncContacts() {
        // TODO 同步前先判断是否初始化成功
        if (!TXZConfigManager.getInstance().isInitedSuccess())
            return;

        List<Contact> lst = new ArrayList<Contact>();
        Contact con;
        con = new Contact();
        con.setName("张三");
        con.setNumber("30001");
        lst.add(con);
        con = new Contact();
        con.setName("张三");
        con.setNumber("30002");
        lst.add(con);
        con = new Contact();
        con.setName("张三");
        con.setNumber("30003");
        lst.add(con);
        con = new Contact();
        con.setName("章三");
        con.setNumber("30100");
        lst.add(con);
        con = new Contact();
        con.setName("張三");
        con.setNumber("30200");
        lst.add(con);
        con = new Contact();
        con.setName("李四");
        con.setNumber("40000");
        lst.add(con);
        con = new Contact();
        con.setName("杨总-腾讯");
        con.setNumber("40001");
        lst.add(con);
        con = new Contact();
        con.setName("杨总-华为");
        con.setNumber("40002");
        lst.add(con);
        con = new Contact();
        con.setName("杨总-百度");
        con.setNumber("40003");
        lst.add(con);
        con = new Contact();
        con.setName("曾茜");
        con.setNumber("40001");
        lst.add(con);
        con = new Contact();
        con.setName("层倩");
        con.setNumber("40002");
        lst.add(con);
        con = new Contact();
        con.setName("层希");
        con.setNumber("40003");
        lst.add(con);
        con = new Contact();
        con.setName("增倩");
        con.setNumber("40004");
        lst.add(con);
        con = new Contact();
        con.setName("增希");
        con.setNumber("40005");
        lst.add(con);
        con = new Contact();
        con.setName("号码测试");
        con.setNumber("15361088570");
        lst.add(con);
        con = new Contact();
        con.setName("号码测试");
        con.setNumber("5678");
        lst.add(con);
        con = new Contact();
        con.setName("号码测试");
        con.setNumber("075588888888");
        lst.add(con);
        con = new Contact();
        con.setName("归属地运营商");
        con.setNumber("15361088570");
        lst.add(con);
        con = new Contact();
        con.setName("归属地运营商");
        con.setNumber("15334565678");
        lst.add(con);
        con = new Contact();
        con.setName("归属地运营商");
        con.setNumber("18612997778");
        lst.add(con);
        TXZCallManager.getInstance().syncContacts(lst);
        
        LogUtil.e("已同步联系人：张三、李四、曾茜、杨总、号码测试、归属地运营商");
    }

}
