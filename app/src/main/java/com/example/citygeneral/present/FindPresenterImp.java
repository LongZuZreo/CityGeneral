package com.example.citygeneral.present;

import com.example.citygeneral.contract.FindContract;
import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.FirstFindBean;
import com.example.citygeneral.model.http.BaseVolley;

/**
 * Created by Administrator on 2017/5/16.
 */

public class FindPresenterImp implements FindContract.FindPresenter{

    private FindContract.FindView findView;

    public FindPresenterImp(FindContract.FindView findView){
        this.findView = findView;
    }

    @Override
    public void getData(String url, String tag, String params) {
        BaseVolley.getInstance().doReplacePostString(url, tag, params, new MyCallBack<FirstFindBean>() {
            @Override
            public void onDataChanged(FirstFindBean data) {
                findView.loadFirstData(data);
            }

            @Override
            public void onErrorHappened(String errorMessage) {

            }
        });
    }
}
