package com.example.citygeneral.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.utils.PublicUtils;


//快速注册设置密码界面
public class SetPassWord extends BaseActivity implements OnClickListener {
    // UI组件
    private ImageView backText;
    private TextView titleText;
    private LinearLayout jumpLay;
    private EditText passEdit1, passEdit2;
    private Button confirmBtn;
    //    private MyProgressDialog smallDialog;
    // 工具
    private Context context;
    private PublicUtils utils;

    @Override
    protected int getLayoutId() {
        return R.layout.resetpasswrad;
    }

    public void initTools() {
        context = this;
    }

    public void initView() {
        backText = (ImageView) findViewById(R.id.btn_back_login);
        backText.setOnClickListener(this);
        titleText = (TextView) findViewById(R.id.tv_title_login);
        titleText.setVisibility(View.INVISIBLE);
        jumpLay = (LinearLayout) findViewById(R.id.jump_lay);
        jumpLay.setVisibility(View.VISIBLE);
        jumpLay.setOnClickListener(this);
        passEdit1 = (EditText) findViewById(R.id.editText_user_login_name);
        passEdit1.setHint("设置密码");
        passEdit2 = (EditText) findViewById(R.id.editText_user_login_pwd);
        passEdit2.setHint("确认密码");
        confirmBtn = (Button) findViewById(R.id.button_user_login);
        confirmBtn.setText("确定");
        confirmBtn.setOnClickListener(this);
//        smallDialog = new MyProgressDialog(context);
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

//    public void initHandler() {
//        handler = new PostHandler() {
//
//            @Override
//            public void onSuccess(String arg0) {
//                super.onSuccess(arg0);
//                if (utils.getConnectState(arg0, "设置成功")) {
//                    setListData2(arg0);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable arg0) {
//                // TODO Auto-generated method stub
//                Log.i("失败", "失败" + arg0);
//                utils.showConnectFail(arg0);
//            }
//
//            @Override
//            public void onFinish() {
//                // TODO Auto-generated method stub
//                super.onFinish();
//                smallDialog.dismiss();
//            }
//
//        };
//    }

    // ---------网络-------请求访问和数据解析
//    private String creatParams() {
//        JSONObject jo = new JSONObject();
//        try {
//            jo.put("siteID", utils.getCityId());
//            jo.put("userID", utils.getUserID());
//            jo.put("curPage", 1);
//            jo.put("pageSize", 50);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        final String params = Parameter.createnewsParam(
//                Constants.PHSocket_APP_GetUserPrivilegeList, jo);
//        return params;
//    }
//
//    private void setListData2(String jsonStr) {
//        if (jsonStr != null && !jsonStr.equals("")) {
//            try {
//                JSONObject jsonMain = new JSONObject(jsonStr);
//                JSONObject serverJson = new JSONObject(
//                        jsonMain.getString("ServerInfo"));
//
//                // 第一个列表的解析
//                JSONObject serverJson2 = new JSONObject(
//                        serverJson.getString("MyPrivilegeList"));
//
//                JSONArray serverJsonArray = new JSONArray(
//                        serverJson2.getString("MyPrivilegeList"));
//                TaskRightBean taskBean1;
//                for (int i = 0; i < serverJsonArray.length(); i++) {
//                    JSONObject json = serverJsonArray.optJSONObject(i);
//                    taskBean1 = new TaskRightBean();
//                    taskBean1
//                            .setPrivilegeID(json.get("PrivilegeID").toString());
//                    taskBean1.setName(json.get("Name").toString());
//                    taskBean1.setPImage(json.get("PImage").toString());
//                    taskBean1
//                            .setDescription(json.get("Description").toString());
//                    taskBean1.setHName(json.get("HName").toString());
//                }
//
//                startData();
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        } else {
//            CustomToast.showToastError2(context);
//        }
//
//    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_back_login:
                finish();
                break;
            case R.id.jump_lay:
                startData();
                break;
            case R.id.button_user_login:
                String pass1 = passEdit1.getText().toString()
                        .trim();
                String pass2 = passEdit2.getText().toString()
                        .trim();
                if (pass1.equals("") && TextUtils.isEmpty(pass1)) {
                    getFocus(passEdit1, "请输入密码~");
                } else if (pass2.equals("") && TextUtils.isEmpty(pass2)) {
                    getFocus(passEdit2, "请确认密码~");
                } else if (pass1.length() < 6) {
                    getFocus(passEdit1, "密码不能小于6位~");
                } else if (pass2.length() < 6) {
                    getFocus(passEdit2, "密码不能小于6位~");
                } else if (!pass1.equals(pass2)) {
                    Toast.makeText(context, "密码不一致~", Toast.LENGTH_SHORT).show();
                } else {
                    // 关闭软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            passEdit1.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
//                    smallDialog.show();
//                    HttpParamsTool.Post(creatParams(), handler, getApplicationContext());
                    startData();
                }
                break;
        }
    }

    //跳往信息完善页面
    public void startData() {
        setResult(300);
        finish();
        Intent intent = new Intent(context,
                RegistBackInfoActivity.class);
        intent.putExtra("PHONE", getIntent().getStringExtra("PHONE"));
        intent.putExtra("PASSWORD", passEdit1.getText().toString().trim());
        intent.putExtra("USERNAME", "");
        intent.putExtra("USERID", 0);
        startActivity(intent);
    }

    //获取焦点并且进行提示
    public void getFocus(EditText e, String str) {
        e.requestFocus();
        e.setFocusable(true);
        e.setFocusableInTouchMode(true);
        utils.getInputManager().showSoftInput(e,
                0);
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
