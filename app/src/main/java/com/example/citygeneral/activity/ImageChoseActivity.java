package com.example.citygeneral.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.citygeneral.R;
import com.example.citygeneral.base.BaseActivity;
import com.example.citygeneral.model.entity.GalleryEntity;
import com.example.citygeneral.mywidget.MyProgressDialog;
import com.example.citygeneral.utils.BitmapCompress;
import com.example.citygeneral.utils.CityApp;
import com.example.citygeneral.utils.ImageFileTool;
import com.example.citygeneral.utils.PublicUtils;
import com.example.citygeneral.utils.UploadFileTools;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 新增onActivityResult的相机返回照片的处理
 * 在相册gridview中判断第一个为相机图标。
 *
 * @author Administrator
 */
public class ImageChoseActivity extends BaseActivity {
    // 控件
    private GridView gridview; // 图片列表
    private ImageView back;
    private RelativeLayout chose;
    private TextView save, chose1;
    private LinearLayout addLayout; // 底部添加布局
    private View addview; // 新加view
    private HorizontalScrollView scrollView;
    private View transView;
    private PopupWindow popupWindow;
    public static final int PHOTO_CAMERA = 0;
    private String imagename = "";

    // 参数
    private List<GalleryEntity> list; // 相册集合
    private SparseArray<Boolean> mapchecked;

    private List<String> selectedImageList = new ArrayList<>();
    private List<String> listImagePath = new ArrayList<String>(); // 图片集合
    private List<String> listImagePath2 = new ArrayList<String>();//相机照出来的图片集合
    private Map<String, String> imageMap;// 已选照片的集合
    private int number; // 最大图片选择数量
    private int num = 0; // 已选图片数量

    /**
     * true  论坛贴吧调转
     */
    private boolean from_ = false;

    /**
     * 1网页调取
     */
    private int type_ = 0;

    private UploadFileTools uploadFileTools;
    private int photoType_ = 0;

    // 工具
    private PublicUtils utils;
    private MyProgressDialog dialog;
    private ImageChoseAdapter adapter;
    private ListAdapter listadapter;
    private static int location;

    private boolean flag;
    private boolean isCameraBack;
    private String path;

    private int px_60 = 60;
    private int px_48 = 48;

    // 图片压缩接收
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            dialog.closeProgressDialog();
            String str = msg.getData().getString("file");
            if (null == str) {
                num--;
                Toast.makeText(ImageChoseActivity.this, "您选择的图片不存在~", Toast.LENGTH_SHORT).show();
                listImagePath.remove(location);
                adapter.notifyDataSetChanged();

            } else {
                String key = msg.getData().getString("key");
                if (!selectedImageList.contains(str))
                    selectedImageList.add(str);
                imageMap.put(key, str);
                // 数量添加
                save.setText("完成(" + num + "/" + number + ")");
                // 底部布局添加
                addLayout.removeViewAt(addLayout.getChildCount() - 1);
                View view = LayoutInflater.from(getApplicationContext()).inflate(
                        R.layout.tieba_aite_item_imageview, null);
                view.setTag(key);
                ImageView image = (ImageView) view.findViewById(R.id.imageview);
                image.setImageBitmap(utils.getBitmapByWidth(str, utils.dip2px(45),
                        1));
                view.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ImageChoseActivity.this,
                                ImageChoseShowActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("map", (Serializable) imageMap);
                        bundle.putSerializable("list", (Serializable) selectedImageList);
                        intent.putExtra("data", bundle);
                        intent.putExtra("maxNum", number);
                        startActivityForResult(intent, 0);
                    }
                });
                addLayout.addView(view);
                addLayout.addView(addview);
                scrollView.scrollTo(scrollView.getChildAt(0).getRight(), 0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_imagechose);

        px_60 = PublicUtils.dip2px(this, 60);
        px_48 = PublicUtils.dip2px(this, 48);

        Intent intent = getIntent();
        flag = intent.getBooleanExtra("flag", false);
        number = intent.getIntExtra("number", 9);

        from_ = intent.getBooleanExtra("from", false);
        type_ = intent.getIntExtra("type", 0);
        photoType_ = intent.getIntExtra("photoType", 0);

        if (number > 9) {
            number = 9;
        }
        init();
        set();
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

    private void init() {
        // 初始化控件
        gridview = (GridView) findViewById(R.id.gridview);
        chose = (RelativeLayout) findViewById(R.id.image_chose);
        chose1 = (TextView) findViewById(R.id.image_chose1);
        save = (TextView) findViewById(R.id.image_save);
        back = (ImageView) findViewById(R.id.image_back);
        addLayout = (LinearLayout) findViewById(R.id.add_layout);
        addview = LayoutInflater.from(this).inflate(
                R.layout.tieba_aite_item_imageview, null);
        scrollView = (HorizontalScrollView) findViewById(R.id.scrollview);
        transView = findViewById(R.id.trans_view);
        // 数据获取
        getData();
        // 工具
        utils = new PublicUtils(this);
        dialog = new MyProgressDialog(this);
        dialog.setCancelable(false);

        imageMap = new HashMap<String, String>();
        listadapter = new ListAdapter();
        adapter = new ImageChoseAdapter();
    }

    private void set() {
        save.setText("完成(" + num + "/" + number + ")");
        addLayout.addView(addview);

        back.setOnClickListener(new ImageChoseClick());
        chose.setOnClickListener(new ImageChoseClick());

        save.setOnClickListener(new ImageChoseClick());

        gridview.setAdapter(adapter);
        //gridview.setOnScrollListener(new MyScrollListener());
        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                if ((position == 0) && (num < number)) {
                    Log.i("王伟数据", "点击的位置" + position);
                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);

                    imagename = android.text.format.DateFormat
                            .format("yyyyMMddkkmmss",
                                    new Date()).toString();

                    if (!Environment.getExternalStorageState()
                            .equals(Environment.MEDIA_MOUNTED)) {
                            Toast.makeText(ImageChoseActivity.this,
                                "SD卡不存在", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (flag) {
                        File file = new File(Environment
                                .getExternalStorageDirectory()
                                .getPath()
                                + File.separator + "Camera");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        String p = Environment
                                .getExternalStorageDirectory()
                                .getPath()
                                + File.separator
                                + "Camera"
                                + File.separator
                                + imagename
                                + ".jpg";
                        CityApp.imagePath = p;
                        intent.putExtra(
                                MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(new File(p)));
                        startActivityForResult(intent, PHOTO_CAMERA);
                    } else {
                        File file = new File(Environment
                                .getExternalStorageDirectory()
                                .getPath()
                                + File.separator + "DCIM/Camera");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        String p = Environment
                                .getExternalStorageDirectory()
                                .getPath()
                                + File.separator
                                + "DCIM/Camera"
                                + File.separator
                                + imagename
                                + ".jpg";
                        CityApp.imagePath = p;
                        intent.putExtra(
                                MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(new File(p)));
                        startActivityForResult(intent, PHOTO_CAMERA);
                    }
                } else {
                    ImageView image = (ImageView) view.findViewById(R.id.image_chose);
                    CheckBox check = (CheckBox) view.findViewById(R.id.check);
                    LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout_view);
                    image.setDrawingCacheEnabled(true);
                    Bitmap pic = image.getDrawingCache();
                    location = position - 1;
                    if (null == pic) {
                        Toast.makeText(ImageChoseActivity.this, "您选择的图片为错误图片~", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!check.isChecked()) {
                            check.setChecked(true);
                            if (num == number) {
                                Toast.makeText(ImageChoseActivity.this, "最多可选择" + number + "张图片",
                                        Toast.LENGTH_SHORT).show();
                                check.setChecked(false);
                                return;
                            }
                            num++;
                            if (from_) {
                                number--;
                            }
                            dialog.showProgressDialog();
                            layout.setVisibility(View.VISIBLE);
                            new Thread() {
                                public void run() {
                                    //String str = BitmapCompress.getimage2(listImagePath.get(position));
                                    String str = BitmapCompress
                                            .getimage3(listImagePath.get(position - 1),
                                                    PublicUtils.getBitmapDegree(listImagePath.get(position - 1)));
                                    Message msg = new Message();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("file", str);//压缩后的图片地址
                                    bundle.putString("key",
                                            listImagePath.get(position - 1));//压缩前的图片地址
                                    msg.setData(bundle);
                                    handler.sendMessage(msg);//发送选中照片的信息
                                }
                            }.start();
                        } else {
                            check.setChecked(false);
                            num--;
                            String k = imageMap.get(listImagePath.get(position - 1));
                            if (!TextUtils.isEmpty(k)) {
                                if (selectedImageList.contains(k))
                                    selectedImageList.remove(k);
                            }
                            imageMap.remove(listImagePath.get(position - 1));
                            layout.setVisibility(View.GONE);
                            for (int i = 0; i < addLayout.getChildCount(); i++) {
                                View view2 = addLayout.getChildAt(i);
                                if (view2.getTag() != null
                                        && view2.getTag().equals(
                                        listImagePath.get(position - 1))) {
                                    addLayout.removeViewAt(i);
                                }
                            }
                            save.setText("完成(" + num + "/" + number + ")");
                        }
                    }

                    image.setDrawingCacheEnabled(false);
                }
            }
        });

    }

    // 图片数据获取
    private void getData() {
        List<File> _xiangjiList = new ArrayList<>();
        list = ImageFileTool.queryGallery(this);
        for (GalleryEntity g : list) { // 移除ccoo文件夹
            if (g.getPath().contains("/ccoo/")) {
                list.remove(g);
                break;
            }
        }
        for (GalleryEntity g : list) {
            if (g.getPath().contains("/shangchuan/")) {// 移除shuangchuan文件夹
                list.remove(g);
                break;
            }
        }
        for (GalleryEntity g : list) {
            if (g.getName().equalsIgnoreCase("weixin")) {
                list.remove(g);
                g.setName("微信");
                list.add(0, g);
                break;
            }
        }
        for (GalleryEntity g : list) {
            if (g.getPath().contains("/Camera/")) {
                list.remove(g);
                g.setName("相机胶卷");
                list.add(0, g);
                break;
            }
        }

        mapchecked = new SparseArray<>();

        for (int i = 0; i < list.size(); i++) {
            mapchecked.put(i, false);
        }

        List<String> camera = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            String str = list.get(i).getPath();
            int j = str.lastIndexOf("/");
            File file = new File(str.substring(0, j));

            File[] array = file.listFiles();
            if (array != null) {
                if (str.contains("/Camera/")) {
                    chose1.setText("相机胶卷");
                    if (str.contains("DCIM")) {
                        for (File anArray : array) {
//                            String name = anArray.toString();
//                            if (isPic(name)) {
//                                camera.add(0, name);
//                            }
                            if (isPic(anArray)) {
                                _xiangjiList.add(anArray);
                            }
                        }
                    } else {
                        for (File anArray : array) {
//                            String name = anArray.toString();
//                            if (isPic(name)) {
//                                listImagePath2.add(0, name);
//                            }
                            if (isPic(anArray)) {
                                _xiangjiList.add(anArray);
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(_xiangjiList, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                if (f1.lastModified() > f2.lastModified()) return -1;
                if (f1.lastModified() < f2.lastModified()) return 1;
                return 0;
            }
        });
        for (File file : _xiangjiList) {
            listImagePath2.add(file.toString());
        }

        if (camera.size() > 0) {
            listImagePath2.addAll(0, camera);
        }

        if (list.size() > 1) { // 合并camera
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).getPath().contains("/Camera/")) {
                    list.remove(i);
                }
            }
        }
        if (list.size() > 0) {
            GalleryEntity bean = list.get(0);
            bean.setNum(listImagePath2.size());
            mapchecked.put(0, true);
        }

        if (listImagePath2.size() > 0) {
            listImagePath.clear();
            listImagePath.addAll(listImagePath2);
            // chose1.setText("Camera");
            chose1.setText("相机胶卷");
        } else {
            // 显示所有图片的第一组列表
            if (!chose1.getText().toString().equals("Camera")) {
                if (list.size() > 0) {
                    String choseName = list.get(0).getName();
                    String str = list.get(0).getPath();
                    mapchecked.put(0, true);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getPath().contains("/DCIM/")) {
                            choseName = list.get(i).getName();
                            str = list.get(i).getPath();
                            mapchecked.put(0, false);
                            mapchecked.put(i, true);
                            break;
                        }
                    }
                    chose1.setText(choseName);
                    int j = str.lastIndexOf("/");
                    File file = new File(str.substring(0, j));
                    File[] array = file.listFiles();
                    if (array != null) {
                        for (File anArray : array) {
                            String name = anArray.toString();
                            if (isPic(name)) {
                                listImagePath.add(0, name);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 是否是常规图片
     * @param name
     * @return
     */
    private boolean isPic(String name) {
        name = name.toLowerCase();
        return name.endsWith(".jpg")
                || name.endsWith(".gif")
                || name.endsWith(".png")
                || name.endsWith(".jpeg");
    }
    private boolean isPic(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg")
                || name.endsWith(".gif")
                || name.endsWith(".png")
                || name.endsWith(".jpeg");
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //相机返回
        if (200 == resultCode) {
            // 照片返回回调
            if (flag) {
               // EventBus.getDefault().post(new ActivityForResultEntity(TiebaFabuActivity.class.getSimpleName(), 0));
                Intent data1 = new Intent();
                data1.putExtra("file", data.getExtras().getString("file"));
                //请求代码可以自己设置，这里设置成20
                setResult(200, data1);
                finish();
            } else {
                if (num < number) {

                    isCameraBack = true;
                    listImagePath.add(1, path);

                    if (isCameraBack) {
                        isCameraBack = false;
                        imageMap.put(path, "");

                        num++;
                        dialog.showProgressDialog();

                        new Thread() {
                            public void run() {
                                String str = BitmapCompress
                                        .getimage2(listImagePath.get(1));
                                Message msg = new Message();
                                Bundle bundle = new Bundle();
                                bundle.putString("file", str);//压缩后的图片地址
                                bundle.putString("key",
                                        listImagePath.get(1));//压缩前的图片地址
                                msg.setData(bundle);
                                handler.sendMessage(msg);//发送选中照片的信息
                            }
                        }.start();
                    }

                    adapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(ImageChoseActivity.this, "最多可选择" + number + "张图片",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } else if (resultCode == 203){ // 相机预览返回
           // EventBus.getDefault().post(new ActivityForResultEntity(TiebaFabuActivity.class.getSimpleName(), 0));
            Intent data1 = new Intent();
            data1.putExtra("file", data.getExtras().getString("file"));
            //请求代码可以自己设置，这里设置成20
            setResult(200, data1);
            finish();
        }

        if (resultCode == RESULT_OK) {//新增相机拍照返回结果，取得相机返回数据后进入照片修剪的activity
            switch (requestCode) {
                case PHOTO_CAMERA:
                    if (flag) {
                        path = Environment.getExternalStorageDirectory().getPath()
                                + File.separator + "Camera" + File.separator
                                + imagename + ".jpg";
                        if (TextUtils.isEmpty(imagename)) path = CityApp.imagePath;
                    } else {
                        path = Environment.getExternalStorageDirectory().getPath()
                                + File.separator + "DCIM/Camera" + File.separator
                                + imagename + ".jpg";
                        if (TextUtils.isEmpty(imagename)) path = CityApp.imagePath;
                    }
               //     EventBus.getDefault().post(new ActivityForResultEntity(TiebaFabuActivity.class.getSimpleName(), 0));
                    Intent data1 = new Intent();
                    data1.putExtra("file", path);
                    //请求代码可以自己设置，这里设置成20
                    setResult(200, data1);
                    finish();
                    break;
            }
        }

        //相册返回
        if (resultCode == 10) {
            @SuppressWarnings("unchecked")
            List<String> listTag = (List<String>) data.getExtras()
                    .getSerializable("list");

            if (listTag.size() > 0) {
                if (listTag.size() <= num) {
                    num = num - listTag.size();
                    save.setText("完成(" + num + "/" + number + ")");
                }
                for (int i = 0; i < listTag.size(); i++) {
                    imageMap.remove(listTag.get(i));
                    for (int j = 0; j < addLayout.getChildCount(); j++) {
                        View view = addLayout.getChildAt(j);
                        if (view.getTag() != null
                                && view.getTag().equals(listTag.get(i))) {
                            addLayout.removeViewAt(j);
                        }
                    }
                }
            }
            if (listTag.size() > 0) {
                adapter.notifyDataSetChanged();
            }
        }
        if (resultCode == 20) {
            @SuppressWarnings("unchecked")
            List<String> listTag = (List<String>) data.getExtras()
                    .getSerializable("list");
            for (int i = 0; i < listTag.size(); i++) {
                imageMap.remove(listTag.get(i));
            }
           // EventBus.getDefault().post(new ActivityForResultEntity(TiebaFabuActivity.class.getSimpleName(), 0));
            Intent data2 = new Intent();
            data2.putExtra("map", (Serializable) imageMap);
            data2.putExtra("list", (Serializable) listTag);
            setResult(1000, data2);
            finish();
        }
    }

    private class ImageChoseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //if (curPage * limitSize < listImagePath.size()) return curPage * limitSize + 1;
            return listImagePath.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            ImageView image;
            CheckBox check;
            LinearLayout layout;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ImageChoseActivity.this).inflate(
                        R.layout.item_choseimage, null);
                holder.image = (ImageView) convertView.findViewById(R.id.image_chose);
                holder.check = (CheckBox) convertView.findViewById(R.id.check);
                holder.layout = (LinearLayout) convertView.findViewById(R.id.layout_view);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == 0) {//在相册页面判断gridview 的第一个 item（把这个item设置成相机的样子）点击进入相机剩余的item是从相册中调取出来
                holder.check.setVisibility(View.GONE);
                holder.layout.setVisibility(View.GONE);
                holder.image.setScaleType(ScaleType.CENTER_INSIDE);
                Glide.with(ImageChoseActivity.this)
                        .load(R.drawable.xiangji)
                        .asBitmap()
                        .override(px_60, px_48)
                        .into(holder.image);
                holder.image.setBackgroundColor(Color.parseColor("#666666"));
            } else {
                holder.image.setScaleType(ScaleType.CENTER_CROP);
                holder.layout.getBackground().setAlpha(150);
                holder.check.setVisibility(View.VISIBLE);

                String imagUri = listImagePath.get(position - 1);
                Glide.with(ImageChoseActivity.this)
                        .load("file://" + imagUri)
                        .asBitmap()
                        .centerCrop()
                        .into(holder.image);
                Set<String> set = imageMap.keySet();
                if (set.contains(imagUri)) {
                    holder.check.setChecked(true);
                    holder.layout.setVisibility(View.VISIBLE);
                } else {
                    holder.check.setChecked(false);
                    holder.layout.setVisibility(View.GONE);
                }
            }
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView image;
    }

    // 选择相册适配器
    private class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView context;
            ImageView image;
            CheckBox check;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ImageChoseActivity.this).inflate(
                        R.layout.item_dialog_imagechose, null);
                holder.context = (TextView) convertView
                        .findViewById(R.id.content);
                holder.image = (ImageView) convertView.findViewById(R.id.image);

                holder.check = (CheckBox) convertView
                        .findViewById(R.id.check);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.context.setText(list.get(position).getName() + "(" + list.get(position).getNum() + ")");
            Glide.with(ImageChoseActivity.this)
                    .load("file://" + list.get(position).getPath())
                    .asBitmap()
                    .into(holder.image);

            if (mapchecked.get(position)) {
                holder.check.setChecked(true);
            } else {
                holder.check.setChecked(false);
            }

            return convertView;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            pageBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void pageBack() {
   //     EventBus.getDefault().post(new ActivityForResultEntity(TiebaFabuActivity.class.getSimpleName(), 1));
        finish();
    }

    private class ImageChoseClick implements OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_back:
                    pageBack();
                    break;
                case R.id.image_chose:
                    ShowPop();
                    break;
                case R.id.image_save:
                    if (num != 0) {
                       // EventBus.getDefault().post(new ActivityForResultEntity(TiebaFabuActivity.class.getSimpleName(), 0));
                        if (flag) {
                            Set<String> keySet = imageMap.keySet();
                            for (String string : keySet) {
                                //照片旋转的角度
                                int degree = getBitmapDegree(string);
                                Intent intent = new Intent(ImageChoseActivity.this, ImageCreateActivity.class);
                                intent.putExtra("imagefile", string);
                                intent.putExtra("degree", degree);
                                intent.putExtra("flag", true);
                                startActivityForResult(intent, PHOTO_CAMERA);
                                break;
                            }
                        } else {
                            if (type_ == 1) { // webview
                                List<String> ll = new ArrayList<String>();
                                for (Map.Entry<String, String> m : imageMap.entrySet()) {
                                    ll.add(m.getValue());
                                }
                                uploadFileTools = new UploadFileTools(ImageChoseActivity.this, ll, photoType_,
                                        new UploadFileTools.CallBack() {
                                            @Override
                                            public void success(List<String> images) {
                                                String add = "";
                                                for (String s : images) {
                                                    if (add.equals("")) {
                                                        add = s;
                                                    } else {
                                                        add += "," + s;
                                                    }
                                                }
                                                Intent data = new Intent();
                                                data.putExtra("path", add);
                                                setResult(1000, data);
                                                finish();
                                            }

                                            @Override
                                            public void fail() {
                                                Toast.makeText(CityApp.getInstance(), "上传失败！", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                Intent data = new Intent();
                                data.putExtra("map", (Serializable) imageMap);
                                data.putExtra("list", (Serializable) selectedImageList);
                                setResult(1000, data);
                                finish();
                            }
                        }
                    } else {
                        Toast.makeText(ImageChoseActivity.this, "请选择图片~", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    }

    @SuppressWarnings("deprecation")
    private void ShowPop() {
        if (popupWindow == null) {
            View view = LayoutInflater.from(this).inflate(
                    R.layout.layout_dialog_imagechsoer, null);
            popupWindow = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT, true);
            popupWindow.setOutsideTouchable(true);
            // 添加背景
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setFocusable(true);
            popupWindow.setAnimationStyle(R.style.pop_animstyle);

            popupWindow.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss() {
                    transView.setVisibility(View.GONE);
                }
            });
            ListView listview = (ListView) view.findViewById(R.id.list);

            LinearLayout layout = (LinearLayout) view.findViewById(R.id.lay);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    CityApp.mScreenWidth, LayoutParams.WRAP_CONTENT);//utils.dip2px(320)
            layout.setLayoutParams(params);
            listview.setAdapter(listadapter);
            listview.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    for (int i = 0; i < list.size(); i++) {
                        mapchecked.put(i, false);
                    }
                    mapchecked.put(position, true);

                    curPage = 1;
                    listImagePath.clear();
                    if (list.get(position).getName().equals("相机胶卷")) {
                        listImagePath.addAll(listImagePath2);
                    } else {
                        String str = list.get(position).getPath();
                        int j = str.lastIndexOf("/");
                        File file = new File(str.substring(0, j));
                        File[] array = file.listFiles();
                        if (array != null) {
                            for (File anArray : array) {
                                String name = anArray.toString();
                                if (isPic(name)) {
                                    listImagePath.add(0, name);
                                }
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                    chose1.setText(list.get(position).getName());
                    popupWindow.dismiss();
                }
            });
        }
        if (!popupWindow.isShowing()) {
            popupWindow.showAsDropDown(chose, 0, 1);
            transView.setVisibility(View.VISIBLE);
            listadapter.notifyDataSetChanged();
        } else {
            popupWindow.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        if (uploadFileTools != null) {
            uploadFileTools.clear();
        }
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    boolean isLoadingData = false;
    int limitSize = 30;
    int curPage = 1;
    private class MyScrollListener implements AbsListView.OnScrollListener {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (view.getLastVisiblePosition() > view.getChildCount() - 6
                    && !isLoadingData) {
                isLoadingData = true;
                curPage++;
                adapter.notifyDataSetChanged();
                isLoadingData = false;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    }
}
