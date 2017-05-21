package com.example.citygeneral.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.citygeneral.BuildConfig;
import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseFragment;
import com.example.citygeneral.utils.EventBusUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 2017/5/21.
 */

public class EditJianLiFragment extends BaseFragment{

    private Activity activity;
    private String urlStr;
    private WebView webView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_editjianli;
    }

    @Override
    protected void loadData() {
        webView.loadUrl
                ("http://m.changwu.ccoo.cn/post/fabu/form_editjl.aspx?qq-pf-to=pcqq.group");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        webView = (WebView) view.findViewById(R.id.webView);
        webView.requestFocusFromTouch();
        WebSettings webSettings =   webView .getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void show() {

    }

    @Override
    protected void hide() {

    }

    @Override
    public void onClick(View v) {

    }

    @Subscribe
    public void getEventMsg(EventBusUtils eventBusUtils){
        String url = eventBusUtils.getUrl();
        this.urlStr = url;
    }
}
