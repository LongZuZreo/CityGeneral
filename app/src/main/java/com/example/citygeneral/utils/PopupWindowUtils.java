package com.example.citygeneral.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.citygeneral.MainActivity;
import com.example.citygeneral.R;

/**
 * Created by ASUS on 2017/5/19.
 */

public class PopupWindowUtils {

    private static PopupWindowUtils popupWindowUtils;
    private PopupWindowUtils(){

    }
    public static PopupWindowUtils getInstance(){
        if(popupWindowUtils == null){
                synchronized (PopupWindowUtils.class){
                    if(popupWindowUtils == null){
                        popupWindowUtils = new PopupWindowUtils();

                    }
                }

        }
        return popupWindowUtils;
    }


}
