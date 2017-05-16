package com.example.citygeneral.contract;

import com.example.citygeneral.base.BasePresenter;
import com.example.citygeneral.base.BaseView;

/**
 * Created by Administrator on 2017/5/12.
 */

public class FindContract {

    interface FindView extends BaseView{
        void loadData();
    }


    interface FindPresenter extends BasePresenter{

    }
}
