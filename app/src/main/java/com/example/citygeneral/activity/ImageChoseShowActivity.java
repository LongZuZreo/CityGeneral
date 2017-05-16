package com.example.citygeneral.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.model.entity.ImageBean;
import com.example.citygeneral.utils.PublicUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageChoseShowActivity extends BaseActivity {

    // 控件
    private ViewPager viewPager;
    private ImageView back, check;
    private TextView tag, save;

    // 参数
    private List<View> dataView = new ArrayList<View>();
    private int maxNum = 0;
    // 工具
    private PublicUtils utils;

    private List<ImageBean> imageBeanList;

    @SuppressWarnings("unchecked")

    @Override
    protected int getLayoutId() {
        return R.layout.image_chose_show_activity;
    }

    @Override
    protected void initView() {
        List<String> list = getIntent().getBundleExtra("data").getStringArrayList("list");
        if (list == null || list.size() <= 0) {
            finish();
            return;
        }
        imageBeanList = new ArrayList<>();
        ImageBean bean;
        for (String s : list) {
            bean = new ImageBean();
            bean.setSelected(true);
            bean.setAfterImagePath(s);
            imageBeanList.add(bean);
        }

        maxNum = getIntent().getExtras().getInt("maxNum");
        init();
        set();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        back = (ImageView) findViewById(R.id.back);
        check = (ImageView) findViewById(R.id.check);
        tag = (TextView) findViewById(R.id.tag);
        save = (TextView) findViewById(R.id.save);

        utils = new PublicUtils(getApplicationContext());
    }

    private void set() {
        tag.setText("1/" + imageBeanList.size());
        save.setText("完成(" + imageBeanList.size() + "/" + maxNum + ")");
        for (ImageBean bean : imageBeanList) {// 遍历set去出里面的的Key
            String str = bean.getAfterImagePath();
            View view = LayoutInflater.from(getApplicationContext()).inflate(
                    R.layout.photo_show_layout, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            imageView.setImageBitmap(utils.readBitMap(str));
            dataView.add(view);
        }
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                List<String> backList = new ArrayList<>();
                for (int i = 0; i < imageBeanList.size(); i++) {
                    if (imageBeanList.get(i).isSelected()) {
                        backList.add(imageBeanList.get(i).getAfterImagePath());
                    }
                }
                Intent data = new Intent();
                data.putExtra("list", (Serializable) backList);
                setResult(20, data);
                finish();
            }
        });
        check.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = viewPager.getCurrentItem();
                ImageBean imageBean = imageBeanList.get(position);
                boolean b = imageBean.isSelected();
                imageBean.setSelected(!b);
                int a = 0;
                for (int i = 0; i < imageBeanList.size(); i++) {
                    if (imageBeanList.get(i).isSelected()) {
                        a++;
                    }
                }
                if (b) {
                    check.setImageResource(R.drawable.btn_choose_photo_n);
                    save.setText("完成" + "(" + a + "/" + maxNum + ")");
                } else {
                    check.setImageResource(R.drawable.btn_choose_photo_s);
                    save.setText("完成" + "(" + a + "/" + maxNum + ")");
                }

            }
        });
        viewPager.setAdapter(new Adapter());
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                tag.setText((arg0 + 1) + "/" + imageBeanList.size());
                boolean b = imageBeanList.get(arg0).isSelected();
                if (b) {
                    check.setImageResource(R.drawable.btn_choose_photo_s);
                } else {
                    check.setImageResource(R.drawable.btn_choose_photo_n);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    /**
     * VIEWPAGER适配器
     */
    private class Adapter extends PagerAdapter {

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return dataView.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(dataView.get(position));
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(dataView.get(position));
            return dataView.get(position);
        }
    }

}
