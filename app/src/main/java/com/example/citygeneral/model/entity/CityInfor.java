package com.example.citygeneral.model.entity;

/**
 * Created by Administrator on 2017/5/10.
 */
//所有城市列表的城市名字和城市ID
public class CityInfor {

    private String cityName;
    private String cityId;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
