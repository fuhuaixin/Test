package com.example.test0.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baidu.lbsapi.panoramaview.PanoramaView;
import com.baidu.lbsapi.panoramaview.PanoramaViewListener;
import com.example.test0.R;
import com.example.test0.bean.LocationBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 实景fragment
 */
public class PanFragment extends Fragment {

    private TextView tvMessage222;
    private PanoramaView mPanoramaView;
    View inflate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_pan, container, false);
        tvMessage222 = inflate.findViewById(R.id.tvMessage222);
        tvMessage222.setText("第er个Map");
        EventBus.getDefault().register(this);
        return inflate;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LocationBean bean) {
        getPanorama(bean.getLng(), bean.getLat());
        Log.e("fhxx", bean.toString());
    }

    private void getPanorama(double lng, double lat) {
        mPanoramaView = inflate.findViewById(R.id.panorama);
        //是否显示邻接街景箭头（有邻接全景的时候）
        mPanoramaView.setShowTopoLink(true);
        //设置全景图的俯仰角
        mPanoramaView.setPanoramaPitch(90);
        //获取当前全景图的俯仰角
        //更新俯仰角的取值范围：室外景[-15, 90], 室内景[-25, 90],
        //90为垂直朝上方向，0为水平方向
        mPanoramaView.getPanoramaPitch();
        //设置全景图的偏航角
        mPanoramaView.setPanoramaHeading(60);
        //获取当前全景图的偏航角
        mPanoramaView.getPanoramaHeading();
        //设置全景图的缩放级别
        //level分为1-5级
        mPanoramaView.setPanoramaLevel(1);
        //获取当前全景图的缩放级别
        mPanoramaView.getPanoramaLevel();
        //全景的事件监听要在setPanorama之前使用，否者可能会引发异常
        mPanoramaView.setPanoramaViewListener(new PanoramaViewListener() {
            @Override
            public void onMoveStart() {

            }

            @Override
            public void onMoveEnd() {

            }

            @Override
            public void onMessage(String arg0, int arg1) {

            }

            @Override
            public void onLoadPanoramaError(String arg0) {

            }

            @Override
            public void onLoadPanoramaEnd(String arg0) {

            }

            @Override
            public void onLoadPanoramaBegin() {

            }

            @Override
            public void onDescriptionLoadEnd(String arg0) {

            }

            @Override
            public void onCustomMarkerClick(String arg0) {

            }
        });
//        Toast.makeText(getContext(), lng+"-----"+lat, Toast.LENGTH_SHORT).show();
        mPanoramaView
                .setPanoramaImageLevel(PanoramaView.ImageDefinition.ImageDefinitionHigh);
        mPanoramaView.setPanorama(lng, lat, PanoramaView.COORDTYPE_BD09LL);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPanoramaView.destroy();
        EventBus.getDefault().unregister(this);
    }
}
