package io.github.nianhua110.myswipemenulistview;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MySwipeMenuListView mySwipeMenuListView ;
    ArrayAdapter<String> stringArrayAdapter ;
    SwipeMenuAdapter swipeMenuAdapter ;
   AppAdapter appAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        stringArrayAdapter = new ArrayAdapter<String>(this,R.layout.item, R.id.textView);
        mySwipeMenuListView =(MySwipeMenuListView) findViewById(R.id.swipeView);
        appAdapter = new AppAdapter();
        swipeMenuAdapter = new SwipeMenuAdapter(this,appAdapter);
        mySwipeMenuListView.setAdapter(swipeMenuAdapter);
       // mySwipeMenuListView.setAdapter(stringArrayAdapter);
        stringArrayAdapter.add("a");
        stringArrayAdapter.notifyDataSetChanged();
        stringArrayAdapter.add("B0");
        stringArrayAdapter.add("c0");
        stringArrayAdapter.add("d0");
        stringArrayAdapter.add("e0");
        //stringArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    class AppAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return "adb";
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           // SwipeMenuLayout swipeMenuLayout = new SwipeMenuLayout(convertView.getContext());
            convertView = View.inflate(getApplicationContext(),R.layout.item,null);
            return convertView;
        }
    }
}