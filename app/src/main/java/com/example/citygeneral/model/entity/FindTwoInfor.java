package com.example.citygeneral.model.entity;

/**
 * Created by Administrator on 2017/5/18.
 */

public class FindTwoInfor {

    private String channelName, channelMemo, channelImg, channelType,
            channelUrl, channelInfo, channelTitle, channelData;

    public FindTwoInfor(String channelName, String channelMemo,
                       String channelImg, String channelType, String channelUrl,
                       String channelInfo, String channelTitle, String channelData) {
        super();
        this.channelName = channelName;
        this.channelMemo = channelMemo;
        this.channelImg = channelImg;
        this.channelType = channelType;
        this.channelUrl = channelUrl;
        this.channelInfo = channelInfo;
        this.channelTitle = channelTitle;
        this.channelData = channelData;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelMemo() {
        return channelMemo;
    }

    public void setChannelMemo(String channelMemo) {
        this.channelMemo = channelMemo;
    }

    public String getChannelImg() {
        return channelImg;
    }

    public void setChannelImg(String channelImg) {
        this.channelImg = channelImg;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public String getChannelInfo() {
        return channelInfo;
    }

    public void setChannelInfo(String channelInfo) {
        this.channelInfo = channelInfo;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getChannelData() {
        return channelData;
    }

    public void setChannelData(String channelData) {
        this.channelData = channelData;
    }

}
