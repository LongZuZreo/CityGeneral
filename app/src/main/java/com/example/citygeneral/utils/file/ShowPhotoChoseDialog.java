package com.example.citygeneral.utils.file;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.citygeneral.R;


public class ShowPhotoChoseDialog extends Dialog {

	private TextView itemOne, itemTwo, cancle, submit;
	private Myclick click;
	private View.OnClickListener click1,click2;

	public ShowPhotoChoseDialog(Context context) {
		super(context, R.style.Theme_CustomDialog2);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chose_photo_dialog);
		click = new Myclick();
		itemOne = (TextView) findViewById(R.id.item_one);
		itemTwo = (TextView) findViewById(R.id.item_two);
		cancle = (TextView) findViewById(R.id.cancle);
		submit = (TextView) findViewById(R.id.submit);

		cancle.setOnClickListener(click);
		submit.setOnClickListener(click);
		itemOne.setOnClickListener(click1);
		itemTwo.setOnClickListener(click2);

	}

	private class Myclick implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.cancle:
				ShowPhotoChoseDialog.this.dismiss();
				break;
			case R.id.submit:
				ShowPhotoChoseDialog.this.dismiss();
				break;

			default:
				break;
			}

		}
	}
	public void setItemOneOlick(View.OnClickListener on){
		click1 = on;
	}
	public void setItemTwoOlick(View.OnClickListener on){
		click2 = on;
	}
	
}
