package com.example.citygeneral.base.commonadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by 张志远 on 2017/3/14.
 */

public class CommonViewHolder {
    private View convertView;

    private Context context;

    private SparseArray<View> sparseArray;

    private CommonViewHolder(Context context, int layoutId) {
        this.convertView = LayoutInflater.from(context).inflate(layoutId, null);
        this.convertView.setTag(this);
        sparseArray = new SparseArray<>();
        this.context = context;
    }

    public static CommonViewHolder getInstance(Context context, View convertView, int layoutId) {
        if (convertView == null)
            return new CommonViewHolder(context, layoutId);
        return (CommonViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int viewId) {
        View view = sparseArray.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            sparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }

    public CommonViewHolder setText(int viewId, String str) {
        TextView textView = getView(viewId);
        textView.setText(str);
        return this;
    }

    public CommonViewHolder setImage(int viewId, String url) {
        ImageView imageView = getView(viewId);
        Glide.with(context).load(url).into(imageView);
        return this;
    }

}
