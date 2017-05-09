package com.example.citygeneral.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by hp1 on 2017-05-09.
 */
//public class MultiDexApplication extends Application {

public class CityApp extends MultiDexApplication {
    public static String version = "1.0";
    public static int cityId = 0;
    public static int NETTYPE = 0;
    public static int userId = 0;
    public static String XINGHAO = android.os.Build.MODEL;
    public static String BANBEN = "Android " + android.os.Build.VERSION.RELEASE;
    public static String PHONEID = "";
    public static String PHONENUM = "0";

    @Override
    public void onCreate() {
        super.onCreate();


        /**
         * 获取手机唯一标示
         * */
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        PHONEID = tm.getDeviceId();


        /**
         * 获取版本号
         * */
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * 获取网络格式
         * */
        NETTYPE = getCurrentNetType(this);

        /**
         * 获取电话号
         * */
        PHONENUM = getPhoneNumber();

    }
    private String getPhoneNumber() {
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyMgr.getLine1Number() == null) {
            return "";
        }
        return mTelephonyMgr.getLine1Number();
    }
    public static int getCurrentNetType(Context context) {
        int type = 0;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            type = 0; // 无网络
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = 1; // wifi
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA
                    || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = 2; // 2g
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                    || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = 3;// 3g
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                type = 4;// 4g
            } else {
                type = 0;
            }
        } else {
            type = 0;
        }
        return type;
    }
}
