package com.example.mytestdemo.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import cn.ritu.bluephone.bean.BtContact;

import com.example.mytestdemo.R;
import com.example.mytestdemo.utils.LogUtil;

/**
 * @author Zengcq
 * @date 2017Äê1ÔÂ18ÈÕ
 * @version 1.0
 * @description
 */
public class PbAdapter extends BaseAdapter implements SectionIndexer {
    private List<BtContact> list = null;
    Set<String> initalList = new HashSet<String>();
    List<String> nameList = new ArrayList<String>();
    private Context mContext;
    private OnLetterChangedListener onLetterChangedListener;

    public PbAdapter(Context mContext, List<BtContact> list) {
        this.mContext = mContext;
        this.list = list;
    }

    public void updateListView(List<BtContact> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final BtContact contact = list.get(arg0);
        if (arg1 == null) {
            viewHolder = new ViewHolder();
            arg1 = LayoutInflater.from(mContext).inflate(
                    R.layout.bt_phone_book_list_item, null);
            viewHolder.name = (TextView) arg1.findViewById(R.id.name);
            viewHolder.number = (TextView) arg1.findViewById(R.id.number);
            viewHolder.title = (TextView) arg1.findViewById(R.id.title);
            viewHolder.line = arg1.findViewById(R.id.line);
            arg1.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) arg1.getTag();
        }

        if (!initalList.contains(contact.getNameInital())) {
            initalList.add(contact.getNameInital());
            nameList.add(contact.getName());
        }

        /*
         * int section = getSectionForPosition(arg0);
         * 
         * if(arg0 == getPositionForSection(section)) {
         * viewHolder.title.setVisibility(View.VISIBLE);
         * viewHolder.line.setVisibility(View.VISIBLE);
         * viewHolder.title.setText(contact.getNameInital());
         * if(onLetterChangedListener != null) {
         * onLetterChangedListener.onLetterChanged(contact.getNameInital()); } }
         */
        if (nameList.contains(contact.getName())) {
            boolean isSameName = false;
            if (arg0 != 0
                    && list.get(arg0).getName()
                            .equals(list.get(arg0 - 1).getName())) {
                isSameName = true;
            }
            if (!isSameName) {
                viewHolder.title.setVisibility(View.VISIBLE);
                viewHolder.line.setVisibility(View.VISIBLE);
                viewHolder.title.setText(contact.getNameInital());
            } else {
                viewHolder.title.setVisibility(View.GONE);
                viewHolder.line.setVisibility(View.GONE);
            }
            /*
             * if(onLetterChangedListener != null) {
             * onLetterChangedListener.onLetterChanged(contact.getNameInital());
             * }
             */
        } else {
            viewHolder.title.setVisibility(View.GONE);
            viewHolder.line.setVisibility(View.GONE);
        }

        viewHolder.name.setText(contact.getName());
        viewHolder.number.setText(contact.getNumber());

        return arg1;
    }

    @Override
    public int getPositionForSection(int arg0) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getNameInital();
            int firstChar = sortStr.toUpperCase().charAt(0);
            LogUtil.i("firstChar£º" + firstChar);
            if (firstChar == arg0) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSectionForPosition(int arg0) {
        int section = (int) list.get(arg0).getNameInital().charAt(0);
        LogUtil.i("section£º" + section);
        return section;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    final static class ViewHolder {
        TextView name;
        TextView number;
        TextView title;
        View line;
    }

    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();

        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    public void setLetterChange(OnLetterChangedListener onLetterChangedListener) {
        this.onLetterChangedListener = onLetterChangedListener;
    }

    public interface OnLetterChangedListener {
        public void onLetterChanged(String s);
    }
}
