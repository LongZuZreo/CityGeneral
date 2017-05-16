package com.example.citygeneral;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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

    }

    public static RequestQueue getInstance() {
        return queues;
    }
}
