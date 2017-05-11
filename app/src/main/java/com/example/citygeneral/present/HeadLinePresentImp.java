package com.example.citygeneral.present;

import com.example.citygeneral.contract.HeadLineCtract;
import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.HeadLineBean;
import com.example.citygeneral.model.http.BaseVolley;

/**
 * Created by ASUS on 2017/5/10.
 */

public class HeadLinePresentImp implements HeadLineCtract.Presenter{
    private HeadLineCtract.View view;
    public HeadLinePresentImp(HeadLineCtract.View view){
        this.view = view;
    }

    @Override
    public void getAllList(String url, String tag, String params) {
        BaseVolley.getInstance().doPostString(url, tag, params, new MyCallBack<HeadLineBean>() {
            @Override
            public void onDataChanged(HeadLineBean data) {
                view.loadHeadLine(data);
            }

            @Override
            public void onErrorHappened(String errorMessage) {

            }
        });
    }
}
