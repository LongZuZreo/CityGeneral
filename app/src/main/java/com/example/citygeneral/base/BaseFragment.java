package com.example.citygeneral.base;

;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp1 on 2017-05-08.
 */

public abstract class BaseFragment extends Fragment{
    protected Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
        initData();
        initListener();
        loadData();
    }


    public void setParams(Bundle bundle){
        this.bundle=bundle;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            show();
        }else{
            hide();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void loadData() ;

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract void initListener();

    protected abstract void show();

    protected abstract void hide();


}
