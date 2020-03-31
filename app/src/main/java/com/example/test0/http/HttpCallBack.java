package com.example.test0.http;


/**
 * 网络框架-泛型回调结果
 *
 * 封装Volley
 */
public abstract class HttpCallBack<T> {

    public abstract void onSuccess(T data);

    public abstract void onFail(String msg);
}
