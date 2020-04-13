package com.example.test0.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.test0.R;
import com.example.test0.activity.StreetSceneryActivity;
import com.example.test0.activity.WeatherActivity;
import com.example.test0.adapter.VoiceDiaNaVAdapter;
import com.example.test0.adapter.VoiceReplyAdapter;
import com.example.test0.bean.VoiceNavBean;
import com.example.test0.bean.VoiceReplyBean;
import com.example.test0.utlis.NoScrollerLinearLayoutManager;
import com.example.test0.utlis.ToastUtils;
import com.example.test0.view.VolumeWaveView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class VoiceDialog extends Dialog implements View.OnClickListener {

    private View viewLeft, viewTop, viewBottom, viewRight;
    private RecyclerView dialog_recycle;
    private Context context;
    private VoiceDiaNaVAdapter voiceDiaNaVAdapter;
    private VoiceReplyAdapter voiceReplyAdapter;
    private List<VoiceNavBean> mData = new ArrayList();
    private List<VoiceReplyBean> mReplyData = new ArrayList<>();
    private ImageView image_start;
    private MyDialogListener listener;
    private RecyclerView reply_recycle;
    private LinearLayout llExamples;
    private VolumeWaveView volumeWaveView;

    public VoiceDialog(@NonNull Context context, int themeResId, MyDialogListener listener) {
        super(context, themeResId);
        this.context = context;
        this.listener = listener;
    }

    public interface MyDialogListener {
        public void onClick(View view);
    }

    @Override
    public void show() {
        super.show();
        mReplyData.clear();
        if (mReplyData.size() > 0) {
            reply_recycle.setVisibility(View.VISIBLE);
            llExamples.setVisibility(View.GONE);
        } else {
            reply_recycle.setVisibility(View.GONE);
            llExamples.setVisibility(View.VISIBLE);
        }
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_voice);
        getWindow().setWindowAnimations(R.style.animScale);
        viewLeft = findViewById(R.id.viewLeft);
        viewTop = findViewById(R.id.viewTop);
        viewBottom = findViewById(R.id.viewBottom);
        viewRight = findViewById(R.id.viewRight);
        dialog_recycle = findViewById(R.id.dialog_recycle);
        image_start = findViewById(R.id.image_start);
        reply_recycle = findViewById(R.id.reply_recycle);
        llExamples = findViewById(R.id.llExamples);
        volumeWaveView = findViewById(R.id.volumeWaveView);
        viewLeft.setOnClickListener(this);
        viewTop.setOnClickListener(this);
        viewBottom.setOnClickListener(this);
        viewRight.setOnClickListener(this);
        image_start.setOnClickListener(this);
        volumeWaveView.setOnClickListener(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

    private void initView() {
        mData.add(new VoiceNavBean("查天气", "快速获取当前位置天气情况"));
        mData.add(new VoiceNavBean("查位置", "地图直观展示您想去的位置"));
        mData.add(new VoiceNavBean("在线办事", "社区办事详情流程展示"));
        dialog_recycle.setLayoutManager(new LinearLayoutManager(context));
        voiceDiaNaVAdapter = new VoiceDiaNaVAdapter(context, mData);
        dialog_recycle.setAdapter(voiceDiaNaVAdapter);
        voiceDiaNaVAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rlItem:
                        switch (position) {
                            case 0: {
                                intoWeather();
                                break;
                            }
                            case 1: {
                                intoLocation();
                                break;
                            }
                            case 2: {
                                break;
                            }
                        }
                        break;
                }
            }
        });

        reply_recycle.setLayoutManager(new NoScrollerLinearLayoutManager(context));
        voiceReplyAdapter = new VoiceReplyAdapter(mReplyData);
        reply_recycle.setAdapter(voiceReplyAdapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(VoiceReplyBean bean) {
        mReplyData.add(bean);
        refreshAdapter(bean.getMessage());
    }

    private void refreshAdapter(String message) {
        if (mReplyData.size() > 0) {
            reply_recycle.setVisibility(View.VISIBLE);
            llExamples.setVisibility(View.GONE);
        } else {
            reply_recycle.setVisibility(View.GONE);
            llExamples.setVisibility(View.VISIBLE);
        }
        voiceReplyAdapter.notifyDataSetChanged();

        if (message.indexOf("天气") != -1) {
            mReplyData.add(new VoiceReplyBean("去天气的界面", 2));
        } else if (message.indexOf("位置") != -1) {
            mReplyData.add(new VoiceReplyBean("去位置的界面", 2));
        } else if (message.indexOf("在线") != -1 || message.indexOf("办事") != -1) {
            mReplyData.add(new VoiceReplyBean("去在线办事的界面", 2));
        } else if (message.indexOf("你好") != -1 || message.indexOf("您好") != -1) {
            mReplyData.add(new VoiceReplyBean("您好，请问有什么能帮到您", 2));
        } else {
            mReplyData.add(new VoiceReplyBean("我不太明白您的意思", 2));
        }
        reply_recycle.scrollToPosition(mReplyData.size() - 1);
        voiceReplyAdapter.notifyDataSetChanged();
        voiceReplyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rlItem:
                        String message1 = mReplyData.get(position).getMessage();
                        if (message1.equals("去天气的界面")) {
                            intoWeather();
                        } else if (message1.equals("去位置的界面")) {
                            intoLocation();
                        } else if (message1.equals("去在线办事的界面")) {
                            ToastUtils.show("点击了---》去在线办事的界面");
                        }
                        break;
                }
            }
        });
    }

    private void intoWeather() {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra("path", "英协路智慧街道云平台/天气详情");
        context.startActivity(intent);
        dismiss();
    }

    private void intoLocation() {
        Intent intent = new Intent(context, StreetSceneryActivity.class);
        context.startActivity(intent);
        dismiss();
    }
}
