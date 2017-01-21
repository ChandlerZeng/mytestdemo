package com.example.mytestdemo.adapter;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.ritu.bluephone.bean.CallRecord;

import com.example.mytestdemo.R;
import com.example.mytestdemo.utils.DateUtil;

public class CallRecordListAdapter extends BaseAdapter
{


    private Context mContext;
    private List<CallRecord> records;

    public CallRecordListAdapter(Context context, List<CallRecord> records)
    {
        this.mContext = context;
        this.records = records;
        
    }
    public void setCallRecords(List<CallRecord> records)
    {
        this.records = records;
        this.notifyDataSetChanged();
    }
    
    @Override
    public int getCount()
    {
        if(records != null) return records.size();
        return 0;
    }

    @Override
    public Object getItem(int arg0)
    {
        return records.get(arg0);  
    }

    @Override
    public long getItemId(int arg0)
    {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2)
    {
        ViewHolder holder = null;
        if (arg1 == null) {  
            holder = new ViewHolder();    
            arg1 = LayoutInflater.from(mContext).inflate(R.layout.bt_phone_call_record_list_item, null);
            holder.name= (TextView) arg1.findViewById(R.id.call_name);
            holder.telephoneNum = (TextView) arg1.findViewById(R.id.telephone_num);  
            holder.callDate = (TextView) arg1.findViewById(R.id.call_date);  
            holder.callTime = (TextView) arg1.findViewById(R.id.call_time); 
            holder.imageView = (ImageView) arg1.findViewById(R.id.imageview); 
            arg1.setTag(holder);             
        } else {    
            holder = (ViewHolder) arg1.getTag();  
        } 
        
        if(records.get(arg0).getName()==null || records.get(arg0).getName().equals(""))
        {
        	holder.name.setText("");
        }else
        {
            holder.name.setText(records.get(arg0).getName());
        }
        holder.telephoneNum.setText(records.get(arg0).getNumber());
        long timeStamp = Long.parseLong(records.get(arg0).getDate());
        holder.callDate.setText(DateUtil.converTime(timeStamp));
        holder.callTime.setText(DateUtil.converTime(timeStamp));
        
        holder.name.setTextColor(mContext.getResources().getColor(R.color.white));
        holder.telephoneNum.setTextColor(mContext.getResources().getColor(R.color.white));
        holder.callDate.setTextColor(mContext.getResources().getColor(R.color.white));
        holder.callTime.setTextColor(mContext.getResources().getColor(R.color.white));
        if(records.get(arg0).getType().equals("3"))
        {
        	holder.imageView.setVisibility(View.GONE);
        }
        else if(records.get(arg0).getType().equals("2"))
        {
        	holder.imageView.setVisibility(View.VISIBLE);
        	holder.imageView.setImageResource(R.drawable.missed);
        	
            holder.name.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.telephoneNum.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.callDate.setTextColor(mContext.getResources().getColor(R.color.red));
            holder.callTime.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        else if(records.get(arg0).getType().equals("1"))
        {
        	holder.imageView.setVisibility(View.VISIBLE);
        	holder.imageView.setImageResource(R.drawable.dialed);
        }
        
        return arg1;
    }

    private class ViewHolder 
    {
        TextView name;
        TextView telephoneNum;
        TextView callDate;
        TextView callTime;
        ImageView imageView;
    }
    
}
