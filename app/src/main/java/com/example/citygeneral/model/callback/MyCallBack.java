package com.example.citygeneral.model.callback;

/**
 * Created by hp1 on 2017-05-08.
 */

public interface MyCallBack<T>
{
     void onDataChanged(T data);
     void onErrorHappened(String errorMessage);
}
