package com.example.citygeneral;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hp1 on 2017-05-08.
 */

public class AppApplication extends Application {
    public static Activity activity;
    public static RequestQueue queues;

    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getInstance() {
        return queues;
    }
}
