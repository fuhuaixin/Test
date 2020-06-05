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
import com.example.test0.activity.ChiefPublicActivity;
import com.example.test0.activity.FeedBackActivity;
import com.example.test0.activity.GovernmentActivity;
import com.example.test0.activity.HistoryTodayActivity;
import com.example.test0.activity.HotLineActivity;
import com.example.test0.activity.InOursActivity;
import com.example.test0.activity.LetterBoxActivity;
import com.example.test0.activity.NearActivity;
import com.example.test0.activity.NormativeActivity;
import com.example.test0.activity.OpenDirectoryActivity;
import com.example.test0.activity.PublicNewsActivity;
import com.example.test0.activity.QuestionActivity;
import com.example.test0.activity.StreetIntroActivity;
import com.example.test0.activity.StreetSceneryActivity;
import com.example.test0.activity.VoteActivity;
import com.example.test0.activity.WeatherActivity;
import com.example.test0.activity.WebActivity;
import com.example.test0.adapter.VoiceDiaNaVAdapter;
import com.example.test0.adapter.VoiceReplyAdapter;
import com.example.test0.base.NetConstants;
import com.example.test0.bean.CheifPubTitleBean;
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
                                intoOnline();
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
        Intent intent = new Intent();
        if (message.indexOf("天气") != -1) {
//            mReplyData.add(new VoiceReplyBean("去天气的界面", 2));
            intoWeather();
        } else if (message.indexOf("位置") != -1) {
//            mReplyData.add(new VoiceReplyBean("去位置的界面", 2));
            intoLocation();
        } else if (message.indexOf("在线办事") != -1 || message.indexOf("办事") != -1) {
//            mReplyData.add(new VoiceReplyBean("去在线办事的界面", 2));
            intoOnline();
        } else if (message.indexOf("你好") != -1 || message.indexOf("您好") != -1) {
            mReplyData.add(new VoiceReplyBean("您好，请问有什么能帮到您", 2));
        } else if (message.indexOf("政务要闻") != -1 || message.indexOf("要闻") != -1){
            intent.setClass(context, GovernmentActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/政务服务/政务要闻");
            intent.putExtra("type", "政务要闻");
            dismiss();
        }else if (message.indexOf("通知公告") != -1 || message.indexOf("公告") != -1){
            intent.setClass(context, GovernmentActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/政务服务/通知公告");
            intent.putExtra("type", "通知公告");
            dismiss();
        }else if (message.indexOf("政策法规") != -1 || message.indexOf("政策") != -1){
            intent.setClass(context, GovernmentActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/政务服务/政策法规");
            intent.putExtra("type", "政策法规");
            dismiss();
        }else if (message.indexOf("政务文件") != -1){
            intent.setClass(context, GovernmentActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/政务服务/政务文件");
            intent.putExtra("type", "政务文件");
            dismiss();
        } else if (message.indexOf("政务文件") != -1){
            intent.setClass(context, GovernmentActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/政务服务/政务文件");
            intent.putExtra("type", "政务文件");
            dismiss();
        }else if (message.indexOf("便民服务") != -1){
            intent.setClass(context, WebActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/便民服务/便民服务");
            intent.putExtra("url", "file:////android_asset/services.html");
            dismiss();
        }else if (message.indexOf("疫情信息") != -1||message.indexOf("疫情")!=-1){
            intent.setClass(context, WebActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/便民服务/疫情信息");
            intent.putExtra("url", "https://alihealth.taobao.com/medicalhealth/influenzamap");
            dismiss();
        }else if (message.indexOf("社区热线") != -1||message.indexOf("热线")!=-1){
            intent.setClass(context, HotLineActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/便民服务/社区热线");
            intent.putExtra("type", "community");
            dismiss();
        }else if (message.indexOf("区直电话") != -1||message.indexOf("电话")!=-1){
            intent.setClass(context, HotLineActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/便民服务/区直电话");
            intent.putExtra("type", "department");
            dismiss();
        }else if (message.indexOf("街道简介") != -1||message.indexOf("简介")!=-1){
            intent.setClass(context, StreetIntroActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/宣传平台/街道简介");
            intent.putExtra("type", "街道简介");
            dismiss();
        }else if (message.indexOf("走进我们") != -1){
            intent.setClass(context, InOursActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/宣传平台/走进我们");
            dismiss();
        }else if (message.indexOf("新闻中心") != -1||message.indexOf("新闻")!=-1){
            intent.setClass(context, PublicNewsActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/宣传平台/走进我们");
            intent.putExtra("type", 1);
            dismiss();
        }else if (message.indexOf("政务动态") != -1||message.indexOf("动态")!=-1){
            intent.setClass(context, PublicNewsActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/宣传平台/政务动态");
            intent.putExtra("type", 2);
            dismiss();
        } else if (message.indexOf("党史今天") != -1||message.indexOf("党史")!=-1){
            intent.setClass(context, HistoryTodayActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/宣传平台/党史今天");
            dismiss();
        }else if (message.indexOf("问卷调查") != -1||message.indexOf("问卷")!=-1){
            intent.setClass(context, QuestionActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/公众参与/问卷调查");
            intent.putExtra("type", "问卷调查");
            dismiss();
        }else if (message.indexOf("投票管理") != -1||message.indexOf("投票")!=-1){
            intent.setClass(context, VoteActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/公众参与/投票管理");
            intent.putExtra("type", "投票管理");
            dismiss();
        }else if (message.indexOf("留言建议") != -1||message.indexOf("建议")!=-1){
            intent.setClass(context, FeedBackActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/公众参与/投票管理");
            dismiss();
        }else if (message.indexOf("区长信箱") != -1||message.indexOf("信箱")!=-1){
            intent.setClass(context, LetterBoxActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/公众参与/区长信箱");
            dismiss();
        } else if (message.indexOf("街道实景") != -1||message.indexOf("实景")!=-1){
            intent.setClass(context, StreetSceneryActivity.class);
            dismiss();
        }else if (message.indexOf("建筑物信息") != -1||message.indexOf("建筑物")!=-1){
            intent.setClass(context, WebActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/GIS地图/建筑物信息");
            intent.putExtra("url", NetConstants.BASE_URL+"zhjd/earthstreet.html");
            dismiss();
        }else if (message.indexOf("公开目录") != -1||message.indexOf("目录")!=-1){
            intent.setClass(context, OpenDirectoryActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/政府信息公开/公开目录");
            intent.putExtra("type", "公开目录");
            dismiss();
        }else if (message.indexOf("公开指南") != -1||message.indexOf("指南")!=-1){
            intent.setClass(context, StreetIntroActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/政府信息公开/公开指南");
            intent.putExtra("type", "公开指南");
            dismiss();
        }else if (message.indexOf("依申请公开") != -1){
            intent.setClass(context, StreetIntroActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/政府信息公开/依申请公开");
            intent.putExtra("type", "依申请公开");
            dismiss();
        }else if (message.indexOf("规范性文件") != -1){
            intent.setClass(context, NormativeActivity.class);
            intent.putExtra("path","英协路智慧街道云平台/政府信息公开/规范性文件");
            dismiss();
        }
        else {
            mReplyData.add(new VoiceReplyBean("我不太明白您的意思", 2));
        }
        context.startActivity(intent);
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
                            intoOnline();
                        }
                        break;
                }
            }
        });
    }

    /**
     * 去天气界面
     */
    private void intoWeather() {
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.putExtra("path", "英协路智慧街道云平台/天气详情");
        context.startActivity(intent);
        dismiss();
    }

    /**
     * 去定位界面
     */
    private void intoLocation() {
//        Intent intent = new Intent(context, StreetSceneryActivity.class);
        Intent intent = new Intent(context, NearActivity.class);
        intent.putExtra("path", "英协路智慧街道云平台/位置信息");
        context.startActivity(intent);
        dismiss();
    }

    /**
     * 去办事界面
     */
    private void intoOnline() {
        Intent intent = new Intent(context, ChiefPublicActivity.class);
        intent.putExtra("path", "英协路智慧街道云平台/政务服务/在线办事");
        context.startActivity(intent);
        dismiss();
    }
}
