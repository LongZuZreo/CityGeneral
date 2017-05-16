package com.example.citygeneral.utils;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class ChangeTypeTools {
    public static int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

    public static float parseFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return 0;
        }
    }
}
