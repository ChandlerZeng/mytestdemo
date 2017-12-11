package com.example.mytestdemo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.mytestdemo.entity.CityBean;
import com.example.mytestdemo.entity.ProvinceBean;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProvinceCityUtil extends SQLiteOpenHelper
{
    public static String PROVINCE_NAME = "Name";
    public static String CITY_NAME = "Name";
    public static String _ID = "_id";
    public static int NAME_INDEX = 1;
    private static String DB_NAME = "map.db";
    private static String PROVINCE_TABLE = "Province";
    private static String CITY_TABLE = "City";
    private static String CITY_FILE = "city.sql";
    private static String PROVINCE_FILE = "province.sql";

    private Context context;
    private static String CREATE_PROVINCE = "CREATE TABLE Province(  _id int not NULL,"+  
            "Name varchar(50) not NULL, " + 
            "orderid int not NULL);";
    
    private static String CREATE_CITY = "CREATE TABLE City(_id int not NULL," +
    		"ProvinceId int not NULL," +
    		"Name varchar(50) not NULL," +
    		"AreaCode varchar(50) not NULL);";
    
    public ProvinceCityUtil(Context context)
    {
        super(context, DB_NAME, null, 15);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        AssetManager manager = context.getAssets();
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(manager.open(CITY_FILE),"utf-8"));
            String line = null;
            while((line = br.readLine()) != null) {
                db.execSQL(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(manager.open(PROVINCE_FILE),"utf-8"));
            String line = null;
            while((line = br.readLine()) != null) {
                db.execSQL(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String dropString = "drop table if exists " + PROVINCE_TABLE;
        db.execSQL(dropString);

        dropString = "drop table if exists " + CITY_TABLE;
        db.execSQL(dropString);
        onCreate(db);
    }

    public Cursor getProvince()
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select _id, Name from Province", null);
    }

    public Cursor getCities(long pid)
    {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select _id, Name from City where ProvinceId=?", new String[]{Long.toString(pid)} );
    }
    
    public List<ProvinceBean> getProvinceList(){
    	List<ProvinceBean> listProvinces = new ArrayList<ProvinceBean>();
    	Cursor cursor = getProvince();
    	while (cursor.moveToNext()) {
    		ProvinceBean bean = new ProvinceBean();
			String name = cursor.getString(cursor.getColumnIndex(PROVINCE_NAME));
			int id = cursor.getInt(cursor.getColumnIndex(_ID));
			bean.setName(name);
			bean.setId(id);
			listProvinces.add(bean);
		}
    	cursor.close();
    	return listProvinces;
    }
    
    public List<CityBean> getCityList(long pid){
    	List<CityBean> listCities = new ArrayList<CityBean>();
    	Cursor cursor = getCities(pid);
    	while (cursor.moveToNext()) {
    		CityBean bean = new CityBean();
			String name = cursor.getString(cursor.getColumnIndex(CITY_NAME));
			int id = cursor.getInt(cursor.getColumnIndex(_ID));
			bean.setName(name);
			bean.setId(id);
			listCities.add(bean);
		}
    	cursor.close();
    	return listCities;
    }

}
