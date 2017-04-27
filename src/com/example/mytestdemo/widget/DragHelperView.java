package com.example.mytestdemo.widget;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author Zengcq
 * @date 2017年4月21日
 * @version 1.0
 * @description
 */
public class DragHelperView extends LinearLayout {

    private final String TAG = "RituNavi";
    private ViewDragHelper mDragger;

    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;

    private Point mAutoBackOriginPos = new Point();
    private static int curPosTop;
    private boolean isHide = true;
    private VelocityTracker mVelocityTracker;
    private float xMove;
    private float yMove;
    float startY;
    float endY;

    public DragHelperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this, 1.0f,
                new ViewDragHelper.Callback() {
                    @Override
                    public boolean tryCaptureView(View child, int pointerId) {
                        // mEdgeTrackerView禁止直接移动
//                        return child == mDragView || child == mAutoBackView;
                        return child == mAutoBackView;
                    }

                    @Override
                    public int clampViewPositionHorizontal(View child,
                            int left, int dx) {
                        
                        return 0;
                    }

                    @Override
                    public int clampViewPositionVertical(View child, int top,
                            int dy) {
                        curPosTop = top; 
                        Log.i(TAG,"top:"+top+" dy:"+dy);
                        if(top>0){
                            return 0;
                        }
                        return top;
                    }

                    // 手指释放的时候回调
                    @Override
                    public void onViewReleased(View releasedChild, float xvel,
                            float yvel) {
                        // mAutoBackView手指释放时可以自动回去
                        if (releasedChild == mAutoBackView) {
                            if(isHide){
                                if(curPosTop>-getHeight()/2-150 && yMove<0 || Math.abs(xMove)>350 && yMove<0){
                                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x,
                                            getHeight()-mAutoBackView.getHeight());
                                    isHide = false;
                                    
                                }else{
                                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x,
                                            mAutoBackOriginPos.y);
                                    isHide = true;
                                }
                            }else{
                                if(curPosTop<-getHeight()/2+150 && yMove>0 || Math.abs(xMove)>350 && yMove>0){
                                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x,
                                            mAutoBackOriginPos.y);
                                    isHide = true;
                                    
                                }else{
                                    
                                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x,
                                            getHeight()-mAutoBackView.getHeight());
                                    isHide = false;
                                }
                            }
//                            if(curPosTop<-getHeight()/2-150){
//                                mDragger.settleCapturedViewAt(mAutoBackOriginPos.x,
//                                        mAutoBackOriginPos.y);
//                                isHide = true;
//                            }else{
//                                mDragger.settleCapturedViewAt(mAutoBackOriginPos.x,
//                                        getHeight()-mAutoBackView.getHeight());
//                                isHide = false;
//                            }
                            Log.i(TAG,"x:"+mAutoBackOriginPos.x+" y:"+mAutoBackOriginPos.y+" curPosTop:"+curPosTop
                                    +" getHeight:"+getHeight()+" isHide:"+isHide);
                            invalidate();
                        }
                    }

                    // 在边界拖动时回调
                    @Override
                    public void onEdgeDragStarted(int edgeFlags, int pointerId) {
//                        mDragger.captureChildView(mEdgeTrackerView, pointerId);
                    }
                });
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP);       
    }
    

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        int action = event.getAction();
        acquireVelocityTracker(event);   
        xMove = mVelocityTracker.getXVelocity();

        switch (action) {
        case MotionEvent.ACTION_DOWN:
            startY = event.getRawY();
            break;
            
        case MotionEvent.ACTION_MOVE:
            endY = event.getRawY();
            yMove = startY-endY;
            break;
            
        case MotionEvent.ACTION_UP:
            endY = event.getRawY();
            yMove = startY-endY;
            break;

        default:
            break;
        }
        Log.i(TAG,"xMove:"+xMove +" yMove:"+yMove);
        return true;
    }
    
    private void acquireVelocityTracker(final MotionEvent event) {   
        if(null == mVelocityTracker) {   
            mVelocityTracker = VelocityTracker.obtain();   
        }   
        mVelocityTracker.computeCurrentVelocity(1000); 
        mVelocityTracker.addMovement(event);   
    }   
    
    /**  
     * 释放VelocityTracker  
     *  
     * @see android.view.VelocityTracker#clear()  
     * @see android.view.VelocityTracker#recycle()  
     */  
    private void releaseVelocityTracker() {   
        if(null != mVelocityTracker) {   
            mVelocityTracker.clear();   
            mVelocityTracker.recycle();   
            mVelocityTracker = null;   
        }   
    }   

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
            mAutoBackOriginPos.x = mAutoBackView.getLeft();
            mAutoBackOriginPos.y = mAutoBackView.getTop(); 
        
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

//        mDragView = getChildAt(1);
        mAutoBackView = getChildAt(0);
        
//        mEdgeTrackerView = getChildAt(2);
    }

}
