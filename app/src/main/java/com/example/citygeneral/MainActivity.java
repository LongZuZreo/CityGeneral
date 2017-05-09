package com.example.citygeneral;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.citygeneral.model.callback.MyCallBack;
import com.example.citygeneral.model.entity.User;
import com.example.citygeneral.model.http.BaseVolley;
import com.example.citygeneral.model.http.Parameter;
import com.example.citygeneral.utils.NetUrl;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
