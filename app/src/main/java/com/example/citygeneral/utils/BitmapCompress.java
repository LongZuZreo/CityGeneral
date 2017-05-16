package com.example.citygeneral.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片处理
 */
public class BitmapCompress {
    /**
     * 图片大小限制 最大500k
     */
    private static int maxSize = 500;
    // 宽高限制  最大1280
    private static final float width_h = 1280f;

    public static Bitmap getimage1(String srcPath, int degree) {
        //友盟BUG NULLpoint
        if (srcPath == null) {
            return null;
        }

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap;
        BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是640*480分辨率，所以高和宽我们设置为
        float hh = width_h;// 这里设置高度为800f
        float ww = width_h;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (w / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (h / hh);
        }
        if (be <= 0)
            be = 1;

        newOpts.inSampleSize = be;// 设置缩放比例
        Log.i("比例", newOpts.inSampleSize + "");
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage1(bitmap, degree);// 压缩好比例大小后再进行质量压缩
    }

    public static String getimage2(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap;
        BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是640*480分辨率，所以高和宽我们设置为
        float hh = width_h;// 这里设置高度为800f
        float ww = width_h;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (w / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (h / hh);
        }
        if (be <= 0)
            be = 1;
        if (CityApp.mScreenHeight < hh) {
            be = 4;
        }

        newOpts.inSampleSize = be;// 设置缩放比例
        Log.i("比例", newOpts.inSampleSize + "");
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage2(bitmap, srcPath);// 压缩好比例大小后再进行质量压缩
    }

    public static Bitmap compressImage1(Bitmap image, int degree) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //友盟BUG  Nullpoint
        if (image == null) {
            return null;
        }
        int options = 100;
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        while (baos.toByteArray().length / 1024 > maxSize) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        NetUrl.imagesize = options;

        if (degree != 0) {
            Bitmap returnBm = null;
            // 根据旋转角度，生成旋转矩阵
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            try {
                // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
                returnBm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                        bitmap.getHeight(), matrix, true);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
            if (returnBm == null) {
                returnBm = bitmap;
            }
            if (bitmap != returnBm) {
                bitmap.recycle();
            }
            return returnBm;
        } else {
            return bitmap;
        }

    }

    public static String compressImage2(Bitmap image, String imgname) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (null != image) {
            int options = 100;
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
          //  LogUtils.showLog("-----", "compressImage2图片大小：" + (baos.toByteArray().length / 1024));
            while (baos.toByteArray().length / 1024 > maxSize) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
                baos.reset();// 重置baos即清空baos
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                options -= 10;// 每次都减少10
            }
            //LogUtils.showLog("-----", "compressImage2 options：" + options + "  图片大小：" + (baos.toByteArray().length / 1024));
            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
            Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
            NetUrl.imagesize = options;

            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "shangchuan");
            if (file.exists()) {
            } else {
                file.mkdir();
            }

            String name = imgname.substring(imgname.lastIndexOf("/") + 1);

            File f = new File(file, name);
            FileOutputStream fileout;
            try {
                fileout = new FileOutputStream(f);
                bitmap.compress(Bitmap.CompressFormat.JPEG,
                        NetUrl.imagesize, fileout);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return f.toString();
        }
        return null;
    }

    public static String getimage3(String srcPath, int degree) {
        //友盟BUG NULLpoint
        if (srcPath == null) {
            return null;
        }
        if (srcPath.endsWith(".gif")
                || srcPath.endsWith(".GIF")) {
            return srcPath;
        }
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap;
        BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;

        if (w <= 500 && h <= 500) {
            return srcPath;
        }

        //LogUtils.showLog("-----", "图片宽高：w=" + w + "   h=" + h);
        if (w > width_h || h > width_h) {
         //   LogUtils.showLog("-----", "---开始等比例缩放---");
            float scale = 1f;
            if (w >= h && w > width_h) {
                scale = width_h / w;
            } else if (w < h && h > width_h) {
                scale = width_h / h;
            }
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            try {
                bitmap = Bitmap.createBitmap(BitmapFactory.decodeFile(srcPath), 0, 0, w, h, matrix, true);
            } catch (Exception e) {
                return null;
            }
        } else {
          //  LogUtils.showLog("-----", "---不需要缩放---");
            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        }

        return compressImage3(bitmap, srcPath, degree);// 压缩好比例大小后再进行质量压缩
    }

    public static String compressImage3(Bitmap image, String imgname, int degree) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (null != image) {
            int options = 100;
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int _size = baos.toByteArray().length / 1024;
          //  LogUtils.showLog("-----", "compressImage3 options：" + options + "  图片大小：" + _size);
            options = 90;
            if (_size < maxSize) {
                if (_size >= 300) {
                    options = 85;
                } else if (_size >= 200 && _size < 300) {
                    options = 90;
                }
                baos.reset();// 重置baos即清空baos
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            } else {
                while (baos.toByteArray().length / 1024 >= maxSize) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
                    baos.reset();// 重置baos即清空baos
                    image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                    options -= 10;// 每次都减少10
                }
            }
         //   LogUtils.showLog("-----", "compressImage3 options：" + options + "  图片大小：" + (baos.toByteArray().length / 1024));
            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
            Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
            if (degree != 0) bitmap = PublicUtils.toturn(bitmap, degree);
            NetUrl.imagesize = options;

            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "shangchuan");
            if (!file.exists()) {
                file.mkdir();
            }

            String name = imgname.substring(imgname.lastIndexOf("/") + 1);

            File f = new File(file, name);
            FileOutputStream fileout;
            try {
                fileout = new FileOutputStream(f);
                bitmap.compress(Bitmap.CompressFormat.JPEG,
                        NetUrl.imagesize, fileout);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return f.toString();
        }
        return null;
    }
}
