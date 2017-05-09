package com.example.citygeneral.base;

/**
 * Created by 张志远 on 2017/4/6.
 */

public interface BaseView {
    /**
     * 显示进度条
     */
    void showProgress();

    /**
     * 取消进度条
     */
    void dismiss();

    /**
     * 加载失败
     */
    void loadError();

    /**
     *下拉刷新效果
     */
    void refresh();

    /**
     * 上拉加载效果
     */
    void loadMore();

    /**
     * 跳转到个人中心页
     */
    void toPersonCenter();

    /**
     *跳转到视频播放页
     */
    void toVideoPlay();

    /**
     * 标题页的切换
     */
    void changeTitleBar();
}
