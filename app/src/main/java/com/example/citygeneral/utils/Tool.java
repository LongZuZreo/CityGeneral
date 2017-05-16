package com.example.citygeneral.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Tool {
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);// 得到当前服务

		NetworkInfo info = cm.getActiveNetworkInfo();// 得到网络状态
		if (info != null && info.isConnected()) {// 网络状态对象不为空且isConnected为true则当前服务网络可用
			return true;
		} else {
			return false;
		}
	}
}
