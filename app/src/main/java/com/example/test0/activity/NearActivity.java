package com.example.test0.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.test0.R;
import com.example.test0.bean.LocationBean;
import com.example.test0.utlis.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class NearActivity extends AppCompatActivity {

    private boolean isFirstLoc = true;
    MapView mMapView = null;
    LocationClient mLocationClient;
    BaiduMap mBaiduMap;
    TextView tvPath;
    LinearLayout llBack, ll_search;
    EditText et_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near);
        initViews();
    }

    private void initViews() {
        mMapView = findViewById(R.id.mapView);
        tvPath = findViewById(R.id.tvPath);
        llBack = findViewById(R.id.llBack);
        ll_search = findViewById(R.id.ll_search);
        et_search = findViewById(R.id.et_search);
        mMapView.removeViewAt(1);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);

        poiSearch = PoiSearch.newInstance();


        initMap();


        tvPath.setText(getIntent().getStringExtra("path"));
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchNear(et_search.getText().toString());
            }
        });
    }

    private void initMap() {
        //定位初始化
        mLocationClient = new LocationClient(this);
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


    /**
     * 区域检索
     */
    private String isBus = "";
    private PoiSearch poiSearch;

    private void searchNear(String str) {

        poiSearch.setOnGetPoiSearchResultListener(poiResltListener);
        isBus = str;
        /**
         * 设置矩形检索区域
         */
        LatLngBounds searchBounds = new LatLngBounds.Builder()
                .include(new LatLng(34.768242, 113.721181))
                .include(new LatLng(34.758004, 113.728637))
                .build();

        poiSearch.searchInBound(new PoiBoundSearchOption()
                .bound(searchBounds)
                .keyword(str)
                .pageNum(0)
                .pageCapacity(30));

    }

    /**
     * POI检索监听器
     */

    private List<OverlayOptions> optionsList = new ArrayList<>();
    private OnGetPoiSearchResultListener poiResltListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            Log.e("fhxx  poi top", poiResult.error + "  ----  " + (poiResult.error == SearchResult.ERRORNO.NO_ERROR));

            if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                //先定位到英协路
                MapStatus mMapStatus = new MapStatus.Builder()
                        .target(new LatLng(34.763375, 113.724974))
                        .zoom(17)
                        .build();
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                mBaiduMap.setMapStatus(mMapStatusUpdate);
                //清除marker点 清除marker点击事件  清除list存的marker点
                mBaiduMap.clear();
                mBaiduMap.removeMarkerClickListener(onMarkerClickListener);
                optionsList.clear();
                List<PoiInfo> allPoi = poiResult.getAllPoi();
                Log.e("fhxx  poi bot", allPoi.toString());

                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.mipmap.icon_near_location_red_big);

                for (int i = 0; i < allPoi.size(); i++) {
                    Bundle mBundle = new Bundle();
                    mBundle.putString("title", allPoi.get(i).getName());
                    mBundle.putDouble("lat", allPoi.get(i).getLocation().latitude);
                    mBundle.putDouble("lng", allPoi.get(i).getLocation().longitude);
                    mBundle.putString("address", allPoi.get(i).getAddress());
                    mBundle.putString("number", allPoi.get(i).getPhoneNum());

                    OverlayOptions option = new MarkerOptions()
                            .extraInfo(mBundle)
                            .position(allPoi.get(i).getLocation())
                            .icon(bitmap);
                    optionsList.add(option);
                }
                mBaiduMap.addOverlays(optionsList);
                mBaiduMap.setOnMarkerClickListener(onMarkerClickListener);
            } else {
                ToastUtils.show("附近暂无");
            }
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        }
    };


    /**
     * marker点监听
     */


    private BaiduMap.OnMarkerClickListener onMarkerClickListener = new BaiduMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {


            Bundle extraInfo = marker.getExtraInfo();
            String title = extraInfo.getString("title");
            String address = extraInfo.getString("address");
            String number = extraInfo.getString("number");
            double lat = extraInfo.getDouble("lat");
            double lng = extraInfo.getDouble("lng");



            MyDialog myDialog = new MyDialog(NearActivity.this);
            myDialog.setMessage(title,address,number);

            Window window = myDialog.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.CENTER;
//            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            myDialog.getWindow().setAttributes(lp);

            myDialog.show();
//            ToastUtils.show(title + "-----" + address);

            return false;
        }
    };


    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }


            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                EventBus.getDefault().post(new LocationBean(location.getLatitude(), location.getLongitude()));
            }
        }
    };


    private class MyDialog extends Dialog{

        TextView tv_title,tv_address,tv_phone;
        String title,address,phone;
        public void setMessage(String title,String address,String phone){
            this.title =title;
            this.address =address;
            this.phone =phone;
        }
        public MyDialog(@NonNull Context context) {
            super(context, R.style.nearDialog);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_near);
            tv_title =findViewById(R.id.tv_title);
            tv_address =findViewById(R.id.tv_address);
            tv_phone =findViewById(R.id.tv_phone);

            setData();

        }

        private void setData() {
            tv_title.setText(title);
            tv_address.setText("地址："+address);
            if (phone.equals("")){
                tv_phone.setText("电话：暂无");
            }else {
                tv_phone.setText("电话："+phone);

            }
        }

    }

}
