package com.example.citygeneral;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by hp1 on 2017-05-08.
 */

public class AppApplication extends Application {
    public static Activity activity;
    public static RequestQueue queues;
    public static boolean rollViewPagerTouching;
    @Override
    public void onCreate() {
        super.onCreate();
        rollViewPagerTouching = false;
        queues = Volley.newRequestQueue(getApplicationContext());
        UMShareAPI.get(this);
        Config.DEBUG = true;

    }

    public static RequestQueue getInstance() {
        return queues;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    {
        PlatformConfig.setWeixin("wxaca4b7e727c64183", "373097dff7fa64ae9ec93073a93aa350");
    }
}
