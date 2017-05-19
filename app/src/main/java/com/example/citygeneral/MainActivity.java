package com.example.citygeneral;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.base.BaseFragment;
import com.example.citygeneral.base.FragmentFlyer;
import com.example.citygeneral.fragment.BbsWeiduFm;
import com.example.citygeneral.fragment.CoverMainFragment;
import com.example.citygeneral.fragment.HeadLineFragment;
import com.example.citygeneral.fragment.HouseMainFragment;
import com.example.citygeneral.fragment.HuodongMainFragment;
import com.example.citygeneral.fragment.MesFragment;
import com.example.citygeneral.fragment.NewLifeFragment2;
import com.example.citygeneral.fragment.NewsMainFragment;
import com.example.citygeneral.fragment.PriviligeFragment;
import com.example.citygeneral.fragment.TiebaMainFragment;
import com.example.citygeneral.fragment.ZhaoPinFragment;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.utils.PopupWindowUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static BaseFragment currentFragment;
    public static final int HOME_TITLE = 1;
    public static final int OTHER_TITLE = 2;
    public static final int PERSON_OR_INTERTACT = 3;
    public static final int EDIT_TITLE = 4;
    public static final int RIGHT_TYPE = 5;
    LinearLayout leftMenuLayout;
    private List<Drawable> dataIcon = new ArrayList<Drawable>();// 下方菜单的图标集合
    private List<Drawable> dataIconAfter = new ArrayList<Drawable>();// 下方菜单的图标选中集合
    private List<TextView> dataTextView = new ArrayList<TextView>();// 下方菜单的文字集合
    private List<Fragment> dataFragments = new ArrayList<Fragment>();// 间盛放的fragment集合
    private List<String> dataTitle = new ArrayList<String>();// 上方显示的标题集合 首页 城市
    // 秀场 名店 发现
    private ImageView titleBackImage;
    private TextView tabTitle;
    private TextView homeRadio;
    private TextView eyeRadio;
    private TextView cultureRadio;
    private TextView liveRadio;
    private TextView liveChinaRadio;
    public LinearLayout radioGroup;
    private SharedPreferences sp;
    private ImageView addMore;
    private PopupWindow popupWindow;

    /**
     * 当前下降的是哪个item
     */
    private int downNum;
    private GridView mGridView;
    /**
     * 是否多次点击关闭按钮
     */
    private boolean isMultiple = false;

    private List<String> stringlList;

    @Override
    protected int getLayoutId() {
        if (getSupportActionBar().isShowing()) {
            getSupportActionBar().hide();
        }
        return R.layout.activity_main;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView() {
        addMore = (ImageView) findViewById(R.id.more);
        titleBackImage = (ImageView) findViewById(R.id.user_hand);
        tabTitle = (TextView) findViewById(R.id.tab_title);
        radioGroup = (LinearLayout) findViewById(R.id.fragment_radio_linear);
        homeRadio = (TextView) findViewById(R.id.hotBtn);
        eyeRadio = (TextView) findViewById(R.id.naoBtn);
        cultureRadio = (TextView) findViewById(R.id.communityBtn);
        liveRadio = (TextView) findViewById(R.id.liveBtn);
        liveChinaRadio = (TextView) findViewById(R.id.findBtn);
        dataTextView.add(homeRadio);
        dataTextView.add(eyeRadio);
        dataTextView.add(cultureRadio);
        dataTextView.add(liveRadio);
        dataTextView.add(liveChinaRadio);
        sp = getSharedPreferences("MAINMENU", Context.MODE_PRIVATE);
        BaseVolley baseVolley = BaseVolley.getInstance();
        leftMenuLayout= (LinearLayout) findViewById(R.id.left_menu_layout);
        leftMenuLayout.setOnClickListener(this);
        if (sp.getInt("MAINFRAGMENT", 0) == 0) {
            dataIcon.add(getResources().getDrawable(R.drawable .ccoo_icon_main));
            dataIconAfter.add(getResources().getDrawable(
                    R.drawable.ccoo_icon_main_after));
            dataTitle.add("首页");
        } else {
            dataIcon.add(getResources().getDrawable(R.drawable.toutiao));
            dataIconAfter.add(getResources().getDrawable(
                    R.drawable.toutiao_over));
            dataTitle.add("头条");
        }
        dataFragments.add(new HeadLineFragment());

        setSelect(sp.getInt("MAINMENU1",1),1);
        setSelect(sp.getInt("MAINMENU2",2),2);
        setSelect(sp.getInt("MAINMENU3",3),3);


        dataIcon.add(getResources().getDrawable(R.drawable.ccoo_icon_find));
        dataIconAfter.add(getResources().getDrawable(
                R.drawable.ccoo_icon_find_after));
        dataTitle.add("发现");
        dataFragments.add(new HeadLineFragment());
        setRightView();
        FragmentFlyer.getInstance(this).setLayoutId(R.id.mFram).startFragment(HeadLineFragment.class);
    }

    private void setRightView() {
        for (int i = 0; i < dataIconAfter.size(); i++) {
            if (i == 0) {// 默认第一个点击的 是首页，于是第一个的话设置为点击样式
                TextView textView = dataTextView.get(i);
                Drawable drawable = dataIconAfter.get(i);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable, null, null);
                textView.setText(dataTitle.get(i));
                textView.setTextColor(getResources().getColor(R.color.red_text));
            } else {
                TextView textView = dataTextView.get(i);
                Drawable drawable = dataIcon.get(i);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                        drawable.getMinimumHeight());
                textView.setCompoundDrawables(null, drawable, null, null);
                textView.setText(dataTitle.get(i));
                textView.setTextColor(getResources()
                        .getColor(R.color.color_999));
            }
        }

    }

    private void setSelect(int type, int position) {

        int s=0;

        if (position==1){
            s=sp.getInt("type1",0);
        }else if (position==2){
            s=sp.getInt("type2",0);
        }else{
            s=sp.getInt("type3",0);
        }
        switch (type){
            case 1: // 城市
                dataIcon.add(getResources().getDrawable(R.drawable.ccoo_icon_news));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_news_after));
                dataFragments.add(new NewsMainFragment());
                dataTitle.add("城事");
                break;
            case 2: // 贴吧
                dataIcon.add(getResources().getDrawable(R.drawable.ccoo_icon_tieba));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_tieba_after));
                TiebaMainFragment fragment = new TiebaMainFragment();
                Bundle bundle = new Bundle();
                switch (s) {
                    case 0://旧版闹闹
                        bundle.putString("version", "0");
                        dataFragments.add(fragment);
                        break;
                    case 1://新版闹闹
                        bundle.putString("version", "1");
                        dataFragments.add(fragment);
                        break;
                    default:
                        bundle.putString("version", "0");
                        dataFragments.add(fragment);
                        break;
                }
                dataTitle.add("闹闹");
                break;
            case 3:// 论坛
                dataIcon.add(getResources().getDrawable(R.drawable.ccoo_icon_bbs));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_bbs_after));
                BbsWeiduFm bbsFragment = new BbsWeiduFm();
                Bundle bbsBundle = new Bundle();
                bbsBundle.putBoolean("flag", true);
                switch (s) {
                    case 0://旧版社区
                        bbsBundle.putString("version", "0");
                        dataFragments.add(bbsFragment);
                        break;
                    case 1://新版社区
                        bbsBundle.putString("version", "1");
                        dataFragments.add(bbsFragment);
                        break;
                    default:
                        bbsBundle.putString("version", "0");
                        dataFragments.add(bbsFragment);
                        break;
                }
                dataTitle.add("社区");
                break;
            case 4:// 秀场
                dataIcon.add(getResources().getDrawable(R.drawable.ccoo_icon_show));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_show_after));
                dataFragments.add(new CoverMainFragment());
                dataTitle.add("秀场");
                break;
            case 5:// 活动
                dataIcon.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_huodong));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_huodong_after));
                dataFragments.add(new HuodongMainFragment());
                dataTitle.add("活动");
                break;
            case 6:// 分类生活
                dataIcon.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_shenghuo));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_shenghuo_after));
                switch (s) {
                    case 0://旧版生活
                        dataFragments.add(new NewLifeFragment2());
                        break;
                    case 1://新版生活1.0
                        dataFragments.add(new NewLifeFragment2());
                        break;
                    case 2://新版生活1.1
                        dataFragments.add(new NewLifeFragment2());
                        break;
                    default:
                        dataFragments.add(new NewLifeFragment2());
                        break;
                }
                dataTitle.add("生活");
                break;
            case 7:// 招聘
                dataIcon.add(getResources().getDrawable(R.drawable.ccoo_icon_job));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_job_after));
                dataFragments.add(new ZhaoPinFragment());
                dataTitle.add("招聘");
                break;
            case 8:// 房产
                dataIcon.add(getResources().getDrawable(R.drawable.ccoo_icon_house));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_house_after));
                dataFragments.add(new HouseMainFragment());
                dataTitle.add("房产");
                break;
            case 9:// 名店优惠
                dataIcon.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_mingdian));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_mingdian_after));
                dataFragments.add(new PriviligeFragment());
                dataTitle.add("名店");
                break;

            case 10://消息
                dataIcon.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_mes));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_mes_after));
                dataFragments.add(new MesFragment());
                dataTitle.add("消息");
                break;

            default:
                dataIcon.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_mes));
                dataIconAfter.add(getResources().getDrawable(
                        R.drawable.ccoo_icon_mes_after));
                dataFragments.add(new MesFragment());
                dataTitle.add("消息");
                break;
        }

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
        addMore.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.hotBtn:
                setColor(0);
                break;
            case R.id.naoBtn:
                setColor(1);
                break;
            case R.id.communityBtn:
                setColor(2);
                break;
            case R.id.liveBtn:
                setColor(3);
                break;
            case R.id.findBtn:
                setColor(4);
                break;
            case R.id.user_hand:
                break;
            case R.id.tab_title:
                Intent intentCitys = new Intent(MainActivity.this,
                        CityChoiceActivity.class);
                startActivity(intentCitys);
                break;
            case R.id.left_menu_layout:
                Intent intent=new Intent(this,UserInfoActivity.class);
                break;
            case R.id.more:

                Animation animation = AnimationUtils.loadAnimation(this,R.anim.ratate_addmore);
                //动画执行完后是否停留在执行完的状态
                animation.setFillAfter(true);
                addMore.startAnimation(animation);

                animation.setAnimationListener(new Animation.AnimationListener() {


                    private GridView mGridView;

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //弹出个popupWindow
                        View view = LayoutInflater.from(MainActivity.this ).inflate(R.layout.activity_main_popupwindow, null);
                        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        popupWindow.setContentView(view);
                        view.getBackground().setAlpha(200);
                        popupWindow.setOutsideTouchable(true);
                        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
                        popupWindow.setFocusable(true);
                        // popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
                        popupWindow.showAtLocation(findViewById(R.id.base_action_bar), Gravity.BOTTOM,0,0);
                        mGridView = (GridView) view.findViewById(R.id.mGridView);
                        ImageView mimageViewTop = (ImageView) view.findViewById(R.id.mGridView_top);
                        ImageView imageViewBottom = (ImageView) view.findViewById(R.id.mGridView_botton);
                        imageViewBottom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                downNum = stringlList.size()-1;
                                removeGridViewItem(mGridView.getChildAt(downNum),downNum);
                            }
                        });
                        List list = new ArrayList<>();
                        HashMap map = new HashMap();
                        map.put("image",R.drawable.my_main_gift);
                        map.put("name","发帖子");
                        list.add(map);
                        map = new HashMap();
                        map.put("image",R.drawable.my_main_gift);
                        map.put("name","爆个照");
                        list.add(map);
                        map = new HashMap();
                        map.put("image",R.drawable.my_main_gift);
                        map.put("name","小视频");
                        list.add(map);
                        map = new HashMap();
                        map.put("image",R.drawable.my_main_gift);
                        map.put("name","有奖爆料");
                        list.add(map);
                        map = new HashMap();
                        map.put("image",R.drawable.my_main_gift);
                        map.put("name","分类信息");
                        list.add(map);
                        map = new HashMap();
                        map.put("image",R.drawable.my_main_gift);
                        map.put("name","二维码");
                        list.add(map);
                        mGridView.setLayoutAnimation(getAnimalItem());

                        stringlList=new ArrayList<>();
                        for (int i = 0; i < 6; i++) {
                            stringlList.add(i+"");
                        }


                        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,list,R.layout.activity_main_popupwindow_gridview,new String[]{"image","name"},new int[]{R.id.mGridView_imagge,R.id.mGridView_name});

                        mGridView.setAdapter(adapter);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                break;


        }
    }

    private int lastPosition=0;
    private void setColor(int position) {
        TextView nowText = (TextView) radioGroup.getChildAt(position);// 点击状态的文字
        TextView lastText = (TextView) radioGroup.getChildAt(lastPosition);// 未点击状态的文字
        Drawable drawable = dataIconAfter.get(position);// 本次被点击的被点击状态的图标
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        Drawable drawableAfter = dataIcon.get(lastPosition);// 上一个被点击的未被点击状态图标
        drawableAfter.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        lastText.setCompoundDrawables(null, drawableAfter, null, null);
        nowText.setCompoundDrawables(null, drawable, null, null);
        lastPosition = position;
        FragmentFlyer.getInstance(this).setLayoutId(R.id.mFram).startFragment(dataFragments.get(position).getClass());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    //动态弹出动画
    public LayoutAnimationController getAnimalItem(){

        int duration = 400;
        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(duration);
        set.addAnimation(animation);
        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        animation.setDuration(duration);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        return controller;
    }
    /**
     * 移除gridView固定位置的一个item
     *
     * @param rootView
     *            gridView固定位置的View
     * @param position
     *            gridView固定位置的position
     */
    private void removeGridViewItem(final View rootView, final int position) {

        AnimationSet set = new AnimationSet(true);

        Animation   animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(400);
        set.addAnimation(animation);

        animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(400);
        set.addAnimation(animation);



        set.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                //隐藏已经退出的item
                rootView.setVisibility(View.INVISIBLE);
                downNum--;
                if (downNum == -1) {

                    isMultiple = false;
                    popupWindow.dismiss();
                    Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.back);
                    animation.setFillAfter(true);
                    addMore.startAnimation(animation1);
                    return;
                }
                removeGridViewItem(mGridView.getChildAt(downNum), downNum);
                if (BuildConfig.DEBUG) Log.d("MainActivity", "downNum:" + downNum);
            }
        });
        rootView.startAnimation(set);

    }

}

