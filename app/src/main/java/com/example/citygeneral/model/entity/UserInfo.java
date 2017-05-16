package com.example.citygeneral.model.entity;

import android.text.TextUtils;

public class UserInfo {
    private String Id, SiteID, SiteSqURL, UserName, Nick, UserFace, Sex, Name,
            Birthday, XingZuo, Info, Mobile, Tel1, Tel, Email, TrueEmail,
            Position, PositionName, FrendNum, FansNum, Coin, Level, Integral,
            Image, HonorName, TaskCount, IntegralRank, MedalCount, MedalImags,
            isQian = "0", BBID, IsFirstPub, QQ, WeiXin, LifeAddr, Marry = "", Job,
            MsgCount = "", title = "", headimg = "", firstid = "",
            secondid = "", IsQQBind = "", IsWXBind = "", IsWBBind = "";

    public UserInfo(String id, String siteID, String siteSqURL,
                    String userName, String nick, String userFace, String sex,
                    String name, String birthday, String xingZuo, String info,
                    String mobile, String tel1, String tel, String email,
                    String trueEmail, String position, String positionName,
                    String frendNum, String fansNum, String coin, String level,
                    String integral, String image, String honorName, String taskCount,
                    String integralRank, String medalCount, String medalImags,
                    String isQian, String bBID, String IsFirstPub, String qQ, String weiXin,
                    String lifeAddr, String marry, String job, String MsgCount,
                    String title, String headimg, String firstid, String secondid, String IsQQBind, String IsWXBind, String IsWBBind) {
        super();
        Id = id;
        SiteID = siteID;
        SiteSqURL = siteSqURL;
        UserName = userName;
        Nick = nick;
        UserFace = userFace;
        Sex = sex;
        Name = name;
        Birthday = birthday;
        XingZuo = xingZuo;
        Info = info;
        Mobile = mobile;
        Tel1 = tel1;
        Tel = tel;
        Email = email;
        TrueEmail = trueEmail;
        Position = position;
        PositionName = positionName;
        FrendNum = frendNum;
        FansNum = fansNum;
        Coin = coin;
        Level = level;
        Integral = integral;
        Image = image;
        HonorName = honorName;
        TaskCount = taskCount;
        IntegralRank = integralRank;
        MedalCount = medalCount;
        MedalImags = medalImags;
        this.isQian = isQian;
        BBID = bBID;
        this.IsFirstPub = IsFirstPub;
        QQ = qQ;
        WeiXin = weiXin;
        LifeAddr = lifeAddr;
        Marry = marry;
        Job = job;
        this.MsgCount = MsgCount;
        this.title = title;
        this.headimg = headimg;
        this.firstid = firstid;
        this.secondid = secondid;
        this.IsQQBind = IsQQBind;
        this.IsWXBind = IsWXBind;
        this.IsWBBind = IsWBBind;

    }

    /**
     * Id //用户id SiteID //城市id UserName// 用户名 Nick//昵称 UserFace//头像 Sex //性别
     * Name //姓名 Birthday //出生时间 XingZuo //星座 Info //签名（原简介） Mobile //手机号 Tel1
     * //固定电话 Tel //认证的电话 Email//电子邮箱 TrueEmail //认证的电子邮箱 Integral//用户积分 Coin
     * //城市币 Level//积分等级 Image//等级图标 HonorName //头衔名称 Position //地理位置
     * PositionName //位置坐标
     */

    public String getId() {
        return Id;
    }

    public String getBBID() {
        return BBID;
    }

    public void setBBID(String bBID) {
        BBID = bBID;
    }

    public String getIsFirstPub() {
        return IsFirstPub;
    }

    public void setIsFirstPub(String isFirstPub) {
        IsFirstPub = isFirstPub;
    }

    public String getSiteSqURL() {
        return SiteSqURL;
    }

    public void setSiteSqURL(String siteSqURL) {
        SiteSqURL = siteSqURL;
    }

    public String getHttpSiteSqURL() {
        return "http://" + SiteSqURL;
    }

    public String getTaskCount() {
        return TaskCount;
    }

    public void setTaskCount(String taskCount) {
        TaskCount = taskCount;
    }

    public String getIntegralRank() {
        return IntegralRank;
    }

    public void setIntegralRank(String integralRank) {
        IntegralRank = integralRank;
    }

    public String getMedalCount() {
        return MedalCount;
    }

    public void setMedalCount(String medalCount) {
        MedalCount = medalCount;
    }

    public String getMedalImags() {
        return MedalImags;
    }

    public void setMedalImags(String medalImags) {
        MedalImags = medalImags;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSiteID() {
        return SiteID;
    }

    public void setSiteID(String siteID) {
        SiteID = siteID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getUserFace() {
        return UserFace;
    }

    public void setUserFace(String userFace) {
        UserFace = userFace;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getXingZuo() {
        return XingZuo;
    }

    public void setXingZuo(String xingZuo) {
        XingZuo = xingZuo;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getTel1() {
        return Tel1;
    }

    public void setTel1(String tel1) {
        Tel1 = tel1;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTrueEmail() {
        return TrueEmail;
    }

    public void setTrueEmail(String trueEmail) {
        TrueEmail = trueEmail;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }

    public String getFrendNum() {
        return FrendNum;
    }

    public void setFrendNum(String frendNum) {
        FrendNum = frendNum;
    }

    public String getFansNum() {
        return FansNum;
    }

    public void setFansNum(String fansNum) {
        FansNum = fansNum;
    }

    public String getCoin() {
        return TextUtils.isEmpty(Coin) ? "0" : Coin;
    }

    public void setCoin(String coin) {
        Coin = coin;
    }

    public String getLevel() {
        return TextUtils.isEmpty(Level) ? "0" : Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getIntegral() {
        return Integral;
    }

    public void setIntegral(String integral) {
        Integral = integral;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getHonorName() {
        return HonorName;
    }

    public void setHonorName(String honorName) {
        HonorName = honorName;
    }

    public String getIsQian() {
        return isQian;
    }

    public void setIsQian(String isQian) {
        this.isQian = isQian;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String qQ) {
        QQ = qQ;
    }

    public String getWeiXin() {
        return WeiXin;
    }

    public void setWeiXin(String weiXin) {
        WeiXin = weiXin;
    }

    public String getLifeAddr() {
        return LifeAddr;
    }

    public void setLifeAddr(String lifeAddr) {
        LifeAddr = lifeAddr;
    }

    public String getMarry() {
        return Marry;
    }

    public void setMarry(String marry) {
        Marry = marry;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getMsgCount() {
        return MsgCount;
    }

    public void setMsgCount(String msgCount) {
        MsgCount = msgCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getFirstid() {
        return firstid;
    }

    public void setFirstid(String firstid) {
        this.firstid = firstid;
    }

    public String getSecondid() {
        return secondid;
    }

    public void setSecondid(String secondid) {
        this.secondid = secondid;
    }

    public String getIsQQBind() {
        return IsQQBind;
    }

    public void setIsQQBind(String isQQBind) {
        IsQQBind = isQQBind;
    }

    public String getIsWXBind() {
        return IsWXBind;
    }

    public void setIsWXBind(String isWXBind) {
        IsWXBind = isWXBind;
    }

    public String getIsWBBind() {
        return IsWBBind;
    }

    public void setIsWBBind(String isWBBind) {
        IsWBBind = isWBBind;
    }
}
