package com.example.citygeneral.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.androidkun.PullToRefreshRecyclerView;
import com.example.citygeneral.AppApplication;
import com.example.citygeneral.R;
import com.example.citygeneral.adapter.HeadLineAdapter;
import com.example.citygeneral.adapter.HeadLineFragmentAdapter;
import com.example.citygeneral.base.BaseFragment;
import com.example.citygeneral.contract.HeadLineCtract;
import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.HeadLineBean;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.present.HeadLinePresentImp;
import com.example.citygeneral.utils.CityApp;
import com.example.citygeneral.utils.NetUrl;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/5/9.
 */

public class HeadLineFragment extends BaseFragment implements HeadLineCtract.View{

    private SlidingUpPanelLayout slidingUpPanelLayout;
    private PullToRefreshRecyclerView mPullToRefresh;
    private ViewPager mViewPager;
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_headline;
    }

    @Override
    protected void loadData() {
        presentImp.getAllList(NetUrl.APPURL,"aaa",listCreateParams());
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
        group = (ViewGroup) view.findViewById(R.id.mRoude_dot);
        /*mPullToRefresh = (PullToRefreshRecyclerView) view.findViewById(R.id.mPullToReFresh);*/
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);
        mListView = (ListView) view.findViewById(R.id.mListView);

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
    private String listCreateParams() {
        JSONObject jo = new JSONObject();
        try {
            jo.put("siteID", 1);//动态获取
            jo.put("page", 1);
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
}
