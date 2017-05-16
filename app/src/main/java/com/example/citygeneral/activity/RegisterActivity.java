package com.example.citygeneral.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.UserGetCode;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.mywidget.MyProgressDialog;
import com.example.citygeneral.utils.CityApp;
import com.example.citygeneral.utils.NetUrl;
import com.example.citygeneral.utils.PublicUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private String IP = CityApp.ip;
    private int cityid;
    private PublicUtils utils;
    private MyHandler handler = new MyHandler(this);
    private ImageView btn_back_login;
    private TextView textView_user_verify, cityname;
    private TextView tv_title_login;
    private EditText editText_user_login_name;
    private EditText editText_user_login_pwd;
    private Button button_user_login;
    private Context context;
    private int timer = 180;
    private Timer mytime;

    private boolean falg = true;
    private boolean falgaa = true;
    private MyProgressDialog pDialog;
    private boolean exit = true;
    private boolean flag = true;
    private MyProgressDialog pDialog1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        btn_back_login = (ImageView) findViewById(R.id.btn_back_login);
        textView_user_verify = (TextView) findViewById(R.id.textView_user_verify);
        tv_title_login = (TextView) findViewById(R.id.tv_title_login);
        editText_user_login_name = (EditText) findViewById(R.id.editText_user_login_name);
        editText_user_login_pwd = (EditText) findViewById(R.id.editText_user_login_pwd);
        button_user_login = (Button) findViewById(R.id.button_user_login);
        cityname = (TextView) findViewById(R.id.cityname);

        tv_title_login.setText("快速注册");
        button_user_login.setText("提交注册");

        pDialog = new MyProgressDialog(this, "短信发送中...");
        pDialog1 = new MyProgressDialog(this, "注册中...");
    }

    @Override
    protected void initData() {
       // cityid = new PublicUtils(getApplicationContext()).getCityId();
      //  cityname.setText("当前注册站点:" + 2422);
        cityid = 2422;
        cityname.setVisibility(View.VISIBLE);
        cityname.setText("延庆");
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        btn_back_login.setOnClickListener(this);
        button_user_login.setOnClickListener(this);
        editText_user_login_name.setOnClickListener(this);
        textView_user_verify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back_login:
                if (mytime != null) {
                    mytime.cancel();
                }
                finish();
                break;
            case R.id.textView_user_verify:
                String username = editText_user_login_name.getText().toString()
                        .trim();
                if (!PublicUtils.isMobileNO(username)) {
                    editText_user_login_name.requestFocus();
                    editText_user_login_name.setFocusable(true);
                    editText_user_login_name.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(editText_user_login_name,
                            0);
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号~",
                            Toast.LENGTH_SHORT).show();
                } else {
                    boolean aaaaa = false;
                    if (Integer.valueOf(username.substring(0, 1)) == 1
                            && ((Integer.valueOf(username.substring(1, 2)) == 7)
                            || (Integer.valueOf(username.substring(1, 2)) == 3)
                            || (Integer.valueOf(username.substring(1, 2)) == 5) || (Integer
                            .valueOf(username.substring(1, 2)) == 8))) {
                        aaaaa = true;
                    } else {
                        Toast.makeText(RegisterActivity.this, "请输入正确的手机号~",
                                Toast.LENGTH_SHORT).show();
                    }
                    if (falg && aaaaa) {
                        falg = false;
                        falgaa = true;
                        String params = ReplyParams(username);
                       // manager.request(params, 0, 1);

                        BaseVolley.getInstance().doPostString(NetUrl.APPURL, "", params, new MyCallBack<Object>() {
                            @Override
                            public void onDataChanged(Object data) {

                            }

                            @Override
                            public void onErrorHappened(String errorMessage) {

                            }
                        });


                        // 做一个倒计时
                        mytime = new Timer();
                        mytime.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                pDialog.dismiss();

                                if (!falgaa) {
                                    if (mytime != null) {
                                        mytime.cancel();
                                        timer = 180;
                                        falg = true;
                                        textView_user_verify.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                textView_user_verify
                                                        .setBackgroundResource(R.drawable.getcode_select);
                                                textView_user_verify
                                                        .setText("获取验证码");
                                            }
                                        });
                                    }
                                } else {

                                    textView_user_verify.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            textView_user_verify
                                                    .setBackgroundResource(R.drawable.getcode_select);
                                            textView_user_verify.setText("已发送"
                                                    + timer + "秒");
                                        }
                                    });
                                    if (timer == 0) {
                                        textView_user_verify.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                textView_user_verify
                                                        .setBackgroundResource(R.drawable.getcode_select);
                                                textView_user_verify
                                                        .setText("重新获取");
                                            }
                                        });
                                        timer = 181;
                                        falg = true;
                                        mytime.cancel();
                                    }
                                    timer--;
                                }
                            }
                        }, 2000, 1000);
                    } else {
                        Toast.makeText(context, "请稍后再试~", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.button_user_login:
                // 提交注册
                String username1 = editText_user_login_name.getText().toString()
                        .trim();
                String userpwd = editText_user_login_pwd.getText().toString()
                        .trim();
                if (username1.equals("") && TextUtils.isEmpty(username1)) {
                    editText_user_login_name.requestFocus();
                    editText_user_login_name.setFocusable(true);
                    editText_user_login_name.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(editText_user_login_name,
                            0);
                    Toast.makeText(RegisterActivity.this, "请输入手机号~",
                            Toast.LENGTH_SHORT).show();
                } else if (!PublicUtils.isMobileNO(username1)) {
                    editText_user_login_name.requestFocus();
                    editText_user_login_name.setFocusable(true);
                    editText_user_login_name.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(editText_user_login_name,
                            0);
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号~",
                            Toast.LENGTH_SHORT).show();
                } else if (username1.equals("") && TextUtils.isEmpty(username1)) {
                    editText_user_login_pwd.requestFocus();
                    editText_user_login_pwd.setFocusable(true);
                    editText_user_login_pwd.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(editText_user_login_pwd,
                            0);
                    Toast.makeText(RegisterActivity.this, "请输入收到的短信验证码~",
                            Toast.LENGTH_SHORT).show();
                } else if (username1.length() < 6) {
                    editText_user_login_pwd.requestFocus();
                    editText_user_login_pwd.setFocusable(true);
                    editText_user_login_pwd.setFocusableInTouchMode(true);
                    utils.getInputManager().showSoftInput(editText_user_login_pwd,
                            0);
                    Toast.makeText(RegisterActivity.this, "验证码错误~", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    if (flag) {
                        flag = false;
                        button_user_login
                                .setBackgroundResource(R.drawable.btn_userlogin_shape3);
                        // 关闭软键盘
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(
                                editText_user_login_pwd.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        String params = ReplyParams1(username1, userpwd);
                        BaseVolley.getInstance().doPostString(NetUrl.APPURL, "", params, new MyCallBack<UserGetCode>() {
                            @Override
                            public void onDataChanged(UserGetCode data) {

                            }

                            @Override
                            public void onErrorHappened(String errorMessage) {

                            }
                        });
                        //manager.request(params, 1, 1);
                    }
                }
                break;
        }
    }
    // 用户注册验证码
    private String ReplyParams(String username) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("phone", username);
            jo.put("siteID", cityid);
            jo.put("ip", IP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                NetUrl.METHOD_SetRegSendCode, jo);
        return params;
    }

    // 用户获取验证码返回值
    private void parserResult(String result) {

        if (result != null) {
            try {
                JSONObject obj = new JSONObject(result);
                JSONObject object = obj.optJSONObject("MessageList");
                int getid = object.getInt("code");
                String usertishi = object.optString("message");
                if (getid != 0) {
                    switch (getid) {
                        case 1000:
                            pDialog.dismiss();
                            Toast.makeText(context, "验证码已发送~", Toast.LENGTH_SHORT).show();
                            // 做一个倒计时
                            break;
                        default:
                            Toast.makeText(RegisterActivity.this, usertishi,
                                    Toast.LENGTH_SHORT).show();
                            // pDialog.dismiss();
                            pDialog.dismiss();
                            falg = true;
                            if (mytime != null) {
                                mytime.cancel();
                                timer = 180;
                                falg = true;
                                textView_user_verify.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView_user_verify
                                                .setBackgroundResource(R.drawable.getcode_select);
                                        textView_user_verify.setText("获取验证码");
                                    }
                                });
                            }
                            break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private static class MyHandler extends Handler {
        WeakReference<RegisterActivity> ref;

        public MyHandler(RegisterActivity activity) {
            this.ref = new WeakReference<RegisterActivity>(activity);
        }

        public void handleMessage(Message msg) {
            String result;
            RegisterActivity activity = ref.get();
            if (activity != null && activity.exit) {
                activity.pDialog1.dismiss();
                activity.pDialog.dismiss();
                switch (msg.what) {
                    case -2:
                        activity.falgaa = false;
                        Toast.makeText(activity,
                                "网络连接异常，请稍后再试~", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        activity.falgaa = false;
                        Toast.makeText(activity, "网络无法连接，请检查网络~",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        result = (String) msg.obj;
                        activity.parserResult(result);
                        break;
                    case 1:
                        result = (String) msg.obj;
                        activity.parserResult1(result);
                        break;
                    case 5:
                        result = (String) msg.obj;
                        if (result != null
                                && activity.editText_user_login_pwd != null) {
                            activity.editText_user_login_pwd.append(result);
                        }
                }
            }
            if (activity != null) {
                activity.flag = true;
                activity.button_user_login
                        .setBackgroundResource(R.drawable.btn_userlogin);
            }
        }
    }



    // 用户手机注册
    private String ReplyParams1(String username, String userpwd) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("phone", username);// 手机号
            jo.put("authKey", userpwd);// 短信验证码
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                NetUrl.PHSocket_GetPhoneRegCodeCheck, jo);
        return params;
    }

    // 用户注册返回值
    private void parserResult1(String result) {
        if (result != null) {
            try {
                JSONObject obj = new JSONObject(result);
                JSONObject object = obj.optJSONObject("MessageList");
                int getid = object.getInt("code");
                String usertishi = object.optString("message");
                if (getid != 0) {
                    switch (getid) {
                        case 1000:
                            Toast.makeText(RegisterActivity.this, "验证成功",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,
                                    SetPassWord.class);
                            intent.putExtra("PHONE", editText_user_login_name
                                    .getText().toString().trim());
                            //返回，setResult表示回调，2016表示如果是注册的话，返回到登录页则finish登录页
                            setResult(2016);
                            startActivityForResult(intent, 0);
                            break;
                        case 1001:
                            Toast.makeText(RegisterActivity.this, "验证码错误",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(RegisterActivity.this, usertishi,
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
