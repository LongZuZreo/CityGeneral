package com.example.citygeneral;

import android.content.Intent;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.base.BaseFragment;
import com.example.citygeneral.base.FragmentFlyer;
import com.example.citygeneral.fragment.HeadLineFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static BaseFragment currentFragment;
    public static final int HOME_TITLE = 1;
    public static final int OTHER_TITLE = 2;
    public static final int PERSON_OR_INTERTACT=3;
    public static final int EDIT_TITLE=4;
    public static final int RIGHT_TYPE=5;

    private ImageView titleBackImage;
    private TextView tabTitle;
    private RadioButton homeRadio;
    private RadioButton eyeRadio;
    private RadioButton cultureRadio;
    private RadioButton liveRadio;
    private RadioButton liveChinaRadio;
    public RadioGroup radioGroup;

    int[] drawables=new int[]{
      R.drawable.main_left1,  R.drawable.main_left2, R.drawable.main_left4, R.drawable.main_left5, R.drawable.main_left6, R.drawable.main_left7, R.drawable.main_left8, R.drawable.main_left9, R.drawable.main_left10, R.drawable.main_left11, R.drawable.main_left12
    };
    private ListView listView;

    @Override
    protected int getLayoutId() {
        if(getSupportActionBar().isShowing()){
            getSupportActionBar().hide();
        }
        FragmentFlyer.getInstance(this).setLayoutId(R.id.mFram).isAddToBackStack(true).startFragment(HeadLineFragment.class).build();
        return R.layout.activity_main;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.mList);
        titleBackImage = (ImageView) findViewById(R.id.user_hand);
        tabTitle = (TextView) findViewById(R.id.tab_title);
        radioGroup = (RadioGroup) findViewById(R.id.fragment_radio_group);
        homeRadio = (RadioButton) findViewById(R.id.hotBtn);
        eyeRadio = (RadioButton) findViewById(R.id.naoBtn);
        cultureRadio = (RadioButton) findViewById(R.id.communityBtn);
        liveRadio = (RadioButton) findViewById(R.id.liveBtn);
        liveChinaRadio = (RadioButton) findViewById(R.id.findBtn);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initListener() {
        tabTitle.setOnClickListener(this);
        homeRadio.setOnClickListener(this);
        eyeRadio.setOnClickListener(this);
        cultureRadio.setOnClickListener(this);
        liveRadio.setOnClickListener(this);
        liveChinaRadio.setOnClickListener(this);
        titleBackImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hotBtn:
                FragmentFlyer.getInstance(this).setLayoutId(R.id.mFram).startFragment(HeadLineFragment.class).addToStack().build();
                break;
            case R.id.naoBtn:
                break;
            case R.id.communityBtn:
                break;
            case R.id.liveBtn:
                break;
            case R.id.findBtn:
                break;
            case R.id.user_hand:
                break;
            case R.id.tab_title:
                Intent intentCitys = new Intent(MainActivity.this,
                        CityChoiceActivity.class);
                startActivity(intentCitys);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(Process.myPid());
        System.exit(0);
    }
}

