package com.example.test0.http;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.test0.base.BaseApplication;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class HttpUtil {


    private static HttpUtil mHttpUtil;
    private Gson mGson;

    //连接超时时间
    private static final int REQUEST_TIMEOUT_TIME = 60 * 1000;

    //volley请求队列
    public static RequestQueue mRequestQueue;

    private HttpUtil() {
        mGson = new Gson();
        //这里使用Application创建全局的请求队列
        mRequestQueue = Volley.newRequestQueue(BaseApplication.myApplication);
    }

    public static HttpUtil getInstance() {
        if (mHttpUtil == null) {
            synchronized (HttpUtil.class) {
                if (mHttpUtil == null) {
                    mHttpUtil = new HttpUtil();
                }
            }
        }
        return mHttpUtil;
    }

    /**
     * http请求
     *
     * @param url          http地址（后缀）
     * @param param        参数
     * @param httpCallBack 回调
     */
    public <T> void request(final Context context, String url, final Map<String, String> param, final HttpCallBack<T> httpCallBack) {
        //可以放loading框
        Toast.makeText(context, "请求中...", Toast.LENGTH_SHORT).show();
        //日志打印
        Log.e("网络请求入参:\n", param.toString());

        JSONObject paramJsonObject = new JSONObject(param);//把map转换为json

        //参数：post请求、入参json格式、返回监听、错误监听
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, paramJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //可以放loading框
                Toast.makeText(context, "请求完成", Toast.LENGTH_SHORT).show();
                //日志打印
                Log.e("网络请求返回:\n", response.toString());
                if (httpCallBack == null) {
                    return;
                }

                HttpResult httpResult = mGson.fromJson(String.valueOf(response), HttpResult.class);
                if (httpResult != null) {
                    if (httpResult.getCode() != 200) {//失败
                        httpCallBack.onFail(httpResult.getMsg());
                    } else {//成功
                        //判断String/Bean/List<Bean>
                        Type type = getTType(httpCallBack.getClass());
                        if (type == String.class) {//泛型是String，返回结果json字符串
                            String stringData = httpResult.getData().toString();
                            httpCallBack.onSuccess((T) stringData);
                        } else {//泛型是实体或者List<>
                            String beanData= mGson.toJson(httpResult.getData());
                            T t = mGson.fromJson(beanData, type);
                            httpCallBack.onSuccess(t);
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (httpCallBack == null) {
                    return;
                }
                String msg = error.getMessage();
                httpCallBack.onFail(msg);
            }
        }) {
            //实现JsonObjectRequest的方法，设置ContentType（和后台商定好）
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

        };
        //设置请求超时和重试
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_TIME, 1, 1.0f));
        //加入到请求队列
        if (mRequestQueue != null)
            mRequestQueue.add(jsonObjectRequest.setTag(url));
    }

    private Type getTType(Class<?> clazz) {
        Type mySuperClassType = clazz.getGenericSuperclass();
        Type[] types = ((ParameterizedType) mySuperClassType).getActualTypeArguments();
        if (types != null && types.length > 0) {
            //T
            return types[0];
        }
        return null;
    }
}
