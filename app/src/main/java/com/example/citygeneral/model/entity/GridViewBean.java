package com.example.citygeneral.model.entity;

import java.util.List;

/**
 * Created by ASUS on 2017/5/20.
 */

public class GridViewBean {


    /**
     * MessageList : {"code":1000,"message":"Success"}
     * ServerInfo : {"configData":[{"Title":"发帖子","Icon":"http://img.pccoo.cn/wap/webapp/fabu/bbs.png","FlagID":2,"FirstType":1,"SecondType":10},{"Title":"爆个照","Icon":"http://img.pccoo.cn/wap/webapp/fabu/takepic.png","FlagID":1,"FirstType":2,"SecondType":7},{"Title":"小视频","Icon":"http://img.pccoo.cn/wap/webapp/fabu/smallvideo.png","FlagID":17,"FirstType":2,"SecondType":15},{"Title":"有奖爆料","Icon":"http://img.pccoo.cn/wap/webapp/fabu/disclose.png","FlagID":16,"FirstType":1,"SecondType":15},{"Title":"分类信息","Icon":"http://img.pccoo.cn/wap/webapp/fabu/post.png","FlagID":4,"FirstType":5,"SecondType":17},{"Title":"二维码","Icon":"http://img.pccoo.cn/wap/webapp/fabu/ewm.png","FlagID":7,"FirstType":18,"SecondType":5}]}
     * Extend : null
     * Count : 0
     * GxNum : 0
     * PageNum : 0
     * retime : 0.0058594
     */

    private MessageListBean MessageList;
    private ServerInfoBean ServerInfo;
    private Object Extend;
    private int Count;
    private int GxNum;
    private int PageNum;
    private double retime;

    public MessageListBean getMessageList() {
        return MessageList;
    }

    public void setMessageList(MessageListBean MessageList) {
        this.MessageList = MessageList;
    }

    public ServerInfoBean getServerInfo() {
        return ServerInfo;
    }

    public void setServerInfo(ServerInfoBean ServerInfo) {
        this.ServerInfo = ServerInfo;
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
        private List<ConfigDataBean> configData;

        public List<ConfigDataBean> getConfigData() {
            return configData;
        }

        public void setConfigData(List<ConfigDataBean> configData) {
            this.configData = configData;
        }

        public static class ConfigDataBean {
            /**
             * Title : 发帖子
             * Icon : http://img.pccoo.cn/wap/webapp/fabu/bbs.png
             * FlagID : 2
             * FirstType : 1
             * SecondType : 10
             */

            private String Title;
            private String Icon;
            private int FlagID;
            private int FirstType;
            private int SecondType;

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getIcon() {
                return Icon;
            }

            public void setIcon(String Icon) {
                this.Icon = Icon;
            }

            public int getFlagID() {
                return FlagID;
            }

            public void setFlagID(int FlagID) {
                this.FlagID = FlagID;
            }

            public int getFirstType() {
                return FirstType;
            }

            public void setFirstType(int FirstType) {
                this.FirstType = FirstType;
            }

            public int getSecondType() {
                return SecondType;
            }

            public void setSecondType(int SecondType) {
                this.SecondType = SecondType;
            }
        }
    }
}
