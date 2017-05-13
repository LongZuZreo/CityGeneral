package com.example.citygeneral.view;

import android.app.Activity;
import android.support.v4.view.ViewPager;

import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseActivity;

/**
 * Created by ASUS on 2017/5/12.
 */

public class FirstActivity extends BaseActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_first;
    }

    @Override
    protected void initView() {
        ViewPager mViewPager = (ViewPager) this.findViewById(R.id.mActivity_first_viewpager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }
}
