package io.github.nianhua110.myswipemenulistview;

import android.content.Context;
import android.graphics.Point;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
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
    private  SwipeMenuLayout swipeMenuLayout;
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
        super.setAdapter(adapter);
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
      //  return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                Log.i(TAG, "down x is "+mDownX + "  down y is "+ mDownY);
            case MotionEvent.ACTION_MOVE:
                float dx=Math.abs(mDownX- ev.getX());
                float dy = Math.abs(mDownY - ev.getY());
                Log.i(TAG, "on touch move x "+ev.getX() +" y "+ev.getY());
                Log.i(TAG, "dx "+dx+" dy "+dy);

                if(dx > MAX_X){
                    Log.i(TAG, "enough offset!");
                }

                break;
        }
        return super.onTouchEvent(ev);
    }
}