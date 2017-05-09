package com.example.citygeneral.model.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.citygeneral.AppApplication;
import com.example.citygeneral.model.callback.MyCallBack;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hp1 on 2017-05-08.
 */

public class BaseVolley {
    private static BaseVolley baseVolley = new BaseVolley();

    private BaseVolley() {

    }

    public synchronized static BaseVolley getInstance() {
        if (baseVolley == null)
            baseVolley = new BaseVolley();
        return baseVolley;
    }

    public <T> void doGetString(String url, String tag,  String params, final MyCallBack<T> mCallBack) {
       /* if (params != null && !params.isEmpty()) {
            Set<String> keySet = params.keySet();
            StringBuffer urlBuffere = new StringBuffer(url);
            urlBuffere.append("?");
            for (String key : keySet) {
                urlBuffere.append(key).append("=").append(params.get(key)).append("&");
            }
            url = urlBuffere.substring(0, urlBuffere.length() - 1);}*/
            String params1 = "param="
                    + params.replace(
                    "+6Hp9X5zR39SOI6oP0685Bk77gG56m7PkV89xYvl86A=",
                    "%2b6Hp9X5zR39SOI6oP0685Bk77gG56m7PkV89xYvl86A=");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, params1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                T t = t(response, mCallBack);
                mCallBack.onDataChanged(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mCallBack.onErrorHappened(error.getMessage());
            }
        });
        stringRequest.setTag(tag);
        AppApplication.getInstance().add(stringRequest);
    }

    public <T> void doGetString(String url, String tag, final MyCallBack<T> mCallBack) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                T t = t(response, mCallBack);
                mCallBack.onDataChanged(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mCallBack.onErrorHappened(error.getMessage());
            }
        });
        stringRequest.setTag(tag);
        AppApplication.getInstance().add(stringRequest);
    }

    public <T> void doPostString(String url, String tag, final String params, final MyCallBack<T> mCallBack) {
         String params1 = "param="
                + params.replace(
                "+6Hp9X5zR39SOI6oP0685Bk77gG56m7PkV89xYvl86A=",
                "%2b6Hp9X5zR39SOI6oP0685Bk77gG56m7PkV89xYvl86A=");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                T t = t(s, mCallBack);
                mCallBack.onDataChanged(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mCallBack.onErrorHappened(volleyError.getMessage());
            }
        }) /*{
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return;
            }
        }*/;
        stringRequest.setTag(tag);
        AppApplication.getInstance().add(stringRequest);
    }

    private <T> T t(String response, MyCallBack myCallBack) {
        Gson gson = new Gson();
        Type[] types = myCallBack.getClass().getGenericInterfaces();
        Type[] parameterized = ((ParameterizedType) types[0]).getActualTypeArguments();
        T t = gson.fromJson(response, parameterized[0]);
        return t;
    }

}
