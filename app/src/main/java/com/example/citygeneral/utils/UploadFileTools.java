package com.example.citygeneral.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.citygeneral.R;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传
 *
 * @author Administrator
 */
public class UploadFileTools {
    private Context context;
    private List<String> list;
    private String houzhui = "filesrc=app_bbs&sw=300&sh=225&mw=500&mode=s&mmode=w&print=0";

    private PublicUtils utils;
    /**
     * 当前上传文件
     */
    private int curPos = 1;

    private LayoutInflater inflater;

    /**
     * 上传图片对话框
     */
    private AlertDialog cProgress_;
    private TextView title_tview_;
    private ProgressBar progressBar_;
    private TextView progress_tview_;
    private TextView cancel_tv;

    /**
     * 返回的图片
     */
    private List<String> returnPic = new ArrayList<String>();

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: // 成功
                    curPos++;
                    returnPic.add(msg.obj.toString());
                    if (curPos <= list.size()) {
                        sendFile(list.get(curPos - 1));
                    } else {
                        // 上传完成
                        clear();
                        if (callBack != null) callBack.success(returnPic);
                    }
                    break;
                case 1: // 失败
                    cProgress_.dismiss();
                    if (callBack != null) callBack.fail();
                    break;
                case 2: // 更新
                    progressBar_.setProgress(msg.arg1);
                    //progress_tview_.setText("当前上传进度：" + msg.arg1 + "%");
                    break;
                case 3: // 取消

                    break;
                default:
                    break;
            }
        }

        ;
    };

    /**
     * @param c
     * @param l
     * @param back
     * @param photoType 缩略图样式 0长方形300*225  1正方形300*300
     */
    public UploadFileTools(Context c, List<String> l, int photoType, CallBack back) {
        context = c;
        list = l;
        inflater = LayoutInflater.from(context);
        callBack = back;

        if (photoType == 1) {
            houzhui = "filesrc=app_com&sw=300&sh=300&mw=500&mode=s&mmode=w&print=0";
        } else {
            houzhui = "filesrc=app_com&sw=300&sh=225&mw=500&mode=s&mmode=w&print=0";
        }

        utils = new PublicUtils(context);
        customProgressDialog();

        sendFile(list.get(curPos - 1));
    }

    /**
     * 初始化对话框
     */
    private void customProgressDialog() {
        View layout = inflater.inflate(R.layout.custom_dialog, null);
        cProgress_ = new AlertDialog.Builder(context, R.style.Theme_CustomDialog2).create();
        cProgress_.setView(layout);
        cProgress_.setCanceledOnTouchOutside(false);
        cProgress_.setCancelable(false);
        cProgress_.show();
        Window window = cProgress_.getWindow();
        window.setContentView(R.layout.custom_dialog);
        title_tview_ = (TextView) window.findViewById(R.id.title_tview);
        progress_tview_ = (TextView) window.findViewById(R.id.progress);
        progressBar_ = (ProgressBar) window.findViewById(R.id.progreesbar);
        cancel_tv = (TextView) window.findViewById(R.id.cancel_tv);

        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    /**
     * 是否取消
     */
    private boolean isCancel = false;

    public void clear() {
        isCancel = true;
        if (cProgress_ != null) cProgress_.dismiss();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private String getFileName(String path) {
        if (TextUtils.isEmpty(path)) return "";
        String[] temp = path.split(File.separator);
        return temp[temp.length - 1];
    }

    /**
     * 文件上传
     */
    private void sendFile(final String path) {
        // title_tview_.setText("上传第" + curPos + "张照片(共" + list.size() + "张)...");
        progressBar_.setProgress(0);
        //progress_tview_.setText("当前上传进度：0%");
        progress_tview_.setText("(" + curPos + "/" + list.size() + ")");
     //   LogUtils.showErrorLog(getClass(), "进度：" + progress_tview_.getText().toString());

        new Thread(new Runnable() {
            public void run() {
                String fileName = getFileName(path);
                String end = "\r\n";
                String twoHyphens = "--";
                String boundary = "HEDAODE";
                try {
                    URL url = new URL(NetUrl.URL_UPLOAD + houzhui + "&frmpage="
                            + utils.getCityUrl() + "&siteid=" + new PublicUtils(context).getCityId() + "&uid=" + new PublicUtils(context).getUserID() + "&source=2");
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
                    int bufferSize = 64;
                    byte[] buffer = new byte[bufferSize];
                    int length = -1;
					/* 从文件读取数据至缓冲区 */
                    int all = fStream.available();
                    Message message;
                    long total = 0;
                    while ((length = fStream.read(buffer)) != -1) {
                        if (isCancel) {
                            fStream.close();
                            handler.sendEmptyMessage(3);
                            break;
                        } else {
							/* 将资料写入DataOutputStream中 */
                            ds.write(buffer, 0, length);
                            // 百分比
                            total += length;
                            message = handler.obtainMessage(2, (int) ((total * 100) / all), 0);
                            handler.sendMessage(message);
                        }
                    }
                    if (isCancel) {
                        return;
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
                        Message msg = handler.obtainMessage(0, sb.toString());
                        handler.sendMessage(msg);
                    } else {
                        handler.sendEmptyMessage(1);
                    }
                } catch (Exception e) {
                    handler.sendEmptyMessage(1);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private CallBack callBack;

    public void setCallBack(CallBack c) {
        callBack = c;
    }

    public interface CallBack {
        void success(List<String> images);

        void fail();
    }
}
