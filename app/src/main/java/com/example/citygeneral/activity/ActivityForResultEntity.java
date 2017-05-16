package com.example.citygeneral.activity;

/**
 * Created by zhangzl
 * 描述:
 * 日期: 2017/4/11 0011.
 */

public class ActivityForResultEntity {
    private String name;
    private int requestCode;
    private int resultCode;

    public ActivityForResultEntity(String name, int resultCode) {
        this.name = name;
        this.resultCode = resultCode;
    }

    public ActivityForResultEntity() {
    }

    public ActivityForResultEntity(String name, int requestCode, int resultCode) {
        this.name = name;
        this.requestCode = requestCode;
        this.resultCode = resultCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
