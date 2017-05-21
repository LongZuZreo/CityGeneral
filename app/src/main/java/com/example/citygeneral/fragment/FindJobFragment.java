package com.example.citygeneral.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseFragment;
import com.example.citygeneral.base.FragmentFlyer;
import com.example.citygeneral.utils.EventBusUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/5/20.
 */

public class FindJobFragment extends BaseFragment{

    private Activity activity;
    private LinearLayout findJob;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_findjob;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        findJob = (LinearLayout) view.findViewById(R.id.iFindJob);
    }

    @Override
    protected void initListener() {
        findJob.setOnClickListener(this);
    }

    @Override
    protected void show() {

    }

    @Override
    protected void hide() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iFindJob:
                FragmentFlyer.getInstance(this).
                        startFragment(EditJianLiFragment.class).build();
                EventBus.getDefault().post(new EventBusUtils
                        ("http://m.changwu.ccoo.cn/post/fabu/form_editjl.aspx?qq-pf-to=pcqq.group"));
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }
}
