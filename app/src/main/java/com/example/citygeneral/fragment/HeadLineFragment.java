package com.example.citygeneral.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.citygeneral.view.ActivityUtils;
import com.example.citygeneral.AppApplication;
import com.example.citygeneral.R;
import com.example.citygeneral.adapter.HeadLineAdapter;
import com.example.citygeneral.adapter.HeadLineFragmentAdapter;
import com.example.citygeneral.base.BaseFragment;
import com.example.citygeneral.contract.HeadLineCtract;
import com.example.citygeneral.model.entity.HeadLineBean;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.present.HeadLinePresentImp;
import com.example.citygeneral.utils.NetUrl;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/5/9.
 */

public class HeadLineFragment extends BaseFragment implements HeadLineCtract.View,AbsListView.OnScrollListener{

    private SlidingUpPanelLayout slidingUpPanelLayout;
    private PullToRefreshRecyclerView mPullToRefresh;
    //图片的集合
    private List<ImageView> list;
    private HeadLineFragmentAdapter imageAdapter;
    //不同的图片
    private int[] imageID;
    //从1000张开始
    private int imageChage = 1000;
    // 圆点图片集合
    private ImageView[] tips;
    private int currentItem = 0; // 当前图片的索引号
    private ViewGroup group;
    private String[] defaultLength;
    private ListView mListView;
    private HeadLinePresentImp presentImp;
    //头条集合
    private List<HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean.DataBean> lineBeanList;
    private HeadLineAdapter lineAdapter;
    private HeadLineBean headLineBean;
    //上拉刷新
    private View footView;
    //判断是不是最后一个
    private boolean isLastMove;
    //判断是不是正在加载
    private boolean isLoadMove;
    private int page = 1;
    private ViewPager mViewPager;
    private RadioButton findWork;
    private RadioButton findHouse;
    private RadioButton findLive;
    private RadioButton citySay;
    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_headline;
    }

    @Override
    protected void loadData() {//
        presentImp.getAllList(NetUrl.APPURL,"aaa",listCreateParams(1));
    }

    @Override
    protected void initData() {
        presentImp = new HeadLinePresentImp(this);
        //banner图
        list = new ArrayList<>();
        getImageView();
        //listView集合
        lineBeanList = new ArrayList<>();
        lineAdapter = new HeadLineAdapter(getActivity(),lineBeanList);
        mListView.setAdapter(lineAdapter);


    }

    @Override
    protected void initView(View view) {
        slidingUpPanelLayout = (SlidingUpPanelLayout) view.findViewById(R.id.mHeadline);

        /*mPullToRefresh = (PullToRefreshRecyclerView) view.findViewById(R.id.mPullToReFresh);*/
       // mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);

        mListView = (ListView) view.findViewById(R.id.mListView);
        //上拉刷新的布局
        footView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_headline_listview_footview, null);
        mListView.addFooterView(footView);
        mListView.removeFooterView(footView);
        mListView.setOnScrollListener(this);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_headline_head, null);
        mViewPager = (ViewPager) headView.findViewById(R.id.mViewPager);
        findWork = (RadioButton) headView.findViewById(R.id.mfragmentlistview_findWord);
        findWork.setOnClickListener(this);
        findHouse = (RadioButton) headView.findViewById(R.id.mfragmentlistview_findHouse);
        findHouse.setOnClickListener(this);
        findLive = (RadioButton) headView.findViewById(R.id.mfragmentlistview_findLive);
        findLive.setOnClickListener(this);
        citySay = (RadioButton) headView.findViewById(R.id.mfragmentlistview_CitySay);
        citySay.setOnClickListener(this);
        group = (ViewGroup) headView.findViewById(R.id.mRoude_dot);
        mListView.addHeaderView(headView);
    }

    public void getImageView(){
        imageID = new int[]{R.drawable.my_main_advice1,R.drawable.my_main_advice2,R.drawable.my_main_advice3,R.drawable.my_main_advice4};
        for (int i = 0;i<imageID.length;i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(imageID[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(imageView);
        }
        defaultLength = new String[list.size()];
        dynamicAddition(defaultLength);
        imageAdapter = new HeadLineFragmentAdapter(list);
        mViewPager.setAdapter(imageAdapter);
        mViewPager.setCurrentItem(1000);
        handler.sendEmptyMessageDelayed(1, 2000);
    }
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    imageChage = imageChage + 1;
                    mViewPager.setCurrentItem(imageChage);
                    sendEmptyMessageDelayed(1, 2000);
                    break;
            }
        }
    };
    /**
     * 设置底部圆点滚动
     *
     * @创建日期 2016-1-18
     *
     * @参数 array 图片个数
     * @返回值 void
     *
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void dynamicAddition(String[] array) {
        // 动态的添加圆点图片设置
        tips = new ImageView[array.length];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.message_read);
            } else {
                tips[i].setBackgroundResource(R.drawable.message_noread);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }
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
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int oldPosition = 0;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageChage = position;
                int p = position % list.size();
                currentItem = p;
                tips[oldPosition]
                        .setBackgroundResource(R.drawable.message_noread);
                tips[p].setBackgroundResource(R.drawable.message_read);
                oldPosition = p;
                /*handler.sendMessage(Message.obtain(handler,
                        ImageHandler.MSG_PAGE_CHANGED, position, 0));*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean.DataBean dataBean = lineBeanList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void show() {

    }

    @Override
    protected void hide() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        AppApplication.getInstance().cancelAll("aaa");
    }

    //头条今日前的请求
    private String listCreateParams(int page) {

        JSONObject jo = new JSONObject();
        try {
            jo.put("siteID", 1);//动态获取
            jo.put("page", page);
            jo.put("pageSize", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                NetUrl.PHSocket_GetHeadlinesInfoO, jo);
        //listParam2 = Constants.PHSocket_GetHeadlinesInfoO + jo.toString();
        return params;
    }
    //头条的网络请求
    @Override
    public void loadHeadLine(HeadLineBean headLineBean) {
        this.headLineBean = headLineBean;
        List<HeadLineBean.ServerInfoBean.HeadOInfoListBean> headOInfoList = headLineBean.getServerInfo().getHeadOInfoList();
        for(HeadLineBean.ServerInfoBean.HeadOInfoListBean headLineBean1:headOInfoList){
            List<HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean> info = headLineBean1.getInfo();
            for(HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean infoBean:info){
                List<HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean.DataBean> data = infoBean.getData();
                lineBeanList.addAll(data);
                lineAdapter.notifyDataSetChanged();
                isLoadMove = false;
                if(mListView.getFooterViewsCount()!=0){
                    mListView.removeFooterView(footView);
                }
            }
        }


    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismiss() {

    }

    @Override
    public void loadError() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void toPersonCenter() {

    }

    @Override
    public void toVideoPlay() {

    }

    @Override
    public void changeTitleBar() {

    }
    //上拉加载的监听
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(SCROLL_STATE_IDLE ==scrollState &&isLastMove && !isLoadMove){
            isLoadMove = true;
            mListView.addFooterView(footView);

            presentImp.getAllList(NetUrl.APPURL,"aaa",listCreateParams(page++));

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem + visibleItemCount == totalItemCount){
        isLastMove = true;
        }else{
            isLastMove = false;
        }
    }

    private String creatParamsSign() {
        JSONObject jo = new JSONObject();
        try {
            jo.put("siteID", 2422);
            jo.put("userID", 28859905);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                NetUrl.PHSocket_GetUserSign, jo);
        return params;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mfragmentlistview_findWord:
                intent = new Intent(getActivity(), ActivityUtils.class);
                startActivity(intent);
                break;
            case R.id.mfragmentlistview_findHouse:
                intent = new Intent(getActivity(), ActivityUtils.class);
                startActivity(intent);
                break;
            case R.id.mfragmentlistview_findLive:
                intent = new Intent(getActivity(), ActivityUtils.class);
                startActivity(intent);
                break;
            case R.id.mfragmentlistview_CitySay:
                intent = new Intent(getActivity(), ActivityUtils.class);
                startActivity(intent);
                break;
        }
    }
}
