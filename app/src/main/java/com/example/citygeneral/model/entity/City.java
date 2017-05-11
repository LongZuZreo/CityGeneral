package com.example.citygeneral.model.entity;

import java.util.List;

/**
 * Created by hp1 on 2017-05-10.
 */

public class City {

    /**
     * MessageList : {"code":1000,"message":"Success"}
     * ServerInfo : [{"SiteID":2422,"CityName":"延庆(北京)","SiteName":"延庆在线","Distance":35041,"areaName":"延庆","wapSiteUrl":"m.yanqing.ccoo.cn","SiteSqURL":"m.yanqing.ccoo.cn","isPhoneReg":1,"isAuthe":0,"spell":"yan","initial":"yqzx"},{"SiteID":2494,"CityName":"朝阳(北京)","SiteName":"朝阳热线","Distance":37013,"areaName":"朝阳","wapSiteUrl":"m.bjcy.ccoo.cn","SiteSqURL":"m.bjcy.ccoo.cn","isPhoneReg":1,"isAuthe":0,"spell":"chao","initial":"cyrx"},{"SiteID":2651,"CityName":"","SiteName":"燕郊大视野","Distance":60722,"areaName":"燕郊","wapSiteUrl":"m.yanjiao.ccoo.cn","SiteSqURL":"m.yanjiao.ccoo.cn","isPhoneReg":0,"isAuthe":0,"spell":"","initial":""}]
     * Extend : null
     * Count : 3
     * GxNum : 0
     * PageNum : 0
     * retime : 0.2226563
     */

    private MessageListBean MessageList;
    private Object Extend;
    private int Count;
    private int GxNum;
    private int PageNum;
    private double retime;
    private List<ServerInfoBean> ServerInfo;

    public MessageListBean getMessageList() {
        return MessageList;
    }

    public void setMessageList(MessageListBean MessageList) {
        this.MessageList = MessageList;
    }

    public Object getExtend() {
        return Extend;
    }

    public void setExtend(Object Extend) {
        this.Extend = Extend;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public int getGxNum() {
        return GxNum;
    }

    public void setGxNum(int GxNum) {
        this.GxNum = GxNum;
    }

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int PageNum) {
        this.PageNum = PageNum;
    }

    public double getRetime() {
        return retime;
    }

    public void setRetime(double retime) {
        this.retime = retime;
    }

    public List<ServerInfoBean> getServerInfo() {
        return ServerInfo;
    }

    public void setServerInfo(List<ServerInfoBean> ServerInfo) {
        this.ServerInfo = ServerInfo;
    }

    public static class MessageListBean {
        /**
         * code : 1000
         * message : Success
         */

        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ServerInfoBean {
        /**
         * SiteID : 2422
         * CityName : 延庆(北京)
         * SiteName : 延庆在线
         * Distance : 35041
         * areaName : 延庆
         * wapSiteUrl : m.yanqing.ccoo.cn
         * SiteSqURL : m.yanqing.ccoo.cn
         * isPhoneReg : 1
         * isAuthe : 0
         * spell : yan
         * initial : yqzx
         */

        private int SiteID;
        private String CityName;
        private String SiteName;
        private int Distance;
        private String areaName;
        private String wapSiteUrl;
        private String SiteSqURL;
        private int isPhoneReg;
        private int isAuthe;
        private String spell;
        private String initial;

        public int getSiteID() {
            return SiteID;
        }

        public void setSiteID(int SiteID) {
            this.SiteID = SiteID;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public String getSiteName() {
            return SiteName;
        }

        public void setSiteName(String SiteName) {
            this.SiteName = SiteName;
        }

        public int getDistance() {
            return Distance;
        }

        public void setDistance(int Distance) {
            this.Distance = Distance;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getWapSiteUrl() {
            return wapSiteUrl;
        }

        public void setWapSiteUrl(String wapSiteUrl) {
            this.wapSiteUrl = wapSiteUrl;
        }

        public String getSiteSqURL() {
            return SiteSqURL;
        }

        public void setSiteSqURL(String SiteSqURL) {
            this.SiteSqURL = SiteSqURL;
        }

        public int getIsPhoneReg() {
            return isPhoneReg;
        }

        public void setIsPhoneReg(int isPhoneReg) {
            this.isPhoneReg = isPhoneReg;
        }

        public int getIsAuthe() {
            return isAuthe;
        }

        public void setIsAuthe(int isAuthe) {
            this.isAuthe = isAuthe;
        }

        public String getSpell() {
            return spell;
        }

        public void setSpell(String spell) {
            this.spell = spell;
        }

        public String getInitial() {
            return initial;
        }

        public void setInitial(String initial) {
            this.initial = initial;
        }
    }
}
