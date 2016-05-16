package io.github.nianhua110.myswipemenulistview;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kankan on 2016/5/14.
 */
public class SwipeMenuView extends LinearLayout {
    private  SwipeMenu mMenu;
    private MySwipeMenuListView mListView ;
    public SwipeMenuView(Context context) {
        super(context);
     //   init(context);
    }

    public SwipeMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    //    init(context);
    }

    public  SwipeMenuView(MySwipeMenuListView view, SwipeMenu menu){
        super(view.getContext());

        mListView = view;
        mMenu = menu;
        List<SwipeMenuItem> items = menu.getMenuItems();
        int id = 0;
        for (SwipeMenuItem item : items) {
            addItem(item, id++);
        }
      //  init(view.getContext());
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


    }


    private void addItem(SwipeMenuItem item, int id) {
        LayoutParams params = new LayoutParams(item.getWidth(),
                LayoutParams.MATCH_PARENT);
        LinearLayout parent = new LinearLayout(getContext());
        parent.setId(id);
        parent.setGravity(Gravity.CENTER);
        parent.setOrientation(LinearLayout.VERTICAL);
        parent.setLayoutParams(params);
        parent.setBackgroundDrawable(item.getBackground());
      //  parent.setOnClickListener(this);
        addView(parent);

        if (item.getIcon() != null) {
            parent.addView(createIcon(item));
        }
        if (!TextUtils.isEmpty(item.getTitle())) {
            parent.addView(createTitle(item));
        }

    }

    private ImageView createIcon(SwipeMenuItem item) {
        ImageView iv = new ImageView(getContext());
        iv.setImageDrawable(item.getIcon());
        return iv;
    }

    private TextView createTitle(SwipeMenuItem item) {
        TextView tv = new TextView(getContext());

        tv.setText(item.getTitle());
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(item.getTitleSize());
        tv.setTextColor(item.getTitleColor());
        return tv;
    }
}
