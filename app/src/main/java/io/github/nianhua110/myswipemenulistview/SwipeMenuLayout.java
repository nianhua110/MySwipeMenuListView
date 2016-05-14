package io.github.nianhua110.myswipemenulistview;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by kankan on 2016/5/11.
 */
public class SwipeMenuLayout extends FrameLayout {
    String TAG= this.getClass().getSimpleName();
    public SwipeMenuLayout(Context context) {
        super(context);
        init(context);
    }


    public SwipeMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    void init(Context context){
        Button button = new Button(context);
        button.setText("button");
        TextView textView = new TextView(context);
        textView.setText("text");
        this.addView(button);
        this.addView(textView);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int count = getChildCount();
        for(int i=0; i<count ; i++){
            View view= getChildAt(i);
            view.layout(left, top, right, bottom);
        }
        Log.i(TAG, "the top position of current item is "+top);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
