package com.example.citygeneral.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.citygeneral.AppApplication;
import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseFragment;
import com.example.citygeneral.base.commonadapter.CommonViewHolder;
import com.example.citygeneral.base.commonadapter.MyCommonBaseAdapter;
import com.example.citygeneral.contract.FindContract;
import com.example.citygeneral.model.entity.CityAllEntity;
import com.example.citygeneral.model.entity.FindOneInfor;
import com.example.citygeneral.model.entity.FindTwoInfor;
import com.example.citygeneral.model.entity.FirstFindBean;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.present.FindPresenterImp;
import com.example.citygeneral.utils.NetUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class FindFragment extends BaseFragment implements FindContract.FindView{

    private ScrollView scrollView;
    private LinearLayout parentView;
    private FindPresenterImp findPresenterImp;
    private FindOneInfor findOneInfor;
    private List<FindOneInfor> findOneInfors;
    private LayoutInflater inflater;
    private FindTwoInfor findTwoInfor;
    private List<FindTwoInfor> findTwoInfors;

    @Override
    protected int getLayoutId() {
        return R.layout.find_parent_layout;
    }

    @Override
    protected void initData() {
        findOneInfors = new ArrayList<>();
        findTwoInfors = new ArrayList<>();
        findPresenterImp = new FindPresenterImp(this);
    }

    @Override
    protected void initView(View view) {
        inflater = LayoutInflater.from(getActivity());
        scrollView = (ScrollView) view.findViewById(R.id.contentView);
        parentView = (LinearLayout) view.findViewById(R.id.parentView);
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

    @Override
    public void loadData() {
        findPresenterImp.getData(NetUrl.APPURL,"find",createParams());
    }

    private JSONObject dbJson = new JSONObject();

    /**
     * 参数拼接
     */
    private String createParams() {
        JSONObject jo = new JSONObject();
        try {
            jo.put("siteID", 2422);
            jo.put("type", 3);

            dbJson.put("siteID", 2422);
            dbJson.put("type", 3);
            dbJson.put("method", NetUrl.PHSocket_GetAppSetInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String params = Parameter.createnewsParam(
                NetUrl.PHSocket_GetAppSetInfo, jo);
        return params;
    }

    @Override
    public void loadFirstData(FirstFindBean firstFindBean) {
        List<FirstFindBean.ServerInfoBean> serverInfo = firstFindBean.getServerInfo();
        for(FirstFindBean.ServerInfoBean serverInfoBean:serverInfo){
            parserResultInfo(serverInfoBean);
            setData();
        }

    }

    // 数据设置
    private void setData() {
        for (int i = 0; i < findOneInfors.size(); i++) {
            int type = findOneInfors.get(i).getPlateNo();
            switch (type) {
                case 1:
                    setTypeOne(findOneInfors.get(i));
                    break;
                case 2:
                    //setTypeTwo(findOneInfors.get(i));
                    break;
                case 3:
                    //setTypeThree(findOneInfors.get(i));
                    break;
                case 4:
                    //setTypeFour(findOneInfors.get(i));
                    break;
                case 5:
                    //setTypeFive(findOneInfors.get(i));
                    break;
                case 6:
                    //setTypeSix(findOneInfors.get(i));
                    break;
                default:
                    break;
            }
        }
    }

    // 第一类型设置
    private void setTypeOne(FindOneInfor info) {
        View view = inflater.inflate(R.layout.find_one_type, null);
        TextView textView = (TextView) view.findViewById(R.id.title_Text);
        textView.setText(info.getPlateName());

        View convertView = null;
        LinearLayout bgLayout;
        ImageView imageView;
        TextView title;
        TextView content;
        for (int i = 0; i < findOneInfor.getList().size(); i++) {
            if (i % 2 == 0) {//左边的
                convertView = view.findViewById(R.id.left_one);
                imageView = (ImageView) convertView
                        .findViewById(R.id.cityThing);
                title = (TextView) convertView
                        .findViewById(R.id.titleText);
                content = (TextView) convertView
                        .findViewById(R.id.title_detail);
            } else {//右边的
                convertView = view.findViewById(R.id.right_one);
                imageView = (ImageView) convertView
                        .findViewById(R.id.activeImg);
                title = (TextView) convertView
                        .findViewById(R.id.titleText1);
                content = (TextView) convertView
                        .findViewById(R.id.title_detail1);
            }
            FindTwoInfor findTwoInfor = findOneInfor.getList().get(i);
            Glide.with(this).load(findTwoInfor.getChannelUrl()).into(imageView);
            title.setText(findTwoInfor.getChannelName());
            content.setText(findTwoInfor.getChannelMemo());

            if (i == 0) {
                convertView.setBackgroundColor(getResources().getColor(
                        R.color.found_bg1));
            } else if (i == 1) {
                convertView.setBackgroundColor(getResources().getColor(
                        R.color.found_bg2));
            } else if (i == 2) {
                convertView.setBackgroundColor(getResources().getColor(
                        R.color.found_bg3));
            } else {
                convertView.setBackgroundColor(getResources().getColor(
                        R.color.found_bg1));
            }
        }
        parentView.addView(view);
        }

    //解析数据
    private List<FindOneInfor> parserResultInfo(FirstFindBean.ServerInfoBean serverInfoBean){
        if(serverInfoBean != null){
            for(FirstFindBean.ServerInfoBean.ChannelListBean channelListBean : serverInfoBean.getChannelList()){
                String channelName = channelListBean.getChannelName();
                String channelMemo = channelListBean.getChannelMemo();
                String channelImg = channelListBean.getChannelImg();
                int channelType = channelListBean.getChannelType();
                String channelUrl = channelListBean.getChannelUrl();
                String channelInfo = channelListBean.getChannelInfo();
                String channelTitle = channelListBean.getChannelTitle();
                String channelData = channelListBean.getChannelData();

                FindTwoInfor findTwoInfor =
                        new FindTwoInfor(channelName,channelMemo,channelImg,channelType+"",
                                channelUrl,channelInfo,channelTitle,channelData);

                findTwoInfors.add(findTwoInfor);
            }
            FindOneInfor findOneInfor =
                    new FindOneInfor(serverInfoBean.getPlateNo(),serverInfoBean.getPlateName(),findTwoInfors);
        }
        findOneInfors.add(findOneInfor);

        return findOneInfors;
    }
}
