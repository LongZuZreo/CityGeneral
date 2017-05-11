package com.example.citygeneral.contract;

import com.example.citygeneral.base.BasePresenter;
import com.example.citygeneral.base.BaseView;
import com.example.citygeneral.model.entity.HeadLineBean;

/**
 * Created by ASUS on 2017/5/9.
 */

public interface HeadLineCtract {

    interface View extends BaseView {

    void loadHeadLine(HeadLineBean headLineBean);

    }

    interface Presenter extends BasePresenter {

        void getAllList(String url,String tag,String params);
    }
}
