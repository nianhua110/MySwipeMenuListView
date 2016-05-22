package io.github.nianhua110.myswipemenulistview;

import android.app.Application;

/**
 * Created by kankan on 2016/5/22.
 */
public class SwipeMenuApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
