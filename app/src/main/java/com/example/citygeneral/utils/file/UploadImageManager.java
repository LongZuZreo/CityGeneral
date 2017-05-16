package com.example.citygeneral.utils.file;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;


import com.example.citygeneral.utils.BitmapCompress;
import com.example.citygeneral.utils.NetUrl;
import com.example.citygeneral.utils.PublicUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadImageManager {
    private static UploadImageManager manager;
    private Handler handler;

    private UploadImageManager() {}

    public static UploadImageManager getManager() {
        if (manager == null) {
            manager = new UploadImageManager();
        }
        return manager;
    }

    public void upload(Handler handler, final String path, final String houzhui, Context cont) {
        this.handler = handler;

        new Thread(new Runnable() {
            @Override
            public void run() {
                int degree = PublicUtils.getBitmapDegree(path);
                String p = null;
                if (degree != 0) {
                    p = BitmapCompress.getimage3(path, PublicUtils.getBitmapDegree(path));
                }
                if (TextUtils.isEmpty(p)) {
                    uploadFile(path, houzhui);
                } else {
                    uploadFile(p, houzhui);
                }
            }
        }).start();


    }

    private void uploadFile(String path, String houzhui) {

        String fileName = getFileName(path);
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "HEDAODE";
        try {
            URL url = new URL(NetUrl.URL_UPLOAD + houzhui
                    + "&frmpage="
                    + PublicUtils.getCityUrl()
                    + "&siteid=" + PublicUtils.getCityId()
                    + "&uid=" + PublicUtils.getUserID()
                    + "&source=2");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
			/* 设置传送的method=POST */
            con.setRequestMethod("POST");
			/* setRequestProperty */
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
			/* 设置DataOutputStream */
            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; "
                    + "name=\"Filedata\"; filename=\"" + fileName + "\"" + end);
            ds.writeBytes("Content-Type: application/octet-stream" + end);
            ds.writeBytes(end);
			/* 取得文件的FileInputStream */
            FileInputStream fStream = new FileInputStream(path);
			/* 设置每次写入1024bytes */
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
			/* 从文件读取数据至缓冲区 */
            while ((length = fStream.read(buffer)) != -1) {
				/* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
            fStream.close();
            ds.flush();
            ds.close();
            int code = con.getResponseCode();
            if (code == 200) {
				/* 取得Response内容 */
                InputStream is = con.getInputStream();
                int ch;
                StringBuffer sb = new StringBuffer();
                while ((ch = is.read()) != -1) {
                    sb.append((char) ch);
                }
                handler.sendMessage(handler.obtainMessage(0, sb));
            } else {
                handler.sendMessage(handler.obtainMessage(1, "code isn't 200"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            handler.sendMessage(handler.obtainMessage(1, e.getMessage()));
        }
    }

    private String getFileName(String path) {
        String[] temp = path.split(File.separator);
        return temp[temp.length - 1];
    }

}
