package com.example.citygeneral.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore.Images;


import com.example.citygeneral.model.entity.GalleryEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取图库图片
 */
public class ImageFileTool {

    public static List<GalleryEntity> queryGallery(Activity context) {
        List<GalleryEntity> galleryList = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        String[] columns = {Images.Media._ID, Images.Media.DATA, Images.Media.BUCKET_ID, Images.Media.BUCKET_DISPLAY_NAME, "COUNT(1) AS count"};
        String selection = "0==0) GROUP BY (" + Images.Media.BUCKET_ID;
        String sortOrder = Images.Media.DATE_MODIFIED + " desc ";
        Cursor cur = cr.query(Images.Media.EXTERNAL_CONTENT_URI, columns, selection, null, sortOrder);
        if (cur != null) {
            if (cur.moveToFirst()) {

                int id_column = cur.getColumnIndex(Images.Media._ID);
                int image_id_column = cur.getColumnIndex(Images.Media.DATA);
                int bucket_id_column = cur.getColumnIndex(Images.Media.BUCKET_ID);
                int bucket_name_column = cur.getColumnIndex(Images.Media.BUCKET_DISPLAY_NAME);
                int count_column = cur.getColumnIndex("count");

                do {
                    // Get the field values
                    int id = cur.getInt(id_column);
                    String image_path = cur.getString(image_id_column);
                    int bucket_id = cur.getInt(bucket_id_column);
                    String bucket_name = cur.getString(bucket_name_column);
                    int count = cur.getInt(count_column);
                    // Do something with the values.
                    GalleryEntity gallery = new GalleryEntity();
                    gallery.setId(id);
                    gallery.setPath(image_path);
                    gallery.setAllid(bucket_id);
                    gallery.setName(bucket_name);
                    gallery.setNum(count);
                    galleryList.add(gallery);
                } while (cur.moveToNext());
            }
        }
        return galleryList;
    }

    public static Bitmap getImageThumbnail(String imagePath, int width, int height) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null  
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false; // 设为 false  
        // 计算缩放比  
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth = w / width;
        int beHeight = h / height;
        int be = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false  
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象  
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }
}
