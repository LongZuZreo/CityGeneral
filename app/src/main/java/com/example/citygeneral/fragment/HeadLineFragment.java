package com.example.citygeneral.fragment;

import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.citygeneral.AppApplication;
import com.example.citygeneral.R;
import com.example.citygeneral.adapter.HeadLineFragmentAdapter;
import com.example.citygeneral.base.BaseFragment;
import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.HeadLineBean;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.utils.NetUrl;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/5/9.
 */

public class HeadLineFragment extends BaseFragment{

    private SlidingUpPanelLayout slidingUpPanelLayout;
    private PullToRefreshRecyclerView mPullToRefresh;
    private ViewPager mViewPager;

    private List<ImageView> list;
    private HeadLineFragmentAdapter imageAdapter;
    private int[] imageID;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_headline;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        slidingUpPanelLayout = (SlidingUpPanelLayout) view.findViewById(R.id.mHeadline);
        /*mPullToRefresh = (PullToRefreshRecyclerView) view.findViewById(R.id.mPullToReFresh);*/
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);
        list = new ArrayList<>();
        imageID = new int[]{R.drawable.my_main_advice1,R.drawable.my_main_advice2,R.drawable.my_main_advice3,R.drawable.my_main_advice4};
        for (int i = 0;i<imageID.length;i++){
        ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(imageID[i]);
            list.add(imageView);
        }
        imageAdapter = new HeadLineFragmentAdapter(list);
        mViewPager.setAdapter(imageAdapter);
    }

    @Override
    protected void initListener() {
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });
    }

    @Override
    protected void show() {

    }

    @Override
    protected void hide() {

    }
    private void sendHttp(){
        BaseVolley.getInstance().doPostString(NetUrl.APPURL, "aaa", "", new MyCallBack<HeadLineBean>() {
            @Override
            public void onDataChanged(HeadLineBean data) {

            }

            @Override
            public void onErrorHappened(String errorMessage) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppApplication.getInstance().cancelAll("aaa");
    }

    //头条今日前的请求
    /*private String listCreateParams() {
        JSONObject jo = new JSONObject();
        try {
            jo.put("siteID", utils.getCityId());
            jo.put("page", 1);
            jo.put("pageSize", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                Constants.PHSocket_GetHeadlinesInfoO, jo);
        listParam2 = Constants.PHSocket_GetHeadlinesInfoO + jo.toString();
        return params;
    }*/
}
