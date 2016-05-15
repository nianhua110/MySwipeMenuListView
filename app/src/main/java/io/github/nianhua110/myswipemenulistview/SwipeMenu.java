package io.github.nianhua110.myswipemenulistview;

import android.content.Context;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kankan on 2016/5/14.
 */
public class SwipeMenu {

    List<SwipeMenuItem> mItems;
    Context mContex;

    public SwipeMenu(Context mContex) {
        this.mContex = mContex;
        mItems = new ArrayList<>();
    }

    public List<SwipeMenuItem> getMenuItems() {
        return mItems;
    }
    public  void addMenuItem(SwipeMenuItem item){
     mItems.add(item);
    }

    public  void removeItem(SwipeMenuItem item){
        mItems.remove(item);
    }
    public  SwipeMenuItem getMenuItem(int position){
        return mItems.get(position);
    }
}
