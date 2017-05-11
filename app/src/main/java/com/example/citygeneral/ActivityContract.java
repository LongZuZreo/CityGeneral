package com.example.citygeneral;

import com.example.citygeneral.base.BasePresenter;
import com.example.citygeneral.base.BaseView;
import com.example.citygeneral.model.entity.CityAllEntity;

/**
 * Created by Administrator on 2017/5/10.
 */

public class ActivityContract {

    interface View extends BaseView{
        void loadData(CityAllEntity cityAllEntity);
    }

    interface Presenter extends BasePresenter{
        void getCityList(String url);
    }

}
