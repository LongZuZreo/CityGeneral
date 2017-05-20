package com.example.citygeneral.adapter;

import android.content.Context;
import android.widget.GridView;

import com.example.citygeneral.R;
import com.example.citygeneral.base.commonadapter.CommonBaseAdapter;
import com.example.citygeneral.base.commonadapter.CommonViewHolder;
import com.example.citygeneral.base.commonadapter.MyCommonBaseAdapter;
import com.example.citygeneral.model.entity.GridViewBean;

import java.util.List;

/**
 * Created by ASUS on 2017/5/20.
 */

public class MainGridViewAdapter extends MyCommonBaseAdapter<GridViewBean.ServerInfoBean.ConfigDataBean>{


    public MainGridViewAdapter(Context context, List<GridViewBean.ServerInfoBean.ConfigDataBean> datas) {
        super(context,datas, R.layout.activity_main_popupwindow_gridview);
    }

    @Override
    protected void display(CommonViewHolder holder, GridViewBean.ServerInfoBean.ConfigDataBean gridViewBean) {
        holder.setText(R.id.mGridView_name,gridViewBean.getTitle());
        holder.setImage(R.id.mGridView_imagge,gridViewBean.getIcon());
    }
}
