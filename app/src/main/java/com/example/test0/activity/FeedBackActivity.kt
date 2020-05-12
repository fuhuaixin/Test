package com.example.test0.activity

import android.text.Editable
import android.text.TextWatcher
import com.example.test0.R
import com.example.test0.base.BaseActivity
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_feedback.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 留言建议
 */
class FeedBackActivity : BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_feedback
    }

    override fun initView() {
        tvPath.text=intent.getStringExtra("path")
        tv_commit.isClickable =false
        et_message.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                tv_length.text = s.toString().length.toString() + "/400"
                if (s.toString().isNotEmpty()) {
                    tv_commit.isClickable = true
                    tv_commit.setBackgroundResource(R.drawable.shape_commit_sel)
                } else {
                    tv_commit.isClickable = false
                    tv_commit.setBackgroundResource(R.drawable.shape_commit_unsel)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    override fun initListener() {
        tv_commit.setOnClickListener {
            if (et_message.text.toString().isNotEmpty()){
                ToastUtils.show("提交成功")
                finish()
            } else{
                ToastUtils.show("请输入留言意见")
            }

        }
        llBack.setOnClickListener {
            finish();
        }
    }
}