package com.example.citygeneral.model.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.citygeneral.R;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    // 将毫秒转换为指定格式日期
    public static String dateFormat(long l) {// new Date().getTime()是当前的毫秒数

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String date = df.format(l);// 将当前事件转换为指定格式的日期
        System.out.println(date);// 打印指定格式的日期
        return date;
    }

    // 将毫秒转换为指定格式日期
    public static String dateFormat2(long l) {// new Date().getTime()是当前的毫秒数

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        String date = df.format(l);// 将当前事件转换为指定格式的日期
        System.out.println(date);// 打印指定格式的日期
        return date;
    }

    // 将指定格式日期转换为毫秒
    public static long formatDate(String date) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        Date date2 = df.parse(date);// 将指定的日期转换为毫秒数
        System.out.println(date2.getTime());// 打印转换回来的毫秒数
        return date2.getTime();
    }

    // 将指定格式日期转换为毫秒
    public static long formatDate2(String date) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        Date date2 = df.parse(date);// 将指定的日期转换为毫秒数
        System.out.println(date2.getTime());// 打印转换回来的毫秒数
        return date2.getTime();
    }

    // 将指定格式日期转换为毫秒
    public static long formatDate3(String date) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 设置日期格式
        Date date2 = df.parse(date);// 将指定的日期转换为毫秒数
        System.out.println(date2.getTime());// 打印转换回来的毫秒数
        return date2.getTime();
    }


    // 将毫秒数换算成 天,时,分,秒,毫秒 5个字符串
    public static String[] format(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second
                * ss;

        String strDay = day < 10 ? "0" + day : "" + day;
        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : ""
                + milliSecond;
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : ""
                + strMilliSecond;
        // return strDay + " " + strHour + ":" + strMinute + ":" + strSecond +
        // " "
        // + strMilliSecond;
        // 上方是另一种格式的返回方式

        String[] string = new String[]{strDay, strHour, strMinute, strSecond,
                strMilliSecond};
        return string;
    }

    public static String[] imageMethod(String s) {// 几个图片地址字符串，分成单个地址，并以数组形式返回
        if (s == null || s.equals("")) {
            return new String[]{""};
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',' && i != s.length() - 1) {
                count++;
            }
        }
        count++;// 得到图片的个数
        String[] imageArray = new String[count];// 如长度为1，表示没有逗号分隔，只有一张图片

        for (int i = 0; i < count; i++)// jiang
        {
            imageArray[i] = s.split(",")[i];
        }

        return imageArray;
    }

    public static String[] imageMethod2(String s, char c) {// 几个图片地址字符串，分成单个地址，并以数组形式返回
        if (s == null || s.equals("")) {
            return new String[]{""};
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c && i != s.length() - 1) {
                count++;
            }
        }
        count++;// 得到图片的个数
        String[] imageArray = new String[count];// 如长度为1，表示没有逗号分隔，只有一张图片

        for (int i = 0; i < count; i++)// jiang
        {
            imageArray[i] = s.split(c + "")[i];
        }

        return imageArray;
    }

    // 将html带标签的字符串过滤成纯文本文字，并返回
    public static String deleteHTMLTag(String htmlStr) {
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

        Pattern p_style = Pattern
                .compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        htmlStr = htmlStr.replace(" ", "");
        htmlStr = htmlStr.replaceAll("\\s*|\t|\r|\n", "");
        htmlStr = htmlStr.replace("“", "");
        htmlStr = htmlStr.replace("”", "");
        htmlStr = htmlStr.replaceAll("　", "");

        return htmlStr.trim(); // 返回文本字符串
    }

    // 得到当前的网络状态
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);// 得到当前服务

        NetworkInfo info = cm.getActiveNetworkInfo();// 得到网络状态
        if (info != null && info.isConnected()) {// 网络状态对象不为空且isConnected为true则当前服务网络可用
            return true;
        } else {
            return false;
        }
    }

    /*public TextView styleText(Context context, String name, String info,
                              String date) {
        SpannableString styledText = new SpannableString(name + info + date);
        styledText.setSpan(new TextAppearanceSpan(context,
                        R.style.reply_namestyle), 0, name.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText
                .setSpan(new TextAppearanceSpan(context,
                        R.style.reply_infostyle), name.length(), name.length()
                        + info.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(context,
                        R.style.reply_datestyle), name.length() + info.length(),
                name.length() + info.length() + date.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView text = new TextView(context);

        text.setText(styledText, TextView.BufferType.SPANNABLE);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 5, 0, 0);
        text.setLayoutParams(lp);
        return text;
    }
*/
   /* public static String pushMethod(PublicUtils utils) {// 推送发送当前登录者信息
        JSONObject jo = new JSONObject();
        try {
            jo.put("customerID", Constants.CUSTOMER_ID);
            jo.put("customerKey", Constants.CUSTOMER_KEY);
            jo.put("requestTime", Tools.dateFormat(new Date().getTime()));
            jo.put("Method", "Socket_Connect");
            JSONObject jo2 = new JSONObject();
            jo2.put("siteID", utils.getCityId());
            jo2.put("userID", utils.getUserID());
            jo.put("Param", jo2);
            SocketManager4.sendMsg(jo.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toString();
    }*/

}
