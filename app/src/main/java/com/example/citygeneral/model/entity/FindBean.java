package com.example.citygeneral.model.entity;

import java.util.List;

/**
 * Created by ASUS on 2017/5/20.
 */

public class FindBean {

    /**
     * MessageList : {"code":1000,"message":"Success"}
     * ServerInfo : [{"NType":2,"Title":"找工作","Memo":"找工作","Color":"#333333","UrlType":1,"Url":"/post/zhaopin/","NId":"5,18,0,0","channelType":1,"channelUrl":"/post/zhaopin/","channelInfo":"5,18,0,0","IconStyle":1,"Icon":"http://img.pccoo.cn/wx/app/yuan/icon_8.png"},{"NType":2,"Title":"找房子","Memo":"找房子","Color":"#333333","UrlType":1,"Url":"/post/fangwu/","NId":"5,16,0,0","channelType":1,"channelUrl":"/post/fangwu/","channelInfo":"5,16,0,0","IconStyle":1,"Icon":"http://img.pccoo.cn/wx/app/yuan/icon_9.png"},{"NType":2,"Title":"分类生活","Memo":"分类生活","Color":"#333333","UrlType":1,"Url":"/post/","NId":"5,0,0,0","channelType":1,"channelUrl":"/post/","channelInfo":"5,0,0,0","IconStyle":1,"Icon":"http://img.pccoo.cn/wx/app/yuan/icon_7.png"},{"NType":2,"Title":"同城爆料","Memo":"同城爆料","Color":"#333333","UrlType":1,"Url":"/bbs/index.aspx?flag=5","NId":"1,16,0,0","channelType":1,"channelUrl":"/bbs/index.aspx?flag=5","channelInfo":"1,16,0,0","IconStyle":1,"Icon":"http://img.pccoo.cn/wx/app/yuan/icon_26.png"}]
     * Extend : null
     * Count : 4
     * GxNum : 0
     * PageNum : 0
     * retime : 0.0517578
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
         * NType : 2
         * Title : 找工作
         * Memo : 找工作
         * Color : #333333
         * UrlType : 1
         * Url : /post/zhaopin/
         * NId : 5,18,0,0
         * channelType : 1
         * channelUrl : /post/zhaopin/
         * channelInfo : 5,18,0,0
         * IconStyle : 1
         * Icon : http://img.pccoo.cn/wx/app/yuan/icon_8.png
         */

        private int NType;
        private String Title;
        private String Memo;
        private String Color;
        private int UrlType;
        private String Url;
        private String NId;
        private int channelType;
        private String channelUrl;
        private String channelInfo;
        private int IconStyle;
        private String Icon;

        public int getNType() {
            return NType;
        }

        public void setNType(int NType) {
            this.NType = NType;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }

        public String getColor() {
            return Color;
        }

        public void setColor(String Color) {
            this.Color = Color;
        }

        public int getUrlType() {
            return UrlType;
        }

        public void setUrlType(int UrlType) {
            this.UrlType = UrlType;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getNId() {
            return NId;
        }

        public void setNId(String NId) {
            this.NId = NId;
        }

        public int getChannelType() {
            return channelType;
        }

        public void setChannelType(int channelType) {
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

        public int getIconStyle() {
            return IconStyle;
        }

        public void setIconStyle(int IconStyle) {
            this.IconStyle = IconStyle;
        }

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String Icon) {
            this.Icon = Icon;
        }
    }
}
