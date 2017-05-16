package com.example.citygeneral;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.view.ShareActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by ASUS on 2017/5/11.
 */

public class DetailsActivity extends BaseActivity implements View.OnClickListener{

    private WebView mWebView;
    private ImageView mShareImage;
    private Button mSendBtn;
    private View view;
    private TextView mShare;
    private TextView mCollect;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        mWebView = (WebView) findViewById(R.id.mWebView);
        mShareImage = (ImageView) findViewById(R.id.mWebView_share);
        mSendBtn = (Button) findViewById(R.id.mWebView_sendBtn);
        //popupwond的布局
        view = LayoutInflater.from(this).inflate(R.layout.activity_details_popupwindow, null);
        mShare = (TextView) view.findViewById(R.id.mWebView_share_share_share);
        mCollect = (TextView) view.findViewById(R.id.mWebView_share_share_collect);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void loadData() {
        //加载url的数据
        mWebView.loadUrl("");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
    }

    @Override
    protected void initListener() {
        mSendBtn.setOnClickListener(this);
        mShareImage.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mCollect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mWebView_sendBtn:
                break;
            case R.id.mWebView_share:
                //弹出个popupWindow
                PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(view);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(view, Gravity.BOTTOM,310,1210);

                break;
            case R.id.mWebView_share_share_share:
               /* Intent intent = new Intent(this, ShareActivity.class);
                startActivity(intent);*/
                new ShareAction(this).withText("你好！")
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).open();

                break;
            case R.id.mWebView_share_share_collect:
                break;
        }
    }
    public int px2dpValue(int dpValue){
        int value = (int) (getResources().getDisplayMetrics().density*dpValue + 0.5f);
        return value;
    }
    UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {

        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
}
