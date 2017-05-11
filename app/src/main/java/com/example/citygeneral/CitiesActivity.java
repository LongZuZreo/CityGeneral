package com.example.citygeneral;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.CityAllEntity;
import com.example.citygeneral.model.entity.CityInfor;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.utils.NetUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */

public class CitiesActivity extends BaseActivity implements ActivityContract.View{

    ListView cityListView;
    private List<CityInfor> dataCity;
    private ActivityPresenter presenter;

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
        dataCity = new ArrayList<>();
        presenter = new ActivityPresenter(this);
    }

    @Override
    protected void loadData() {
        //presenter.getCityList(NetUrl.PARAMS);
    }

    //解析服务器返回的城市列表数据
    private List<CityInfor> parasCityInfor(List<CityAllEntity.ServerInfoBean> serverInfo){
        //遍历集合取出其中的城市和cityId
        CityInfor city;
        for(CityAllEntity.ServerInfoBean bean:serverInfo){
            String wapSiteName = bean.getWapSiteName();
            String siteID = bean.getSiteID();
            city = new CityInfor();
            city.setCityName(wapSiteName);
            city.setCityId(siteID);
            dataCity.add(city);
        }
        return dataCity;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismiss() {

    }

    @Override
    public void loadError() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void toPersonCenter() {

    }

    @Override
    public void toVideoPlay() {

    }

    @Override
    public void changeTitleBar() {

    }

    @Override
    public void loadData(CityAllEntity cityAllEntity) {

        List<CityAllEntity.ServerInfoBean> serverInfo = cityAllEntity.getServerInfo();
        List<CityInfor> cityInfors = parasCityInfor(serverInfo);


    }
}
