package com.example.citygeneral.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.citygeneral.DetailsActivity;
import com.example.citygeneral.R;
import com.example.citygeneral.base.commonadapter.CommonBaseAdapter;
import com.example.citygeneral.base.commonadapter.CommonViewHolder;
import com.example.citygeneral.base.commonadapter.MyCommonBaseAdapter;
import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.HeadLineBean;
import com.example.citygeneral.model.entity.ItemBean;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.utils.NetUrl;
import com.example.citygeneral.utils.PublicUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ASUS on 2017/5/10.
 */

public class HeadLineAdapter extends MyCommonBaseAdapter<HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean>{





    public HeadLineAdapter(Context context, List<HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean> datas) {
        super(context, datas, R.layout.fragment_headline_listview);
    }


    @Override
    protected void display(CommonViewHolder holder, final HeadLineBean.ServerInfoBean.HeadOInfoListBean.InfoBean headLineBean) {

        holder.setText(R.id.mfragmentlistview_Title, headLineBean.getData().get(0).getTitle());
        holder.setImage(R.id.mfragmentlistview_image, headLineBean.getData().get(0).getImage());
        holder.setText(R.id.mfragmentlistview_msg, headLineBean.getData().get(0).getVariable8());
        holder.setText(R.id.mfragmentlistview_number, headLineBean.getData().get(0).getImageCount() + "");


        holder.getView(R.id.mfragmentlistview_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theirID = headLineBean.getTheirID();
                //跳转到详情页
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("cityID",theirID);

                context.startActivity(intent);


            }
        });
    }

}
