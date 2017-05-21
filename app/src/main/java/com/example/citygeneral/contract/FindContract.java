package com.example.citygeneral.contract;

import com.example.citygeneral.base.BasePresenter;
import com.example.citygeneral.base.BaseView;
import com.example.citygeneral.model.entity.FirstFindBean;

/**
 * Created by Administrator on 2017/5/12.
 */

public interface FindContract {

    interface FindView extends BaseView{
        void loadFirstData(FirstFindBean firstFindBean);
    }


    interface FindPresenter extends BasePresenter{
        void getData(String url,String tag,String params);
    }
}
