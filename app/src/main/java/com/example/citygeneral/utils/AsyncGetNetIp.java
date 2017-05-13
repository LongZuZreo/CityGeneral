package com.example.citygeneral.utils;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 获取IP地址
 */
public class AsyncGetNetIp extends AsyncTask<String, String, String> {

    getIP getip;

    public AsyncGetNetIp(getIP getip) {
        super();
        this.getip = getip;
    }

    @Override
    protected String doInBackground(String... params) {
        return GetNetIp();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            getip.setIP(result);
        }
    }

    public static String GetNetIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        try {
            infoUrl = new URL("http://apitest.ccoo.cn/GetIPAddress.ashx");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                // Log.i("IP地址啊", strber.toString().replace("\n", ""));
                return strber.toString().replace("\n", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public interface getIP {
        public void setIP(String ip);
    }

}
