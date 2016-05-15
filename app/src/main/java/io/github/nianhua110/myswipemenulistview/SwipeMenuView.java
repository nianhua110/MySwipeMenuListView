package io.github.nianhua110.myswipemenulistview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kankan on 2016/5/14.
 */
public class SwipeMenuView extends LinearLayout {
    public SwipeMenuView(Context context) {
        super(context);
        init(context);
    }

    public SwipeMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public  SwipeMenuView(MySwipeMenuListView view, SwipeMenu menu){
        super(view.getContext());
        init(view.getContext());
    }
    void init(Context context){
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setOrientation(HORIZONTAL);
        setLayoutParams(layoutParams);
        TextView textView = new TextView(context);
        textView.setText("hello");
        textView.setTextColor(Color.RED);

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher);
        addView(textView);
        addView(imageView);

    }
}
