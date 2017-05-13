package com.example.citygeneral.utils.baidu;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.model.entity.City;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.utils.CityApp;
import com.example.citygeneral.utils.NetUrl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hp1 on 2017-05-10.
 */

public class BaiduDingwei extends BaseActivity implements
        View.OnClickListener {
    public LocationClient mLocationClient = null;
    public MyLocationListener myListener;
    private ImageView headImage;
    private Context context;
   private CityApp app;
    private TextView text1, text2, btnText, choiceText;
    private boolean locationed = false;
    private RelativeLayout btnRelay;
    private ProgressBar pb;

    @Override
    protected int getLayoutId() {
        return R.layout.main_location_layout;
    }

    @Override
    protected void initView() {
        context = this;
        app = (CityApp) getApplication();
        headImage = (ImageView) findViewById(R.id.head_image);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        btnText = (TextView) findViewById(R.id.btn_text);
        choiceText = (TextView) findViewById(R.id.choice_text);
        btnRelay = (RelativeLayout) findViewById(R.id.btn_relay);
        choiceText.setOnClickListener(this);
    }
    public void initTools() {

        myListener = new MyLocationListener() {
            public void setContent(String content) {
            }

            //  BDLocation类，封装了定位SDK的定位结果，通过该类用户可以获取error code，位置的坐标，精度半径等信息
            public void onReceiveLocation(BDLocation location) {
                if (location == null) {
                    setView();
                } else {
                    double lon = location.getLongitude(), lat = location
                            .getLatitude();
                    if (!locationed) {
//                        HttpParamsTool.Post(requestLocation(lon, lat),
//                                cityListHandler, context);
                    }
                }
            }

        };
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(10000);
        option.setAddrType("all");
        mLocationClient.setLocOption(option);
        mLocationClient.start();
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
    public void setView() {
        locationed = true;
        pb.setVisibility(View.INVISIBLE);
        /*if (data1.size() > 0) {
            text1.setText("当前定位城市(" + data1.get(0).getName() + ")");
            text2.setText("3秒后自动跳转");
            btnText.setText("直达" + data1.get(0).getCityName());
            btnRelay.setBackgroundResource(R.drawable.btn_userlogin);
            btnRelay.setOnClickListener(this);
            startAnim(text2);
        } else {
            if (mLocationClient != null) {
                mLocationClient.stop();
                mLocationClient = null;
            }*/
            myListener = null;
            text1.setText("系统定位失败");
            text2.setText("请检查您的网络或者手动选择城市");
            btnText.setText("定位失败");
            btnRelay.setBackgroundResource(R.drawable.btn_userlogin_shape3);
            btnRelay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    locationed = false;
                    pb.setVisibility(View.VISIBLE);
                    text1.setText("正在定位当前所在城市");
                    text2.setText("请稍后");
                    btnText.setText("正在定位城市");
                    btnRelay.setBackgroundResource(R.drawable.btn_userlogin);
                    initTools();
                }
            });
        }
    //}

    @Override
    public void onClick(View v) {

    }
    private String requestLocation(double longi, double lati) {
        String params = "";
        if (lati != 0 && longi != 0) {
            JSONObject jo = new JSONObject();
            try {
                jo.put("lat", lati);
                jo.put("lng", longi);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            params = Parameter
                    .createParam(NetUrl.METHOD_GetSiteMapCoord, jo);
        }
        return params;
    }
}
