package com.example.citygeneral.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.mywidget.ClipImageLayout;
import com.example.citygeneral.utils.BitmapCompress;
import com.example.citygeneral.utils.NetUrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

public class ImageCreateActivity extends BaseActivity {

    private String imagefile = "";
    Bitmap bitmap;
    boolean flag;
    private int width, heigh, degree;
    private RelativeLayout normal_view;
    private ImageView image;
    private LinearLayout first, second, thread, forth;
    private Button save;
    private ImageView back;
    private ProgressBar bar, bar2;
    private int page_location;
    private ClipImageLayout mClipImageLayout;//可多点触控放大缩小的view。
    File file;
    File isWebFile;
    private boolean isWebView;
    /**
     * 二次修改预览
     */
    private boolean preview;
    private int previewPos;
    private String previewUrl;

    /**
     * 相机跳转
     */
    private boolean xiangji = false;

    Handler hanler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            image.setImageBitmap(bitmap);
            bar.setVisibility(View.GONE);
            if (flag) {
                mClipImageLayout.setImageBitmap(bitmap);
                bar2.setVisibility(View.GONE);
            }
            first.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (bitmap == null) return;
                    width = bitmap.getWidth();
                    heigh = bitmap.getHeight();
                    Bitmap bit = Bitmap.createBitmap(heigh, width, Config.ARGB_8888);
                    Canvas cv = new Canvas(bit);

                    Matrix m = new Matrix();
//                    m.postScale(1, 1);
//                    m.postScale(1, 1);
                    m.postRotate(-90);
                    Bitmap new2 = Bitmap.createBitmap(bitmap, 0, 0, width, heigh, m, true);
                    cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()), new Rect(0, 0, heigh, width), null);
                    bitmap = bit;
                    if (flag) {
                        mClipImageLayout.setImageBitmap(bitmap);
                    } else {
                        image.setImageBitmap(bitmap);
                    }
                }
            });
            second.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    //友盟  BUG  NULLPOINT
                    if (null == bitmap) {
                        return;
                    }
                    width = bitmap.getWidth();
                    heigh = bitmap.getHeight();
                    Bitmap bit = Bitmap.createBitmap(heigh, width, Config.ARGB_8888);
                    Canvas cv = new Canvas(bit);
                    Matrix m = new Matrix();
                    m.postScale(1, 1);
                    m.postScale(1, 1);
                    m.postRotate(90);
                    Bitmap new2 = Bitmap.createBitmap(bitmap, 0, 0, width, heigh, m, true);
                    cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()), new Rect(0, 0, heigh, width), null);
                    bitmap = bit;
                    if (flag) {
                        mClipImageLayout.setImageBitmap(bitmap);
                    } else {
                        image.setImageBitmap(bitmap);
                    }
                }
            });
            thread.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    //友盟  BUG  NULLPOINT
                    if (null == bitmap) {
                        return;
                    }
                    width = bitmap.getWidth();
                    heigh = bitmap.getHeight();
                    Bitmap bit = Bitmap.createBitmap(width, heigh, Config.ARGB_8888);
                    Canvas cv = new Canvas(bit);
                    Matrix m = new Matrix();
                    //m.postScale(1, 1);
                    m.postScale(-1, 1);
                    m.postRotate(0);
                    Bitmap new2 = Bitmap.createBitmap(bitmap, 0, 0, width, heigh, m, true);
                    cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()), new Rect(0, 0, width, heigh), null);
                    bitmap = bit;
                    if (flag) {
                        mClipImageLayout.setImageBitmap(bitmap);
                    } else {

                        image.setImageBitmap(bitmap);
                    }
                }
            });
            forth.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    //友盟  BUG  NULLPOINT
                    if (null == bitmap) {
                        return;
                    }
                    width = bitmap.getWidth();
                    heigh = bitmap.getHeight();
                    Bitmap bit = Bitmap.createBitmap(width, heigh, Config.ARGB_8888);
                    Canvas cv = new Canvas(bit);
                    Matrix m = new Matrix();
                    //m.postScale(1, 1);
                    m.postScale(1, -1);
                    m.postRotate(0);
                    Bitmap new2 = Bitmap.createBitmap(bitmap, 0, 0, width, heigh, m, true);
                    cv.drawBitmap(new2, new Rect(0, 0, new2.getWidth(), new2.getHeight()), new Rect(0, 0, width, heigh), null);
                    bitmap = bit;
                    if (flag) {
                        mClipImageLayout.setImageBitmap(bitmap);
                    } else {
                        image.setImageBitmap(bitmap);
                    }

                }
            });
            save.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    File f;
                    Date time = new Date();
                    String strtime = DateFormat.format("yyyyMMddkkmmss",
                            time).toString();
                    if (isWebView) {
                        if (isWebFile.exists()) {
                        } else {
                            isWebFile.mkdirs();
                        }
                        f = new File(isWebFile, strtime + ".jpg");
                    } else {
                        if (file.exists()) {
                        } else {
                            file.mkdir();
                        }
                        f = new File(file, strtime + ".jpg");
                    }
                    FileOutputStream fileout;
                    try {
                        fileout = new FileOutputStream(f);
                        if (flag) {
                            bitmap = mClipImageLayout.clip();
                        }
                        bitmap.compress(Bitmap.CompressFormat.JPEG, NetUrl.imagesize, fileout);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //从照片修改页面进入发布页面
                    //判断空，我就不判断了。。。。
                    Intent data = new Intent();
                    data.putExtra("file", f.getPath());
                    //请求代码可以自己设置，这里设置成20
                   // LogUtils.showErrorLog(getClass(), "preview="+preview+"   previewPos" + previewPos+"  path="+f.getPath());
                    if (xiangji) {
                        setResult(203, data);
                    } else {
                        if (preview) {
                            data.putExtra("pos", previewPos);
                            data.putExtra("previewUrl", previewUrl);
                            setResult(201, data);
                        } else {
                            setResult(200, data);
                        }
                    }

                    //关闭掉这个Activity
                    finish();
                }
            });
            back.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    finish();
                }
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image_create);
        Intent intent = getIntent();
        if ((null != intent) && (0 != (intent.getIntExtra("page_location", 0)))) {
            page_location = intent.getIntExtra("page_location", 0);
        }

        if (getIntent() != null) {
            preview = getIntent().getBooleanExtra("preview", false);
            previewPos = getIntent().getIntExtra("previewPos", -1);
            previewUrl = getIntent().getStringExtra("previewUrl");
        }

        xiangji = intent.getBooleanExtra("xiangji", false);

        flag = intent.getBooleanExtra("flag", false);
        normal_view = (RelativeLayout) findViewById(R.id.normal_view);
        image = (ImageView) findViewById(R.id.shangchuan_image);
        first = (LinearLayout) findViewById(R.id.shangchuan_first);
        second = (LinearLayout) findViewById(R.id.shangchuan_second);
        thread = (LinearLayout) findViewById(R.id.shangchuan_thread);
        forth = (LinearLayout) findViewById(R.id.shangchuan_forth);
        save = (Button) findViewById(R.id.shangchuanSave);
        bar = (ProgressBar) findViewById(R.id.progressBar_imageCreate);
        back = (ImageView) findViewById(R.id.shangchuan_image_back);

        mClipImageLayout = (ClipImageLayout) findViewById(R.id.id_clipImageLayout);
        bar2 = (ProgressBar) findViewById(R.id.progressBar_imageCreate2);

        if (flag) {
            // 计算padding的px
            image.setVisibility(View.GONE);
            bar.setVisibility(View.GONE);
            normal_view.setVisibility(View.GONE);
            mClipImageLayout.setVisibility(View.VISIBLE);
            bar2.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.VISIBLE);
            bar.setVisibility(View.VISIBLE);
            normal_view.setVisibility(View.VISIBLE);
            mClipImageLayout.setVisibility(View.GONE);
            bar2.setVisibility(View.GONE);
        }

        isWebView = intent.getBooleanExtra("isWebView", false);
        imagefile = intent.getExtras().getString("imagefile");
        degree = intent.getExtras().getInt("degree");
        file = new File(Environment.getExternalStorageDirectory() + File.separator + "shangchuan");
        isWebFile = new File(Environment.getExternalStorageDirectory() + File.separator + "DCIM/Camera");

        new Thread() {
            public void run() {
                //友盟BUG NULL POINT
                try {
                    bitmap = BitmapCompress.getimage1(imagefile, degree);
                    hanler.sendEmptyMessage(0);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }.start();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

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

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
      //  MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    //    MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (hanler != null) hanler.removeCallbacksAndMessages(null);
    }
}
