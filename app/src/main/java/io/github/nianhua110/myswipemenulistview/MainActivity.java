package io.github.nianhua110.myswipemenulistview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ApplicationInfo> mAppList;
    MySwipeMenuListView mySwipeMenuListView ;
    ArrayAdapter<String> stringArrayAdapter ;
    SwipeMenuAdapter swipeMenuAdapter ;
   AppAdapter appAdapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mAppList = getPackageManager().getInstalledApplications(0);
        mySwipeMenuListView = (MySwipeMenuListView) findViewById(R.id.swipeView);
        appAdapter = new AppAdapter();
        swipeMenuAdapter = new SwipeMenuAdapter(this, appAdapter);
        mySwipeMenuListView.setAdapter(swipeMenuAdapter);
        // mySwipeMenuListView.setAdapter(stringArrayAdapter);

        //stringArrayAdapter.notifyDataSetChanged();
        mySwipeMenuListView.setmOnMenuItemClickListener(new MySwipeMenuListView.OnMenuItemClickListener() {
            @Override
           public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Log.i("MAIN", "onMenuItemClick`");
                Toast.makeText(context, "tost", Toast.LENGTH_SHORT);
                open(mAppList.get(position));
               return false;
            }
        });
    }
    private void open(ApplicationInfo item) {
        // open app
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(item.packageName);
        List<ResolveInfo> resolveInfoList = getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        if (resolveInfoList != null && resolveInfoList.size() > 0) {
            ResolveInfo resolveInfo = resolveInfoList.get(0);
            String activityPackageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName componentName = new ComponentName(
                    activityPackageName, className);

            intent.setComponent(componentName);
            startActivity(intent);
        }
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
          return  30;
          // /  return mAppList.size();
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = View.inflate(getApplicationContext(),R.layout.item,null);
            ApplicationInfo applicationInfo = getItem(position);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);
            imageView.setImageDrawable(applicationInfo.loadIcon(getPackageManager()));
            TextView textView = (TextView)convertView.findViewById(R.id.textView);
            textView.setText(applicationInfo.loadLabel(getPackageManager()));


            return convertView;
        }
    }
}
