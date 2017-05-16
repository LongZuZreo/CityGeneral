package com.example.citygeneral.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.model.entity.LoginGet;
import com.example.citygeneral.model.entity.UserInfo;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.model.http.Tools;
import com.example.citygeneral.mywidget.MyProgressDialog;
import com.example.citygeneral.utils.CityApp;
import com.example.citygeneral.utils.JDateTimeTools;
import com.example.citygeneral.utils.NetUrl;
import com.example.citygeneral.utils.PublicUtils;
import com.example.citygeneral.utils.ThreadTools;
import com.example.citygeneral.utils.file.ShowPhotoChoseDialog;
import com.example.citygeneral.utils.file.UploadImageManager;
import com.example.citygeneral.view.RoundImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//完善资料
public class RegistBackInfoActivity extends BaseActivity implements
        OnClickListener {
    private Context context;
    private RoundImageView image;
    private ImageView manImage, womenImage, back, positionImage;
    private EditText nameEdit, addressEditText;
    private TextView topTitle, bangdingTextView, birText;
    private String sexFlag = "";
    private Button btn;
    private RelativeLayout birLayout;
    // ---------网络-------网络请求需求的变量
    private PublicUtils utils;

    MyProgressDialog loginDia;

    // 参数
    private String phone = "";
    private String username = "";
    private int userid = 0;
    private String birStr = "";

    private final int LOCATION = 4;

    // 上传图片参数
    private UploadImageManager loadManager;
    private ShowPhotoChoseDialog showPhotoChoseDialog;
    private MyProgressDialog dialog;
    private String imageName = "";// 拍照图片临时上传名字
    private String imageUrl = "";// 图片地址
    private String path;
    @SuppressLint("HandlerLeak")
    private Handler loadHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(context, "图片上传失败", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    break;
                case 0:
                    dialog.dismiss();
                    imageUrl = msg.obj.toString();
                    image.setImageBitmap(utils.getBitmapByWidth(path,
                            utils.dip2px(50), 1));

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        if (loadHandler != null) loadHandler.removeCallbacksAndMessages(null);
        if (dialog != null && dialog.isShowing()) dialog.dismiss();

        super.onDestroy();
    }



    // ---------网络-------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_regist_userinfo_layout);
        phone = getIntent().getStringExtra("PHONE");
        username = getIntent().getStringExtra("USERNAME");
        userid = getIntent().getExtras().getInt("USERID");
        init();
        initPhotoDialog();
        System.out.println("1客户端登录信息" +
                getIntent().getStringExtra("client_flag"));
        System.out.println("2客户端登录信息" + getIntent().getStringExtra("client_id"));
        System.out.println("3客户端登录信息" + getIntent().getStringExtra("client_name"));
        System.out.println("4客户端登录信息" + getIntent().getStringExtra("client_pic"));
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    public void init() {
        context = this;
        loginDia = new MyProgressDialog(this, "请求中...");
        btn = (Button) findViewById(R.id.submit_btn);
        btn.setOnClickListener(this);
        back = (ImageView) findViewById(R.id.back);

        birLayout = (RelativeLayout) findViewById(R.id.bir_layout);
        birText = (TextView) findViewById(R.id.bir_text);
        image = (RoundImageView) findViewById(R.id.image);
        nameEdit = (EditText) findViewById(R.id.name_edit);
        addressEditText = (EditText) findViewById(R.id.address_edit);
        positionImage = (ImageView) findViewById(R.id.position_image);
        manImage = (ImageView) findViewById(R.id.mansex_image);
        womenImage = (ImageView) findViewById(R.id.womansex_image);
        topTitle = (TextView) findViewById(R.id.top_title);
        bangdingTextView = (TextView) findViewById(R.id.bangding_textview);
        manImage.setOnClickListener(this);
        womenImage.setOnClickListener(this);
        image.setOnClickListener(this);
        positionImage.setOnClickListener(this);
        // ---------网络-------请求结果的返回

        utils = new PublicUtils(context);
        if (getIntent().getStringExtra("client_flag") != null && !getIntent().getStringExtra("client_flag").equals("")) {//是客户端登录跳转来
            topTitle.setText("完善资料");
            btn.setText("确定提交");
            bangdingTextView.setVisibility(View.VISIBLE);
            bangdingTextView.setOnClickListener(this);
        } else {
            if (!phone.equals("")) {
                topTitle.setText("完善资料");
                btn.setText("确定提交");
                bangdingTextView.setVisibility(View.VISIBLE);
                bangdingTextView.setOnClickListener(this);
            }
        }

        birLayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                JDateTimeTools dateTools = new JDateTimeTools(context, birText
                        .getText().toString().trim());
                dateTools.dateTimePicKDialog(birText);
            }
        });
        back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogMethod();
            }
        });

        // 默认值设置
        UserInfo info = utils.getUserInfo();

        if (!info.getUserFace().equals("")) {
            imageUrl = info.getUserFace();
            Glide.with(this).load(imageUrl).into(image);
         //   ImageLoaderTools.loadCommenImage(imageUrl, image);
        }

        if (!info.getNick().equals("")) {
            nameEdit.setText(info.getNick());
        }
        if (info.getSex().equals("男")) {
            manImage.setImageResource(R.drawable.regist_man_check);
            womenImage.setImageResource(R.drawable.regist_woman);
            sexFlag = "男";
        }
        if (info.getSex().equals("女")) {
            manImage.setImageResource(R.drawable.regist_man);
            womenImage.setImageResource(R.drawable.regist_woman_check);
            sexFlag = "女";
        }
        if (!info.getBirthday().equals("") && info.getBirthday().contains("-")
                && !info.getBirthday().equals("1900-01-01")) {
            birStr = info.getBirthday();
            birText.setText(birStr);
        } else {
            birStr = "1980-01-01";
            birText.setText(birStr);
        }
        if (!info.getLifeAddr().equals("") && !info.getLifeAddr().equals("未设置")) {
            addressEditText.setText(info.getLifeAddr());
        }

        //客户端登录专有设置，传过来了客户端授权id
        if (getIntent().getStringExtra("client_flag") != null && !getIntent().getStringExtra("client_flag").equals("")) {
            imageUrl = getIntent().getStringExtra("client_pic");
            Glide.with(this).load(imageUrl).into(image);
            //ImageLoaderTools.loadCommenImage(imageUrl, image);
            nameEdit.setText(getIntent().getStringExtra("client_name"));
        }

    }

    private void initPhotoDialog() {
        loadManager = UploadImageManager.getManager();
        showPhotoChoseDialog = new ShowPhotoChoseDialog(this);
        dialog = new MyProgressDialog(this);
        // 拍照
        showPhotoChoseDialog.setItemOneOlick(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 拍照图片的名字
                imageName = DateFormat.format("yyyyMMddkkmmss", new Date())
                        .toString();
                // 判断内存卡是否可用
                if (!Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(RegistBackInfoActivity.this, "SD卡不存在",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                // 拍照的照片的文件夹
                File file = new File(Environment.getExternalStorageDirectory()
                        .getPath() + File.separator + "shangchuan");
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 设置照片的输出路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                        Environment.getExternalStorageDirectory().getPath()
                                + File.separator + "shangchuan", imageName
                        + ".jpg")));
                startActivityForResult(intent, 0);
                showPhotoChoseDialog.dismiss();

            }
        });
        // 相册
        showPhotoChoseDialog.setItemTwoOlick(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(getApplicationContext(),
                        ImageChoseActivity.class);
                intent2.putExtra("number", 1);
                intent2.putExtra("flag", true);
                startActivityForResult(intent2, 1000);
                showPhotoChoseDialog.dismiss();
            }
        });
    }

    // 完善信息参数
    private String creatParams() {
        JSONObject jo = new JSONObject();
        try {
            jo.put("siteID", utils.getCityId());
            jo.put("userName", username);
            jo.put("userID", userid);
            jo.put("face", imageUrl);
            jo.put("nick", nameEdit.getText().toString().trim());
            jo.put("sex", sexFlag);
            jo.put("birthday", birText.getText().toString().trim());
            jo.put("lifeaddr", addressEditText.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                NetUrl.PHSocket_SetAddUserBaseInfo, jo);
        return params;
    }

    // 注册参数
    private String ReplyParams() {
        JSONObject jo = new JSONObject();
        JSONObject jo1 = new JSONObject();
        try {
            jo.put("RoleIMG", imageUrl);
            jo.put("SiteID", utils.getCityId());
            jo.put("RoleName", nameEdit.getText().toString().trim());
            jo.put("lifeaddr", addressEditText.getText().toString().trim());
            jo.put("sex", sexFlag);
            jo.put("birthday", birText.getText().toString().trim());
            if (getIntent().getStringExtra("client_flag") != null && !getIntent().getStringExtra("client_flag").equals("")) {//客户端过来
                jo.put("Tel", "");
                jo.put("UserPWD", "");
                if (getIntent().getStringExtra("client_flag").equals("qq")) {
                    jo.put("OType", 2);
                    jo.put("QQOpenID", getIntent().getStringExtra("client_id"));//qqid
                } else if (getIntent().getStringExtra("client_flag").equals("weixin")) {
                    jo.put("OType", 4);
                    jo.put("openID", getIntent().getStringExtra("client_id"));//微信id
                    jo.put("weixin", "weixin@ccoo.cn");
                } else {
                    jo.put("OType", 3);
                    jo.put("WeiboID", getIntent().getStringExtra("client_id"));//微博id
                }
            } else {
                jo.put("OType", 5);
                jo.put("Tel", phone);
                jo.put("UserPWD", getIntent().getStringExtra("PASSWORD"));
            }
            jo1.put("InUser", jo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                NetUrl.METHOD_SetRegUserInfo, jo1);
        return params;
    }

    /**
     * 注册成功的话会返回，返回的信息包含了简略用户信息
     * "UserID"。"UserName"。"RoleName"。"RoleImg"。"uSiteID"
     * 存储本地，表示本地登录成功
     *
     * @param jsonStr
     */
    private void setListData2(String jsonStr) {
        if (jsonStr != null && !jsonStr.equals("")) {
            try {
                int code = utils.getResult2(jsonStr);
                String message = utils.getResult(jsonStr);
                if (code == 1000) {
                    if (getIntent().getStringExtra("client_flag") != null && !getIntent().getStringExtra("client_flag").equals("")) {//微信过来
                        Toast.makeText(RegistBackInfoActivity.this, "注册成功",
                                Toast.LENGTH_SHORT).show();
                        parserResult(jsonStr);
                    } else {
                        if (phone == null || phone.equals("")) {
                            Toast.makeText(RegistBackInfoActivity.this, "完善资料成功",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistBackInfoActivity.this, "注册成功",
                                    Toast.LENGTH_SHORT).show();
                            parserResult(jsonStr);
                        }
                    }
                    finish();
                } else {
                    Toast.makeText(RegistBackInfoActivity.this, message,
                            Toast.LENGTH_SHORT).show();
                }
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //CustomToast.showToastError2(context);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    String path;
                    path = Environment.getExternalStorageDirectory().getPath()
                            + File.separator + "shangchuan" + File.separator
                            + imageName + ".jpg";
                    Intent intent = new Intent(this, ImageCreateActivity.class);
                    intent.putExtra("imagefile", path);
                    intent.putExtra("flag", true);
                    startActivityForResult(intent, 200);
                    break;
            }
        }

        if (resultCode == 200) {
            String path = "";
            if ((null != data.getExtras().get("file"))
                    && (!"".equals(data.getExtras().get("file")))) {
                path = (String) data.getExtras().get("file");
                this.path = path;
            } else {
                path = "";
            }
            loadManager
                    .upload(loadHandler,
                            path,
                            "filesrc=app_avatar&sw=150&sh=150&mw=300&mh=300&mode=s&mmode=s&siz=2",
                            getApplicationContext());
            dialog.show();

        }
        // 相册照片返回
        if (resultCode == 1000) {
            @SuppressWarnings("unchecked")
            Map<String, String> map = (Map<String, String>) data.getExtras()
                    .getSerializable("map");
            String path = "";
            Set<String> set = map.keySet();
            for (String str : set) {
                path = map.get(str);
            }
            this.path = path;
            loadManager
                    .upload(loadHandler,
                            path,
                            "filesrc=app_avatar&sw=150&sh=150&mw=300&mh=300&mode=s&mmode=s&siz=2",
                            getApplicationContext());
            dialog.show();
        }

        if (resultCode == 1001) {
            // 绑定返回回来
            String result = data.getStringExtra("result");
            parserResult(result);
            finish();
        }
    }

    public void dialogMethod() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.setup_dialog_item, null);

        TextView titleText = (TextView) view.findViewById(R.id.title_text);
        final Dialog dialog = new Dialog(context, R.style.Theme_CustomDialog2);
        dialog.setContentView(view);

        view.findViewById(R.id.main_rlay).setVisibility(View.VISIBLE);
        titleText.setText("操作提示");
        titleText.setTextColor(getResources().getColor(R.color.color_333));
        TextView infoText2 = (TextView) view.findViewById(R.id.info);
        if (getIntent().getStringExtra("client_flag") != null && !getIntent().getStringExtra("client_flag").equals("")) {//是客户端登录跳转来
            infoText2.setText("完善资料才能完成注册，现在退出将不能完成注册，确定退出吗？");
        } else {
            if (!phone.equals("")) {
                infoText2.setText("完善资料才能完成注册，现在退出将不能完成注册，确定退出吗？");
            } else {
                infoText2.setText("完善资料才能进行登录，现在退出将不能完成登录，确定退出吗？");
            }
        }
        infoText2.setTextColor(getResources().getColor(R.color.color_333));
        dialog.setContentView(view);
        dialog.show();

        view.findViewById(R.id.cancle).setVisibility(View.VISIBLE);
        view.findViewById(R.id.cancle).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.bottom_view).setVisibility(View.VISIBLE);
        view.findViewById(R.id.confirm).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       /* AppWebActivity.setLoginCookieNull(context,
                                utils.getUserInfo().getSiteSqURL());
                        AppWebActivity.setLoginCookieNull(context,
                                utils.getCityUrl());
                        AppWebActivity.setHeaderCookies(context, utils
                                .getUserInfo().getSiteSqURL());
                        AppWebActivity.setHeaderCookies(context,
                                utils.getCityUrl());*/
                        utils.clearUserinfo();
                        utils.clearUserName();
                        finish();
                    }
                });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {// 当手机的任意一个硬件按钮被点击的时候都会调用当前的onKeyDown方法，并且把被点击的按钮的id传递给onKeyDown的第一个参数，而back按钮的id为KeyEvent.KEYCODE_BACK,KeyEvent.KEYCODE_MENU是菜单按钮,那么KeyEvent.KEYCODE_BACK
            // == keyCode就表示是点击了back按钮
            dialogMethod();
            return true;
        }
        // TODO Auto-generated method stub
        return super.onKeyDown(keyCode, event);
    }

    // ---------网络-------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mansex_image:
                manImage.setImageResource(R.drawable.regist_man_check);
                womenImage.setImageResource(R.drawable.regist_woman);
                sexFlag = "男";

                break;
            case R.id.womansex_image:
                manImage.setImageResource(R.drawable.regist_man);
                womenImage.setImageResource(R.drawable.regist_woman_check);
                sexFlag = "女";
                break;
            case R.id.submit_btn:
                if (!Tools.isNetworkConnected(context)) {

                    return;
                }
                if (imageUrl.equals("") || imageUrl.contains("nopic.gif")) {
                    Toast.makeText(context, "请上传头像~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nameEdit.getText().toString().trim().equals("")) {
                    nameEdit.requestFocus();
                    nameEdit.setFocusable(true);
                    nameEdit.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(nameEdit, 0);
                    Toast.makeText(context, "请填写昵称~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (nameEdit.getText().toString().trim().contains("guest")) {
                    nameEdit.requestFocus();
                    nameEdit.setFocusable(true);
                    nameEdit.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(nameEdit, 0);
                    Toast.makeText(context, "请设置个性昵称，不能含有guest字符~", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (nameEdit.getText().toString().length() < 2
                        || nameEdit.getText().toString().length() > 10) {
                    nameEdit.requestFocus();
                    nameEdit.setFocusable(true);
                    nameEdit.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(nameEdit, 0);
                    Toast.makeText(context, "请正确设置昵称,2-10字内~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!sexFlag.equals("男") && !sexFlag.equals("女")) {
                    Toast.makeText(context, "请设置性别~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (birText.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "请填写你的生日信息~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (addressEditText.getText().toString().trim().equals("")) {
                    addressEditText.requestFocus();
                    addressEditText.setFocusable(true);
                    addressEditText.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(addressEditText, 0);
                    Toast.makeText(context, "居住地不能为空~", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (addressEditText.getText().toString().length() < 2
                        || addressEditText.getText().toString().length() > 12) {
                    addressEditText.requestFocus();
                    addressEditText.setFocusable(true);
                    addressEditText.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(addressEditText, 0);
                    Toast.makeText(context, "请正确设置居住地,2-12字内~", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginDia.show();

                /*if (getIntent().getStringExtra("client_flag") != null && !getIntent().getStringExtra("client_flag").equals("")) {//客户端过来
                    HttpParamsTool.Post(ReplyParams(), handler,
                            getApplicationContext());
                } else {//不是客户端过来
                    if (phone.equals("")) {
                        HttpParamsTool.Post(creatParams(), handler,
                                getApplicationContext());
                    } else {
                        HttpParamsTool.Post(ReplyParams(), handler,
                                getApplicationContext());
                    }
                }*/

                break;
            case R.id.image:
                showPhotoChoseDialog.show();
                break;
            case R.id.bangding_textview:
               /* Intent intent = new Intent(this, RegistBackBangdingActivity.class);
                if (getIntent().getStringExtra("client_flag") != null && !getIntent().getStringExtra("client_flag").equals("")) {
                    intent.putExtra("client_flag", getIntent().getStringExtra("client_flag"));
                    intent.putExtra("client_id", getIntent().getStringExtra("client_id"));
                    intent.putExtra("client_name", getIntent().getStringExtra("client_name"));
                    intent.putExtra("client_pic", getIntent().getStringExtra("client_pic"));
                }
                intent.putExtra("PHONE", phone);
                startActivityForResult(intent, 0);*/
                break;
            case R.id.position_image:
             /*   Intent positionIntent = new Intent(this, CurrentPositionActivity.class);
                startActivity(positionIntent);*/
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if (CityApp.address != null && !CcooApp.address.equals("") && addressEditText != null) {
            addressEditText.setText(CcooApp.address);
        }*/
    }

    private void parserResult(String result) {
        if (result != null) {
            try {
                JSONObject obj = new JSONObject(result);
                JSONObject object = obj.optJSONObject("MessageList");
                int getid = object.optInt("code");
                if (getid != 0) {
                    switch (getid) {
                        case 1000:
                            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT)
                                    .show();
                            Map<String, Object> map = new HashMap<String, Object>();
                            System.out.println();
                            map = LoginGet
                                    .getlogin(obj.optJSONObject("ServerInfo"));
                            if (map.size() != 0) {
                                SharedPreferences spm = context
                                        .getSharedPreferences("loginuser",
                                                Context.MODE_PRIVATE);
                                Editor editor = spm.edit();
                                editor.putString("UserID", map.get("UserID")
                                        .toString());
                                editor.putString("UserName", map.get("UserName")
                                        .toString());
                                editor.putString("RoleName", map.get("RoleName")
                                        .toString());
                                editor.putString("RoleImg", map.get("RoleImg")
                                        .toString());
                                editor.putString("uSiteID", map.get("uSiteID")
                                        .toString());
                                editor.putInt("ouSiteID", Integer.parseInt(map.get(
                                        "ouSiteID").toString()));
                                editor.commit();
                              //  NewsContentActivit.getusername();

                                // 给cookies写入数据，是webview使用
                                if (new PublicUtils(getApplicationContext())
                                        .getCityId() != -1) {
                                    ThreadTools.threadMethod(utils, context);
                                }
                //                BaseVolley.getInstance().doPostString(NetUrl.APPURL,"",);
                            }
                            break;

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 个人信息数据请求参数
     */
    private String createParamsPerson() {
        JSONObject jo = new JSONObject();
        try {
            jo.put("siteID", utils.getCityId());
            jo.put("userName", utils.getUserName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                NetUrl.PHSocket_GetBBSUsersInfoNew, jo);
        return params;
    }

    /**
     * 个人信息数据解析
     */
    private void parserResultPerson(String result) {
        UserInfo userInfo = null;
        if (result != null) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.getString("ServerInfo") != null) {
                    JSONObject info = jsonObject.getJSONObject("ServerInfo");
                    String Id = info.getString("Id");
                    final String SiteID = info.getString("SiteID");

                    String SiteSqURL = info.getString("SiteSqURL");
                    String UserName = info.getString("UserName");
                    String Nick = info.getString("Nick");
                    String UserFace = info.getString("UserFace");
                    String Sex = info.getString("Sex");
                    String Name = info.getString("Name");
                    String Birthday = info.getString("Birthday");
                    String XingZuo = info.getString("XingZuo");
                    String Info = info.getString("Info");
                    String Mobile = info.getString("Mobile");
                    String Tel1 = info.getString("Tel1");
                    String Tel = info.getString("Tel");
                    String Email = info.getString("Email");
                    String TrueEmail = info.getString("TrueEmail");
                    String Position = info.getString("Position");
                    String PositionName = info.getString("PositionName");
                    String FrendNum = info.getString("FrendNum");
                    String FansNum = info.getString("FansNum");
                    String Coin = info.getString("Coin");
                    String Level = info.getString("Level");
                    String Integral = info.getString("Integral");
                    String Image = info.getString("Image");
                    String HonorName = info.getString("HonorName");

                    String TaskCount = info.getString("TaskCount");
                    String IntegralRank = info.getString("IntegralRank");
                    String MedalCount = info.getString("MedalCount");
                    String MedalImags = info.getString("MedalImags");
                    String IsQian = "0";

                    if (!info.getString("TState").equals("null")) {
                        String code = info.getJSONObject("TState").getString(
                                "code");
                        if (code.equals("1006")) {
                            IsQian = "1";
                        }
                    }
                    String BBID = info.getString("BBID");
                    String IsFirstPub = info.getString("IsFirstPub");
                    String QQ = info.getString("QQ");
                    String WeiXin = info.getString("WeiXin");
                    String LifeAddr = info.getString("LifeAddr");
                    String Marry = info.getString("Marry");
                    String Job = info.getString("Job");
                    String MsgCount = info.getString("MsgCount");
                    String title = "";
                    String headimg = "";
                    String firstid = "";
                    String secondid = "";
                    if (!info.getString("MsgAlert").equals("null")) {
                        title = info.getJSONObject("MsgAlert").getString(
                                "title");
                        headimg = info.getJSONObject("MsgAlert").getString(
                                "headimg");
                        firstid = info.getJSONObject("MsgAlert").getString(
                                "firstid");
                        secondid = info.getJSONObject("MsgAlert").getString(
                                "secondid");
                    }

                    String IsQQBind = info.getString("IsQQBind");
                    String IsWXBind = info.getString("IsWXBind");
                    String IsWBBind = info.getString("IsWBBind");


                    userInfo = new UserInfo(Id, SiteID, SiteSqURL, UserName,
                            Nick, UserFace, Sex, Name, Birthday, XingZuo, Info,
                            Mobile, Tel1, Tel, Email, TrueEmail, Position,
                            PositionName, FrendNum, FansNum, Coin, Level,
                            Integral, Image, HonorName, TaskCount,
                            IntegralRank, MedalCount, MedalImags, IsQian, BBID, IsFirstPub,
                            QQ, WeiXin, LifeAddr, Marry, Job, MsgCount, title,
                            headimg, firstid, secondid, IsQQBind, IsWXBind, IsWBBind);

                    final String SiteName = info.getString("SiteName");
                    final String SiteAreaName = info.getString("SiteAreaName");
                    final String SiteURL = info.getString("SiteURL");
                    final String Initial = info.getString("Initial");

                   /* if (SiteID.equals(utils.getCityId() + "")) {
                        utils.saveUserInfo(userInfo);
                    } else {
                        dialog.dismiss();
                        KuanzhanDialog kuanzhanDialog = new KuanzhanDialog(
                                activity, SiteName);
                        kuanzhanDialog.setOnReturnSubmit(new onReturnSubmit() {

                            @Override
                            public void onSubmit() {
                                new PublicUtils(getApplicationContext())
                                        .saveCityId(Integer.parseInt(SiteID),
                                                SiteName, SiteAreaName,
                                                SiteURL, 0, Initial, "0");
                                HttpParamsTool.Post(creatParamsPeizhi(Integer
                                                .parseInt(SiteID)), peizhiHandler,
                                        getApplicationContext());
                                dialog.show();

                            }
                        });
                        kuanzhanDialog.show();
                    }*/
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 配置参数拼接
     */
    private String creatParamsPeizhi(int cityId) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("siteID", cityId);
            jo.put("type", 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                NetUrl.PHSocket_GetAppSetInfo, jo);
        return params;
    }


}
