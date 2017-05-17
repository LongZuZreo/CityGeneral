package com.example.citygeneral;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.ItemBean;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.utils.NetUrl;
import com.example.citygeneral.view.ShareActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

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
    private View view1;
    //详情
    private JSONObject dbJson = new JSONObject();
    private int id;
    private String url;

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
        view1 = LayoutInflater.from(this).inflate(R.layout.activity_webview_addhead, null);
       /* LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.mWebView_Linear);
        linearLayout.addView(view1);*/

    }

    @Override
    protected void initData() {


        Intent intent = getIntent();
        id = intent.getIntExtra("cityID", 0);

    }

    @Override
    protected void loadData() {
        BaseVolley.getInstance().doReplacePostString(NetUrl.APPURL, "bbb", RequestData(), new MyCallBack<ItemBean>() {
            @Override
            public void onDataChanged(ItemBean data) {
               url = data.getServerInfo().getNewsInfo().get(0).getBody();
                Log.d("DetailsActivity", "成功");
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                mWebView.getSettings().setBlockNetworkImage(true);
                mWebView.loadDataWithBaseURL("file:///android_asset/", url.replaceAll("file:///", ""),
                        "text/html", "utf-8", null);


            }

            @Override
            public void onErrorHappened(String errorMessage) {
                Log.d("DetailsActivity", "失败");
            }
        });
        //

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
    public String RequestData() {// 上方信息请求
        // TODO 数据请求
        JSONObject jo = new JSONObject();
        try {

            jo.put("siteID", 1);
            jo.put("newsID", id);

            jo.put("sourceType", 0);

            dbJson.put("sourceType", 1);

            dbJson.put("siteID", 1);
            dbJson.put("newsID", id);
            dbJson.put("method", NetUrl.PHSocket_GetCityNewsBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = Parameter.createnewsParam(
                NetUrl.PHSocket_GetCityNewsBody, jo);
        /*manager1.request(params, 0);*/
        return params;
    }
}
