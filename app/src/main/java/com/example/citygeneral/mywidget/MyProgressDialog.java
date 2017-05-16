package com.example.citygeneral.mywidget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;

import com.example.citygeneral.R;


public class MyProgressDialog extends Dialog {
	public MyProgressDialog(Context context, boolean cancelable,
                            OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}

	public MyProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public MyProgressDialog(Context context, String str) {
		super(context, R.style.bk_dialog);
	}
	public MyProgressDialog(Context context) {
		super(context, R.style.bk_dialog);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().getAttributes().gravity = Gravity.CENTER;
		//this.setCancelable(false);
		setContentView(R.layout.layout_dialog);
		
		this.setCancelable(false);
	}

	public void closeProgressDialog() {
		dismiss();
	}

	public void showProgressDialog() {
		show();
	}
	public void showProgressDialog(String str) {
		show();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		handler.postDelayed(runnable, 5000);
	}
	
	@Override
	public void dismiss() {
		//友盟BUGIllegalArgumentException: View=com.android.internal.policy.impl.PhoneWindow$DecorView{42f56270 V.E..... R.....ID 0,0-270,270} not attached to window manager
		try {
			
			if (handler != null) handler.removeCallbacksAndMessages(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.dismiss();
	}
	
	private Handler handler = new Handler();
	
	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			setCancelable(true);
		}
	};
}
