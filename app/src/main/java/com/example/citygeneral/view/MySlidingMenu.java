package com.example.citygeneral.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Toast;


import com.example.citygeneral.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by 张志远 on 2017/5/9.
 */

public class MySlidingMenu extends HorizontalScrollView {

    private LinearLayout linearLayout;

    private ViewGroup mMenu;

    private ViewGroup mContent;

    private int mScreenWidth;

    private int mMenuWidth;

    private int mRightPadding;

    private boolean once;

    private boolean isOpen;

    private Scroller mScroller;

    private VelocityTracker mVelocityTracker;


    public MySlidingMenu(Context context) {
        super(context);
    }

    public MySlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public MySlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       WindowManager windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics outMetrics=new DisplayMetrics();

        windowManager.getDefaultDisplay().getMetrics(outMetrics);

        mScreenWidth=outMetrics.widthPixels;

        Log.v("TAG","density"+outMetrics.density);

        Log.v("TAG","densityDpi"+outMetrics.densityDpi);

        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.MySlidingMenu,defStyleAttr,0);

        for (int i=0;i<a.getIndexCount();i++){

            int attr=a.getIndex(i);

            switch (attr){
                case R.styleable.MySlidingMenu_rightContentWidth:
                    int defValue=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics());

                    mRightPadding=a.getDimensionPixelSize(attr,defValue);


                    break;
                default:
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!once){
            linearLayout=(LinearLayout)getChildAt(0);
            mMenu=(ViewGroup)linearLayout.getChildAt(0);
            mContent=(ViewGroup)linearLayout.getChildAt(1);

            mMenuWidth=mMenu.getLayoutParams().width=mScreenWidth-mRightPadding;

            mContent.getLayoutParams().width=mScreenWidth;

            once=true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    int lastXIntercept;

    int lastYIntercept;

    boolean notIntercept;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);

        boolean intercept=false;
        int x= (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastXIntercept=x;
                lastYIntercept=y;
                intercept = false;
                if (x>10){
                    notIntercept =true;
                }else{
                    notIntercept =false;
                }
                Log.d("123", "x:" + x);
                break;
            case MotionEvent.ACTION_MOVE:
                final int deltaX=x-lastXIntercept;
                final int deltaY=y-lastYIntercept;
                Log.d("123","onInterceptTouchEvent: "+Math.abs(deltaX)+"----------------"+Math.abs(deltaY));
                if (Math.abs(deltaX)>Math.abs(deltaY)+5){
                    intercept=true;
                    if (!isOpen) {
                        if (notIntercept) {
                            return false;
                        }
                    }
                }else{
                    intercept=false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept=false;
                break;
        }

        Log.d("123", "intercept:" + intercept);
        return intercept;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                int scrollx=getScrollX();

                if (scrollx>=mMenuWidth/2){
                    this.scrollTo(mMenuWidth,0);
                    isOpen=false;
                    return true;
                }else{
                    this.scrollTo(0,0);
                    isOpen=true;
                    return true;
                }
        }
        return  super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed){
            this.scrollTo(mMenuWidth,0);


            isOpen=false;
        }
    }
    private void openMenu(){
        if (isOpen){
            return;
        }
        this.smoothScrollTo(0,0);
        isOpen=true;
    }
    private void closeMenu(){
        if (!isOpen){
            return;
        }
        this.smoothScrollTo(mMenuWidth,0);
        isOpen=false;
    }
    public void toogle(){
        if (isOpen)
            closeMenu();
        else
            openMenu();
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        ViewHelper.setTranslationX(mMenu,l*0.8f);

        float rate=l*1.0f/mMenuWidth;

        float menuScale=1.0f-0.2f*rate;

        float menuAlpha=menuScale;

        float contentScale=1.0f-0.2f * (1-rate);

        ViewHelper.setScaleX(mMenu,menuScale);

        ViewHelper.setScaleY(mMenu,     menuScale);

        ViewHelper.setAlpha(mMenu,menuAlpha);

        ViewHelper.setScaleX(mContent,contentScale);

        ViewHelper.setScaleY(mContent,contentScale);
    }

}
