package com.example.citygeneral;

import android.widget.ListView;

import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.utils.NetUrl;

/**
 * Created by Administrator on 2017/5/9.
 */

public class CitiesActivity extends BaseActivity{

    ListView cityListView;

    @Override
    protected int getLayoutId() {
        if(getSupportActionBar().isShowing()){
            getSupportActionBar().hide();
        }
        return R.layout.activity_citylist;
    }

    @Override
    protected void initView() {
        cityListView = (ListView) findViewById(R.id.cityListView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {
        BaseVolley.getInstance().doPostString(NetUrl.APPURL,"hhh","",null);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppApplication.getInstance().cancelAll("hhh");
    }
}
