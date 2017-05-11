package com.example.citygeneral;

import android.content.Context;
import android.util.Log;

import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.CityAllEntity;
import com.example.citygeneral.model.http.BaseVolley;

/**
 * Created by Administrator on 2017/5/10.
 */

class ActivityPresenter implements ActivityContract.Presenter {

    private CitiesActivity citiesActivity;

    public ActivityPresenter(Context context){
        citiesActivity = (CitiesActivity) context;
    }

    @Override
    public void getCityList(String url) {

        BaseVolley.getInstance().doGetString(url, "cityList", new MyCallBack<CityAllEntity>() {
            @Override
            public void onDataChanged(CityAllEntity data) {
                citiesActivity.loadData(data);
            }

            @Override
            public void onErrorHappened(String errorMessage) {

            }
        });
    }
}
