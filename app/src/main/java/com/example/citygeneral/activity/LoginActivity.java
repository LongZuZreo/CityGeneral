package com.example.citygeneral.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.City;
import com.example.citygeneral.model.entity.UserLogin;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.utils.CityApp;
import com.example.citygeneral.utils.NetUrl;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private String IP = CityApp.ip;
    private EditText editTextName;
    private EditText editTextPwd;
    private TextView loginBtn;
    private TextView registerBtn;
    private TextView forgetPwd;
    private ImageView uDl;
    private ImageView eyesImg;
    private ImageView pDl;
    private InputMethodManager imm;
    private ImageView backImg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        backImg = (ImageView) findViewById(R.id.btn_back_login);
        editTextName = (EditText) findViewById(R.id.editText_user_login_name);
        editTextPwd = (EditText) findViewById(R.id.editText_user_login_pwd);
        loginBtn = (TextView) findViewById(R.id.button_user_login);
        registerBtn = (TextView) findViewById(R.id.textView_user_register);
        forgetPwd = (TextView) findViewById(R.id.textView_user_forget_pwd);
        uDl = (ImageView) findViewById(R.id.username_delete);
        pDl = (ImageView) findViewById(R.id.password_delete);
        eyesImg = (ImageView) findViewById(R.id.eyes);
    }

    @Override
    protected void initData() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        backImg.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        forgetPwd.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextName.getText().toString().equals("")) {
                    uDl.setVisibility(View.GONE);
                } else {
                    uDl.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextPwd.getText().toString().equals("")) {
                    pDl.setVisibility(View.GONE);
                    eyesImg.setVisibility(View.GONE);
                } else {
                    pDl.setVisibility(View.VISIBLE);
                    eyesImg.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        eyesImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isPass) {
                    editTextPwd
                            .setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyesImg.setImageResource(R.drawable.ccoo_icon_eyes_open);

                    isPass = false;
                } else {
                    editTextPwd
                            .setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyesImg.setImageResource(R.drawable.ccoo_icon_eyes_close);
                    isPass = true;
                }
                CharSequence text = editTextPwd.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable) text;
                    Selection.setSelection(spanText, text.length());
                }
            }
        });

        uDl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editTextName.setText("");
            }
        });
        pDl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editTextPwd.setText("");
            }
        });

    }
    // password密码属性
    private boolean isPass = false;
    private boolean flag = true;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_login:
                finish();
                break;
            case R.id.button_user_login:

                String username = editTextName.getText().toString()
                        .trim();
                String userpasswrad = editTextPwd.getText().toString();
                if (username.equals("") && TextUtils.isEmpty(username)) {
                    editTextName.requestFocus();
                    editTextName.setFocusable(true);
                    editTextName.setFocusableInTouchMode(true);
                    imm.showSoftInput(editTextName, 0);
                    Toast.makeText(this, "请输入登录用户名~", Toast.LENGTH_SHORT).show();
                } else if (userpasswrad.equals("") && TextUtils.isEmpty(userpasswrad)) {
                    editTextPwd.requestFocus();
                    editTextPwd.setFocusable(true);
                    editTextPwd.setFocusableInTouchMode(true);
                    imm.showSoftInput(editTextPwd, 0);
                    Toast.makeText(this, "请输入密码~", Toast.LENGTH_SHORT).show();
                } else if (username.length() < 4) {
                    editTextName.requestFocus();
                    editTextName.setFocusable(true);
                    editTextName.setFocusableInTouchMode(true);
                    imm.showSoftInput(editTextName, 0);
                    Toast.makeText(this, "输入的用户名不正确~", Toast.LENGTH_SHORT).show();
                } else if (userpasswrad.length() < 6) {
                    editTextPwd.requestFocus();
                    editTextPwd.setFocusable(true);
                    editTextPwd.setFocusableInTouchMode(true);
                    imm.showSoftInput(editTextPwd, 0);
                    Toast.makeText(this, "输入的密码不正确~", Toast.LENGTH_SHORT).show();
                } else {
                    if (flag) {
                        flag = false;
                        loginBtn
                                .setBackgroundResource(R.drawable.btn_userlogin_shape3);

                        loginBtn.setText("登 录 中...");
                        // 关闭软键盘
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(
                                editTextPwd.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        String params = ReplyParams(username.replace(" ", ""),
                                userpasswrad.replace(" ", ""));
                        BaseVolley.getInstance().doPostString(NetUrl.APPURL, "",params, new MyCallBack<UserLogin>() {
                            @Override
                            public void onDataChanged(UserLogin data) {
                               finish();
                                Log.d("LoginActivity", "成功");
                            }

                            @Override
                            public void onErrorHappened(String errorMessage) {
                                Log.d("LoginActivity", "失败");
                            }
                        });

                    }
                }
                break;
            case R.id.textView_user_register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                //startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
    private String ReplyParams(String username, String userpwd) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("loginName", username);
            jo.put("userPWD", userpwd);
            jo.put("ip", IP);
            jo.put("post", "8000");
            jo.put("version", "android 4.3");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = Parameter.createnewsParam(
                NetUrl.METHOD_CheckUserLogin, jo);
        return params;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
