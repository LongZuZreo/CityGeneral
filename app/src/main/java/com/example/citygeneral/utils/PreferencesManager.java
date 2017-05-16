package com.example.citygeneral.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.citygeneral.model.entity.City;

/**
 * Created by zhangzl
 * desc: 保存sharepreferens数据
 * date: 2016/11/10 0010.
 */

public class PreferencesManager {
    public static final String CCOOCITYID = "CCOOCITYID";
    public static final String LOGIN_USER = "loginuser";
    public static final String SETTING_USER = "settinguser";
    public static final String PRE_INT = "preint";
    public static final String CLASS_CAINI = "classcaini";
    public static final String USER_INFO = "userinfo";
    public static final String GIF_FLAG = "gifflag";
    public static final String GUIDE = "guide";

    public static SharedPreferences getSp(String type) {
        return CityApp.getInstance().getSharedPreferences(type, Context.MODE_PRIVATE);
    }

    public static boolean putInt(String type, String key, int value) {
        return getSp(type).edit().putInt(key, value).commit();
    }

    public static int getInt(String type, String key, int defValue){
        return getSp(type).getInt(key, defValue);
    }

    public static boolean putLong(String type, String key, long value){
        return getSp(type).edit().putLong(key, value).commit();
    }

    public static long getLong(String type, String key, long defValue){
        return getSp(type).getLong(key, defValue);
    }

    public static boolean putString(String type, String key, String value){
        return getSp(type).edit().putString(key, value).commit();
    }

    public static String getString(String type, String key, String defValue){
        return getSp(type).getString(key, defValue);
    }

    public static boolean putBoolean(String type, String key, boolean value){
        return getSp(type).edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String type, String key, boolean defValue){
        return getSp(type).getBoolean(key, defValue);
    }

    public static boolean clearAllValue(String type){
        return getSp(type).edit().clear().commit();
    }
}
