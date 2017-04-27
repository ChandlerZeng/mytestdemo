package com.example.mytestdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mytestdemo.utils.LogUtil;
import com.example.mytestdemo.widget.QQListView;
import com.example.mytestdemo.widget.SwipeListView;
import com.example.mytestdemo.widget.SwipeListView.OnDeleteListener;

public class SwipeListViewActivity extends Activity implements QQListView.DelButtonClickListener {

    private CustomListViewAdapter adapter;
//    private SwipeListView listView;
    private QQListView listView;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_list_view);
        getListContent();
        listView = (QQListView) findViewById(R.id.qq_list_view);
        adapter = new CustomListViewAdapter(this, 0, list);
        listView.setAdapter(adapter);
        listView.setDelButtonClickListener(this);
    }

    private void getListContent() {
        list = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            list.add("item " + i);
        }
    }

    public class CustomListViewAdapter extends ArrayAdapter<String> {

        private Context context;
        public CustomListViewAdapter(Context context, int textViewResourceId,
                List<String> objects) {
            super(context, textViewResourceId, objects);
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(
                        R.layout.swipe_list_view_item, null);
            } else {
                view = convertView;
            }

            TextView contentTv = (TextView) view.findViewById(R.id.content_tv);
            contentTv.setText(getItem(position));
//            view.setOnTouchListener(new View.OnTouchListener() {
//
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    // TODO Auto-generated method stub
//                    switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        v.setBackgroundColor(context.getResources().getColor(R.color.blue));
//                        LogUtil.i(" v ACTION_DOWN");
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        v.setBackgroundColor(context.getResources().getColor(R.color.transparent));
//                        LogUtil.i("v ACTION_UP");
//                        break;
//                    }
//                    return true;
//                }
//            });

            return view;
        }

    }

//    @Override
//    public void OnDelete(int position) {
//        list.remove(position);
//        adapter.notifyDataSetChanged();
//    }

    @Override
    public void clickHappend(int position) {
        // TODO Auto-generated method stub
        list.remove(position);
        adapter.notifyDataSetChanged();
    }
}
