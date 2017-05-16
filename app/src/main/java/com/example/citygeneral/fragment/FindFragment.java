package com.example.citygeneral.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseFragment;

/**
 * Created by Administrator on 2017/5/12.
 */

public class FindFragment extends BaseFragment {

    private ScrollView scrollView;
    private LinearLayout parentView;

    @Override
    protected int getLayoutId() {
        return R.layout.find_parent_layout;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
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
}
