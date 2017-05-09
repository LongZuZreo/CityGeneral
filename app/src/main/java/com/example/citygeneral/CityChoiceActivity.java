package com.example.citygeneral;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.citygeneral.base.BaseActivity;

/**
 * Created by Administrator on 2017/5/9.
 */

public class CityChoiceActivity extends BaseActivity implements View.OnClickListener{

    private Button moreCityBtn;
    private ImageView backImg;

    @Override
    protected int getLayoutId() {
        getSupportActionBar().hide();
        return R.layout.activity_citychoice;
    }

    @Override
    protected void initView() {
        moreCityBtn = (Button) findViewById(R.id.button);
        backImg = (ImageView) findViewById(R.id.back_text);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        moreCityBtn.setOnClickListener(this);
        backImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Intent intentCitys = new Intent(this,
                        CitiesActivity.class);
                startActivity(intentCitys);
                break;
            case R.id.back_text:
                finish();
                break;
        }
    }
}
