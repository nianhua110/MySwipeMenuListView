package io.github.nianhua110.myswipemenulistview;

import android.content.Context;
import android.graphics.Point;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by kankan on 2016/5/10.
 */
public class MySwipeMenuListView extends ListView {
     String TAG = this.getClass().getSimpleName();
    private int MAX_Y = 5;
    private int MAX_X = 3;
    private float mDownX ;
    private float mDownY;
    private  SwipeMenuLayout mTouchView;
    private int mTouchPosition;
    private int mDirection = 1;//swipe from right to left by default

    public void setmOnMenuItemClickListener(OnMenuItemClickListener mOnMenuItemClickListener) {
        this.mOnMenuItemClickListener = mOnMenuItemClickListener;
    }

    private  OnMenuItemClickListener mOnMenuItemClickListener;



    public void setSwipeDirection(int direction) {
        mDirection = direction;
    }
    public MySwipeMenuListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MySwipeMenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySwipeMenuListView(Context context) {
        super(context);
        init(context);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {

        super.setAdapter(new SwipeMenuAdapter(getContext(), adapter){
            @Override
            public void createMenu(SwipeMenu menu) {
                super.createMenu(menu);
            }

            @Override
            public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index) {
                /*响应用户的点击菜单事件并关闭菜单*/
              //  super.onItemClick(view, menu, index);
                if(mOnMenuItemClickListener != null){
                    mOnMenuItemClickListener.onMenuItemClick(view.getPosition(),menu, index);
                }

                Log.i(TAG, "OnItemClick");
                if(mTouchView!= null){
                    mTouchView.smoothCloseMenu();
                }
            }
        });
    }

    void init(Context context){
        MAX_X = dp2dx(MAX_X);
        MAX_Y = dp2dx(MAX_Y);
        Log.i(TAG, "max_x is"+MAX_X + "  max_y is "+MAX_Y);
        //swipeMenuLayout = new SwipeMenuLayout(context);
    }
    private int dp2dx(int dp){
        DisplayMetrics displayMetrics =   getContext().getResources().getDisplayMetrics();
        int tempHeight = displayMetrics.heightPixels;
        int tempWidth = displayMetrics.widthPixels;
        float temp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        Log.i(TAG, "height pixels is " + tempHeight + "  width pixels is " + tempWidth);

        return  (int)temp;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent!");
        if (mTouchView != null && mTouchView.isOpen() ) {
          //  return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        View view;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                int oldPosition = mTouchPosition;

                Log.i(TAG, "Action Down");
                if(mTouchView != null && mTouchView.isOpen() ){
                    return  true;
                }
                mTouchPosition = pointToPosition((int)ev.getX(),(int)ev.getY());
                view =  getChildAt(mTouchPosition - getFirstVisiblePosition());
                if(view instanceof  SwipeMenuLayout){
                    mTouchView =(SwipeMenuLayout)view;
                    mTouchView.setPosition(mTouchPosition);
                    mTouchView.setSwipeDirection(mDirection);
                    mTouchView.onSwipe(ev);
                }
                break;
            case MotionEvent.ACTION_MOVE:
               float dx=Math.abs(mDownX- ev.getX());
                float dy = Math.abs(mDownY - ev.getY());
                mTouchPosition = pointToPosition((int)ev.getX(),(int)ev.getY());
                if(mTouchPosition != mTouchView.getPosition())
                {
                    break;
                }

                if(dx > MAX_X){
                 //   Log.i(TAG, "enough offset!");
                }

                 if(mTouchView != null){
                    mTouchView.onSwipe(ev);
                }
                Log.i(TAG,"Action Move");
            break;
             //   return  true;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "ACtion Up");
                if(mTouchView != null){
                    mTouchView.onSwipe(ev);
               }
             break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    public static interface OnMenuItemClickListener {
        boolean onMenuItemClick(int position, SwipeMenu menu, int index);
    }

}
