package io.github.nianhua110.myswipemenulistview;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by kankan on 2016/5/11.
 */
public class SwipeMenuLayout extends FrameLayout {
    String TAG= this.getClass().getSimpleName();
    private  View mContentView;
    private  SwipeMenuView mMenuView;
    private  int Position;
    public SwipeMenuLayout(View view, SwipeMenuView menuView) {
        super(view.getContext());

        mContentView = view;
        mMenuView = menuView;
        init(view.getContext());
    }


    public SwipeMenuLayout(View view, AttributeSet attrs) {
        super(view.getContext(), attrs);
        init(view.getContext());
    }
    private SwipeMenuLayout(Context context) {
        super(context);
        init(context);
    }


    private SwipeMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    void init(Context context){
        setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        this.addView(mMenuView);
        this.addView(mContentView);
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public  void onSwipe(MotionEvent event){
        View view ;
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                view = getChildAt(0);
                mMenuView.layout((int) event.getX(), view.getTop(), view.getRight(), view.getBottom());
                Log.i(TAG,"the position of view is "+view.getTop()+"  ;the position of y is "+event.getY()
                +" ; the posistion of bottom is "+view.getBottom());

                //view.layout(0,(int)(event.getX()), view.getRight(), view.getBottom());
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "Action up");
                //postInvalidate();
                mMenuView.layout(getMeasuredWidth(), 0 , getMeasuredWidth()+ mMenuView.getMeasuredWidth(), mMenuView.getBottom());
                break;
        }
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mContentView.layout(0, 0, getMeasuredWidth(), mContentView.getMeasuredHeight());
        mMenuView.layout(getMeasuredWidth(), 0 , getMeasuredWidth()+ mMenuView.getMeasuredWidth(), bottom);
        Log.i(TAG, "the top position of current item is " + top);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
