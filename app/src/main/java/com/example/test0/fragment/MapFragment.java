package com.example.test0.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.example.test0.R;
import com.example.test0.bean.LocationBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * 地图fragment
 */
public class MapFragment extends Fragment {

    TextureMapView mMapView = null;
    LocationClient mLocationClient;
    BaiduMap mBaiduMap;
    TextView tvMessage111;
    View inflate;
    private boolean isFirstLoc = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_map, container, false);
        tvMessage111 = inflate.findViewById(R.id.tvMessage111);
        mMapView = inflate.findViewById(R.id.mMapViews);
        tvMessage111.setText("第一个Map");
        initView();

        return inflate;
    }

    private void initView() {
        mMapView.removeViewAt(1);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        initMap();
        MapClickListener();

    }


    private void initMap() {
        //定位初始化
        mLocationClient = new LocationClient(getContext());
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);
        //注册LocationListener监听器
        mLocationClient.registerLocationListener(mListener);
        //开启地图定位图层
        mLocationClient.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }

    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }

            tvMessage111.setText(location.getLatitude() + " ------------" + location.getLongitude());

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(20.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                EventBus.getDefault().post(new LocationBean(location.getLatitude(), location.getLongitude()));
            }
        }
    };

    private void MapClickListener() {
        BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {
            /**
             * 地图单击事件回调函数
             *
             * @param point 点击的地理坐标
             */
            @Override
            public void onMapClick(LatLng point) {
                Log.e("fhxx", "onMapClick latitude: " + point.latitude);//经度
                Log.e("fhxx", "onMapClick longitude: " + point.longitude);//纬度
                setMarkPoint(point.latitude, point.longitude);
                tvMessage111.setText(point.latitude + "--------" + point.longitude);
                EventBus.getDefault().post(new LocationBean(point.latitude, point.longitude));
            }

            /**
             * 地图内 Poi 单击事件回调函数
             *
             * @param mapPoi 点击的 poi 信息
             */
            @Override
            public void onMapPoiClick(MapPoi mapPoi) {
                Log.e("fhxx", "onMapPoiClick getName: " + mapPoi.getName());
                Log.e("fhxx", "onMapPoiClick getUid: " + mapPoi.getUid());
                Log.e("fhxx", "onMapPoiClick getPosition: " + mapPoi.getPosition());
            }
        };//点击获取经纬度；
        mBaiduMap.setOnMapClickListener(listener);
        mBaiduMap.setMyLocationEnabled(true);
    }

    private void setMarkPoint(double jingdu, double weidu) {
        //定义Maker坐标点
        mBaiduMap.clear();
        LatLng point = new LatLng(jingdu, weidu);

        MarkerOptions options = new MarkerOptions().position(point)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_loca));
        // 在地图上添加Marker，并显示
        mBaiduMap.addOverlay(options);


    }

}
