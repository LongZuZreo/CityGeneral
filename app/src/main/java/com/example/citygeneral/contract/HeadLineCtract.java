package com.example.citygeneral.contract;

import com.example.citygeneral.base.BasePresenter;
import com.example.citygeneral.base.BaseView;

/**
 * Created by ASUS on 2017/5/9.
 */

public interface HeadLineCtract {
    interface View extends BaseView {

    }
    interface Presenter extends BasePresenter {
        void getAllList(String url,String params);
    }
}
