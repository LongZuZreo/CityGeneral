package com.www.ccoocity.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.www.ccoocity.ui.R;
import com.www.ccoocity.widget.MyProgressDialog;

/**
 * 公用图片加载
 */
public class ImageLoaderTools {

    public static DisplayImageOptions.Builder getImageLoaderOptions() {
        return new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565);
    }

    /**
     * 通用的imageloader的options
     */
    private static DisplayImageOptions commonOptions;

    public static DisplayImageOptions getCommonOptions() {
        if (commonOptions == null) {
            synchronized (DisplayImageOptions.class) {
                if (commonOptions == null) {
                    commonOptions = getImageLoaderOptions()
                            .showImageOnFail(R.drawable.bg_loading)
                            .showImageForEmptyUri(R.drawable.bg_loading)
                            .showImageOnLoading(R.drawable.bg_loading)
                            .build();
                }
            }
        }

        return commonOptions;
    }

    /**
     * 通用图片加载
     *
     * @param uri
     * @param iv
     */
    public static void loadCommenImage(String uri, ImageView iv) {
        if (TextUtils.isEmpty(uri)) return;
        ImageLoader.getInstance().displayImage(uri, iv, getCommonOptions());
    }

    public static void loadCommenImage(Context context, String uri, ImageView iv) {
        if (TextUtils.isEmpty(uri)) return;
        loadImageByGlide(context, uri, iv, R.drawable.bg_loading);
    }

    public static void loadCommenImage(Context context, String uri, ImageView iv, int defaultImg) {
        if (TextUtils.isEmpty(uri)) return;
        loadImageByGlide(context, uri, iv, defaultImg);
    }

    public static void loadCommenImage(Fragment fm, String uri, ImageView iv, int defaultImg) {
        if (TextUtils.isEmpty(uri)) return;
        loadImageByGlide(fm, uri, iv, defaultImg);
    }

    /**
     * @param uri
     * @param iv
     * @param options
     */
    public static void loadCommenImage(String uri, ImageView iv, DisplayImageOptions options) {
        if (TextUtils.isEmpty(uri)) return;
        ImageLoader.getInstance().displayImage(uri, iv, options);
    }

    public static void loadFromRes(Context context,
                                   int resId,
                                   ImageView imageView) {
        Glide.with(context).load(resId).into(imageView);
    }

    /**
     * 加载图片显示渐变效果
     *
     * @param context
     * @param url          图片地址
     * @param imageView
     * @param defaultImage 默认显示的图片
     */
    public static void loadImageByGlide(Context context,
                                        String url,
                                        ImageView imageView,
                                        int defaultImage) {
        if (defaultImage == 0) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.shape_image_zhanwei)
                    .centerCrop()
                    .crossFade(500)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(url)
                    .placeholder(defaultImage)
                    .centerCrop()
                    .crossFade(500)
                    .into(imageView);
        }
    }

    /**
     *
     * @param context
     * @param url
     * @param imageView
     * @param defaultImage
     * @param errorImage
     */
    public static void loadImageByGlide(Context context,
                                        String url,
                                        ImageView imageView,
                                        int defaultImage, int errorImage) {
        if (defaultImage == 0) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.shape_image_zhanwei)
                    .error(errorImage)
                    .centerCrop()
                    .crossFade(500)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(url)
                    .placeholder(defaultImage)
                    .error(errorImage)
                    .centerCrop()
                    .crossFade(500)
                    .into(imageView);
        }
    }

    public static void loadImageByGlide(Fragment context,
                                        String url,
                                        ImageView imageView,
                                        int defaultImage) {
        if (defaultImage == 0) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.shape_image_zhanwei)
                    .centerCrop()
                    .crossFade(500)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(url)
                    .placeholder(defaultImage)
                    .centerCrop()
                    .crossFade(500)
                    .into(imageView);
        }
    }

    /**
     * 图片加载时显示进度条
     * @param context
     * @param url
     * @param imageView
     * @param dialog
     */
    public static void loadImageByGlideAndShowDialog(Context context,
                                        String url,
                                        final ImageView imageView,
                                        final MyProgressDialog dialog) {
        if (TextUtils.isEmpty(url))
            return;
        Glide.with(context)
                .load(url)
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView){
                    @Override
                    public void onStart() {
                        super.onStart();
                        if (imageView.getDrawable() == null && !dialog.isShowing()) {
                            dialog.show();
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        dialog.dismiss();
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        dialog.dismiss();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 加载gif
     *
     * @param context
     * @param gifUrl
     * @param imageView
     * @param errorImage
     */
    public static void loadGif(Context context,
                               String gifUrl,
                               ImageView imageView,
                               int errorImage) {
        Glide.with(context)
                .load(gifUrl)
                .asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public static void loadSDCard(Context context,
                                  String imageUrl,
                                  ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }

    public static void loadRoundImage(Context context,
                                      String url,
                                      ImageView imageView,
                                      int defaultImage,
                                      BitmapTransformation bitmapTransformation) {
        Glide.with(context)
                .load(url)
                .placeholder(defaultImage)
                .centerCrop()
                .crossFade(500)
                .transform(new GlideRoundTransform(context))
                .into(imageView);
    }
}
