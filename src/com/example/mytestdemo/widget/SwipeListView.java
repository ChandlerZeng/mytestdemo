package com.example.mytestdemo.widget;

import com.example.mytestdemo.R;
import com.example.mytestdemo.utils.LogUtil;

import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.view.View;

/**
 * @author Zengcq
 * @date 2017年4月20日
 * @version 1.0
 * @description
 */
public class SwipeListView extends ListView implements View.OnTouchListener,
        OnGestureListener {

    private OnDeleteListener onDeleteListener;
    private GestureDetector mGestureDetector;
    private boolean isDeleteShown;
    private int selectItem;
    private View deleteBtn;
    private ViewGroup itemLayout;

    public interface OnDeleteListener {
        void OnDelete(int position);
    }

    public SwipeListView(Context context, AttributeSet attribute) {
        super(context, attribute);
        mGestureDetector = new GestureDetector(getContext(), this);
        setOnTouchListener(this);
    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.onDeleteListener = listener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        if (!isDeleteShown) {
            selectItem = pointToPosition((int) e.getX(), (int) e.getY());
        }
        LogUtil.i("swipe onDown");
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
            float velocityY) {
        // 如果当前删除按钮没有显示出来，并且x方向滑动的速度大于y方向的滑动速度
        if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY)) {
            deleteBtn = LayoutInflater.from(getContext()).inflate(
                    R.layout.swipe_list_view_delete_btn, null);
            deleteBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    hideDeleteBtn();
                    onDeleteListener.OnDelete(selectItem);
                }
            });

            itemLayout = (ViewGroup) getChildAt(selectItem
                    - getFirstVisiblePosition());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            if (itemLayout != null) {
                itemLayout.addView(deleteBtn, params);
                isDeleteShown = true;
                 itemLayout.setBackgroundColor(getResources().getColor(R.color.blue));
            }
            LogUtil.i("swipe onFling");
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub
        LogUtil.i("swipe onLongPress");
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        // TODO Auto-generated method stub
        LogUtil.i("swipe onScroll");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub
        LogUtil.i("swipe onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        // itemLayout.setBackgroundColor(getResources().getColor(R.color.white));
        LogUtil.i("swipe onSingleTapUp");
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        LogUtil.i("swipe onTouch");
//        switch (event.getAction()) {
//        case MotionEvent.ACTION_DOWN:
//            if (isDeleteShown) {
//                hideDeleteBtn();
//
//            } else {
//                return mGestureDetector.onTouchEvent(event);
//            }
//            break;
//
//        case MotionEvent.ACTION_UP:
//            if (itemLayout != null) 
//            itemLayout.setBackgroundColor(getResources().getColor(R.color.transparent));
//            break;
//
//        default:
//            break;
//        }
       
        if (isDeleteShown) {
            hideDeleteBtn();

        } else {
            return mGestureDetector.onTouchEvent(event);
        }
        return false;
    }

    public boolean isDeleteShown() {
        return isDeleteShown;
    }

    private void hideDeleteBtn() {
        itemLayout.removeView(deleteBtn);
        isDeleteShown = false;
        deleteBtn = null;
    }

}
