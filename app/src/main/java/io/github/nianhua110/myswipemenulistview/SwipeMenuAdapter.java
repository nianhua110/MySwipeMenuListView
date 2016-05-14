package io.github.nianhua110.myswipemenulistview;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

/**
 * Created by kankan on 2016/5/12.
 */
public class SwipeMenuAdapter implements WrapperListAdapter {
    String TAG = this.getClass().getSimpleName();
    Context mContext;
    ListAdapter mListAdapter;
    public SwipeMenuAdapter(Context context,ListAdapter listAdapter ) {
        mContext = context;
        mListAdapter = listAdapter;
    }

    @Override
    public ListAdapter getWrappedAdapter() {
        return mListAdapter;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return mListAdapter.areAllItemsEnabled();
    }

    @Override
    public boolean isEnabled(int position) {
        return mListAdapter.isEnabled(position);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mListAdapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mListAdapter.unregisterDataSetObserver(observer);
    }

    @Override
    public int getCount() {
        Log.i(TAG,"the number of items is "+mListAdapter.getCount());
        return mListAdapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        return mListAdapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return mListAdapter.getItemId(position);
    }

    @Override
    public boolean hasStableIds() {
        return mListAdapter.hasStableIds();
    }

    int num = 0;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // convertView = View.inflate()
        convertView =mListAdapter.getView(position,convertView,parent);
      SwipeMenuLayout swipeMenuLayout = new SwipeMenuLayout(convertView.getContext());
        Log.i(TAG,"the current item number id is " +num);
        num++;
        return swipeMenuLayout;
    }

    @Override
    public int getItemViewType(int position) {
        return mListAdapter.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return mListAdapter.getViewTypeCount();
    }

    @Override
    public boolean isEmpty() {
        return mListAdapter.isEmpty();
    }
}
