package com.example.citygeneral.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.citygeneral.R;
import com.example.citygeneral.activity.LoginActivity;
import com.example.citygeneral.activity.RegisterActivity;
import com.example.citygeneral.model.entity.UserInfo;
import com.example.citygeneral.model.http.Parameter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"deprecation"})
public class PublicUtils {
    // 网络超时时间
    public static final int CONNECT_TIMEOUT = 25 * 1000;

    private Context context;
    private SharedPreferences pref;
    private SharedPreferences presett;
    private SharedPreferences preint;
    private SharedPreferences classcaini;
    private Editor editorint;
    private SharedPreferences userInfoSpf;
    private SharedPreferences gifspf;

    public String[] smileyTexts;
    public static final String CCOOCITYID = "CCOOCITYID";
    public static boolean flag = false;
    public static boolean flag2 = false;


    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     */
    public boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param drawable
     * @param w
     * @param h
     * @return
     */
    public Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);// drawable转换成bitmap
        Matrix matrix = new Matrix(); // 创建操作图片用的Matrix对象
        float scaleWidth = ((float) w / width); // 计算缩放比例
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight); // 设置缩放比例
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true); // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        newbmp.getWidth();
        newbmp.getHeight();
        return new BitmapDrawable(newbmp); // 把bitmap转换成drawable并返回
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     */
    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        if (TextUtils.isEmpty(path))
            return 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap toturn(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree); /*翻转90度*/
        int width = img.getWidth();
        int height = img.getHeight();
        img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        return img;
    }

    /**
     * 公用调取整个页面是webview中图片地址函数跳转到预览的activity
     *
     * @param img   点击当前图片的url地址
     * @param imags 页面中所有可查看的图片地址串儿
     * @author 王伟
     */
    @JavascriptInterface
    public void openImage(String img, String imags) {
        Log.i("tupiandizhi", img + "\n" + imags);

        int num = 0;
        String[] arrImgs = imags.split(",");
        List<String> imgList = new ArrayList<String>();
        for (String str : arrImgs) {
            if (!TextUtils.isEmpty(str)) imgList.add(str);
        }

        num = imgList.indexOf(img);
        Intent intent = new Intent();
        intent.putExtra("num", num < 0 ? 0 : num);
        intent.putExtra("list", (Serializable) imgList);
        intent.putExtra("title", "图片详情");
    //    intent.setClass(context, BbsPhotoShowActivity.class);
        context.startActivity(intent);
    }

    public static void addImageClickListner(WebView contentWebView) {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接口并传递url过去
        contentWebView.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName(\"img\"); "
                + "var osrc='';" + "for(var i=0;i<objs.length;i++)" + "{"
                + "     osrc+=objs[i].src+',';   " + "}"
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "    objs[i].onclick=function()  " + "    {  "
                + "        window.app.openImage(this.src,osrc);  " + "    }  "
                + "}" + "})()");
    }

    /**
     * 帖子id
     */
    private String tId;

    public void settId(String tId) {
        this.tId = tId;
    }

    @JavascriptInterface
    public void videoPlay() {
        if (TextUtils.isEmpty(tId)) {
            return;
        }
       /* HttpParamsTool.Post(createParams(tId),
                new HttpParamsTool.PostHandler(),
                context);*/
    }

    private String createParams(String tId) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("siteID", PublicUtils.getCityId());
            jo.put("tId", Integer.parseInt(tId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Parameter.createnewsParam(
                NetUrl.PHSocket_EditTieBaVideoHit, jo);
    }

    /**
     * 把drawable对象转化成bitmap对象
     *
     * @param drawable 传入drawable参数
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 获取年和月
     *
     * @param str 格式 y年y月y日
     * @return
     */
    public static int[] getYearAndMonth(String str) {
        int[] ss = null;
        ss = new int[3];
        String r = "[\\d]+";
        Pattern p = Pattern.compile(r);
        Matcher m = p.matcher(str);
        int num = 0;
        while (m.find()) {
            System.out.println("---" + m.group());
            ss[num] = Integer.parseInt(m.group());
            num++;
            if (num > 2) {
                break;
            }
        }

        return ss;
    }

    /**
     * 网络判断
     */
    ConnectivityManager con;

    public PublicUtils(Context context) {
        super();
        this.context = context;
        pref = context.getSharedPreferences("loginuser", Context.MODE_PRIVATE);
        presett = context.getSharedPreferences("settinguser",
                Context.MODE_PRIVATE);
        preint = context.getSharedPreferences("preint", Context.MODE_PRIVATE);
        classcaini = context.getSharedPreferences("classcaini",
                Context.MODE_PRIVATE);
        userInfoSpf = context.getSharedPreferences("userinfo",
                Context.MODE_PRIVATE);
        gifspf = context.getSharedPreferences("gifflag", Context.MODE_PRIVATE);
        // 网络
        con = (ConnectivityManager) context
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
    }

    /**
     * 做分类猜你喜欢的记录
     */
    public void setclasscaini(String cani) {
        String cai = classcaini.getString("setclasscaini", "12345");
        Editor editorcai = classcaini.edit();
        editorcai.putString("setclasscaini", cani + cai);
        editorcai.commit();
    }

    public String getclasscaini() {
        return classcaini.getString("setclasscaini", "12345");
    }

    // 保存用户名
    public void saveUserName(String userName, String uSiteID, String UserID,
                             String RoleName, String RoleImg, int ouSiteID) {
        Editor editor = pref.edit();
        editor.clear();
        editor.putString("UserName", userName);
        editor.putString("uSiteID", uSiteID);
        editor.putInt("ouSiteID", ouSiteID);
        editor.putString("UserID", UserID);
        editor.putString("RoleName", RoleName);
        editor.putString("RoleImg", RoleImg);

        editor.commit();
    }

    // 保存城市ID
    public void saveCityId(int cityid, String cityName, String name,
                           String cityUrl, int PhoneReg, String namePinyin, String isAuthe) {
        SharedPreferences pref = context.getSharedPreferences(CCOOCITYID,
                Context.MODE_PRIVATE);
        Editor editor = pref.edit();
        editor.clear();
        editor.putInt("cityid", cityid);
        editor.putString("cityurl", cityUrl);
        editor.putString("cityname", cityName);
        editor.putString("name", name);
        editor.putInt("PhoneReg", PhoneReg);
        editor.putString("namePinyin", namePinyin);
        editor.putString("isAuthe", isAuthe);

        editor.commit();
    }

    /**
     * 保存用户基本信息
     */
    public void saveUserInfo(UserInfo info) {
        Editor editor = userInfoSpf.edit();
        editor.clear();
        editor.putString("Id", info.getId());
        editor.putString("SiteID", info.getSiteID());
        editor.putString("SiteSqURL", info.getSiteSqURL());
        editor.putString("UserName", info.getUserName());
        editor.putString("Nick", info.getNick());
        editor.putString("UserFace", info.getUserFace());
        editor.putString("Sex", info.getSex());
        editor.putString("Name", info.getName());
        editor.putString("Birthday", info.getBirthday());
        editor.putString("XingZuo", info.getXingZuo());
        editor.putString("Info", info.getInfo());
        editor.putString("Mobile", info.getMobile());
        editor.putString("Tel1", info.getTel1());
        editor.putString("Tel", info.getTel());
        editor.putString("Email", info.getEmail());
        editor.putString("TrueEmail", info.getTrueEmail());
        editor.putString("Position", info.getPosition());
        editor.putString("PositionName", info.getPositionName());
        editor.putString("FrendNum", info.getFrendNum());
        editor.putString("FansNum", info.getFansNum());
        editor.putString("Coin", info.getCoin());
        editor.putString("Level", info.getLevel());
        editor.putString("Integral", info.getIntegral());
        editor.putString("Image", info.getImage());
        editor.putString("HonorName", info.getHonorName());
        editor.putString("TaskCount", info.getTaskCount());
        editor.putString("IntegralRank", info.getIntegralRank());
        editor.putString("MedalCount", info.getMedalCount());
        editor.putString("MedalImags", info.getMedalImags());
        editor.putString("IsQian", info.getIsQian());
        editor.putString("BBID", info.getBBID());
        editor.putString("IsFirstPub", info.getIsFirstPub());
        editor.putString("QQ", info.getQQ());
        editor.putString("WeiXin", info.getWeiXin());
        editor.putString("LifeAddr", info.getLifeAddr());
        editor.putString("Marry", info.getMarry());
        editor.putString("Job", info.getJob());
        editor.putString("MsgCount", info.getMsgCount());
        editor.putString("title", info.getTitle());
        editor.putString("headimg", info.getHeadimg());
        editor.putString("firstid", info.getFirstid());
        editor.putString("secondid", info.getSecondid());
        editor.putString("IsQQBind", info.getIsQQBind());
        editor.putString("IsWXBind", info.getIsWXBind());
        editor.putString("IsWBBind", info.getIsWBBind());

        editor.commit();
    }

    /**
     * 清除用户基本信息
     */
    public void clearUserinfo() {
        Editor editor = userInfoSpf.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 读取用户基本信息
     */
    public static UserInfo getUserInfo() {
        UserInfo info;
        SharedPreferences usp = CityApp.getInstance().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        if (isLogin()) {
            info = new UserInfo(usp.getString("Id", ""),
                    usp.getString("SiteID", ""), usp.getString(
                    "SiteSqURL", ""), usp.getString("UserName",
                    ""), usp.getString("Nick", ""),
                    usp.getString("UserFace", ""),
                    usp.getString("Sex", ""), usp.getString(
                    "Name", ""), usp.getString("Birthday", ""),
                    usp.getString("XingZuo", ""),
                    usp.getString("Info", ""), usp.getString(
                    "Mobile", ""), usp.getString("Tel1", ""),
                    usp.getString("Tel", ""), usp.getString(
                    "Email", ""),
                    usp.getString("TrueEmail", ""),
                    usp.getString("Position", ""),
                    usp.getString("PositionName", ""),
                    usp.getString("FrendNum", ""),
                    usp.getString("FansNum", ""),
                    usp.getString("Coin", ""), usp.getString(
                    "Level", ""),
                    usp.getString("Integral", ""),
                    usp.getString("Image", ""), usp.getString(
                    "HonorName", ""), usp.getString(
                    "TaskCount", ""), usp.getString(
                    "IntegralRank", ""), usp.getString(
                    "MedalCount", ""), usp.getString(
                    "MedalImags", ""), usp.getString("IsQian",
                    ""), usp.getString("BBID", ""), usp.getString("IsFirstPub", ""),
                    usp.getString("QQ", ""), usp.getString(
                    "WeiXin", ""),
                    usp.getString("LifeAddr", ""),
                    usp.getString("Marry", ""), usp.getString(
                    "Job", ""), usp.getString("MsgCount", ""),
                    usp.getString("title", ""), usp.getString(
                    "headimg", ""),
                    usp.getString("firstid", ""),
                    usp.getString("secondid", ""), usp.getString("IsQQBind", ""), usp.getString("IsWXBind", ""), usp.getString("IsWBBind", ""));
        } else {
            info = new UserInfo("", "", usp.getString("SiteSqURL", ""),
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "");
        }
        return info;
    }

    // 读取城市是否开通手机服务

    /**
     * @return 返回值为1表示开通手机服务，否则没有
     */
    public int getPhoneReg() {
        SharedPreferences pref = context.getSharedPreferences(CCOOCITYID,
                Context.MODE_PRIVATE);
        return pref.getInt("PhoneReg", 0);
    }

    public void setapkver(String apkver) {
        Editor editor = presett.edit();
        editor.putString("setapkver", apkver);
        editor.commit();
    }

    // 提示软件升级
    public String getUpApk() {
        return presett.getString("setUpApk1", "");
    }

    public void setUpApk(String apkver) {
        Editor editor = presett.edit();
        editor.putString("setUpApk1", apkver);
        editor.commit();
    }

    // 读取软件版本
    public String getapkver() {
        PackageInfo info = null;
        String versionName = "";
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return presett.getString("setapkver", versionName);
    }

    // 读取程序的名字
    public String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext()
                    .getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(
                    context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = (String) packageManager
                .getApplicationLabel(applicationInfo);
        return applicationName;
    }

    public static boolean isLogin() {
        return !getUserName().equals("");
    }

    // 读取城市ID
    public static int getCityId() {
        return PreferencesManager.getInt(PreferencesManager.CCOOCITYID, "cityid", -1);
    }

    // 设置城市ID
    public void setCityId(int cityId) {
        PreferencesManager.putInt(PreferencesManager.CCOOCITYID, "cityid", cityId);
    }

    // 读取城市的全名name
    public String getCityName() {
        return PreferencesManager.getString(PreferencesManager.CCOOCITYID, "cityname", "");
    }


    // 读取城市的name
    public String getName() {
        SharedPreferences pref = context.getSharedPreferences(CCOOCITYID,
                Context.MODE_PRIVATE);
        return pref.getString("name", "");
    }

    // 读取城市url
    public static String getCityUrl() {
        return PreferencesManager.
                getString(PreferencesManager.CCOOCITYID, "cityurl", "");
    }

    // 读取城市拼音
    public String getNamePinyin() {
        SharedPreferences pref = context.getSharedPreferences(CCOOCITYID,
                Context.MODE_PRIVATE);
        return pref.getString("namePinyin", "");
    }

    // 读取城市认证权限
    public String getIsAuthe() {
        SharedPreferences pref = context.getSharedPreferences(CCOOCITYID,
                Context.MODE_PRIVATE);
        return pref.getString("isAuthe", "");
    }


    // 读取城市域名
    public String getWapSqUrl() {
        SharedPreferences pref = context.getSharedPreferences(CCOOCITYID,
                Context.MODE_PRIVATE);
        return pref.getString("WapSqUrl", "");
    }


    // 获取用户名
    public static String getUserName() {
        return PreferencesManager.getString(PreferencesManager.LOGIN_USER, "UserName", "");
    }

    // 获取用户所以城市ID
    public static String getuSiteID() {
        return PreferencesManager.getString(PreferencesManager.LOGIN_USER, "uSiteID", "");
        //return pref.getString("uSiteID", "");
    }

    // 获取用户所以城市ID
    public static int getouSiteID() {
        return PreferencesManager.getInt(PreferencesManager.LOGIN_USER, "ouSiteID", 1);
        //return pref.getInt("ouSiteID", 1);
    }

    // 获取用户所以城市ID
    public String getouSiteID1() {
        return pref.getString("ouSiteID", "");
    }

    // 获取用户ID
    public static String getUserID() {
        return PreferencesManager.getString(PreferencesManager.LOGIN_USER, "UserID", "-1");
    }

    // 获取用户ID
    public static int getUserIDInt() {
        int i = 0;
        try {
            i = Integer.parseInt(PreferencesManager.getString(PreferencesManager.LOGIN_USER, "UserID", "0"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    // 获取用户昵称
    public String getRoleName() {
        return pref.getString("RoleName", "");
    }

    // 获取用户照片
    public String getRoleImg() {
        return pref.getString("RoleImg", "");
    }

    // 删除用户名
    public void clearUserName() {
        pref.edit().clear().commit();
    }

    // 设置图片显示
    public void setpicReply(String texsize) {
        Editor editor = presett.edit();
        editor.putString("userpicReply", texsize);
        editor.commit();
    }

    // 获取图片显示
    public String getpicReply() {
        return presett.getString("userpicReply", "高清");
    }

    // 设置论坛字体
    public void setluntanTex(int texsize) {
        Editor editor = presett.edit();
        editor.putInt("userluntanTex", texsize);
        editor.commit();
    }

    // 获取论坛字体
    public int getluntanTex() {
        return presett.getInt("userluntanTex", 16);
    }

    // 设置资讯字体
    public void setnewsTex(int texsize) {
        Editor editor = presett.edit();
        editor.putInt("usernewsTex", texsize);
        editor.commit();
    }

    // 获取资讯字体
    public int getnewsTex() {
        return presett.getInt("usernewsTex", 18);
    }

    // 信息推送
    public void setUserSetting5(boolean falg) {
        Editor editor = presett.edit();
        editor.putBoolean("setUserSetting5", falg);
        editor.commit();
    }

    public boolean getUserSetting5() {
        return presett.getBoolean("setUserSetting5", true);
    }

    // 声音提示
    public void setUserSetting6(boolean falg) {
        Editor editor = presett.edit();
        editor.putBoolean("setUserSetting6", falg);
        editor.commit();
    }

    public boolean getUserSetting6() {
        return presett.getBoolean("setUserSetting6", true);
    }

    // 震动提示
    public void setUserSetting7(boolean falg) {
        Editor editor = presett.edit();
        editor.putBoolean("setUserSetting7", falg);
        editor.commit();
    }

    public boolean getUserSetting7() {
        return presett.getBoolean("setUserSetting7", false);
    }

    // 每日焦点
    public void setUserSetting8(boolean falg) {
        Editor editor = presett.edit();
        editor.putBoolean("setUserSetting8", falg);
        editor.commit();
    }

    public boolean getUserSetting8() {
        return presett.getBoolean("setUserSetting8", true);
    }

    // 帖子回复
    public void setUserSetting9(boolean falg) {
        Editor editor = presett.edit();
        editor.putBoolean("setUserSetting9", falg);
        editor.commit();
    }

    public boolean getUserSetting9() {
        return presett.getBoolean("setUserSetting9", true);
    }

    // @我
    public void setUserSetting10(boolean falg) {
        Editor editor = presett.edit();
        editor.putBoolean("setUserSetting10", falg);
        editor.commit();
    }

    public boolean getUserSetting10() {
        return presett.getBoolean("setUserSetting10", true);
    }

    // 团购新单
    public void setUserSetting11(boolean falg) {
        Editor editor = presett.edit();
        editor.putBoolean("setUserSetting11", falg);
        editor.apply();
    }

    /**
     * 获得状态栏高度
     *
     * @param activity
     * @return
     */
    public int getStateBarHeight(Activity activity) {
        View v = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);

        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return v.getTop();
    }

    // 获取状态栏高度
    public int getStatusBarHeight() {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public boolean getUserSetting11() {
        return pref.getBoolean("setUserSetting11", false);
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^1[34578]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isPost(String post) {
        String str = "^[1-9]\\d{5}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(post);
        return m.matches();
    }

    /**
     * 判断网络连接是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            // 如果仅仅是用来判断网络连接　　　　　　
            // 则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // 计算找房子，需要请求哪一个链接
    public void setGetintfce(String strint) {
        editorint = preint.edit();
        String strport = preint.getString("setGetintfce", "");
        if (strport.length() >= 20) {
            strport = strport.substring(0, 19);
        }
        strport = strport + strint;
        editorint.putString("setGetintfce", strport);
        editorint.commit();
    }

    public String getGetintfce() {
        String strport = preint.getString("setGetintfce", "");
        strport = "1234" + strport;
        int intport = (int) (Math.random() * strport.length());
        return strport.substring(intport, intport + 1);
    }

    public String getGetintfcestr() {
        String strport = preint.getString("setGetintfce", "");
        strport = "1234" + strport;
        return strport;
    }
/*

    // 图片下载类1
    public ImageLoader getLoader() {
        loader = ImageLoader.getInstance();
        if (!loader.isInited()) {
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                    context).threadPoolSize(5)
                    .threadPriority(Thread.NORM_PRIORITY - 2)
                    .denyCacheImageMultipleSizesInMemory()
                    .memoryCacheExtraOptions(480, 480)
                    .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                    .memoryCacheSize(2 * 1024 * 1024)
                    .discCacheFileNameGenerator(new Md5FileNameGenerator())
                    .tasksProcessingOrder(QueueProcessingType.LIFO).build();

            loader.init(config);
        }

        return loader;
    }

    public ImageLoader getLoader(int width, int height) {
        ImageLoader loader = ImageLoader.getInstance();
        if (!loader.isInited()) {
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                    context).threadPoolSize(3)
                    .threadPriority(Thread.NORM_PRIORITY - 2)
                    .denyCacheImageMultipleSizesInMemory()
                    .memoryCacheExtraOptions(480, 480)
                    .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                    .memoryCacheSize(2 * 1024 * 1024)
                    .discCacheFileNameGenerator(new Md5FileNameGenerator())
                    .tasksProcessingOrder(QueueProcessingType.LIFO).build();

            loader.init(config);
        }

        return loader;
    }

    public static ImageCacheParams cacheParams;

    // 图片下载类2
    public ImageFetcher getFetcher() {
        if (imageFetcher == null) {
            imageFetcher = new ImageFetcher(context, 100);
            if (cacheParams == null) {
                cacheParams = new ImageCacheParams(context,
                        Constants.IMAGE_CACHE_DIR);
                cacheParams.setMemCacheSizePercent(0.5f);
            }
            imageFetcher.addImageCache(cacheParams);
        }
        return imageFetcher;
    }

    */
/**
     * 瀑布流图片下载
     *//*

    public ImageFetcher getFetcherShow() {
        if (imageFetcherShow == null) {
            imageFetcherShow = new ImageFetcher(context, 200);
        }
        if (cacheParams == null) {
            cacheParams = new ImageCacheParams(context,
                    Constants.IMAGE_CACHE_DIR);
            cacheParams.setMemCacheSizePercent(0.1f);
        }
        imageFetcherShow.addImageCache(cacheParams);
        return imageFetcherShow;
    }

    */
/**
     * 资料上方模糊专用
     *//*

    public ImageFetcher getFetcherTop() {
        ImageFetcher imageFetcher = new ImageFetcher(context, 100);
        if (cacheParams == null) {
            cacheParams = new ImageCacheParams(context,
                    Constants.IMAGE_CACHE_DIR);
            cacheParams.setMemCacheSizePercent(0.3f);
        }
        imageFetcher.addImageCache(cacheParams);
        return imageFetcher;
    }

    public static DisplayImageOptions getOptions() {
        if (options == null) {
            options = ImageLoaderTools.getCommonOptions();
        }
        return options;
    }

    public DisplayImageOptions getOptions2() {
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.bg_loading)
                .showImageForEmptyUri(R.drawable.bbs_moren)
                .showImageOnFail(R.drawable.bg_loading).cacheInMemory()
                .cacheOnDisc().bitmapConfig(Bitmap.Config.RGB_565).build();
        return options;
    }

    public void loadImage(String url, ImageView image) {
        getLoader();
        getOptions();
        loader.displayImage(url, image, options);
    }

    */
/**
     * 头像图片下载
     *//*

    public void loadImage2(String url, ImageView image) {
        getLoader();
        getOptions2();
        loader.displayImage(url, image, options);
    }
*/

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = CityApp.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);

    }

    public static int px2dip(Context c, float pxValue) {
        final float scale = c.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);

    }

    /**
     * textview关键字上色
     */
    public void setColer(String content, String[] key, int color, TextView text) {
        if (key == null || key[0].equals("")) {
            text.setText(content);
            return;
        }
        SpannableString spanTitleString = new SpannableString(content);
        for (int i = 0; i < key.length; i++) {
            int titleNum = -1;
            while (true) {
                titleNum = content.indexOf(key[i], titleNum + 1);
                if (titleNum == -1) {
                    break;
                } else {
                    spanTitleString.setSpan(new ForegroundColorSpan(context
                                    .getResources().getColor(color)), titleNum,
                            titleNum + key[i].length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        text.setText("");
        text.append(spanTitleString);
    }

    /**
     * 读取返回结果message
     */
    public String getResult(String result) {
        String str = "";
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject info = jsonObject.getJSONObject("MessageList");
            str = info.getString("message");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * 读取返回参数code
     */
    public int getResult2(String result) {
        // 友盟Attempt to invoke virtual method
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject info = jsonObject.getJSONObject("MessageList");
            return info.getInt("code");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 等级判定背景显示函数
     * <p/>
     */
    public static int getLevelBg(int level) {
        int bg = 0;
        if (level <= 10) {
            bg = R.drawable.ccoo_drawable_dengji_bg_green;
        } else if (level > 10 && level <= 20) {
            bg = R.drawable.ccoo_drawable_dengji_bg_blue;
        } else {
            bg = R.drawable.ccoo_drawable_dengji_bg_yellow;
        }
        return bg;
    }

    public static boolean filter(String s) {// 判断是不是字母或者数字
        String content = s;
        String regex = "[a-zA-Z0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(content);
        return match.matches();
    }

    /**
     * 适配分辨率设置文字显示数目
     */
    public String getTextSplit(String content, int m, int l, int h, String more) {
        if (null == content) {
            content = "";
        }
        String result = "";
        int length = 0;// 截取字符经过正则的长度
        int length2 = 0;// 截取字符计划的长度
        int length3 = content.length();// 字符串经过正则的总长度

        if (px2dip(CityApp.mScreenWidth) < 360) {
            length = m;
            length2 = m;
        } else if (px2dip(CityApp.mScreenWidth) < 400) {
            length = l;
            length2 = l;
        } else {
            length = h;
            length2 = h;
        }

        for (int i = 0; i < content.length(); i++) {
            if (filter(content.charAt(i) + "")) {
                length3 = length3 + 1;
                if (i < length2) {
                    length = length + 1;
                }
            }
        }

        if (length3 > length && length < content.length()) {
            result = content.substring(0, length) + more;
        } else {
            result = content;
        }
        return result;
    }

    public static String getTextSplit(Context ctx, String content, int m, int l, int h, String more) {
        if (null == content) {
            content = "";
        }
        String result = "";
        int length = 0;// 截取字符经过正则的长度
        int length2 = 0;// 截取字符计划的长度
        int length3 = content.length();// 字符串经过正则的总长度

        if (px2dip(ctx, CityApp.mScreenWidth) < 360) {
            length = m;
            length2 = m;
        } else if (px2dip(ctx, CityApp.mScreenWidth) < 400) {
            length = l;
            length2 = l;
        } else {
            length = h;
            length2 = h;
        }

        for (int i = 0; i < content.length(); i++) {
            if (filter(content.charAt(i) + "")) {
                length3 = length3 + 1;
                if (i < length2) {
                    length = length + 1;
                }
            }
        }

        if (length3 > length && length < content.length()) {
            result = content.substring(0, length) + more;
        } else {
            result = content;
        }
        return result;
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param file
     * @param file
     * @return
     */
    public Bitmap readBitMap(String file) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param resId
     * @return
     */
    public Bitmap readBitMap(int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 实现文本复制功能 add by
     *
     * @param content
     */
    public void copy(String content) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能 add by wangqianzhou
     *
     * @param context
     * @return
     */
    public String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    /**
     * @param text   目标字符串
     * @param length 截取长度
     * @return
     * @throws UnsupportedEncodingException
     */
    public String substring(String text, int length) {
        if (text == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int currentLength = 0;
        for (char c : text.toCharArray()) {
            try {
                currentLength += String.valueOf(c).getBytes("GBK").length;
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "";
            }
            if (currentLength <= length) {
                sb.append(c);
            } else {
                break;
            }
        }
        return sb.toString();

    }

    /**
     * 根据宽度从本地图片路径获取该图片的缩略图
     *
     * @param localImagePath 本地图片的路径
     * @param width          缩略图的宽
     * @param addedScaling   额外可以加的缩放比例
     * @return bitmap 指定宽高的缩略图
     */
    public static Bitmap getBitmapByWidth(String localImagePath, int width,
                                          int addedScaling) {
        Bitmap temBitmap = null;
        try {
            BitmapFactory.Options outOptions = new BitmapFactory.Options();

            // 设置该属性为true，不加载图片到内存，只返回图片的宽高和类型到options中。
            outOptions.inJustDecodeBounds = true;

            // 加载获取图片的宽高
            BitmapFactory.decodeFile(localImagePath, outOptions);

            int height = outOptions.outHeight;

            if (outOptions.outWidth > width) {
                // 根据宽设置缩放比例
                outOptions.inSampleSize = outOptions.outWidth / width + 1
                        + addedScaling;
                outOptions.outWidth = width;

                // 计算缩放后的高度
                height = outOptions.outHeight / outOptions.inSampleSize;
                outOptions.outHeight = height;
            }

            // 重新设置该属性为false，加载图片返回
            outOptions.inJustDecodeBounds = false;
            outOptions.inPurgeable = true;
            outOptions.inInputShareable = true;
            temBitmap = BitmapFactory.decodeFile(localImagePath, outOptions);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return temBitmap;
    }

    /**
     * 交互放大动画效果
     */
    @SuppressLint("HandlerLeak")
    public void startAnim(final View view) {

        view.startAnimation(AnimationUtils.loadAnimation(context,
                R.anim.ccoo_jiaohu_in));
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                view.startAnimation(AnimationUtils.loadAnimation(context,
                        R.anim.ccoo_jiaohu_out));
            }
        };
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 交互信息显示
     */
    public void setMessageShow(String result, String successMessage) {
        String toastMessage = successMessage;
        String message = "";
        try {
            JSONObject json = new JSONObject(result);
            if (json.getString("Extend") == null || json.getString("Extend").equals("null") || !json.getString("Extend").contains("reTask") || !json.getString("Extend").contains("reMsg")) {
                if (!successMessage.equals("")) {
                    System.out.println("~~~~==null");
                    Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show();
                }
                return;
            }
            JSONArray asyList = json.getJSONObject("Extend").getJSONArray(
                    "reTask");
            message = json.getJSONObject("Extend").getString("reMsg");
            for (int i = 0; i < asyList.length(); i++) {
                JSONObject info = asyList.getJSONObject(i);
                if (info.getInt("code") == 1000) {
                    if (info.getInt("coin") == 0) {
                        System.out.println("~~~~成长值");
                        toastMessage = toastMessage + "(+"
                                + info.getString("integral") + "成长值)";
                    } else {
                        System.out.println("~~~~城市币成长值");
                        toastMessage = toastMessage + "(+"
                                + info.getString("coin") + "城市币,+"
                                + info.getString("integral") + "成长值)";
                    }
                    break;
                }
            }
            if (!toastMessage.equals("")) {
                System.out.println("~~~~打印激励" + toastMessage);
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
            }
            if (!message.equals("")) {
                try {
               //     showJiaohuPop(message);
                } catch (Exception e) {

                    Toast.makeText(context, "已完成任务~", Toast.LENGTH_SHORT).show();

                }
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(context, "网络连接异常，请稍后再试~", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 交互pop组件
     */
    /*private void showJiaohuPop(String message) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.jiahu_message_layout, null);
        final PopupWindow pop = new PopupWindow(view,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        final TextView text = (TextView) view.findViewById(R.id.textview);
        ImageView delete = (ImageView) view.findViewById(R.id.delete);
        text.setText(message);
        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
            }
        });
        text.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,
                        SystemNotificationActivity.class);
                intent.putExtra("flag", "replyme");
                context.startActivity(intent);
            }
        });
        pop.setOutsideTouchable(true);
        // 添加背景
        pop.setBackgroundDrawable(context.getResources().getDrawable(
                R.color.transparent));
        pop.setFocusable(true);
        pop.setAnimationStyle(R.style.pop_animstyle);

        if (!pop.isShowing()) {
            pop.showAtLocation(view, 0, 0, getStatusBarHeight() + dip2px(50));
        } else {
            pop.dismiss();
        }
    }*/

    /**
     * 是否可以交互连接
     */
    public boolean isCanConnect() {
        try {
            if (Tool.isNetworkConnected(context)) {
                if (isLogin()) {
                    if (getouSiteID() == getCityId()) {
                        return true;
                    } else {
                        Toast.makeText(context, "非本站点用户，禁止操作", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    return false;
                }
            } else {
                Toast.makeText(context, "网络无法连接，请检查网络~", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCanConnect(Context ctx) {
        try {
            if (Tool.isNetworkConnected(ctx)) {
                if (isLogin()) {
                    if (getouSiteID() == getCityId()) {
                        return true;
                    } else {
                        Toast.makeText(ctx, "非本站点用户，禁止操作", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Intent intent = new Intent(ctx, RegisterActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(intent);
                    return false;
                }
            } else {
                Toast.makeText(ctx, "网络无法连接，请检查网络~", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 交互结果状态处理
     */
    public boolean getConnectState(String result, String successMessage) {
        try {
            JSONObject jsonMain = new JSONObject(result);
            JSONObject serverJson = new JSONObject(
                    jsonMain.getString("MessageList"));
            if (serverJson.getString("code").equals("1000")) {
                System.out.println("~~~~进入setMessageShow");
                setMessageShow(result, successMessage);
                return true;
            } else {// 服务器返回警告性失败
                System.out.println("~~~~没进入");
                Toast.makeText(context, serverJson.getString("message"), Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {// MessageList串缺失
            e.printStackTrace();

            return false;
        }
    }

    /**
     * 失败调用
     */
    public void showConnectFail(Throwable arg0) {
        if (arg0.getMessage() != null && arg0.getMessage().equals("1001")) {
                //CustomToast.showToastError1(context);
        } else {
          //  CustomToast.showToastError1(context);
        }
    }

    /**
     * EDITTEXT管理器
     */
    public InputMethodManager getInputManager() {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE); // 输入框管理器
        return imm;
    }

    /**
     * 获取手机识别码IMSI
     *
     * @param context
     * @return
     */
    public static String getImsi(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String _imsi = tm.getSubscriberId();
            if (!TextUtils.isEmpty(_imsi)) {
                return _imsi;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "";
    }

    public SharedPreferences getGifspf() {
        return gifspf;
    }

    public void setGifspf(SharedPreferences gifspf) {
        this.gifspf = gifspf;
    }


    public static String dealNumber(String num) {
        if (TextUtils.isEmpty(num) || num.length() <= 4) return num;

        try {
            float tmp = Float.parseFloat(num);
            if (tmp < 10000) return num;
            DecimalFormat df = new DecimalFormat(".0");
            String result = df.format(Math.round(tmp) / 10000.0);
            if (result.endsWith(".0"))
                return result.replace(".0", "万");
            return result + "万";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return num;
    }

    public static boolean isShowAddress(String address) {
        return !(TextUtils.isEmpty(address)
                || address.contains("null")
                || address.contains("无法显示")
                || address.contains("不显示")
                || address.contains("位置失败"));

    }

    /**
     * 显示m图或者是s图
     *
     * @param result
     * @return
     */
    public static String changeImageSTOM(String result, int... args) {
        String r1 = "(http://pccoo\\.cn/\\S+/2013(0[6789]|1[012])\\d+/\\d*)(s)(\\.(jpg|jpeg|png))";
        String r2 = "(http://(p1|p2|p3|p4|p5|p6|p7|p8|up1)\\.pccoo\\.cn/\\S+/\\d*/\\d*)(s)(\\.(jpg|jpeg|png))";
        String r3 = "(http://pccoo\\.cn/\\S+/2013(0[6789]|1[012])\\d+/\\d*)(\\.(jpg|jpeg|png))";
        String r4 = "(http://(p1|p2|p3|p4|p5|p6|p7|p8|up1)\\.pccoo\\.cn/\\S+/\\d*/\\d*)(\\.(jpg|jpeg|png))";

        if (result.matches(r1)) {
            result = result.replaceAll(r1, "$1m$3");
            return result;
        }
        if (result.matches(r2)) {
            result = result.replaceAll(r2, "$1m$4");
            return result;
        }
        if (result.matches(r3)) {
            result = result.replaceAll(r3, "$1m$3");
            return result;
        }
        if (result.matches(r4)) {
            result = result.replaceAll(r4, "$1m$3");
            return result;
        }

        if (result.startsWith("http://r9.pccoo.cn")) {
            String rx = "_\\d+x\\d+\\(s\\)";
            if (args != null && args.length > 0) {
                String res = "_" + args[0] + "x300(w)";
                Pattern p = Pattern.compile(rx);
                Matcher m = p.matcher(result);
                if (m.find()) {
                    result = result.replace(m.group(), res);
                    return result;
                }
            }
        }

        result = result.replace("m.jpg", ".jpg")
                .replace("s.jpg", ".jpg")
                .replace("s.png", ".png")
                .replace("m.png", ".png")
                .replace("s.gif", ".gif")
                .replace("m.gif", ".gif")
                .replace("s.jpeg", ".jpeg")
                .replace("m.jpeg", ".jpeg")
                .replace("s.bmp", ".bmp")
                .replace("m.bmp", ".bmp")
                .replace("m.JPG", ".JPG")
                .replace("s.JPG", ".JPG")
                .replace("s.PNG", ".PNG")
                .replace("m.PNG", ".PNG")
                .replace("s.GIF", ".GIF")
                .replace("m.GIF", ".GIF")
                .replace("s.JPEG", ".JPEG")
                .replace("m.JPEG", ".JPEG")
                .replace("s.BMP", ".BMP")
                .replace("m.BMP", ".BMP");

        return result;
    }

    public static String getWidthAndHeight(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        String rx = "_\\d+_\\d+_\\d+";
        Pattern p = Pattern.compile(rx);
        Matcher m = p.matcher(url);
        if (m.find()) {
            return m.group();
        }
        return "";
    }

    public static boolean dealJumpUrl(Activity context, String url) {
        if (url.startsWith("http://" + PublicUtils.getCityUrl())) {
            String ids = null;
            if (url.equals("http://" + PublicUtils.getCityUrl() + "/")) { // 跳转到首页
                ids = "0,1,0,0";
          //      PageTurnUtils.turnPage(ids, "1", url, "", context);
                return true;
            } else if (url.equals("http://" + PublicUtils.getCityUrl() + "/reg/phonereg.aspx")) { // 快速注册
                Intent intent = new Intent(context, RegisterActivity.class);
                context.startActivityForResult(intent, 0);
                return true;
            }
            ids = getFourIds(url);
            if (!TextUtils.isEmpty(ids)) {
            //    PageTurnUtils.turnPage(ids, "1", url, "", context);
                return true;
            }
        }
        return false;
    }

    /**
     * 根据URL获取四个ids
     *
     * @param url
     * @return
     */
    public static String getFourIds(String url) {
        if (url.contains(NetUrl.COOKIE_TAIL)) {
            url = url.replace(NetUrl.COOKIE_TAIL, "");
        }
        String ids = null;
        String sl = url.replace("http://" + PublicUtils.getCityUrl() + "/", "");
        if (sl.equals("message/")) { // 消息通知
            ids = "6,8,0,0";
        } else if (sl.equals("hd/")) { // 活动
            ids = "14,0,0,0";
        } else if (sl.startsWith("bbs/")) { // 社区
            if (sl.equals("bbs/tie.aspx")) { // 社区发布
                ids = "1,10,0,0";
            } else if (sl.startsWith("bbs/rank.aspx")) { // 榜单
                // 1捧场王  2名人堂
                String ss = getValueByKeyFromUrl(url, "type");
                switch (ss) {
                    case "1":
                        ids = "1,18,1,0";
                        break;
                    case "2":
                        ids = "1,19,2,0";
                        break;
                }
            } else if (sl.startsWith("bbs/myAttention.aspx")) { // 版区 我的关注
                ids = "1,27,0,0";
            } else if (sl.startsWith("bbs/boardlist.aspx?stype=")) {
                // 1百姓事  2兴趣圈  3 生活馆  4 问事帮  5 找客服
                String ss = getValueByKeyFromUrl(url, "stype");
                switch (ss) {
                    case "1":
                        ids = "1,5,1,0";
                        break;
                    case "2":
                        ids = "1,6,2,0";
                        break;
                    case "3":
                        ids = "1,7,3,0";
                        break;
                    case "4":
                        ids = "1,8,4,0";
                        break;
                    case "5":
                        ids = "1,9,5,0";
                        break;
                }
            } else if (sl.startsWith("bbs/boardshow.aspx?boardid=")) { // 版块列表
                String ss = getValueByKeyFromUrl(url, "boardid");
                ids = "1,1," + ss + ",0";
            } else if (sl.equals("bbs/")) { // 最新
                ids = "1,0,0,0";
            } else if (sl.startsWith("bbs/index.aspx")) {
                // 1热帖  5同城爆料  9值得一看
                String ss = getValueByKeyFromUrl(url, "flag");
                switch (ss) {
                    case "1":
                        ids = "1,21,1,0";
                        break;
                    case "5":
                        ids = "1,20,5,0";
                        break;
                    case "9":
                        ids = "1,25,9,0";
                        break;
                }
            } else if (sl.equals("bbs/photo.aspx")) {
                // 图说
                ids = "1,22,0,0";
            } else if (sl.equals("bbs/attention.aspx")) {
                // 今日关注
                ids = "1,23,0,0";
            } else if (sl.equals("bbs/zthdlist.aspx")) {
                // 专题活动
                ids = "1,24,0,0";
            } else if (sl.startsWith("bbs/topic.aspx")) {
                // 详情
                String ss = getValueByKeyFromUrl(url, "id");
                ids = "1,2," + ss + ",0";
            }
        } else if (sl.startsWith("tieba/")) { // 贴吧
            if (sl.equals("tieba/add.aspx")) { // 闹闹发布
                ids = "2,7,0,0";
            } else if (sl.startsWith("tieba/rank.aspx")) {
                String ss = getValueByKeyFromUrl(url, "type");
                // 2闹闹帝   1闹闹王    0闹闹星
                switch (ss) {
                    case "0":
                        ids = "2,10,0,0";
                        break;
                    case "1":
                        ids = "2,9,1,0";
                        break;
                    case "2":
                        ids = "2,8,2,0";
                        break;
                }
            } else if (sl.equals("tieba/")) {
                // 广场
                ids = "2,0,0,0";
            } else if (sl.startsWith("tieba/?flag=")) {
                // 话题flag=4&type=2  推荐flag=0  晒图flag=2  网友自荐flag=3
                String flag = getValueByKeyFromUrl(url, "flag");
                String type = getValueByKeyFromUrl(url, "type");

                switch (flag) {
                    case "0":
                        ids = "2,4,0,0";
                        break;
                    case "2":
                        ids = "2,5,2,0";
                        break;
                    case "3":
                        ids = "2,6,3,0";
                        break;
                    default:
                        if (!TextUtils.isEmpty(type)) {
                            ids = "2,2,0,0";
                        }
                        break;
                }
            } else if (sl.startsWith("tieba/thread.aspx?id=")) { // 详情
                String ss = getValueByKeyFromUrl(url, "id");
                ids = "2,1," + ss + ",0";
            } else if (sl.startsWith("tieba/huati.aspx?id=")) { // 话题列表
                String ss = getValueByKeyFromUrl(url, "id");
                ids = "2,11," + ss + ",0";
            }
        } else if (sl.startsWith("cnews/")) { // 城事
            if (sl.startsWith("cnews/rank.aspx")) {
                // 有态度flag=0  打酱油flag=1  爱潜水flag=2
                String ss = getValueByKeyFromUrl(url, "flag");
                switch (ss) {
                    case "0":
                        ids = "13,4,0,0";
                        break;
                    case "1":
                        ids = "13,5,1,0";
                        break;
                    case "2":
                        ids = "13,6,2,0";
                        break;
                }
            } else if (sl.equals("cnews/")) {
                // 最新
                ids = "13,0,0,1";
            } else if (sl.startsWith("cnews/?flag=")) {
                String ss = getValueByKeyFromUrl(url, "flag");
                // 1热议   2民生   3名人  4趣闻  30娱乐  订阅号 待定
                switch (ss) {
                    case "1":
                        ids = "13,2,1,0";
                        break;
                    case "4":
                        ids = "13,3,4,0";
                        break;
                    default:
                        ids = "13,7," + ss + ",0";
                        break;
                }
            } else if (sl.startsWith("cnews/news_show.aspx")) { // 城事详情
                String ss = getValueByKeyFromUrl(url, "id");
                ids = "13,1," + ss + ",0";
            }
        } else if (sl.equals("cover/")) { // 秀场主页
            ids = "3,0,0,0";
        } else if (sl.startsWith("cover/mm/")) { // 美女秀场
            if (sl.startsWith("cover/mm/rank.aspx")) {
                // 1土豪榜  3新上榜  2魅力榜
                String ss = getValueByKeyFromUrl(url, "type");
                switch (ss) {
                    case "1":
                        ids = "3,15,1,0";
                        break;
                    case "2":
                        ids = "3,14,2,0";
                        break;
                    case "3":
                        ids = "3,16,3,0";
                        break;
                }
            } else if (sl.equals("cover/mm/")) {
                // 最新
                ids = "3,2,0,0";
            } else if (sl.startsWith("cover/mm/?type=")) {
                // 封面女郎 cover   新人秀new
                String ss = getValueByKeyFromUrl(url, "type");
                switch (ss) {
                    case "cover":
                        ids = "3,3,0,0";
                        break;
                    case "new":
                        ids = "3,4,0,0";
                        break;
                }
            } else if (sl.startsWith("cover/mm/?tag=")) { // 1性感  2冷艳  3气质  4萌妹子  5女汉子
                String ss = getValueByKeyFromUrl(url, "tag");
                ids = "3,17," + ss + ",0";
            } else if (sl.startsWith("cover/mm/photo_show.aspx")) { // 详情
                String ss = getValueByKeyFromUrl(url, "id");
                ids = "3,1," + ss + ",0";
            }
        } else if (sl.startsWith("cover/gg/")) { // 型男秀场
            if (sl.startsWith("cover/gg/rank.aspx")) { // 1女人缘  2人气男
                String ss = getValueByKeyFromUrl(url, "type");
                switch (ss) {
                    case "1":
                        ids = "3,18,1,0";
                        break;
                    case "2":
                        ids = "3,19,2,0";
                        break;
                }
            } else if (sl.equals("cover/gg/")) { // 最新
                ids = "3,5,0,0";
            } else if (sl.startsWith("cover/gg/?type=")) { // 封面男神 cover  新人秀 new
                String ss = getValueByKeyFromUrl(url, "type");
                switch (ss) {
                    case "cover":
                        ids = "3,6,0,0";
                        break;
                    case "new":
                        ids = "3,7,0,0";
                        break;
                }
            } else if (sl.startsWith("cover/gg/?tag=")) { // 1单身墙  2颜值墙  3托单墙  4炫技墙  5酱油墙
                String ss = getValueByKeyFromUrl(url, "tag");
                ids = "3,28," + ss + ",0";
            } else if (sl.startsWith("cover/gg/photo_show.aspx?id=")) { // 详情
                String ss = getValueByKeyFromUrl(url, "id");
                ids = "3,21," + ss + ",0";
            }
        } else if (sl.startsWith("cover/baobao/")) { // 萌宝
            if (sl.startsWith("cover/baobao/rank.aspx")) {
                // 1最强亲友团  2慷慨粉   3有爱粉
                String ss = getValueByKeyFromUrl(url, "type");
                switch (ss) {
                    case "1":
                        ids = "3,24,1,0";
                        break;
                    case "2":
                        ids = "3,23,2,0";
                        break;
                    case "3":
                        ids = "3,22,3,0";
                        break;
                }
            } else if (sl.equals("cover/baobao/")) { // 最新
                ids = "3,8,0,0";
            } else if (sl.startsWith("cover/baobao/?type=")) {  // 封面宝宝 cover  新宝宝 new
                String ss = getValueByKeyFromUrl(url, "type");
                switch (ss) {
                    case "cover":
                        ids = "3,12,0,0";
                        break;
                    case "new":
                        ids = "3,13,0,0";
                        break;
                }
            } else if (sl.startsWith("cover/baobao/?tag=")) { // 1睡姿秀  2萌娃  3表情帝  4吃货 5潮娃
                String ss = getValueByKeyFromUrl(url, "tag");
                ids = "3,25," + ss + ",0";
            } else if (sl.startsWith("cover/baobao/photo_show.aspx?id=")) { // 详情
                String ss = getValueByKeyFromUrl(url, "id");
                ids = "3,26," + ss + ",0";
            } else if (sl.startsWith("cover/baobao/baby_room.aspx?id=")) { // 宝宝小屋
                String ss = getValueByKeyFromUrl(url, "id");
                ids = "3,27," + ss + ",0";
            }
        } else if (sl.startsWith("home/")) { // 主页
            String ss = getValueByKeyFromUrl(url, "id");
            if (sl.startsWith("home/mm_show.aspx")) { // 美女
                ids = "6,29," + ss + ",0";
            } else if (sl.startsWith("home/gg_show.aspx")) { // 帅男
                ids = "6,28," + ss + ",0";
            } else if (sl.startsWith("home/baby_show.aspx")) { // 萌宝
                ids = "6,30," + ss + ",0";
            } else if (sl.startsWith("home/topic_show.aspx")) { // 闹闹
                ids = "6,31," + ss + ",0";
            } else if (sl.startsWith("home/bbs_show.aspx")) { // 社区
                ids = "6,32," + ss + ",0";
            } else if (sl.startsWith("home/news_show.aspx")) { // 城事
                ids = "6,33," + ss + ",0";
            } else if (sl.startsWith("home/hd_show.aspx")) { // 活动
                ids = "6,34," + ss + ",0";
            } else if (sl.startsWith("home/photo_manager.aspx")) { // 照片管理
                ids = "6,26," + ss + ",0";
            } else if (sl.startsWith("home/index.aspx")) { // 个人主页
                ids = "6,20," + ss + ",0";
            }
        } else if (sl.equals("user/info/")) { // 个人中新设置
            ids = "6,17,0,0";
        } else if (sl.equals("user/my_level.aspx")) { // 我的等级
            ids = "6,1,0,0";
        } else if (sl.equals("user/my_medal.aspx")) { // 我的勋章
            ids = "6,2,0,0";
        } else if (sl.equals("user/my_tequan.aspx")) { // 等级特权
            ids = "6,4,0,0";
        } else if (sl.equals("task/")) { // 任务中新
            ids = "6,3,0,0";
        } else if (sl.equals("user/my_coin.aspx")) { // 我的城市币
            ids = "6,5,0,0";
        } else if (sl.startsWith("shop/my_order.aspx?id=")) { // 我的兑换
            String ss = getValueByKeyFromUrl(url, "id");
            ids = "15,2," + ss + ",0";
        } else if (sl.startsWith("shop/shop_show.aspx?id=")) { // 商品详情
            String ss = getValueByKeyFromUrl(url, "id");
            ids = "15,1," + ss + ",0";
        } else if (sl.equals("shop/")) { // 商城首页
            ids = "15,0,0,0";
        } else if (sl.startsWith("user/fan.aspx?id=")) { // 粉丝 关注
            String ss = getValueByKeyFromUrl(url, "id");
            ids = "6,7," + ss + ",0";
        }

        return ids;
    }

    public static String getValueByKeyFromUrl(String str, String key) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String value = "";
        try {
            URL url = new URL(str);
            String query = url.getQuery();
            String[] ss = query.split("&");
            for (String s : ss) {
                if (s.split("=")[0].equals(key)) {
                    value = s.split("=")[1];
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            value = "";
        }
        return value;
    }
}
