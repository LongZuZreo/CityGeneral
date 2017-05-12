package com.example.citygeneral.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.citygeneral.DetailsActivity;
import com.example.citygeneral.R;
import com.example.citygeneral.base.commonadapter.CommonBaseAdapter;
import com.example.citygeneral.base.commonadapter.CommonViewHolder;
import com.example.citygeneral.base.commonadapter.MyCommonBaseAdapter;
import com.example.citygeneral.model.entity.HeadLineBean;

import java.util.List;

/**
 * Created by ASUS on 2017/5/10.
 */

public class HeadLineAdapter extends MyCommonBaseAdapter<HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean.DataBean>{
    public HeadLineAdapter(Context context, List<HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean.DataBean> datas) {
        super(context, datas, R.layout.fragment_headline_listview);
    }


    @Override
    protected void display(CommonViewHolder holder, HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean.DataBean headLineBean) {
        holder.setText(R.id.mfragmentlistview_Title, headLineBean.getTitle());
        holder.setImage(R.id.mfragmentlistview_image, headLineBean.getImage());
        holder.setText(R.id.mfragmentlistview_msg, headLineBean.getVariable8());
        holder.setText(R.id.mfragmentlistview_number, headLineBean.getImageCount() + "");
        holder.getView(R.id.mfragmentlistview_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页
            Intent intent = new Intent(context, DetailsActivity.class);

                context.startActivity(intent);
            }
        });
    }
}
