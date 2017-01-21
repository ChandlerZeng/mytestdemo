package com.example.mytestdemo.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import cn.ritu.bluephone.bean.BtContact;
import cn.ritu.bluephone.bean.CallRecord;

import com.example.mytestdemo.MyTestApplication;
import com.example.mytestdemo.entity.RandomId;
import com.example.mytestdemo.entity.User;
import com.example.mytestdemo.utils.LogUtil;
import com.example.mytestdemo.utils.PinyinUtils;

public class DemoDBManager {
    static private DemoDBManager dbMgr = new DemoDBManager();
    private DbOpenHelper dbHelper;

    private DemoDBManager() {
        dbHelper = DbOpenHelper.getInstance(MyTestApplication.getInstance()
                .getApplicationContext());
        dbHelper.getWritableDatabase();
    }

    public static synchronized DemoDBManager getInstance() {
        if (dbMgr == null) {
            dbMgr = new DemoDBManager();
        }
        return dbMgr;
    }

    /**
     * save contact list
     * 
     * @param contactList
     */
    synchronized public void saveContactList(List<User> contactList) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.TABLE_NAME, null, null);
            for (User user : contactList) {
                ContentValues values = new ContentValues();
                values.put(UserDao.COLUMN_NAME_ID, user.getUserName());
                if (user.getNick() != null)
                    values.put(UserDao.COLUMN_NAME_NICK, user.getNick());
                if (user.getAvatar() != null)
                    values.put(UserDao.COLUMN_NAME_AVATAR, user.getAvatar());
                db.replace(UserDao.TABLE_NAME, null, values);
            }
        }
    }

    /**
     * get contact list
     * 
     * @return
     */
    synchronized public Map<String, User> getContactList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Map<String, User> users = new Hashtable<String, User>();
        if (db.isOpen()) {
            Cursor cursor = db
                    .rawQuery(
                            "select * from " + UserDao.TABLE_NAME /* + " desc" */,
                            null);
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_ID));
                String nick = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_NICK));
                String avatar = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_AVATAR));
                User user = new User(username, nick, avatar);
                // user.setNick(nick);
                // user.setAvatar(avatar);
                // if (username.equals(Constant.NEW_FRIENDS_USERNAME) ||
                // username.equals(Constant.GROUP_USERNAME)
                // || username.equals(Constant.CHAT_ROOM)||
                // username.equals(Constant.CHAT_ROBOT)) {
                // user.setInitialLetter("");
                // } else {
                // EaseCommonUtils.setUserInitialLetter(user);
                // }
                users.put(username, user);
            }
            cursor.close();
        }
        return users;
    }

    synchronized public List<User> getContactLists() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<User> users = new ArrayList<User>();
        if (db.isOpen()) {
            Cursor cursor = db
                    .rawQuery(
                            "select * from " + UserDao.TABLE_NAME /* + " desc" */,
                            null);
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_ID));
                String nick = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_NICK));
                String avatar = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_AVATAR));
                User user = new User(username, nick, avatar);
                // user.setNick(nick);
                // user.setAvatar(avatar);
                // if (username.equals(Constant.NEW_FRIENDS_USERNAME) ||
                // username.equals(Constant.GROUP_USERNAME)
                // || username.equals(Constant.CHAT_ROOM)||
                // username.equals(Constant.CHAT_ROBOT)) {
                // user.setInitialLetter("");
                // } else {
                // EaseCommonUtils.setUserInitialLetter(user);
                // }
                users.add(user);
            }
            cursor.close();
        }
        return users;
    }

    /**
     * delete a contact
     * 
     * @param username
     */
    synchronized public void deleteContact(String username) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.TABLE_NAME, UserDao.COLUMN_NAME_ID + " = ?",
                    new String[] { username });
        }
    }

    /**
     * save a contact
     * 
     * @param user
     */
    synchronized public void saveContact(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDao.COLUMN_NAME_ID, user.getUserName());
        if (user.getNick() != null)
            values.put(UserDao.COLUMN_NAME_NICK, user.getNick());
        if (user.getAvatar() != null)
            values.put(UserDao.COLUMN_NAME_AVATAR, user.getAvatar());
        if (db.isOpen()) {
            db.replace(UserDao.TABLE_NAME, null, values);
        }
    }

    public void setDisabledGroups(List<String> groups) {
        setList(UserDao.COLUMN_NAME_DISABLED_GROUPS, groups);
    }

    public List<String> getDisabledGroups() {
        return getList(UserDao.COLUMN_NAME_DISABLED_GROUPS);
    }

    public void setDisabledIds(List<String> ids) {
        setList(UserDao.COLUMN_NAME_DISABLED_IDS, ids);
    }

    public List<String> getDisabledIds() {
        return getList(UserDao.COLUMN_NAME_DISABLED_IDS);
    }

    synchronized private void setList(String column, List<String> strList) {
        StringBuilder strBuilder = new StringBuilder();

        for (String hxid : strList) {
            strBuilder.append(hxid).append("$");
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(column, strBuilder.toString());

            db.update(UserDao.PREF_TABLE_NAME, values, null, null);
        }
    }

    synchronized private List<String> getList(String column) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select " + column + " from "
                + UserDao.PREF_TABLE_NAME, null);
        if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }

        String strVal = cursor.getString(0);
        if (strVal == null || strVal.equals("")) {
            return null;
        }

        cursor.close();

        String[] array = strVal.split("$");

        if (array.length > 0) {
            List<String> list = new ArrayList<String>();
            Collections.addAll(list, array);
            return list;
        }

        return null;
    }

    synchronized public void saveRandomIds(List<String> randomIds) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            int count = 0;
            for (String ids : randomIds) {
                Cursor cursor = db.rawQuery("select * from "
                        + UserDao.Random_IDS_TABLE_NAME + " where "
                        + UserDao.COLUMN_NAME_RANDOM_IDS + "='" + ids + "'",
                        null);
                if (cursor.moveToNext()) {
                    String id = cursor.getString(cursor
                            .getColumnIndex(UserDao.COLUMN_NAME_RANDOM_IDS));
                    LogUtil.e("id:" + id);
                    if (id != null && !id.equals("")) {
                        count++;
                    }
                } else {
                    ContentValues values = new ContentValues();
                    values.put(UserDao.COLUMN_NAME_RANDOM_IDS, ids);
                    db.insert(UserDao.Random_IDS_TABLE_NAME, null, values);
                }
                cursor.close();
            }
            if (count > 0) {
                Intent intent = new Intent();
                intent.setAction("id_duplication_indication_action");
                intent.putExtra("count", count);
                MyTestApplication.getInstance().getApplicationContext()
                        .sendBroadcast(intent);
            }
        }
    }

    synchronized public void deleteRandomId(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.Random_IDS_TABLE_NAME,
                    UserDao.COLUMN_NAME_RANDOM_AUTO_IDS + " = ?",
                    new String[] { id });
        }
    }

    synchronized public void deleteAllRandomIds() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.Random_IDS_TABLE_NAME, null, null);
        }
    }

    synchronized public List<RandomId> getRandomIds() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<RandomId> list = new ArrayList<RandomId>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from "
                    + UserDao.Random_IDS_TABLE_NAME + " order by "
                    + UserDao.COLUMN_NAME_RANDOM_AUTO_IDS + " desc", null);
            while (cursor.moveToNext()) {
                RandomId randomId = new RandomId();
                String id = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_RANDOM_AUTO_IDS));
                String random_id = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_RANDOM_IDS));
                randomId.setId(id);
                randomId.setRandomId(random_id);
                list.add(randomId);
            }
            cursor.close();
        }
        return list;
    }

    /**
     * save a message
     * 
     * @param message
     * @return return cursor of the message
     */
    /*
     * public synchronized Integer saveMessage(InviteMessage message){
     * SQLiteDatabase db = dbHelper.getWritableDatabase(); int id = -1;
     * if(db.isOpen()){ ContentValues values = new ContentValues();
     * values.put(InviteMessgeDao.COLUMN_NAME_FROM, message.getFrom());
     * values.put(InviteMessgeDao.COLUMN_NAME_GROUP_ID, message.getGroupId());
     * values.put(InviteMessgeDao.COLUMN_NAME_GROUP_Name,
     * message.getGroupName()); values.put(InviteMessgeDao.COLUMN_NAME_REASON,
     * message.getReason()); values.put(InviteMessgeDao.COLUMN_NAME_TIME,
     * message.getTime()); values.put(InviteMessgeDao.COLUMN_NAME_STATUS,
     * message.getStatus().ordinal());
     * values.put(InviteMessgeDao.COLUMN_NAME_GROUPINVITER,
     * message.getGroupInviter()); db.insert(InviteMessgeDao.TABLE_NAME, null,
     * values);
     * 
     * Cursor cursor = db.rawQuery("select last_insert_rowid() from " +
     * InviteMessgeDao.TABLE_NAME,null); if(cursor.moveToFirst()){ id =
     * cursor.getInt(0); }
     * 
     * cursor.close(); } return id; }
     */

    /**
     * update message
     * 
     * @param msgId
     * @param values
     */
    synchronized public void updateMessage(int msgId, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.update(InviteMessgeDao.TABLE_NAME, values,
                    InviteMessgeDao.COLUMN_NAME_ID + " = ?",
                    new String[] { String.valueOf(msgId) });
        }
    }

    /**
     * get messges
     * 
     * @return
     */
    /*
     * synchronized public List<InviteMessage> getMessagesList(){ SQLiteDatabase
     * db = dbHelper.getReadableDatabase(); List<InviteMessage> msgs = new
     * ArrayList<InviteMessage>(); if(db.isOpen()){ Cursor cursor =
     * db.rawQuery("select * from " + InviteMessgeDao.TABLE_NAME +
     * " desc",null); while(cursor.moveToNext()){ InviteMessage msg = new
     * InviteMessage(); int id =
     * cursor.getInt(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_ID));
     * String from =
     * cursor.getString(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_FROM
     * )); String groupid =
     * cursor.getString(cursor.getColumnIndex(InviteMessgeDao
     * .COLUMN_NAME_GROUP_ID)); String groupname =
     * cursor.getString(cursor.getColumnIndex
     * (InviteMessgeDao.COLUMN_NAME_GROUP_Name)); String reason =
     * cursor.getString
     * (cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_REASON)); long time =
     * cursor.getLong(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_TIME));
     * int status =
     * cursor.getInt(cursor.getColumnIndex(InviteMessgeDao.COLUMN_NAME_STATUS));
     * String groupInviter =
     * cursor.getString(cursor.getColumnIndex(InviteMessgeDao
     * .COLUMN_NAME_GROUPINVITER));
     * 
     * msg.setId(id); msg.setFrom(from); msg.setGroupId(groupid);
     * msg.setGroupName(groupname); msg.setReason(reason); msg.setTime(time);
     * msg.setGroupInviter(groupInviter);
     * 
     * if(status == InviteMesageStatus.BEINVITEED.ordinal())
     * msg.setStatus(InviteMesageStatus.BEINVITEED); else if(status ==
     * InviteMesageStatus.BEAGREED.ordinal())
     * msg.setStatus(InviteMesageStatus.BEAGREED); else if(status ==
     * InviteMesageStatus.BEREFUSED.ordinal())
     * msg.setStatus(InviteMesageStatus.BEREFUSED); else if(status ==
     * InviteMesageStatus.AGREED.ordinal())
     * msg.setStatus(InviteMesageStatus.AGREED); else if(status ==
     * InviteMesageStatus.REFUSED.ordinal())
     * msg.setStatus(InviteMesageStatus.REFUSED); else if(status ==
     * InviteMesageStatus.BEAPPLYED.ordinal())
     * msg.setStatus(InviteMesageStatus.BEAPPLYED); else if(status ==
     * InviteMesageStatus.GROUPINVITATION.ordinal())
     * msg.setStatus(InviteMesageStatus.GROUPINVITATION); else if(status ==
     * InviteMesageStatus.GROUPINVITATION_ACCEPTED.ordinal())
     * msg.setStatus(InviteMesageStatus.GROUPINVITATION_ACCEPTED); else
     * if(status == InviteMesageStatus.GROUPINVITATION_DECLINED.ordinal())
     * msg.setStatus(InviteMesageStatus.GROUPINVITATION_DECLINED);
     * 
     * msgs.add(msg); } cursor.close(); } return msgs; }
     */

    /**
     * delete invitation message
     * 
     * @param from
     */
    synchronized public void deleteMessage(String from) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(InviteMessgeDao.TABLE_NAME,
                    InviteMessgeDao.COLUMN_NAME_FROM + " = ?",
                    new String[] { from });
        }
    }

    synchronized int getUnreadNotifyCount() {
        int count = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select "
                    + InviteMessgeDao.COLUMN_NAME_UNREAD_MSG_COUNT + " from "
                    + InviteMessgeDao.TABLE_NAME, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        return count;
    }

    synchronized void setUnreadNotifyCount(int count) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(InviteMessgeDao.COLUMN_NAME_UNREAD_MSG_COUNT, count);

            db.update(InviteMessgeDao.TABLE_NAME, values, null, null);
        }
    }

    synchronized public void closeDB() {
        if (dbHelper != null) {
            dbHelper.closeDB();
        }
        dbMgr = null;
    }

    /**
     * Save Robot list
     */
    /*
     * synchronized public void saveRobotList(List<RobotUser> robotList) {
     * SQLiteDatabase db = dbHelper.getWritableDatabase(); if (db.isOpen()) {
     * db.delete(UserDao.ROBOT_TABLE_NAME, null, null); for (RobotUser item :
     * robotList) { ContentValues values = new ContentValues();
     * values.put(UserDao.ROBOT_COLUMN_NAME_ID, item.getUsername()); if
     * (item.getNick() != null) values.put(UserDao.ROBOT_COLUMN_NAME_NICK,
     * item.getNick()); if (item.getAvatar() != null)
     * values.put(UserDao.ROBOT_COLUMN_NAME_AVATAR, item.getAvatar());
     * db.replace(UserDao.ROBOT_TABLE_NAME, null, values); } } }
     */

    /**
     * load robot list
     */
    /*
     * synchronized public Map<String, RobotUser> getRobotList() {
     * SQLiteDatabase db = dbHelper.getReadableDatabase(); Map<String,
     * RobotUser> users = null; if (db.isOpen()) { Cursor cursor =
     * db.rawQuery("select * from " + UserDao.ROBOT_TABLE_NAME, null);
     * if(cursor.getCount()>0){ users = new Hashtable<String, RobotUser>(); }
     * while (cursor.moveToNext()) { String username =
     * cursor.getString(cursor.getColumnIndex(UserDao.ROBOT_COLUMN_NAME_ID));
     * String nick =
     * cursor.getString(cursor.getColumnIndex(UserDao.ROBOT_COLUMN_NAME_NICK));
     * String avatar =
     * cursor.getString(cursor.getColumnIndex(UserDao.ROBOT_COLUMN_NAME_AVATAR
     * )); RobotUser user = new RobotUser(username); user.setNick(nick);
     * user.setAvatar(avatar); String headerName = null; if
     * (!TextUtils.isEmpty(user.getNick())) { headerName = user.getNick(); }
     * else { headerName = user.getUsername(); }
     * if(Character.isDigit(headerName.charAt(0))){ user.setInitialLetter("#");
     * }else{
     * user.setInitialLetter(HanziToPinyin.getInstance().get(headerName.substring
     * (0, 1)).get(0).target .substring(0, 1).toUpperCase()); char header =
     * user.getInitialLetter().toLowerCase().charAt(0); if (header < 'a' ||
     * header > 'z') { user.setInitialLetter("#"); } }
     * 
     * try { users.put(username, user); } catch (Exception e) {
     * e.printStackTrace(); } } cursor.close(); } return users; }
     */

    public List<BtContact> getLoacalPhoneContacts(Context context) {
        List<BtContact> list = new ArrayList<BtContact>();
        // 获取联系人
        Uri rawContacts = Uri
                .parse("content://com.android.contacts/raw_contacts");
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(rawContacts, null, null, null,
                null);
        while (cursor.moveToNext()) {
            BtContact contact = new BtContact();
            String contactId = cursor.getString(cursor
                    .getColumnIndex("contact_id"));
            Uri dataUri = Uri.parse("content://com.android.contacts/data");
            Cursor dataCursor = contentResolver.query(dataUri, null,
                    "contact_id=?", new String[] { contactId }, null);
            while (dataCursor.moveToNext()) {
                String data1 = dataCursor.getString(dataCursor
                        .getColumnIndex("data1"));
                String mimetype = dataCursor.getString(dataCursor
                        .getColumnIndex("mimetype"));
                if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                    contact.setNumber(data1);
                } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                    contact.setName(data1);
                    getSpellByName(data1, contact);
                }
            }
            list.add(contact);
            dataCursor.close();
        }
        cursor.close();
        return list;
    }

    public List<BtContact> getLoacalPhoneContactsByKey(Context context,
            String key) {
        List<BtContact> list = new ArrayList<BtContact>();
        // 获取联系人
        Uri rawContacts = Uri
                .parse("content://com.android.contacts/raw_contacts");
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(rawContacts, null,
                "sort_key like?", new String[] { key + "%" }, null);
        while (cursor.moveToNext()) {
            BtContact contact = new BtContact();
            String contactId = cursor.getString(cursor
                    .getColumnIndex("contact_id"));
            Uri dataUri = Uri.parse("content://com.android.contacts/data");
            Cursor dataCursor = contentResolver.query(dataUri, null,
                    "contact_id=?", new String[] { contactId }, null);
            while (dataCursor.moveToNext()) {
                String data1 = dataCursor.getString(dataCursor
                        .getColumnIndex("data1"));
                String mimetype = dataCursor.getString(dataCursor
                        .getColumnIndex("mimetype"));
                if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                    contact.setNumber(data1);
                } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                    contact.setName(data1);
                    getSpellByName(data1, contact);
                }
            }
            list.add(contact);
            dataCursor.close();
        }
        cursor.close();
        return list;
    }

    private void getSpellByName(String name, BtContact contact) {
        String spell = "";
        String initial = "";
        char[] spellArray;
        if (name != null && !name.equals("")) {
            spell = PinyinUtils.getPingYin(name);
            spell = spell.toLowerCase();
            spellArray = spell.toCharArray();
            initial = ("" + spellArray[0]).toUpperCase();

            if (initial.matches("[A-Z]")) {
                initial = initial.toUpperCase();
            } else {
                initial = "#";
            }
            contact.setNameSpell(spell);
            contact.setNameInital(initial);
        }
    }

    synchronized public String getNameByNumber(String number, Context context) {
        String name = null;
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(dataUri, null, "data1 =?",
                new String[] { number }, null);
        if (cursor.moveToNext()) {
            String contactId = cursor.getString(cursor
                    .getColumnIndex("raw_contact_id"));
            Cursor cursor2 = contentResolver.query(dataUri, null,
                    "raw_contact_id =?", new String[] { contactId }, null);
            while (cursor2.moveToNext()) {
                String mineType = cursor2.getString(cursor2
                        .getColumnIndex("mimetype"));
                if (mineType.equals("vnd.android.cursor.item/name")) {
                    name = cursor2.getString(cursor2.getColumnIndex("data1"));
                }
            }
            cursor2.close();
        }
        cursor.close();
        return name;
    }

    synchronized public void saveCallRecord(CallRecord callRecord) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(UserDao.COLUMN_NAME_CALL_RECORD_NUMBER,
                    callRecord.getNumber());
            values.put(UserDao.COLUMN_NAME_CALL_RECORD_DATE,
                    callRecord.getDate());
            values.put(UserDao.COLUMN_NAME_CALL_RECORD_TYPE,
                    callRecord.getType());
            if (callRecord.getName() != null) {
                values.put(UserDao.COLUMN_NAME_CALL_RECORD_NAME,
                        callRecord.getName());
            }
            if (callRecord.getTime() != null) {
                values.put(UserDao.COLUMN_NAME_CALL_RECORD_DURATION,
                        callRecord.getTime());
            }
            db.insert(UserDao.CALL_RECORD_TABLE_NAME, null, values);
        }
    }

    synchronized public List<CallRecord> getCallRecord() {
        List<CallRecord> list = new ArrayList<CallRecord>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.query(UserDao.CALL_RECORD_TABLE_NAME, null,
                    null, null, null, null, UserDao.COLUMN_NAME_CALL_RECORD_ID+" DESC");

            while (cursor.moveToNext()) {
                CallRecord callRecord = new CallRecord();
                String number = cursor
                        .getString(cursor
                                .getColumnIndex(UserDao.COLUMN_NAME_CALL_RECORD_NUMBER));
                String name = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_CALL_RECORD_NAME));
                String date = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_CALL_RECORD_DATE));
                String type = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_CALL_RECORD_TYPE));
                String duration = cursor
                        .getString(cursor
                                .getColumnIndex(UserDao.COLUMN_NAME_CALL_RECORD_DURATION));
                callRecord.setDate(date);
                callRecord.setNumber(number);
                callRecord.setName(name);
                callRecord.setTime(duration);
                callRecord.setType(type);
                list.add(callRecord);
            }
            cursor.close();
        }
        return list;
    }

}
