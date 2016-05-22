package io.github.nianhua110.myswipemenulistview;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ScrollerCompat;
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



    private int mSwipeDirection;

    private  View mContentView;
    private  SwipeMenuView mMenuView;
    private  int Position;
    private ScrollerCompat mOpenScroller;
    private  int mDownX;
    private static final int STATE_CLOSE = 0;
    private static final int STATE_OPEN = 1;
    private int state = STATE_CLOSE;


    public void setSwipeDirection(int mSwipeDirection) {
        this.mSwipeDirection = mSwipeDirection;
    }
    public  boolean isOpen(){
        return  state == STATE_OPEN;
    }
    public SwipeMenuLayout(View view, SwipeMenuView menuView) {
        super(view.getContext());

        mContentView = view;
        mMenuView = menuView;
        mMenuView.setmLayout(this);
        mOpenScroller = ScrollerCompat.create(view.getContext());
;        init(view.getContext());
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
        mMenuView.setPosition(position);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if(mOpenScroller.computeScrollOffset()){
           mMenuView.layout(mOpenScroller.getCurrX(), 0, mOpenScroller.getCurrX()+mMenuView.getMeasuredWidth(), mMenuView.getMeasuredHeight());
            postInvalidate();
        }
    }

    private  void swipe(int dis){
        if(Math.signum(dis) != mSwipeDirection) {
            dis = 0;
        }
        mMenuView.layout(getMeasuredWidth()-dis, 0,(int)getMeasuredWidth()-dis+mMenuView.getWidth(), mMenuView.getMeasuredHeight());
        mContentView.layout(-dis, 0, -dis + mContentView.getMeasuredWidth(), mContentView.getMeasuredHeight());

    }
    public  void onSwipe(MotionEvent event){
        View view ;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = (int)event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int dis = (int) (mDownX - event.getX());
                swipe(dis);
                Log.i(TAG, "dis is "+dis);
                Log.i(TAG, "the position of view is " + event.getX() + "  ;the position of y is " + event.getY()
                        + " ; the posistion of bottom is " + mMenuView.getBottom());
                //view.layout(0,(int)(event.getX()), view.getRight(), view.getBottom());
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "Action up");
                if(Math.abs(event.getX() - mDownX)>mMenuView.getWidth()/2&&Math.signum(mDownX - event.getX()) == mSwipeDirection){
                    state = STATE_OPEN;
                    mContentView.layout(0, 0, getMeasuredWidth(), mContentView.getMeasuredHeight());
                    mMenuView.layout(getMeasuredWidth()- mMenuView.getWidth(), 0, getMeasuredWidth(),mContentView.getBottom());
                }else {
                    state = STATE_CLOSE;
                    mOpenScroller.startScroll((int)event.getX(),0,this.getWidth()-(int)event.getX(), 0,1000);
                    mContentView.layout(0,0,getMeasuredWidth(), mContentView.getMeasuredHeight());
                }
                            //postInvalidate();
              //  mMenuView.layout(getMeasuredWidth(), 0 , getMeasuredWidth()+ mMenuView.getMeasuredWidth(), mMenuView.getBottom());
                break;
        }
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mContentView.layout(0, 0, getMeasuredWidth(), mContentView.getMeasuredHeight());
        Log.i(TAG, "onLayout before: mMenuView Width is " + mMenuView.getWidth() + " , mMenuView getMeasuredWidth is"
                + mMenuView.getMeasuredWidth());
        mMenuView.layout(getMeasuredWidth(), 0 ,getMeasuredWidth()+ mMenuView.getWidth(), bottom);
        Log.i(TAG, "onLayout after: mMenuView Width is "+mMenuView.getWidth()+" , mMenuView getMeasuredWidth is"
        +mMenuView.getMeasuredWidth());//测试前getMeasuredWidth()
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure " + mMenuView.getWidth() + " , mMenuView getMeasuredWidth is"
                + mMenuView.getMeasuredWidth());
        mMenuView.measure(MeasureSpec.makeMeasureSpec(600,
                MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight(), MeasureSpec.EXACTLY));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }

    public  void smoothCloseMenu(){

      Log.i(TAG, "smoothCloseMenu");
    }
}
