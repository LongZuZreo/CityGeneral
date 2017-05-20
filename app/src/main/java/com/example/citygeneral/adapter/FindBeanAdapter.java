package com.example.citygeneral.adapter;

import android.content.Context;

import com.example.citygeneral.R;
import com.example.citygeneral.base.commonadapter.CommonViewHolder;
import com.example.citygeneral.base.commonadapter.MyCommonBaseAdapter;
import com.example.citygeneral.model.entity.FindBean;

import java.util.List;

/**
 * Created by ASUS on 2017/5/20.
 */

public class FindBeanAdapter extends MyCommonBaseAdapter<FindBean.ServerInfoBean>{

    public FindBeanAdapter(Context context, List<FindBean.ServerInfoBean> datas) {
        super(context, datas, R.layout.fragment_headline_gridview);
    }

    @Override
    protected void display(CommonViewHolder holder, FindBean.ServerInfoBean serverInfoBean) {
        holder.setImage(R.id.mFragment_head_gridview_image,serverInfoBean.getIcon());
        holder.setText(R.id.mFragment_head_gridview_title,serverInfoBean.getTitle());
    }
}
