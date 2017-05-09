package com.example.citygeneral.utils;

/**
 * Created by hp1 on 2017-05-09.
 */

public class NetUrl {

    public static int imagesize = 100;
    public static final String COOKIE_TAIL = "#andriod_redirect";
    public static final String savePath = "ccoo/ccoocity";
    public static final String apkName = "ccoocity.apk";
    public static final String logName = "log.txt";
    public static final String PREF = "preferences";
    public static final String PREF_INSTALL = "preferencesInstall";
    public static final String PREF_CITY_VERSION = "preferencesCityVersion";
    public static final String IMAGEBYTE = "imagebyte";
    public static final String IMAGEBYTEURL = "imagebyteurl";
    public static final String PREF_CITY = "current_city";
    public static final String PREF_WEATHER = "weatherInfo";
    public static final String PREF_ITEMS = "preferences_items";
    public static final String PREF_ITEMS_COUNT = "preferences_items_count";
    public static final String massgeStr = "<script type=\"text/javascript\">function imgload(t) {var tis = t;if(tis.width>=100){tis.style.width=\"100%\";tis.style.display=\"block\";tis.style.margin=\"0 auto\"}}</script>";
    public static final String PARAMS = "http://m67.ccoo.cn/webconfig/webconfig.txt";
    public static final String SERVER_ADDRESS = "android.ccoo.cn";
    public static final String SERVER_ADDRESS2 = "push.accoo.cn";

    /**
     * 录音缓存目录
     */
    public static String TEMP_AUDIO_PATH;

    public static final int SERVER_PORT = 31235;
    public static final int SERVER_PORT2 = 31236;
    public static final int CUSTOMER_ID = 8001;

    public static final String APPNAME = "CityGeneral";

    public static final String APPURL = "http://appnew.ccoo.cn/appserverapi.ashx";
    public static final String PHSocket_APP_GetUserCouponInfoList = "PHSocket_APP_GetUserCouponInfoList";
    public static final String PHSocket_APP_TaskConfigList = "PHSocket_APP_TaskConfigList";
    public static final String CUSTOMER_KEY = "+6Hp9X5zR39SOI6oP0685Bk77gG56m7PkV89xYvl86A=";
    public static final String IMAGE_CACHE_DIR = "images";
    public static final String IMAGE_CACHE_HTTP = "http";
    public static final String IMAGE_CACHE_CACHE = "cache";
    public static final String PHSocket_GetHomeHottestInfo = "PHSocket_GetHomeHottestInfo";

    public static final String URL_NEWS = "/bbs/topic.aspx?id=";
    public static final String URL_APP_SEARCH = "/tel/tellist.aspx?keyword=";
    public static final String URL_USER = "/users/";
    public static final String URL_LOGIN = "/login/";
    public static final String URL_WEATHER = "/bianmin/weather.aspx";
    public static final String URL_EDIT = "/bbs/tie.aspx";

    public static final String RECEIVER_ACTION_ADD = "com.www.ccoocity.receiver.action.addapp";
    public static final String RECEIVER_ACTION_CHANGE_CITY = "com.www.ccoocity.receiver.action.changecity";
    public static final String RECEIVER_ACTION_UPDATA_APK = "com.www.ccoocity.receiver.action.updataapk";
    // 上传图片参数
    public static final String URL_UPLOAD = "http://up9.pccoo.cn/upload.ashx?";

    /**
     * 语音上传
     */
    public static final String FILE_UPLOAD_URL = "http://up9.pccoo.cn/upload.ashx?filesrc=%s&siteid=%s&uid=%s&source=2";

    /**
     * 获取文件上传路径
     *
     * @param filesrc
     * @param siteid
     * @param uid
     * @return
     */
    public static String getFileUploadUrl(String filesrc, String siteid, String uid) {
        return String.format(FILE_UPLOAD_URL, filesrc, siteid, uid);
    }

    public static final String[] mSalaryStrings = {"不限", "面议", "1000元以下",
            "1000-1999元", "2000-2999元", "3000-4999元", "5000-7999元", "8000及以上"};
    // 接口名： 获取发布成功后存储在服务器的联系人信息
    public static final String METHOD_PHSocket_GetPostFabuUserInfo = "PHSocket_GetPostFabuUserInfo";
    // 接口名： 分享成功后请求服务器获取响应接口GetPostFabuUserInfo
    public static final String METHOD_PHSocket_SetUserActionDispose = "PHSocket_SetUserActionDispose";
    // 接口名：获取商务模块信息
    public static final String METHOD_PHSocket_GetAdvertInfo = "PHSocket_GetAdvertInfo";

    // 接口名：获得城市App导航模块列表
    public static final String METHOD_PHSocket_GetPubConfigInfo = "PHSocket_GetPubConfigInfo";
    // 接口名：获得电话标签 列表
    public static final String METHOD_PHSocket_GetSiteLifeChoiceness = "PHSocket_GetSiteLifeChoiceness";

    // 接口名：获得城市App导航模块列表
    public static final String METHOD_GetSiteCustomNavigation = "PHSocket_GetSiteCustomNavigation";
    // 接口名：判断城市App导航模块是否开启
    public static final String METHOD_GetCheckSiteModule = "PHSocket_GetCheckSiteModule";
    // 接口名：获得App应用城市列表版本信息
    public static final String METHOD_GetAppConfigVersion = "PHSocket_GetAppConfigVersion";
    // 接口名：获得全部城市信息列表
    public static final String METHOD_GetAllSiteInfoList = "PHSocket_GetAllSiteInfoList";
    // 接口名：获得App应用版本信息
    public static final String METHOD_GetAppVersion = "PHSocket_GetAppVersion";
    // 接口名：获得城市站APP默认开启模块
    public static final String METHOD_GetSiteIsDefauleModule = "PHSocket_GetSiteIsDefauleModule";
    // 接口名：今日关注列表
    public static final String METHOD_GetBBSTopicTodayList = "PHSocket_GetBBSTopicTodayList";
    // 接口名：城市实时天气情况
    public static final String METHOD_GetSiteWeatherRealTime = "PHSocket_GetSiteWeatherRealTime";
    // 接口名：获得城市资讯导航列表
    public static final String METHOD_GetSitePlattsNav = "PHSocket_GetSitePlattsNav";
    // 接口名：获得城市站定位坐标列表
    public static final String METHOD_GetSiteMapCoord = "PHSocket_GetSiteMapCoord";
    // 接口名：详细资讯资讯列表
    public static final String METHOD_GetNewsInfo = "PHSocket_GetNewsInfoList";
    // 接口名：详细资讯资讯列表
    public static final String METHOD_GetNewsOnePageInfo = "PHSocket_GetNewsOnePageInfo";
    //黑名单列表
    public static final String PHSocket_GetUserBlacklist = "PHSocket_GetUserBlacklist";
    // 接口名：资讯幻灯片信息
    public static final String METHOD_GetNewsTopInfo = "PHSocket_GetNewsTopInfo";
    // 接口名：详细资讯内容
    public static final String METHOD_GetNewsInfocon = "PHSocket_GetNewsInfo";
    // 接口名：资讯回复列表
    public static final String METHOD_GetNewsReplyList = "PHSocket_GetNewsReplyList";
    // 接口名：用户回复评论
    public static final String METHOD_SetNewsReplyInfo = "PHSocket_SetNewsReplyInfo";
    //修改密码-获取验证码
    public static final String PHSocket_EditPhoneBindSendCode = "PHSocket_EditPhoneBindSendCode";
    // 接口名：用户回复点赞
    public static final String METHOD_SetNewsReplyTop = "PHSocket_SetNewsReplyTop";
    // 接口名：用户发帖
    public static final String METHOD_SetTopicInfoAdd = "PHSocket_SetTopicInfoAdd";
    // 推送
    public static final String PHSocket_GetOpenMsgDataList = "PHSocket_GetOpenMsgDataList";
    // 接口名：专题活动资讯列表
    public static final String METHOD_GetSpecialInfo = "PHSocket_GetZthdInfoList";
    // 接口名：活动排行榜请求
    public static final String PHSocket_GetActiveRankList = "PHSocket_GetActiveRankList";
    // 接口名：拉黑
    public static final String PHSocket_AddUserBlack = "PHSocket_AddUserBlack";
    // 接口名：解除拉黑
    public static final String PHSocket_DelUserBlackInfo = "PHSocket_DelUserBlackInfo";
    // 接口名：专题活动详细信息
    public static final String METHOD_GetSpecialInfomation = "PHSocket_GetZthdInfo";
    // 接口名：便民工具列表
    public static final String METHOD_GetLifeUtilInfo = "PHSocket_GetWebAppNavList";
    // 接口名：二手信息
    public static final String METHOD_GetErShouKIDList = "PHSocket_GetErShouKIDList";
    // 接口名：车辆买卖
    public static final String METHOD_GetTrafficKindList = "PHSocket_GetTrafficKindList";
    // 接口名：生活服务
    public static final String METHOD_GetLiveKindList = "PHSocket_GetLiveKindList";
    // 接口名：商业机会
    public static final String METHOD_GetBusinessKindList = "PHSocket_GetBusinessKindList";
    // 接口名：教育培训
    public static final String METHOD_GetEducationKindList = "PHSocket_GetEducationKindList";

    // 接口名：美食咨询详细信息
    public static final String METHOD_GetCateNewsInfo = "PHSocket_GetCateNewsInfo";
    // 接口名：汽车咨询详细信息
    public static final String METHOD_GetCarNewsInfo = "PHSocket_GetCarNewsInfo";
    // 接口名：婚嫁咨询详细信息
    public static final String METHOD_GetMarryNewsInfo = "PHSocket_GetMarryNewsInfo";
    // 接口名：家具咨询详细信息
    public static final String METHOD_GetFurniNewsInfo = "PHSocket_GetFurniNewsInfo";
    // 接口名：房产咨询详细信息
    public static final String METHOD_GetPostHouseNewsInfo = "PHSocket_GetPostHouseNewsInfo";
    // 接口名：楼盘动态咨询详细信息
    public static final String METHOD_GetNewHouseNewsInfo = "PHSocket_GetNewHouseNewsInfo";
    // 接口名: 家具案例赏析详细信息
    public static final String METHOD_GetStoreCaseAppreciationInfo = "PHSocket_GetStoreCaseAppreciationInfo";
    // 接口名：行业街相册照片列表（暂实车网展相册列表）
    public static final String METHOD_GetStorePowerImagesList = "PHSocket_GetStorePowerImagesList";
    // 接口名：焦点举报
    public static final String METHOD_SetRealTimeConsultComplain = "PHSocket_SetRealTimeConsultComplain";
    // 接口名：论坛主页接口
    public static final String METHOD_GetBBSDiscoveryList = "PHSocket_GetBBSDiscoveryList";
    // 接口名：论坛板块列表
    public static final String METHOD_GetBBSBoardList = "PHSocket_GetBBSBoardList";
    // 接口名：论坛帖子列表
    public static final String METHOD_GetBBSTopicList = "PHSocket_GetBBSTopicList";
    // 接口名：论坛用户签到
    public static final String METHOD_SetBBSUserSign = "PHSocket_SetBBSUserSign";
    // 接口名：论坛帖子详情
    public static final String METHOD_GetBBSTopicInfo = "PHSocket_GetBBSTopicInfo";
    // 接口名：帖子回复列表
    public static final String METHOD_GetBBSReplyList = "PHSocket_GetBBSReplyList";
    // 接口名：城市论坛统计信息
    public static final String METHOD_GetBBSStatistics = "PHSocket_GetBBSStatistics";
    // 接口名：群体板块会员列表
    public static final String METHOD_GetBBSBoardUser = "PHSocket_GetBBSBoardUser";
    // 接口名：获得用户是否已经签到
    public static final String METHOD_GetBBSUserSign = "PHSocket_GetBBSUserSign";
    // 接口名：获得用户是否关注帖子、专题或版块
    public static final String METHOD_GetUserFollowBoard = "PHSocket_GetUserFollowBoard";
    // 接口名：用户关注版块列表
    public static final String METHOD_GetUserFollowBoardList = "PHSocket_GetUserFollowBoardList";

    // 接口名：话题回复列表接口
    public static final String METHOD_GetMyTieBaReplyList = "PHSocket_GetMyTieBaReplyList";
    // 接口名：用户关注（板块）
    public static final String METHOD_SetUserFollowBoard = "PHSocket_SetUserFollowBoard";
    // 接口名：城市论坛版块详细信息
    public static final String METHOD_GetBBSBoardInfo = "PHSocket_GetBBSBoardInfo";
    // 接口名：用户加入群体模块
    public static final String METHOD_SetUserJoinBoard = "PHSocket_SetUserJoinBoard";
    // 接口名：论坛用户好友列表
    public static final String METHOD_GetBBSFriendList = "PHSocket_GetBBSFriendList";
    // 接口名：论坛用户回帖
    public static final String METHOD_SetTopicInfoReply = "PHSocket_SetTopicInfoReply";
    // 接口名：论坛用户是否加入群体模块
    public static final String METHOD_GetUserJoinBoardBool = "PHSocket_GetUserJoinBoardBool";
    // 接口名：用户关注/取消关注（帖子）
    public static final String METHOD_SetUserFollowTopic = "PHSocket_SetUserFollowTopic";
    // 接口名：实时汇总资讯信息列表
    public static final String METHOD_GetRealTimeConsultList = "PHSocket_GetRealTimeConsultList";
    // 接口名：我的发帖帖子列表
    public static final String METHOD_GetBBSUserTopicList = "PHSocket_GetBBSUserTopicList";
    // 接口名：用户关注的帖子信息列表 PHSocket_GetMyTieBaList
    public static final String METHOD_GetUserFollowTopicList = "PHSocket_GetUserFollowTopicList";
    // 接口名：用户我的话题信息列表
    public static final String METHOD_PHSocket_GetMyTieBaList = "PHSocket_GetMyTieBaList";
    // 接口名：我的论坛回帖列表
    public static final String METHOD_GetUserReplyTopicList = "PHSocket_GetUserReplyTopicList";
    // 接口名：论坛帖子回复楼中楼列表
    public static final String METHOD_GetBBSTopicReplyToReplyList = "PHSocket_GetBBSTopicReplyToReplyList";
    // 接口名：用户投票
    public static final String METHOD_SetTouPiao = "PHSocket_SetTouPiao";
    // 接口名：用户帖子报名
    public static final String METHOD_SetBMTopic = "PHSocket_SetBMTopic";
    // 接口名：用户活动报名
    public static final String METHOD_SetHDUserApply = "PHSocket_SetHDUserApply";
    // 接口名：问答设置最佳
    public static final String METHOD_SetSetAnswer = "PHSocket_SetSetAnswer";
    // 接口名：帖子点赞
    public static final String METHOD_SetTopic_Sup = "PHSocket_SetTopic_Sup";

    // 接口名：二手信息首页信息
    public static final String METHOD_GetErShouIndex = "PHSocket_GetErShouIndex";
    // 接口名：二手信息列表
    public static final String METHOD_GetErShouInfoList = "PHSocket_GetErShouInfoList";
    // 接口名：二手详细信息
    public static final String METHOD_GetErShouInfo = "PHSocket_GetErShouInfo";
    // 接口名：二手认证商家详细信息
    public static final String METHOD_GetErShouShopInfo = "PHSocket_GetErShouShopInfo";
    // 接口名：二手认证商家列表
    public static final String METHOD_GetErShouShopList = "PHSocket_GetErShouShopList";
    // 接口名：二手信息分类通过一级获得二三级分类列表
    public static final String METHOD_GetErShouTwoThreeKindList = "PHSocket_GetErShouTwoThreeKindList";
    // 接口名：物品交换信息插入
    public static final String METHOD_SetErShouJhInfo = "PHSocket_SetErShouJhInfo";
    // 接口名：二手上门收购信息列表
    public static final String METHOD_GetShouGouInfoList = "PHSocket_GetShouGouInfoList";
    // 接口名：二手上门收购详细信息
    public static final String METHOD_GetShouGouInfo = "PHSocket_GetShouGouInfo";

    // 接口名：获得城市站生活首页信息
    public static final String METHOD_GetSiteLifeIndex = "PHSocket_GetSiteLifeIndex";
    // 接口名：电话搜索+商家推荐+全站搜索
    public static final String METHOD_GetSiteTelStoreSearchInfoList = "PHSocket_GetSiteTelStoreSearchInfoList";
    // 接口名：获得电话详细信息
    public static final String METHOD_GetTelInfo = "PHSocket_GetTelInfo";
    // 接口名：全站搜索信息列表
    public static final String METHOD_GetSiteSearchInfoList = "PHSocket_GetSiteSearchInfoList";
    // 接口名：电话簿信息插入/修改
    public static final String METHOD_SetTelInfo = "PHSocket_SetTelInfo";
    // 接口名：获得城市站商家类别列表
    public static final String METHOD_GetStoreAllCategoryList = "PHSocket_GetStoreAllCategoryList";
    // 接口名：获得城市站商圈类别列表
    public static final String METHOD_GetStoreAllAddrList = "PHSocket_GetStoreAllAddrList";
    // 接口名：商家入驻信息插入
    public static final String METHOD_SetStoreInfoAdd = "PHSocket_SetStoreInfoAdd";
    // 接口名：电话搜索信息列表
    public static final String METHOD_GetTelSearchInfoList = "PHSocket_GetTelSearchInfoList";
    // 接口名：用户电话收藏记录插入\删除
    public static final String METHOD_SetUserTelRecord = "PHSocket_SetUserTelRecord";
    // 接口名：用户电话收藏记录是否存在
    public static final String METHOD_GetUserTelRecordIsExist = "PHSocket_GetUserTelRecordIsExist";
    // 接口名：获得城市站商圈商家类别列表
    public static final String METHOD_GetStoreAllCategoryAndAddrList = "PHSocket_GetStoreAllCategoryAndAddrList";
    // 接口名：纠错信息插入
    public static final String METHOD_SetTelNoticeInfo = "PHSocket_SetTelNoticeInfo";
    // 接口名：获取用户发布电话信息
    public static final String METHOD_GetUserTelInfo = "PHSocket_GetUserTelInfo";

    // 接口名：优惠主页三项列表
    public static final String METHOD_GetPreferentialIndex = "PHSocket_GetPreferentialIndex";
    // 接口名：商家促销信息列表 或城市促销信息汇总
    public static final String METHOD_GetStorePromotionList = "PHSocket_GetStorePromotionList";
    // 接口名：优惠卷列表
    public static final String METHOD_GetStoreCouponsList = "PHSocket_GetStoreCouponsList";
    // 接口名：获得城市站初促销信息 类型 / 行业
    public static final String METHOD_GetPromotionCategory = "PHSocket_GetPromotionCategory";
    // 接口名：获得优惠券的类型 / 行业
    public static final String METHOD_GetCouponsCategory = "PHSocket_GetCouponsCategory";

    // 接口名：商家促销信息详细信息
    public static final String METHOD_GetStorePromotionInfo = "PHSocket_GetStorePromotionInfo";

    // 接口名：微封面信息插入
    public static final String METHOD_SetBBSCoverInfoAdd = "PHSocket_SetBBSCoverInfoAdd";
    // 接口名：微封面信息列表
    public static final String METHOD_GetBBSCoverInfoList = "PHSocket_GetBBSCoverInfoList";
    // 接口名：封面配置信息
    public static final String METHOD_GetBBSCoverInfoFrom = "PHSocket_GetBBSCoverInfoFrom";
    // 接口名：帅男排行榜
    public static final String PHSocket_GetBoyUserVirtualCommodityRank = "PHSocket_GetBoyUserVirtualCommodityRank";
    // 接口名：帅男上传印象列表
    public static final String PHSocket_GetTCoverWallTypeListInfo = "PHSocket_GetTCoverWallTypeListInfo";
    // 接口名：封面人物排行榜信息列表
    public static final String METHOD_GetBBSCoverFigureList = "PHSocket_GetBBSCoverFigureList";
    // 接口名：封面秀点赞排行榜列表
    public static final String METHOD_GetBBSCoverInfoRankList = "PHSocket_GetBBSCoverInfoRankList";
    // 接口名：封面秀点赞
    public static final String METHOD_SetBBSCoverInfoDing = "PHSocket_SetBBSCoverInfoDing";
    // 接口名：帅男新人秀
    public static final String PHSocket_GetNewBoyTCoverBoyInfo = "PHSocket_GetNewBoyTCoverBoyInfo";
    // 接口名：帅男照片上传
    public static final String PHSocket_SetBoyCoverUserAndInfo = "PHSocket_SetBoyCoverUserAndInfo";
    // 接口名：封面秀开通的秀场
    public static final String METHOD_GetBBSCoverConfig = "PHSocket_GetBBSCoverConfig";
    // 接口名：封面男神列表
    public static final String PHSocket_GetFigureTCoverBoyInfo = "PHSocket_GetFigureTCoverBoyInfo";
    // 请求帅男最新列表
    public static final String PHSocket_GetNewTCoverBoyInfo = "PHSocket_GetNewTCoverBoyInfo";
    // 帅男各种墙列表页请求
    public static final String PHSocket_GetImpressionTCoverBoyInfo = "PHSocket_GetImpressionTCoverBoyInfo";
    // 接口名：个人中心首页用户信息
    public static final String METHOD_GetBBSUsersInfo = "PHSocket_GetBBSUsersInfo";

    // 接口名：欢迎界面，广告返回
    public static final String METHOD_GetSiteAppStartImg = "PHSocket_GetSiteAppStartImg";
    // 接口名：本站用户登录
    public static final String METHOD_CheckUserLogin = "PHSocket_CheckUserLogin";
    // 接口名：手机注册获取验证码
    public static final String METHOD_SetRegSendCode = "PHSocket_SetRegSendCode";
    // 接口名：手机注册
    public static final String METHOD_SetPhoneRegUser = "PHSocket_SetPhoneRegUser";
    // 接口名： 手机注册验证码验证
    public static final String PHSocket_GetPhoneRegCodeCheck = "PHSocket_GetPhoneRegCodeCheck";
    // 接口名：找回密码的验证码
    public static final String METHOD_SetFindPassWordSendCode = "PHSocket_SetFindPassWordSendCode";
    // 接口名：找回密码的验证 验证码
    public static final String METHOD_GetFindPassWordCheckCode = "PHSocket_GetFindPassWordCheckCode";
    // 接口名：找回密码 ,修改密码
    public static final String METHOD_SetUserFindNewPassWord = "PHSocket_SetUserFindNewPassWord";
    // 接口名：用户短信登录，获得验证码
    public static final String METHOD_SetSmsLoginSendCode = "PHSocket_SetSmsLoginSendCode";
    // 接口名：用户短信登录
    public static final String METHOD_GetSmsLoginCheck = "PHSocket_GetSmsLoginCheck";
    // 接口名：用户QQ和新浪注册登录
    public static final String METHOD_SetRegUserInfo = "PHSocket_SetRegUserInfo";
    // 接口名：用户QQ登录
    public static final String METHOD_QQLoginUserInfo = "PHSocket_QQLoginUserInfo";
    // 接口名：用户微信登录
    public static final String METHOD_WXLoginUserInfo = "PHSocket_WXLoginUserInfo";
    // 接口名：用户新浪登录
    public static final String METHOD_WBLoginUserInfo = "PHSocket_WBLoginUserInfo";
    // 接口名：用户修改昵称
    public static final String METHOD_SetUpdateUserRoleName = "PHSocket_SetUpdateUserRoleName";
    // 接口名：单个修改用户信息
    public static final String METHOD_SetUserBaseInfo = "PHSocket_SetUserBaseInfo";
    // 接口名：验证邮箱
    public static final String METHOD_SetSendAddEmailAttestCode = "PHSocket_SetSendAddEmailAttestCode";
    // 接口名：修改用户密码
    public static final String SetUserResetPassWord = "PHSocket_SetUserResetPassWord";
    // 接口名：用户详细信息
    public static final String METHOD_GetUserInfo = "PHSocket_GetUserInfo";
    // 接口名：本地服务
    public static final String METHOD_GetSiteLocalServer = "PHSocket_GetSiteLocalServer";
    // 接口名：下载看房团列表
    public static final String METHOD_GetLookHouseList = "PHSocket_GetLookHouseList";
    // 接口名：城市房屋区域信息列表
    public static final String METHOD_GetNewHouseList = "PHSocket_GetNewHouseList";
    // 接口名：二手房出租列表
    public static final String METHOD_GetHomeChuZuInfoList = "PHSocket_GetHomeChuZuInfoList";
    // 接口名：二手房小区列表
    public static final String METHOD_GetCommunityInfoList = "PHSocket_GetCommunityInfoList";
    // 接口名：黄页详细列表
    public static final String METHOD_GetYPInfoList = "PHSocket_GetYPInfoList";
    public static final String METHOD_GetYPInfo = "PHSocket_GetYPInfo";

    // 接口名：商家列表
    public static final String METHOD_GetStoreShowList = "PHSocket_GetStoreShowList";
    // 接口名：商家商品列表
    public static final String METHOD_GetProductList = "PHSocket_GetProductList";
    // 接口名：二手房店铺转让列表
    public static final String METHOD_GetShopTransferInfoList = "PHSocket_GetShopTransferInfoList";
    // 接口名：二手房出租详细页
    public static final String METHOD_GetHomeChuZuInfo = "PHSocket_GetHomeChuZuInfo";
    // 接口名：二手房中介员工详细页
    public static final String METHOD_GetMediationStaffInfo = "PHSocket_GetMediationStaffInfo";
    // 接口名：二手房中介详细页
    public static final String METHOD_GetMediationInfo = "PHSocket_GetMediationInfo";
    // 接口名：照片上传
    public static final String METHOD_Code_UploadImg = "PHSocket_Code_UploadImg";
    // 接口名：二手房店铺转让详细页
    public static final String METHOD_GetShopTransferInfo = "PHSocket_GetShopTransferInfo";
    // 接口名：车辆买卖详细页
    public static final String METHOD_GetTrafficInfo = "PHSocket_GetTrafficInfo";
    // 接口名：车辆买卖车行详细页
    public static final String METHOD_GetCheHangInfo = "PHSocket_GetCheHangInfo";
    // 接口名：教育培训详细页
    public static final String METHOD_GetPostEducationInfo = "PHSocket_GetPostEducationInfo";
    // 接口名：黄页大全详细页
    public static final String METHOD_GetYPIndex = "PHSocket_GetYPIndex";
    // 接口名：同城交友详细页
    public static final String METHOD_GetPostFrindInfo = "PHSocket_GetPostFrindInfo";
    // 接口名：商家产品详情页
    public static final String METHOD_GetProductInfo = "PHSocket_GetProductInfo";
    // 接口名：联盟卡详情页
    public static final String METHOD_GetStoreCordInfo = "PHSocket_GetStoreCordInfo";
    // 接口名：服务大全详细页
    public static final String METHOD_GetPostLiveInfo = "PHSocket_GetPostLiveInfo";
    // 接口名：宠物世界详细页
    public static final String METHOD_GetPostPetInfo = "PHSocket_GetPostPetInfo";
    // 接口名：商业机会详细页
    public static final String METHOD_GetPostBusinessInfo = "PHSocket_GetPostBusinessInfo";
    // 接口名：专题活动详细页
    public static final String METHOD_GetZthdInfo = "PHSocket_GetZthdInfo";
    // 接口名：专题活动详细页
    public static final String METHOD_GetBBSzthdInfo = "PHSocket_GetBBSzthdInfo";
    // 接口名：二手房小区详细页
    public static final String METHOD_GetCommunityInfo = "PHSocket_GetCommunityInfo";
    // 接口名：二手房求租详细页
    public static final String METHOD_GetHomeQiuZuInfo = "PHSocket_GetHomeQiuZuInfo";
    // 接口名：二手房求购详细页
    public static final String METHOD_GetHomeQiuGouInfo = "PHSocket_GetHomeQiuGouInfo";
    // 接口名：商家详细页
    public static final String METHOD_GetStoreInfo = "PHSocket_GetStoreInfo";
    // 接口名：二手房出售详细页
    public static final String METHOD_GetHomeChuShouInfo = "PHSocket_GetHomeChuShouInfo";
    // 接口名：二手房求租列表
    public static final String METHOD_GetHomeQiuZuInfoList = "PHSocket_GetHomeQiuZuInfoList";
    // 接口名：二手房求购列表
    public static final String METHOD_GetHomeQiuGouInfoList = "PHSocket_GetHomeQiuGouInfoList";
    // 接口名：二手房出售列表
    public static final String METHOD_GetHomeChuShouInfoList = "PHSocket_GetHomeChuShouInfoList";
    // 接口名：区域列表
    public static final String METHOD_GetNewHouseZoneList = "PHSocket_GetNewHouseZoneList";
    // 接口名：生活服务s
    public static final String METHOD_GetPostLiveInfoList = "PHSocket_GetPostLiveInfoList";
    // 接口名：发布信息设置
    public static final String METHOD_GetPostCheckList = "PHSocket_GetPostCheckList";
    // 接口名：房屋特色列表
    public static final String METHOD_GetSheBei = "PHSocket_GetSheBei";
    // 接口名：手机类别
    public static final String METHOD_GetErShouCIDList = "PHSocket_GetErShouCIDList";
    // 接口名：二手二级列表类别
    public static final String METHOD_GetErShouCID1List = "PHSocket_GetErShouCID1List";
    // 接口名：二手三级列表类别
    public static final String METHOD_GetErShouCID2List = "PHSocket_GetErShouCID2List";
    // 接口名：宠物类别
    public static final String METHOD_GetPetKindList = "PHSocket_GetPetKindList";
    // 接口名：宠物世界类别
    public static final String METHOD_GetPostPetAllKindList = "PHSocket_GetPostPetAllKindList";
    // 接口名：汽车配件类别
    public static final String METHOD_GetAccessoriesCIDList = "PHSocket_GetAccessoriesCIDList";
    // 接口名：公司行业类别
    public static final String METHOD_GetCompTradeList = "PHSocket_GetCompTradeList";
    // 接口名：城市房屋户型分类
    public static final String METHOD_GetHomeTypeList = "PHSocket_GetHomeTypeList";
    // 接口名：商家分类
    public static final String METHOD_GetStoreAllCategoryAndAddrAndOrderList = "PHSocket_GetStoreAllCategoryAndAddrAndOrderList";
    // 接口名：商家产品分类
    public static final String METHOD_GetProductKindList = "PHSocket_GetProductKindList";
    // 接口名：城市房屋区详细页
    public static final String METHOD_GetNewHouseInfo = "PHSocket_GetNewHouseInfo";
    // 接口名：城市房屋区详细介绍
    public static final String METHOD_GetNewHouseAbout = "PHSocket_GetNewHouseAbout";
    // 接口名：全职招聘发布提交
    public static final String METHOD_SetJobInfoAdd = "PHSocket_SetJobInfoAdd";
    // 接口名：房屋出售发布提交
    public static final String METHOD_SetHomeChuShouInfoAdd = "PHSocket_SetHomeChuShouInfoAdd";
    // 接口名：二手信息发布提交
    public static final String METHOD_SetEsInfoAdd = "PHSocket_SetEsInfoAdd";
    // 接口名：同城交友发布提交
    public static final String METHOD_SetFriendInfoAdd = "PHSocket_SetFriendInfoAdd";
    // 接口名：上门收购发布提交
    public static final String METHOD_SetEsSGInfoAdd = "PHSocket_SetEsSGInfoAdd";
    // 接口名：店铺转让发布提交
    public static final String METHOD_SetShopTransferInfoAdd = "PHSocket_SetShopTransferInfoAdd";
    // 接口名：宠物世界发布提交
    public static final String METHOD_SetPetInfoAdd = "PHSocket_SetPetInfoAdd";
    // 接口名：生活服务发布提交
    public static final String METHOD_SetLiveInfoInfoAdd = "PHSocket_SetLiveInfoInfoAdd";
    // 接口名：商业机会发布提交
    public static final String METHOD_SetBusinessInfoAdd = "PHSocket_SetBusinessInfoAdd";
    // 接口名：教育培训发布提交
    public static final String METHOD_SetEduInfoAdd = "PHSocket_SetEduInfoAdd";
    // 接口名：房屋求租发布提交
    public static final String METHOD_SetHomeQiuZuInfoAdd = "PHSocket_SetHomeQiuZuInfoAdd";
    // 接口名：房屋求购发布提交
    public static final String METHOD_SetHomeQiuGouInfoAdd = "PHSocket_SetHomeQiuGouInfoAdd";
    // 接口名：房屋出租发布提交
    public static final String METHOD_SetHomeChuZuInfoAdd = "PHSocket_SetHomeChuZuInfoAdd";
    // 接口名：兼职招聘招聘发布提交
    public static final String METHOD_SetPartJobInfoAdd = "PHSocket_SetPartJobInfoAdd";
    // 接口名：二手房资讯
    public static final String METHOD_GetPostHouseNewsInfoList = "PHSocket_GetPostHouseNewsInfoList";
    // 接口名：专题图集
    public static final String METHOD_GetBBSzthdPictureList = "PHSocket_GetBBSzthdPictureList";
    // 接口名：宠物世界列表
    public static final String METHOD_GetPostPetInfoList = "PHSocket_GetPostPetInfoList";
    // 接口名：教育培训列表
    public static final String METHOD_GetPostEducationInfoList = "PHSocket_GetPostEducationInfoList";
    // 接口名：商业机会列表
    public static final String METHOD_GetPostBusinessInfoList = "PHSocket_GetPostBusinessInfoList";
    // 接口名：同城交友列表
    public static final String METHOD_GetPostFrindInfoList = "PHSocket_GetPostFrindInfoList";
    // 接口名：自定义专题图集
    public static final String METHOD_GetBBSzthdCustoMizedPhotoList = "PHSocket_GetBBSzthdCustoMizedPhotoList";
    // 接口名：专题留言
    public static final String METHOD_GetBBSzthdMessageList = "PHSocket_GetBBSzthdMessageList";
    // 接口名：专题留言
    public static final String METHOD_SetBBSzthdMessageInfo = "PHSocket_SetBBSzthdMessageInfo";
    // 接口名：分贴互动
    public static final String METHOD_GetBBSzthdHuDongList = "PHSocket_GetBBSzthdHuDongList";
    // 接口名：专题动态
    public static final String METHOD_GetBBSzthdDongTaiList = "PHSocket_GetBBSzthdDongTaiList";
    // 接口名：专题自定义动态
    public static final String METHOD_GetBBSzthdCustoMizedNewsList = "PHSocket_GetBBSzthdCustoMizedNewsList";
    // 接口名：专题自定义任务历程
    public static final String METHOD_GetBBSzthdCustoMizedFigureList = "PHSocket_GetBBSzthdCustoMizedFigureList";
    // 接口名：车行信息列表
    public static final String METHOD_GetCheHangList = "PHSocket_GetCheHangList";
    // 接口名：城市房屋区在线问答列表
    public static final String METHOD_GetHouseAskOnlineList = "PHSocket_GetHouseAskOnlineList";
    // 接口名：商家在线问答列表
    public static final String METHOD_GetAskOnlineList = "PHSocket_GetAskOnlineList";
    // 接口名：商家相册列表
    public static final String METHOD_GetStorePhotosList = "PHSocket_GetStorePhotosList";
    // 接口名：商家相册照片列表
    public static final String METHOD_GetStoreImagesList = "PHSocket_GetStoreImagesList";
    // 接口名：商家在线点评列表
    public static final String METHOD_GetStorePostList = "PHSocket_GetStorePostList";
    // 接口名：商家在线商品点评列表
    public static final String METHOD_GetProductPostList = "PHSocket_GetProductPostList";
    // 接口名：商家在线点评列表顶
    public static final String METHOD_SetStorePostDing = "PHSocket_SetStorePostDing";
    // 接口名：商家在线商品点评列表顶
    public static final String METHOD_SetProductPostDing = "PHSocket_SetProductPostDing";
    // 接口名：商家在线点评列表回复
    public static final String METHOD_SetStorePostReply = "PHSocket_SetStorePostReply";
    // 接口名：商家在线商品点评列表回复
    public static final String METHOD_SetProductPostReply = "PHSocket_SetProductPostReply";
    // 接口名：城市房屋楼盘动态
    public static final String METHOD_GetXlpNewsList = "PHSocket_GetXlpNewsList";
    // 接口名：二手房中介大全列表
    public static final String METHOD_GetMediationInfoList = "PHSocket_GetMediationInfoList";
    // 接口名：二手房经纪人列表
    public static final String METHOD_GetMediationStaffList = "PHSocket_GetMediationStaffList";
    // 接口名：城市房屋看房团说明详细页
    public static final String METHOD_GetLookHouseInfoAbout = "PHSocket_GetLookHouseInfoAbout";
    // 接口名：城市房屋看房团详细页
    public static final String METHOD_GetLookHouseInfo = "PHSocket_GetLookHouseInfo";
    // 接口名：城市房屋团购报名
    public static final String METHOD_SetHouseGrouponAdd = "PHSocket_SetHouseGrouponAdd";
    // 接口名：中介用户留言
    public static final String METHOD_SetCustomerMessageAdd = "PHSocket_SetCustomerMessageAdd";
    // 接口名：城市房屋看房团报名
    public static final String METHOD_SetLookHouseAdd = "PHSocket_SetLookHouseAdd";
    // 接口名：城市房屋在线提问提交问题
    public static final String METHOD_SetHouseAskOnlineAdd = "PHSocket_SetHouseAskOnlineAdd";
    // 接口名：商家在线提问提交问题
    public static final String METHOD_SetAskOnlineAdd = "PHSocket_SetAskOnlineAdd";
    // 接口名：商家点评提交
    public static final String METHOD_SetStorePostNewsAdd = "PHSocket_SetStorePostNewsAdd";
    // 接口名：商家商品点评提交
    public static final String METHOD_SetProductPostAdd = "PHSocket_SetProductPostAdd";
    // 接口名：新闻图集接口
    public static final String METHOD_GetNewsPhotoList = "PHSocket_GetNewsPhotoList";
    // 接口名：资讯图集接口
    public static final String METHOD_GetNewsPhotoImgList = "PHSocket_GetNewsPhotoImgList";
    // 接口名：资讯图集评论接口
    public static final String METHOD_SetNewsPhotoReplyInfo = "PHSocket_SetNewsPhotoReplyInfo";
    // 接口名：资讯图集评论列表
    public static final String METHOD_GetNewsPhotoReplyList = "PHSocket_GetNewsPhotoReplyList";
    // 接口名：资讯图集评论顶
    public static final String METHOD_SetNewsPhotoReplyTop = "PHSocket_SetNewsPhotoReplyTop";
    // 接口名：本地服务上传账号
    public static final String METHOD_SetSiteLocalServer = "PHSocket_SetSiteLocalServer";
    // 接口名：获得城市站当日推送信息
    public static final String METHOD_GetSitePushMessage = "PHSocket_GetSitePushMessage";
    // 接口名：获得用户帖子回复推送信息
    public static final String METHOD_GetUserReplyPushMessage = "PHSocket_GetUserReplyPushMessage";
    // 接口名：获得用户@我推送信息
    public static final String METHOD_GetUserCircuseePushMessage = "PHSocket_GetUserCircuseePushMessage";
    // 接口名：获得用户@我推送信息标记
    public static final String METHOD_SetUserPushMsgUpRead = "PHSocket_SetUserPushMsgUpRead";

    // 接口名：团购分类
    public static final String METHOD_GetGrouponCategoryList = "PHSocket_GetGrouponCategoryList";
    // 接口名：团购专题列表
    public static final String METHOD_GetGrouponSpecialList = "PHSocket_GetGrouponSpecialList";
    // 接口名：团购列表
    public static final String METHOD_GetGrouponList = "PHSocket_GetGrouponList";
    // 接口名：团购详情
    public static final String METHOD_GetGrouponInfo = "PHSocket_GetGrouponInfo";
    // 接口名：商家详情
    public static final String METHOD_GetGrouponInfoDetails = "PHSocket_GetGrouponInfoDetails";
    // 接口名：团购订单生成
    public static final String METHOD_GetGrouponOrderSendInfo = "PHSocket_GetGrouponOrderSendInfo";
    // 接口名：用户团购订单信息
    public static final String METHOD_GetMyGrouponInfo = "PHSocket_GetMyGrouponInfo";
    // 接口名：用户团购订单列表（ 0：全部 1：未付款 2：已付款）
    public static final String METHOD_GetMyOrderList = "PHSocket_GetMyOrderList";
    // 接口名：团购评论列表
    public static final String METHOD_GetGrouponPostList = "PHSocket_GetGrouponPostList";
    // 接口名：团购发表评论
    public static final String METHOD_SetGrouponPostAdd = "PHSocket_SetGrouponPostAdd";
    // 接口名：密码修改
    public static final String METHOD_SetUserResetPassWord = "PHSocket_SetUserResetPassWord";
    // 接口名:获得用户推送信息计数
    public static final String METHOD_GetUserPushMsgCount = "PHSocket_GetUserPushMsgCount";
    // 接口名：手机绑定执行
    public static final String METHOD_SetPhoneBindCheck = "PHSocket_SetPhoneBindCheck";
    // 接口名：手机绑定获取验证码

    public static final String METHOD_SetPhoneBindSendCode = "PHSocket_SetPhoneBindSendCode";
    // 接口名：收货地址列表
    public static final String METHOD_GetMyAddrList = "PHSocket_GetMyAddrList";
    // 接口名：设置默认收货地址
    public static final String METHOD_SetMyAddrDefault = "PHSocket_SetMyAddrDefault";
    // 接口名：收货地址详细信息
    public static final String METHOD_GetAddrInfo = "PHSocket_GetAddrInfo";
    // 接口名：添加收货地址（新的）
    public static final String METHOD_SetMyAddrInfoAdd = "PHSocket_SetMyAddrInfoAdd";
    // 接口名：修改收货地址
    public static final String METHOD_SetMyAddrInfoUp = "PHSocket_SetMyAddrInfoUp";
    // 接口名：删除收货地址
    public static final String METHOD_SetMyAddrDel = "PHSocket_SetMyAddrDel";
    // 接口名：生成订单，取得订单号
    public static final String METHOD_SetDealBuySendOrder = "PHSocket_SetDealBuySendOrder";
    // 接口名：根据订单号拿到绑定数据
    public static final String METHOD_GetGrouponOrderInfo = "PHSocket_GetGrouponOrderInfo";
    // 接口名：确认支付方式 生成订单流水号和支付标识
    public static final String METHOD_SetGrouponOrderUpPay = "PHSocket_SetGrouponOrderUpPay";
    // 接口名：余额支付获取验证码
    public static final String METHOD_SetBalanceConsumeSendCode = "PHSocket_SetBalanceConsumeSendCode";
    // 接口名：使用余额支付 确认支付
    public static final String METHOD_SetGrouponBalanceConsume = "PHSocket_SetGrouponBalanceConsume";
    // 接口名：手机验证码 校验
    public static final String METHOD_GetAuthKeyCheckBool = "PHSocket_GetAuthKeyCheckBool";
    // 接口名：支付成功（线下支付）
    public static final String METHOD_GetGrouponOrderPayOK = "PHSocket_GetGrouponOrderPayOK";
    // 接口名：订单详情
    public static final String METHOD_GetOrderPayInfo = "PHSocket_GetOrderPayInfo";
    // 接口名：申请退款页面
    public static final String METHOD_GetGrouponOrderBackInfo = "PHSocket_GetGrouponOrderBackInfo";
    // 接口名：申请退款
    public static final String METHOD_SetGrouponOrderBack = "PHSocket_SetGrouponOrderBack";
    // 接口名：取消退款页面详情
    public static final String METHOD_GetUserGrouponOrderBackInfo = "PHSocket_GetUserGrouponOrderBackInfo";
    // 接口名：取消退款
    public static final String METHOD_SetGrouponOrderBackClear = "PHSocket_SetGrouponOrderBackClear";
    // 接口名：焦点新更新条数
    public static final String METHOD_GetRealTimeConsultNewSum = "PHSocket_GetRealTimeConsultNewSum";
    // 接口名：名企招聘信息列表
    public static final String METHOD_GetFamousEnterpriseTopJobList = "PHSocket_GetFamousEnterpriseTopJobList";

    // 接口名：找工作龙头信息列表
    public static final String METHOD_GetLongTouTopJobList = "PHSocket_GetLongTouTopJobList";

    // 接口名：获得名企详细信息和职位列表
    public static final String METHOD_GetFamousEnterpriseInfo = "PHSocket_GetFamousEnterpriseInfo";
    // 接口名：招聘详细信息
    public static final String METHOD_GetJobInfo = "PHSocket_GetJobInfo";
    // 接口名：兼职招聘详细信息
    public static final String METHOD_GetPartJobInfo = "PHSocket_GetPartJobInfo";
    // 接口名：招聘详情-申请记录
    public static final String METHOD_GetJobApplyRecordList = "PHSocket_GetJobApplyRecordList";
    // 接口名：招聘详情-公司介绍-其他职位
    public static final String METHOD_GetJobCompanyInfo = "PHSocket_GetJobCompanyInfo";
    // 接口名：龙头企业-公司详情-基本信息和招聘职位
    public static final String METHOD_GetLongTouQiyeShow = "PHSocket_GetLongTouQiyeShow";
    // 接口名：龙头企业-公司详情-公司动态

    public static final String METHOD_GetLongTouNewsList = "PHSocket_GetLongTouNewsList";

    public static final String METHOD_GetLongTouPost = "PHSocket_GetLongTouPost";

    public static final String METHOD_GetLongTouNewsShow = "PHSocket_GetLongTouNewsShow";

    // 接口名：区域信息列表
    public static final String METHOD_GetJobZoneList = "PHSocket_GetJobZoneList";
    // 接口名：全职招聘职位列表
    public static final String METHOD_GetSiteJobKindList = "PHSocket_GetSiteJobKindList";
    // 接口名：汽车品牌列表
    public static final String METHOD_GetCarBrandKIDList = "PHSocket_GetCarBrandKIDList";
    // 接口名：汽车车系列表
    public static final String METHOD_GetCarBrandCIDList = "PHSocket_GetCarBrandCIDList";
    // 接口名：车辆买卖首页
    public static final String METHOD_GetCarIndex = "PHSocket_GetCarIndex";
    // 接口名：车辆买卖各个列表
    public static final String METHOD_GetTrafficInfoList = "PHSocket_GetTrafficInfoList";
    // 接口名：兼职招聘职位列表
    public static final String METHOD_GetSitePartJobKindList = "PHSocket_GetSitePartJobKindList";
    // 接口名：全职招聘信息列表
    public static final String METHOD_GetJobInfoList = "PHSocket_GetJobInfoList";
    // 接口名：汽车买卖
    public static final String METHOD_SetTrafficInfoAdd = "PHSocket_SetTrafficInfoAdd";
    // 接口名：兼职招聘信息列表
    public static final String METHOD_GetPartJobInfoList = "PHSocket_GetPartJobInfoList";
    // 接口名：求职简历列表
    public static final String METHOD_GetResumeInfoList = "PHSocket_GetResumeInfoList";
    // 接口名：求职简历详细信息
    public static final String METHOD_GetResumeInfo = "PHSocket_GetResumeInfo";
    // 接口名：用户简历工作经历删除
    public static final String METHOD_SetUserResumeInfoWorkDel = "PHSocket_SetUserResumeInfoWorkDel";
    // 接口名：用户简历工作经历添加
    public static final String METHOD_SetUserResumeInfoWorkAdd = "PHSocket_SetUserResumeInfoWorkAdd";
    // 接口名：用户简历工作经历修改
    public static final String METHOD_SetUserResumeInfoWorkUp = "PHSocket_SetUserResumeInfoWorkUp";
    // 接口名：用户简历佳域经历添加
    public static final String METHOD_SetUserResumeInfoEduAdd = "PHSocket_SetUserResumeInfoEduAdd";
    // 接口名：用户简历教育经历修改
    public static final String METHOD_SetUserResumeInfoEduUp = "PHSocket_SetUserResumeInfoEduUp";
    // 接口名：教育经历经历删除
    public static final String METHOD_SetUserResumeInfoEduDel = "PHSocket_SetUserResumeInfoEduDel";
    // 接口名：求职简历联系方式
    public static final String METHOD_GetResumeContactPhone = "PHSocket_GetResumeContactPhone";
    // 接口名：用户全职简历投递插入
    public static final String METHOD_SetUserApplyForJobs = "PHSocket_SetUserApplyForJobs";
    // 接口名：用户兼职简历投递插入
    public static final String METHOD_SetUserApplyForPartJobs = "PHSocket_SetUserApplyForPartJobs";
    // 接口名：获取简历信息
    public static final String METHOD_GetUserResumeInfo = "PHSocket_GetUserResumeInfo";
    // 接口名：修改简历信息
    public static final String METHOD_SetUserResumeInfoAddOrUp = "PHSocket_SetUserResumeInfoAddOrUp";
    // 接口名：企业用户发送短信执行
    public static final String METHOD_SetResumeSendMsg = "PHSocket_SetResumeSendMsg";
    // 接口名：分类信息举报
    public static final String METHOD_SetPostInfoReport = "PHSocket_SetPostInfoReport";
    // 接口名：找工作首页热门职位
    public static final String METHOD_GetSiteHotJobKindList = "PHSocket_GetSiteHotJobKindList";

    // 接口名：个人中心页面
    public static final String PHSocket_GetBBSUsersInfo = "PHSocket_GetBBSUsersInfo";
    // 接口名：他的帖子
    public static final String PHSocket_UserTopicList = "PHSocket_UserTopicList";
    // 接口名：他的回复
    public static final String PHSocket_UserReplyTopicList = "PHSocket_UserReplyTopicList";
    // 接口名：个人相册列表
    public static final String PHSocket_GetPicInfoList = "PHSocket_GetPicInfoList";
    // 接口名：个人相册专辑列表
    public static final String PHSocket_GetPicClassInfoList = "PHSocket_GetPicClassInfoList";
    // 接口名：删除好友
    public static final String PHSocket_SetDelFriend = "PHSocket_SetDelFriend";
    // 接口名：添加好友
    public static final String PHSocket_SetAddFriend = "PHSocket_SetAddFriend";
    /**
     * 判断用户是否已关注另一个用户
     */
    public static final String PHSocket_GetIsFriend = "PHSocket_GetIsFriend";
    // 接口名：给好友--发送信息
    public static final String PHSocket_SendSmsInfo = "PHSocket_SendSmsInfo";
    // 接口名：好友列表
    public static final String PHSocket_GetUsersFriendList = "PHSocket_GetUsersFriendList";
    // 电话的收藏界面回调Activity的resultCode
    public static final int PHONE_COLLECTION_REQUESTCODE = 121;
    public static final int PHONE_COLLECTION_RESULTCODE = 124;

    // ------------------------------------------------最新版贴吧接口-----------------------------------------------
    // 接口名：贴吧分页列表
    public static final String PHSocket_GetTieBaList = "PHSocket_GetTieBaList";
    // 接口名：闹闹--轻交流话题版接口
    public static final String PHSocket_GetNewTieBaList = "PHSocket_GetNewTieBaList";
    // 接口名：贴吧话题列表
    public static final String PHSocket_GetTieBaGambit = "PHSocket_GetTieBaGambit";
    // 接口名：更多话题
    public static final String PHSocket_GetTieBaGambitList = "PHSocket_GetTieBaGambitList";
    // 接口名：贴吧标签列表
    public static final String PHSocket_GetTieBaLabel = "PHSocket_GetTieBaLabel";
    // 接口名：贴吧详细
    public static final String PHSocket_GetTiebaByID = "PHSocket_GetTiebaByID";
    // 接口名：贴吧回复列表
    public static final String PHSocket_GetTieBaReplyList = "PHSocket_GetTieBaReplyList";
    // 接口名：贴吧打赏列表
    public static final String PHSocket_GetTieBaReward = "PHSocket_GetTieBaReward";
    // 接口名：贴吧楼中楼回复列表
    public static final String PHSocket_GetTieBaReplyToReplyList = "PHSocket_GetTieBaReplyToReplyList";
    // 接口名：帖吧发布
    public static final String PHSocket_SetTieBaAdd = "PHSocket_SetTieBaAdd";
    // 接口名：帖吧回复
    public static final String PHSocket_SetTieBaReplyAdd = "PHSocket_SetTieBaReplyAdd";
    // 接口名：贴吧标签详细
    public static final String PHSocket_GetTieBaLabelByID = "PHSocket_GetTieBaLabelByID";
    // 接口名：贴吧话题详细
    public static final String PHSocket_GetTieBaGambitByID = "PHSocket_GetTieBaGambitByID";
    // 接口名：贴吧大咖列表
    public static final String PHSocket_GetTieBaDaKaList = "PHSocket_GetTieBaDaKaList";
    // 接口名：回复帝排行榜
    public static final String PHSocket_GetTieBaReplyRank = "PHSocket_GetTieBaReplyRank";
    // 接口名：吧帝排行榜
    public static final String PHSocket_GetTieBaToReplyRank = "PHSocket_GetTieBaToReplyRank";
    // 接口名：发帖帝排行榜
    public static final String PHSocket_GetTieBaTopicRank = "PHSocket_GetTieBaTopicRank";
    // 接口名：贴吧点赞
    public static final String PHSocket_SetTieBaLike = "PHSocket_SetTieBaLike";
    // 接口名：贴吧用户打赏
    public static final String PHSocket_SetTieBaReward = "PHSocket_SetTieBaReward";
    // 接口名：贴吧道具获取
    public static final String PHSocket_GetUserFunctionPropertyList = "PHSocket_GetUserFunctionPropertyList";
    // 接口名：贴吧举报
    public static final String PHSocket_SetTieBaComplain = "PHSocket_SetTieBaComplain";

    // ------------------------------------------------最新版活动接口-----------------------------------------------
    // 接口名：活动列表
    public static final String PHSocket_GetActiviesPubList = "PHSocket_GetActiviesPubList";

    // ------------------------------------------------最新版论坛接口-----------------------------------------------
    // 接口名：猜测用户喜欢板块列表
    public static final String PHSocket_GetUserMayFavoriteBoardList = "PHSocket_GetUserMayFavoriteBoardList";
    // 接口名：用户关注板块列表
    public static final String PHSocket_GetBoardInfoListByUser = "PHSocket_GetBoardInfoListByUser";
    // 接口名：城市站论坛板块列表（新）
    public static final String PHSocket_GetBoardListBySiteId = "PHSocket_GetBoardListBySiteId";
    // 接口名：版块关注
    public static final String PHSocket_SetUserFollowBoard = "PHSocket_SetUserFollowBoard";
    // 接口名：获得城市论坛板块详细信息
    public static final String PHSocket_GetBBSBoardInfo = "PHSocket_GetBBSBoardInfo";
    // 接口名：获得城市论坛板块详细信息
    public static final String PHSocket_GetBBSTopicList = "PHSocket_GetBBSTopicList";

    // //接口名：群体板块会员列表
    public static final String PHSocket_GetBBSBoardUser = "PHSocket_GetBBSBoardUser";

    /**
     * 捧场王排行榜
     */
    public static final String PHSocket_GetBBSReplyRank = "PHSocket_GetBBSReplyRank";
    /**
     * 名人堂排行榜
     */
    public static final String PHSocket_GetBBSToReplyRank = "PHSocket_GetBBSToReplyRank";
    /**
     * 论坛值得一看
     */
    public static final String PHSocket_GetTopicAllIsviewList = "PHSocket_GetTopicAllIsviewList";
    /**
     * 论坛图说列表
     */
    public static final String PHSocket_GetHotListNew = "PHSocket_GetHotListNew";
    /**
     * 论坛用户打赏
     */
    public static final String PHSocket_SetBBSReward = "PHSocket_SetBBSReward";
    /**
     * 论坛打赏分页列表
     */
    public static final String PHSocket_GetBBSReward = "PHSocket_GetBBSReward";

    // ------------------------------------------------最新版个人中心接口-----------------------------------------------
    // 接口名：个人信息展示
    public static final String PHSocket_GetUser_Info = "PHSocket_GetUser_Info";
    // 接口名：用户勋章列表
    public static final String PHSocket_GetUserMedalList = "PHSocket_GetUserMedalList";
    // 接口名：用户资料详细
    public static final String PHSocket_GetUserInfoDetails = "PHSocket_GetUserInfoDetails";
    // 接口名：用户印象列表
    public static final String PHSocket_GetUserImpressionList = "PHSocket_GetUserImpressionList";
    // 接口名：获取用户美女秀场图片列表
    public static final String PHSocket_GetUserCover_MM_List = "PHSocket_GetUserCover_MM_List";
    // 接口名：获取用户帅男秀场图片列表
    public static final String PHSocket_GetUserCover_GG_List = "PHSocket_GetUserCover_GG_List";
    // 接口名：获取用户宝宝秀场图片列表
    public static final String PHSocket_GetMyBaoBaoCoverList = "PHSocket_GetMyBaoBaoCoverList";
    // 接口名：获取用户宝宝秀场图片列表
    public static final String PHSocket_GetBB_CoverInfo = "PHSocket_GetBB_CoverInfo";
    // 接口名：获取城市站推荐频道开关
    public static final String PHSocket_GetRecommendChannelList = "PHSocket_GetRecommendChannelList";
    // 接口名：用户参与贴吧内容列表
    public static final String PHSocket_GetUserTieBaList = "PHSocket_GetUserTieBaList";
    // 接口名：用户参与贴吧内容列表
    public static final String PHSocket_GetUserTopicList = "PHSocket_GetUserTopicList";
    // 接口名：用户参与城事内容列表
    public static final String PHSocket_GetUserSiteNewsList = "PHSocket_GetUserSiteNewsList";
    // 接口名：用户参与活动内容列表
    public static final String PHSocket_GetUserActiveList = "PHSocket_GetUserActiveList";
    // 接口名：用户礼物列表
    public static final String PHSocket_GetUserGiftList = "PHSocket_GetUserGiftList";
    // 接口名：商城礼物商品列表
    public static final String PHSocket_GetGiftCommodityList = "PHSocket_GetGiftCommodityList";
    // 接口名：用户礼物商品列表
    public static final String PHSocket_GetUserGiftCommodityList = "PHSocket_GetUserGiftCommodityList";
    // 接口名：礼物赠送
    public static final String PHSocket_APP_SendGiftToUser = "PHSocket_APP_SendGiftToUser";
    // 接口名：单个帅男美女照片列表
    public static final String PHSocket_GetOneTCoverInfoList = "PHSocket_GetOneTCoverInfoList";
    // 接口名：删除帅男、美女照片
    public static final String PHSocket_SetOneTCoverInfoDel = "PHSocket_SetOneTCoverInfoDel";

    // ------------------------------------------------最新版其他接口-----------------------------------------------
    // 接口名：用户签到
    public static final String PHSocket_GetUserSign = "PHSocket_GetUserSign";
    // 接口名：个人信息
    public static final String PHSocket_GetBBSUsersInfoNew = "PHSocket_GetBBSUsersInfoNew";
    // 接口名：网站留言
    public static final String PHSocket_SetContactusInfo = "PHSocket_SetContactusInfo";
    // ------------------------------------------------最新版主页发现接口-----------------------------------------------
    // 接口名：发现
    public static final String PHSocket_GetAppSetInfo = "PHSocket_GetAppSetInfo";
    // 接口名：简版发现
    public static final String PHSocket_GetFindNavigationInfo = "PHSocket_GetFindNavigationInfo";
    // 接口名： 首页列表
    public static final String PHSocket_APP_CityMasterIndex = "PHSocket_APP_CityMasterIndex";
    //头条首页今日列表
    public static final String PHSocket_GetHeadlinesInfoT = "PHSocket_GetHeadlinesInfoT";
    //头条首页今日以前的信息列表
    public static final String PHSocket_GetHeadlinesInfoO = "PHSocket_GetHeadlinesInfoO";
    // 接口名：首页导航设置信息接口
    public static final String PHSocket_GetHomeNavigationInfo = "PHSocket_GetHomeNavigationInfo";

    // 新---------------------------------------------
    // 任务中心列表
    public static final String PHSocket_UserTaskConfigList = "PHSocket_UserTaskConfigList";
    // 任务详情请求
    public static final String PHSocket_APP_TaskConfigDetail = "PHSocket_APP_TaskConfigDetail";
    // 领取任务
    public static final String PHSocket_APP_UserTaskReceiveAdd = "PHSocket_APP_UserTaskReceiveAdd";
    // 任务勋章
    public static final String PHSocket_APP_MyUserMedalList = "PHSocket_APP_MyUserMedalList";
    // 任务等级
    public static final String PHSocket_APP_GetMyTLevel = "PHSocket_APP_GetMyTLevel";
    // 任务我的城市币
    public static final String PHSocket_GetUserCoinLog = "PHSocket_GetUserCoinLog";
    // 个人中心修改信息
    public static final String PHSocket_SetUserBaseInfo = "PHSocket_SetUserBaseInfo";
    // 上传额外的注册信息
    public static final String PHSocket_SetAddUserBaseInfo = "PHSocket_SetAddUserBaseInfo";
    // 我的特权总列表
    public static final String PHSocket_APP_GetUserPrivilegeList = "PHSocket_APP_GetUserPrivilegeList";
    // 商城主页第一个接口
    public static final String PHSocket_APP_TCommodityIndex = "PHSocket_APP_TCommodityIndex";
    // 商城优惠券确认码请求
    public static final String PHSocket_UseCoupon = "PHSocket_UseCoupon";
    // 商城主页第二个接口
    public static final String PHSocket_APP_TCommodityList = "PHSocket_APP_TCommodityList";
    // 商城主页筛选接口
    public static final String PHSocket_APP_GetCommodityCategories = "PHSocket_APP_GetCommodityCategories";
    // 商城详细页上方信息接口
    public static final String PHSocket_APP_CommodityDetail = "PHSocket_APP_CommodityDetail";
    // 商城详细页下方列表信息接口
    public static final String PHSocket_APP_ObservationList = "PHSocket_APP_ObservationList";
    // 点亮勋章
    public static final String PHSocket_APP_SetTMedalLit = "PHSocket_APP_SetTMedalLit";
    // 商品兑换
    public static final String PHSocket_APP_TCommodityOrderAdd = "PHSocket_APP_TCommodityOrderAdd";

    // 我的兑换列表
    public static final String PHSocket_APP_TCommodityOrderList = "PHSocket_APP_TCommodityOrderList";
    // 商品排行榜
    public static final String PHSocket_APP_CommodityPaiHangList = "PHSocket_APP_CommodityPaiHangList";
    // 消息主页接口
    public static final String PHSocket_GetMsg_HomeList = "PHSocket_GetMsg_HomeList";
    // 回复我接口
    public static final String PHSocket_GetUserReplyMsgDataList = "PHSocket_GetUserReplyMsgDataList";
    // 点赞我接口
    public static final String PHSocket_GetUserPriseMeMsgDataList = "PHSocket_GetUserPriseMeMsgDataList";
    // @我接口
    public static final String PHSocket_GetUserAtMeMsgDataList = "PHSocket_GetUserAtMeMsgDataList";

    // 消息列表删除接口
    public static final String PHSocket_ClearUserMsgInfo = "PHSocket_ClearUserMsgInfo";

    // 最近访客接口
    public static final String PHSocket_GetUserMsgVisitorList = "PHSocket_GetUserMsgVisitorList";

    // 最近访客接口
    public static final String PHSocket_GetFriendDynamicList = "PHSocket_GetFriendDynamicList";
    // 消息的粉丝列表
    public static final String PHSocket_GetFansList = "PHSocket_GetFansList";
    // 消息的关注列表
    public static final String PHSocket_GetBBSFriendList = "PHSocket_GetBBSFriendList";
    // 精彩推荐列表
    public static final String PHSocket_GetRecommandDataList = "PHSocket_GetRecommandDataList";
    // 系统通知列表
    public static final String PHSocket_GetUserSysMsgDataList = "PHSocket_GetUserSysMsgDataList";
    // 消息的好友聊天列表
    public static final String PHSocket_GetMsgUserInfoList = "PHSocket_GetMsgUserInfoList";
    // 商品评论列表点赞
    public static final String PHSocket_APP_ObservationDingAdd = "PHSocket_APP_ObservationDingAdd";

    // 聊天回复
    public static final String PHSocket_SetMsgUserInfo = "PHSocket_SetMsgUserInfo";

    // 我的礼包列表
    public static final String PHSocket_GetGiftInfo = "PHSocket_GetGiftInfo";
    // 领取礼包
    public static final String PHSocket_UpGiftUserInfo = "PHSocket_UpGiftUserInfo";
    // 新闻后方列表页
    public static final String PHSocket_GetCityNewsInfo = "PHSocket_GetCityNewsInfo";
    // 商城发表评论
    public static final String PHSocket_APP_ObservationAdd = "PHSocket_APP_ObservationAdd";
    // 城事排行榜
    public static final String PHSocket_GetCityNewsUserList = "PHSocket_GetCityNewsUserList";
    // 城事订阅号
    public static final String PHSocket_GetGovernmenAffairsList = "PHSocket_GetGovernmenAffairsList";
    // 所有举报
    public static final String PHSocket_SetTComplaintAdd = "PHSocket_SetTComplaintAdd";
    // 秀场上传
    public static final String PHSocket_SetCover_UserAndInfo = "PHSocket_SetCover_UserAndInfo";
    // 城事详情上方信息
    public static final String PHSocket_GetCityNewsBody = "PHSocket_GetCityNewsBody";
    // 城事详情下方评论列表
    public static final String PHSocket_GetCityReplyInfo = "PHSocket_GetCityReplyInfo";
    // 秀场最新
    public static final String PHSocket_GetNewTCoverInfo = "PHSocket_GetNewTCoverInfo";
    // 秀场后5标签
    public static final String PHSocket_GetImpressionTCoverInfo = "PHSocket_GetImpressionTCoverInfo";
    // 秀场主页
    public static final String PHSocket_GetTCoverInfo = "PHSocket_GetTCoverInfo";
    // 城事详情回复
    public static final String PHSocket_SetCityNewsReplyInfo = "PHSocket_SetCityNewsReplyInfo";
    // 微封面 -最新-点赞
    public static final String PHSocket_SetCoverInfoDingAdd = "PHSocket_SetCoverInfoDingAdd";
    // 微封面 -封面女郎
    public static final String PHSocket_GetFigureTCoverInfo = "PHSocket_GetFigureTCoverInfo";
    // 微封面 欣赏榜
    public static final String PHSocket_GetCoverXinShangInfo = "PHSocket_GetCoverXinShangInfo";
    // 微封面 土豪榜，魅力榜
    public static final String PHSocket_GetUserVirtualCommodityRank = "PHSocket_GetUserVirtualCommodityRank";
    // 城事详情点赞
    public static final String PHSocket_SetCityNewsReplyDing = "PHSocket_SetCityNewsReplyDing";
    // 秀场新人秀
    public static final String PHSocket_GetNewGirlTCoverInfo = "PHSocket_GetNewGirlTCoverInfo";
    // 微封面详情页上方信息
    public static final String PHSocket_GetCoverPhotoDetails = "PHSocket_GetCoverPhotoDetails";
    // 微封面详情页下方列表信息
    public static final String PHSocket_GetCoverReplyList = "PHSocket_GetCoverReplyList";
    // 微封面详情评论
    public static final String PHSocket_SetCoverReplyAdd = "PHSocket_SetCoverReplyAdd";
    // 首页访问
    public static final String PHSocket_GetHomeInfo = "PHSocket_GetHomeInfo";
    // 萌宝最新
    public static final String PHSocket_GetBaoBaoNewTCoverInfo = "PHSocket_GetBaoBaoNewTCoverInfo";
    // 萌宝照片删除
    public static final String PHSocket_SetBaoBaoCoverInfoDel = "PHSocket_SetBaoBaoCoverInfoDel";
    // 萌宝后5标签
    public static final String PHSocket_GetImpressionBaoBaoTCoverInfo = "PHSocket_GetImpressionBaoBaoTCoverInfo";
    // 萌宝排行榜
    public static final String PHSocket_GetBaoBaoTCoverLovePinkList = "PHSocket_GetBaoBaoTCoverLovePinkList";
    // 封面宝宝
    public static final String PHSocket_GetFigureBaoBaoTCoverInfo = "PHSocket_GetFigureBaoBaoTCoverInfo";
    // 封面上传照片第一次标签列表
    public static final String PHSocket_GetTCoverBaoBaoTagList = "PHSocket_GetTCoverBaoBaoTagList";
    // 萌宝详情
    public static final String PHSocket_GetBaoBaoCoverPhotoDetails = "PHSocket_GetBaoBaoCoverPhotoDetails";

    // 萌宝小屋上方详情
    public static final String PHSocket_GetBaoBaoDressUpList = "PHSocket_GetBaoBaoDressUpList";
    // 萌宝资料
    public static final String PHSocket_GetBaoBaoCoverDetails = "PHSocket_GetBaoBaoCoverDetails";

    // 萌宝小屋粉丝团
    public static final String PHSocket_GetBaoBaoCoverFansList = "PHSocket_GetBaoBaoCoverFansList";
    // 萌宝礼物

    public static final String PHSocket_GetBaoBaoCoverMyGiftList = "PHSocket_GetBaoBaoCoverMyGiftList";
    // 萌宝礼物装扮
    public static final String PHSocket_SetBaoBaoCoverMyGiftDressUp = "PHSocket_SetBaoBaoCoverMyGiftDressUp";
    // 萌宝亲友团
    public static final String PHSocket_GetBaoBaoCoverFriAndRelaList = "PHSocket_GetBaoBaoCoverFriAndRelaList";

    // 萌宝成长时刻
    public static final String PHSocket_GetBaoBaoGrowthTimeList = "PHSocket_GetBaoBaoGrowthTimeList";
    // 萌宝新人秀
    public static final String PHSocket_GetNewShowBaoBaoTCoverInfo = "PHSocket_GetNewShowBaoBaoTCoverInfo";
    // 萌宝资料修改
    public static final String PHSocket_SetChildCoverUpdate = "PHSocket_SetChildCoverUpdate";
    // 萌宝加入亲友团选择关系
    public static final String PHSocket_GetCoverBaoBaoRelativesTagList = "PHSocket_GetCoverBaoBaoRelativesTagList";
    // 萌宝邀请码
    public static final String PHSocket_GetInvitationCodeOne = "PHSocket_GetInvitationCodeOne";
    // 萌宝加入亲友团
    public static final String PHSocket_SetCoverBaoBaoRelativesAdd = "PHSocket_SetCoverBaoBaoRelativesAdd";
    // 判断是否是第一次上传照片
    public static final String PHSocket_GetIsFirstTCoverBaoBaoTagList = "PHSocket_GetIsFirstTCoverBaoBaoTagList";
    // 萌宝上传
    public static final String PHSocket_SetChildCoverAdd = "PHSocket_SetChildCoverAdd";
    // 萌宝详情页印象列表
    public static final String PHSocket_GetToTCoverUserTagList = "PHSocket_GetToTCoverUserTagList";
    // 萌宝详情页评印象
    public static final String PHSocket_SetBaoBaoCoverImpressionDing = "PHSocket_SetBaoBaoCoverImpressionDing";
    // 主页详情页评印象
    public static final String PHSocket_SetCoverPeopleImpressionDing = "PHSocket_SetCoverPeopleImpressionDing";
    // 美女评印象
    public static final String PHSocket_SetCoverImpressionDing = "PHSocket_SetCoverImpressionDing";
    // 萌宝点赞
    public static final String PHSocket_SetBaoBaoCoverInfoDing = "PHSocket_SetBaoBaoCoverInfoDing";
    // 上方导航
    public static final String PHSocket_GetUseNavigationInfo = "PHSocket_GetUseNavigationInfo";

    // 城事评论详情楼中楼列表
    public static final String PHSocket_GetChildrenReply = "PHSocket_GetChildrenReply";

    // 秀场评论详情楼中楼列表
    public static final String PHSocket_GetCoverReplyLZLList = "PHSocket_GetCoverReplyLZLList";

    //生活1.0列表请求接口
    public static final String PHSocket_GetNewPostLiveInfoOne = "PHSocket_GetNewPostLiveInfoOne";
    //生活1.1列表请求接口
    public static final String PHSocket_GetNewPostLiveInfoTwo = "PHSocket_GetNewPostLiveInfoTwo";
    // 接口名：广告信息接口
    public static final String PHSocket_GetDivInfo = "PHSocket_GetDivInfo";
    public static String[] images = {
            "http://img0.bdstatic.com/img/image/2043d07892fc42f2350bebb36c4b72ce1409212020.jpg",
            "http://img0.bdstatic.com/img/image/ef39fc9443de26c7f274351148866aae1409305185.jpg",
            "http://img0.bdstatic.com/img/image/e44f9614005081279a4d0c067868a4131409304989.jpg",
            "http://img0.bdstatic.com/img/image/b5acf1c074cc16bb6ae0d0b6c5b1bced1409305269.jpg",
            "http://f.hiphotos.baidu.com/image/w%3D230/sign=0e4381f5b54543a9f51bfdcf2e178a7b/f3d3572c11dfa9ec3749091660d0f703918fc148.jpg",
            "http://a.hiphotos.baidu.com/image/w%3D230/sign=6e2bbdfcff1f4134e037027d151f95c1/5366d0160924ab185f1ec12837fae6cd7b890ba0.jpg",
            "http://h.hiphotos.baidu.com/image/w%3D230/sign=c41a9ae060d9f2d3201123ec99ed8a53/d8f9d72a6059252d6bc4b805369b033b5ab5b9e4.jpg",
            "http://imgt6.bdstatic.com/it/u=2,771581492&fm=25&gp=0.jpg",
            "http://img0.bdstatic.com/img/image/dd586e72c14c569f7ac9cfdda7481ce91409110017.jpg",
            "http://e.hiphotos.baidu.com/image/w%3D230/sign=4b1f419c442309f7e76faa11420e0c39/b3119313b07eca80616d6def932397dda1448349.jpg",
            "http://img0.bdstatic.com/img/image/86bc543bda8b2a87d10d4d172ea689cc1409211431.jpg",
            "http://f.hiphotos.baidu.com/image/w%3D230/sign=5202966a272dd42a5f0906a8333b5b2f/8c1001e93901213f3d268ddf56e736d12f2e9598.jpg",
            "http://g.hiphotos.baidu.com/image/w%3D230/sign=49f454c29c2f07085f052d03d925b865/0df3d7ca7bcb0a463c3dcdb46963f6246b60af24.jpg",
            "http://imgt9.bdstatic.com/it/u=2,830684732&fm=25&gp=0.jpg",
            "http://img0.bdstatic.com/img/image/4bb637ba34497164a61ee1eccd8a10781409117497.jpg",
            "http://b.hiphotos.baidu.com/image/w%3D230/sign=3dbeecfd79cb0a4685228c3a5b63f63e/96dda144ad34598283faeb950ef431adcbef8440.jpg",
            "http://d.hiphotos.baidu.com/image/w%3D230/sign=fde906dd3887e9504217f46f2039531b/d4628535e5dde711db064ae8a5efce1b9c1661d3.jpg"};

    /**
     * 弹幕
     */
    public static final String PHSocket_GetTanMuList = "PHSocket_GetTanMuList";
    //动态表情列表
    public static final String PHSocket_GetExpressionList = "PHSocket_GetExpressionList";
    //动态表情详细信息
    public static final String PHSocket_GetExpressionInfoByID = "PHSocket_GetExpressionInfoByID";
    //动态表情下载地址
    public static final String PHSocket_GetExpressionDowanLoad = "PHSocket_GetExpressionDowanLoad";
    //判断是否有新表情
    public static final String PHSocket_NewExpressionList = "PHSocket_NewExpressionList";

    /**
     * 帖子删除
     */
    public static final String PHSocket_DeleteTopic = "PHSocket_DeleteTopic";
    /**
     * 闹闹、社区评论删除
     */
    public static final String PHSocket_DeleteTopicReply = "PHSocket_DeleteTopicReply";
    /**
     * 支付宝支付请求订单
     */
    public static final String PHSocket_CreatePayOrderZFB = "PHSocket_CreatePayOrderZFB";
    /**
     * 微信支付请求订单
     */
    public static final String PHSocket_CreatePayOrderWX = "PHSocket_CreatePayOrderWX";
    /**
     * 删帖原因
     */
    public static final String PHSocket_GetTopicDelReason = "PHSocket_GetTopicDelReason";

    /**
     * 闹闹--增加视频点击次数
     */
    public static final String PHSocket_EditTieBaVideoHit = "PHSocket_EditTieBaVideoHit";

    /**
     * 新头条版其它四种维度列表
     */
    public static final String PHSocket_GetHeadJBInfoList = "PHSocket_GetHeadJBInfoList";
    /**
     * 新头条版今日列表
     */
    public static final String PHSocket_GetHeadJBInfoT = "PHSocket_GetHeadJBInfoT";
    /**
     * 除了今日其他时间的列表
     */
    public static final String PHSocket_GetHeadJBInfoO = "PHSocket_GetHeadJBInfoO";

    /**
     * 秀场完善资料
     */
    public static final String PHSocket_SetCoverUserData = "PHSocket_SetCoverUserData";

    /**
     * 秀场打招呼列表
     */
    public static final String PHSocket_GetSayHelloInfo = "PHSocket_GetSayHelloInfo";


    /**
     * 手机验证码验证
     */
    public static final String PHSocket_EditPhoneBindCheck = "PHSocket_EditPhoneBindCheck";

    /**
     * 账号绑定
     */
    public static final String PHSocket_SetThreeUserBind = "PHSocket_SetThreeUserBind";


    /**
     * 账号解除绑定
     */
    public static final String PHSocket_SetThreeUserBindDel = "PHSocket_SetThreeUserBindDel";



}
