package com.example.test0.utlis

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class NoScrollerLinearLayoutManager(context: Context?) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean {
        return false
    }
}